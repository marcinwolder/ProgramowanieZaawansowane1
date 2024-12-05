package adminUnits;

import CSVReader.CSVReader;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.Collator;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitList {
    public List<AdminUnit> units = new ArrayList<>();
    Map<Long, AdminUnit> idToAdminUnitMap = new HashMap<Long, AdminUnit>();

    /**
     * Czyta rekordy pliku i dodaje do listy
     * @param filename nazwa pliku
     */
    public void read(String filename) {
        try {
            CSVReader reader = new CSVReader(filename);
            while(reader.next()) {
                String name = reader.get("name");
                Integer adminLevel = reader.getInt("admin_level");
                Double population = reader.getDouble("population");
                Double area = reader.getDouble("area");
                Double density = reader.getDouble("density");

                if (area == null) {
                    continue;
                }

                AdminUnit au = new AdminUnit(name, adminLevel, population, area, density);

                Double x1 = reader.getDouble("x1");
                Double x2 = reader.getDouble("x2");
                Double x3 = reader.getDouble("x3");
                Double x4 = reader.getDouble("x4");
                Double x5 = reader.getDouble("x5");
                Double y1 = reader.getDouble("y1");
                Double y2 = reader.getDouble("y2");
                Double y3 = reader.getDouble("y3");
                Double y4 = reader.getDouble("y4");
                Double y5 = reader.getDouble("y5");

                if (x1 != null) {
                    au.bbox.addPoint(x1, y1);
                    au.bbox.addPoint(x2, y2);
                    au.bbox.addPoint(x3, y3);
                    au.bbox.addPoint(x4, y4);
                    au.bbox.addPoint(x5, y5);
                }

                au.parentId = reader.getLong("parent");
                Long id = reader.getLong("id");
                this.idToAdminUnitMap.put(id, au);
                this.units.add(au);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (AdminUnit u : this.units) {
            if (idToAdminUnitMap.containsKey(u.parentId)) {
                u.parent = idToAdminUnitMap.get(u.parentId);
                u.parent.children.add(u);
            }
        }
        for (AdminUnit u : this.units) {
            u.fixMissingValues();
        }
    }

    /**
     * Wypisuje zawartoœæ korzystaj¹c z adminUnits.AdminUnit.toString()
     * @param out
     */
    public void list(PrintStream out){
        for (AdminUnit u : units) {
            out.println(u);
        }
    }

    /**
     * Wypisuje co najwy¿ej limit elementów pocz¹wszy od elementu o indeksie offset
     * @param out - strumieñ wyjœciowy
     * @param offset - od którego elementu rozpocz¹æ wypisywanie
     * @param limit - ile (maksymalnie) elementów wypisaæ
     */
    public void list(PrintStream out, int offset, int limit){
        for (int i = offset; i < limit+offset; i++) {
            out.println(this.units.get(i));
        }
    }

    public String getWKT() {
        if (units.isEmpty()) return "";
        StringBuilder sb = new StringBuilder();
        sb.append("MULTILINESTRING(");
        for (AdminUnit u : this.units) {
            sb.append(u.bbox.toString());
            sb.append(", ");
        }
        sb.delete(sb.length()-2, sb.length());
        sb.append(")");
        return sb.toString();
    }

    /**
     * Zwraca now¹ listê zawieraj¹c¹ te obiekty adminUnits.AdminUnit, których nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - jeœli regex=true, u¿yj funkcji String matches(); jeœli false u¿yj funkcji contains()
     * @return podzbiór elementów, których nazwy spe³niaj¹ kryterium wyboru
     */
    public AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit u : units) {
            if (regex) {
                if (u.name.matches(pattern)) {
                   ret.units.add(u);
                };
            } else {
                if (u.name.contains(pattern)) {
                   ret.units.add(u);
                }
            }
        }
        return ret;
    }

    /**
     * Zwraca listê jednostek s¹siaduj¹cych z jendostk¹ unit na tym samym poziomie hierarchii admin_level.
     * Czyli s¹siadami wojweództw s¹ województwa, powiatów - powiaty, gmin - gminy, miejscowoœci - inne miejscowoœci
     * @param unit - jednostka, której s¹siedzi maj¹ byæ wyznaczeni
     * @param maxdistance - parametr stosowany wy³¹cznie dla miejscowoœci, maksymalny promieñ odleg³oœci od œrodka unit,
     *                    w którym maj¹ sie znaleŸæ punkty œrodkowe BoundingBox s¹siadów
     * @return lista wype³niona s¹siadami
     */
    public AdminUnitList getNeighbors(AdminUnit unit, Double maxdistance){
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit u : units) {
            if (Objects.equals(unit.adminLevel, u.adminLevel) && unit.bbox.distanceTo(u.bbox) <= maxdistance) {
                if (!unit.bbox.equals(u.bbox) && unit.bbox.intersects(u.bbox)) {
                    ret.units.add(u);
                }
            }
        }

        return ret;
    }
    public AdminUnitList getNeighbors(AdminUnit unit) { return getNeighbors(unit, Double.MAX_VALUE); };

    public AdminUnitList getAllFromAdminLevel(AdminUnit unit){
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit u : units) {
            if (Objects.equals(unit.adminLevel, u.adminLevel)) {
                ret.units.add(u);
            }
        }

        return ret;
    }

    private class NameComparator implements Comparator<AdminUnit> {
        private final Collator collator;

        public NameComparator() {
            this.collator = Collator.getInstance(Locale.of("pl", "PL"));
        }

        public int compare(AdminUnit u1, AdminUnit u2) {
            return collator.compare(u1.name, u2.name);
        }
    }

    /**
     * Sortuje dan¹ listê jednostek (in place = w miejscu)
     * @return this
     */
    public AdminUnitList sortInplaceByName(){
        this.units.sort(new NameComparator());
        return this;
    }

    /**
     * Sortuje dan¹ listê jednostek (in place = w miejscu)
     * @return this
     */
    public AdminUnitList sortInplaceByArea(){
        this.units.sort(new Comparator<AdminUnit>() {
            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return Double.compare(o1.area, o2.area);
            }
        });
        return this;
    }

    /**
     * Sortuje dan¹ listê jednostek (in place = w miejscu)
     * @return this
     */
    public AdminUnitList sortInplaceByPopulation(){
        this.units.sort(((o1, o2) -> Double.compare(o1.population, o2.population)));
        return this;
    }

    public AdminUnitList sortInplace(Comparator<AdminUnit> cmp){
        this.units.sort(cmp);
        return this;
    }

    public AdminUnitList sort(Comparator<AdminUnit> cmp){
        AdminUnitList ret = new AdminUnitList();
        ret.units.addAll(this.units);
        ret.sortInplace(cmp);
        return ret;
    }

    /**
     *
     * @param pred referencja do interfejsu Predicate
     * @return now¹ listê, na której pozostawiono tylko te jednostki,
     * dla których metoda test() zwraca true
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).collect(Collectors.toList());
        return ret;
    }

    /**
     * Zwraca co najwy¿ej limit elementów spe³niaj¹cych pred
     * @param pred - predykat
     * @param limit - maksymalna liczba elementów
     * @return now¹ listê
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred, int limit){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).limit(limit).collect(Collectors.toList());
        return ret;
    }

    /**
     * Zwraca co najwy¿ej limit elementów spe³niaj¹cych pred pocz¹wszy od offset
     * Offest jest obliczany po przefiltrowaniu
     * @param pred - predykat
     * @param offset - od którego elementu
     * @param limit - maksymalna liczba elementów
     * @return now¹ listê
     */
    public AdminUnitList filter(Predicate<AdminUnit> pred, int offset, int limit){
        AdminUnitList ret = new AdminUnitList();
        ret.units = this.units.stream().filter(pred).skip(offset).limit(limit).collect(Collectors.toList());
        return ret;
    }
}

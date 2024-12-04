package adminUnits;

import CSVReader.CSVReader;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

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
    public AdminUnitList getNeighbors(AdminUnit unit) {return getNeighbors(unit, 15.0);};

    public AdminUnitList getAllFromAdminLevel(AdminUnit unit){
        AdminUnitList ret = new AdminUnitList();

        for (AdminUnit u : units) {
            if (Objects.equals(unit.adminLevel, u.adminLevel)) {
                ret.units.add(u);
            }
        }

        return ret;
    }
}

package adminUnits;

import CSVReader.CSVReader;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminUnitList {
    List<AdminUnit> units = new ArrayList<>();
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
                Double y1 = reader.getDouble("y1");
                Double y2 = reader.getDouble("y2");
                Double y3 = reader.getDouble("y3");
                Double y4 = reader.getDouble("y4");

                if (x1 != null) {
                    au.bbox = new BoundingBox(x1, y1, x2, y2, x3, y3, x4, y4);
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
     * Wypisuje zawarto�� korzystaj�c z adminUnits.AdminUnit.toString()
     * @param out
     */
    public void list(PrintStream out){
        for (AdminUnit u : units) {
//            if (u.population == null) {
                out.println(u);
//            }
        }
    }

    /**
     * Wypisuje co najwy�ej limit element�w pocz�wszy od elementu o indeksie offset
     * @param out - strumie� wyj�ciowy
     * @param offset - od kt�rego elementu rozpocz�� wypisywanie
     * @param limit - ile (maksymalnie) element�w wypisa�
     */
    public void list(PrintStream out, int offset, int limit){
        for (int i = offset; i < limit+offset; i++) {
            out.println(this.units.get(i));
        }
    }

    /**
     * Zwraca now� list� zawieraj�c� te obiekty adminUnits.AdminUnit, kt�rych nazwa pasuje do wzorca
     * @param pattern - wzorzec dla nazwy
     * @param regex - je�li regex=true, u�yj funkcji String matches(); je�li false u�yj funkcji contains()
     * @return podzbi�r element�w, kt�rych nazwy spe�niaj� kryterium wyboru
     */
    public AdminUnitList selectByName(String pattern, boolean regex){
        AdminUnitList ret = new AdminUnitList();
        for (AdminUnit u : units) {
            if (regex) {
//                matches()
                if (u.name.matches(pattern)) {
                   ret.units.add(u);
                };
            } else {
//                contains()
                if (u.name.contains(pattern)) {
                   ret.units.add(u);
                };
            }
        }
        return ret;
    }
}

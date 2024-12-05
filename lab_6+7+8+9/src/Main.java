import adminUnits.AdminUnit;
import adminUnits.AdminUnitList;
import adminUnits.AdminUnitQuery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) throws IOException {


//
//
//        miasto = miasto.filter(new Predicate<AdminUnit>() {
//            @Override
//            public boolean test(AdminUnit adminUnit) {
//                return adminUnit.name.contains("-");
//            }
//        });
//        System.out.println("========");
//        miasto.list(System.out);
//
//        System.out.println("========");
//        AdminUnitQuery query = new AdminUnitQuery()
//                .selectFrom(aul)
//                .where(a->a.area>1000)
//                .or(a->a.name.startsWith("Sz"))
//                .sort((a,b)->Double.compare(a.area,b.area));
//        query.execute().list(System.out);
//        AdminUnitList wieliczka = aul.selectByName("Wieliczka", false);
//        wieliczka.list(System.out);
    }
}
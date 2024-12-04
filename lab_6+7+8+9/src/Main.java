import adminUnits.AdminUnitList;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws IOException {
//        System.out.println("# TEST 1 - odczyt ró¿nych plików");
//        CSVReader.CSVReader reader;
//        for (String path : new String[]{"./csv/accelerator.csv", "./csv/with-header.csv"}) {
//            try {
//                reader = new CSVReader.CSVReader(path, ";", true);
//                System.out.println(reader.columnLabels);
//                while(reader.next()){
//                    for (int i = 0; i < reader.getRecordLength(); i++) {
//                        System.out.printf("%s ", reader.get(i));
//                    }
//                    System.out.print('\n');
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        System.out.println("\n# TEST 2 - ró¿ne typy danych + ró¿ne Ÿród³a + nieistniej¹ce kolumny");
//        String text = """
//            string,int,long,double,date,time
//            Hello world!,2147483647,2147483648,60.90,11/10/2024,12:05:14""";
//        reader = new CSVReader.CSVReader(new StringReader(text),",",true);
//        reader.next();
//        String col1 = reader.get("string");
//        Integer col2 = reader.getInt("int");
//        Long col3 = reader.getLong("long");
//        Double col4 = reader.getDouble("double");
//        LocalDate col5 = reader.getDate("date", "dd/MM/yyyy");
//        LocalTime col6 = reader.getTime("time", "HH:mm:ss");
//        System.out.printf("%s %d %d %ff %s %s\n", col1, col2, col3, col4, col5, col6);
//        Double colX = reader.getDouble("NIEISTNIEJ¥CA KOLUMNA");
//        System.out.println(colX);
//        Long colY = reader.getLong(999);
//        System.out.println(colY);
//
//        System.out.println("\n# TEST 3 - plik z brakuj¹cymi polami");
//        try {
//            reader = new CSVReader.CSVReader("./csv/missing-values.csv", ";", true);
//            System.out.println(reader.columnLabels);
//            while(reader.next()){
//                for (int i = 0; i < reader.columnLabels.size(); i++) {
//                    System.out.printf("%20s ", reader.get(i));
//                }
//                System.out.print('\n');
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        LAB 7
//        AdminUnitList aul = new AdminUnitList();
//        aul.read("./csv/admin-units.csv");
//        System.out.println("# TEST 1a - odczyt pliku admin-units.csv");
//        aul.list(System.out);
//        System.out.println("\n# TEST 1b - odczyt pliku admin-units.csv");
//        aul.list(System.out, 4 , 10);
//
//        System.out.println("\n# TEST 2 - select nazwa ze s³owem 'Bia³a'");
//        AdminUnitList biala = aul.selectByName("Bia³a", false);
//        biala.list(System.out);
//
//        System.out.println("\n# TEST 3 - select nazwa zaczynaj¹cych siê s³owem 'Bia³a'");
//        AdminUnitList biala2 = aul.selectByName("^Bia³a.*", true);
//        biala2.list(System.out);
//        LAB 8
        AdminUnitList aul = new AdminUnitList();
        aul.read("./csv/admin-units.csv");
        AdminUnitList miasto = aul.selectByName("Prudnik", true);
        AdminUnitList wszyscy = aul.getAllFromAdminLevel(miasto.units.getFirst());
        // wywo³anie funkcji
        double t1 = System.nanoTime()/1e6;
        AdminUnitList sasiedzi = aul.getNeighbors(miasto.units.getFirst(), 400.0);
        double t2 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"t2-t1=%f\n",t2-t1);
        BufferedWriter writer = new BufferedWriter(new FileWriter("wszyscy.txt"));
        writer.write(wszyscy.getWKT());
        writer.close();
        System.out.print("Miasto: ");
        System.out.println(miasto.getWKT());
        System.out.print("S¹siedzi: ");
        System.out.println(sasiedzi.getWKT());
//        AdminUnitList wieliczka = aul.selectByName("Wieliczka", false);
//        wieliczka.list(System.out);
    }
}
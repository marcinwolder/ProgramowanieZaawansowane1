import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) {
        System.out.println("# TEST 1 - odczyt ró¿nych plików");
        CSVReader reader;
        for (String path : new String[]{"./csv/accelerator.csv", "./csv/with-header.csv"}) {
            try {
                reader = new CSVReader(path, ";", true);
                System.out.println(reader.columnLabels);
                while(reader.next()){
                    for (int i = 0; i < reader.getRecordLength(); i++) {
                        System.out.printf("%s ", reader.get(i));
                    }
                    System.out.print('\n');
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\n# TEST 2 - ró¿ne typy danych + ró¿ne Ÿród³a + nieistniej¹ce kolumny");
        String text = """
            string,int,long,double,date,time
            Hello world!,2147483647,2147483648,60.90,11/10/2024,12:05:14""";
        reader = new CSVReader(new StringReader(text),",",true);
        reader.next();
        String col1 = reader.get("string");
        Integer col2 = reader.getInt("int");
        Long col3 = reader.getLong("long");
        Double col4 = reader.getDouble("double");
        LocalDate col5 = reader.getDate("date", "dd/MM/yyyy");
        LocalTime col6 = reader.getTime("time", "HH:mm:ss");
        System.out.printf("%s %d %d %ff %s %s\n", col1, col2, col3, col4, col5, col6);
        Double colX = reader.getDouble("NIEISTNIEJ¥CA KOLUMNA");
        System.out.println(colX);
        Long colY = reader.getLong(999);
        System.out.println(colY);

        System.out.println("\n# TEST 3 - plik z brakuj¹cymi polami");
        try {
            reader = new CSVReader("./csv/missing-values.csv", ";", true);
            System.out.println(reader.columnLabels);
            while(reader.next()){
                for (int i = 0; i < reader.columnLabels.size(); i++) {
                    System.out.printf("%20s ", reader.get(i));
                }
                System.out.print('\n');
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
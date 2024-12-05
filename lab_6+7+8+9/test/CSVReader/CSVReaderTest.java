package CSVReader;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.LocalTime;

class CSVReaderTest {
    @org.junit.jupiter.api.Test
    void readMultipleFiles() {
        System.out.println("# TEST 1 - odczyt ró¿nych plików");
        CSVReader reader;
        for (String path : new String[]{"./csv/accelerator.csv", "./csv/with-header.csv"}) {
            try {
                reader = new CSVReader(path, ";", true);
                System.out.println(reader.getColumnLabels());
                while(reader.next()){
                    for (int i = 0; i < reader.getRecordLength(); i++) {
                        System.out.printf("%s ", reader.get(i));
                    }
                    System.out.print('\n');
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @org.junit.jupiter.api.Test
    void readMultipleTypes() {
        System.out.println("# TEST 2 - ró¿ne typy danych + ró¿ne Ÿród³a + nieistniej¹ce kolumny");
        CSVReader reader;
        String text = """
            string,int,long,double,date,time
            Hello world!,2147483647,2147483648,60.90,11/10/2024,12:05:14""";
        reader = new CSVReader(new StringReader(text),",",true);
        reader.next();
        String col1 = reader.get("string");
        assert col1 != null;
        Integer col2 = reader.getInt("int");
        assert col2 != null;
        Long col3 = reader.getLong("long");
        assert col3 != null;
        Double col4 = reader.getDouble("double");
        assert col4 != null;
        LocalDate col5 = reader.getDate("date", "dd/MM/yyyy");
        assert col5 != null;
        LocalTime col6 = reader.getTime("time", "HH:mm:ss");
        assert col6 != null;
        System.out.printf("%s %d %d %ff %s %s\n", col1, col2, col3, col4, col5, col6);

        Double colX = reader.getDouble("NIEISTNIEJ¥CA KOLUMNA");
        assert colX == null;
        Long colY = reader.getLong(999);
        assert colY == null;
    }

    @org.junit.jupiter.api.Test
    void handleMissingFields() {
        System.out.println("\n# TEST 3 - plik z brakuj¹cymi polami");
        CSVReader reader;
        int cnt = 0;
        try {
            reader = new CSVReader("./csv/missing-values.csv", ";", true);
            System.out.println(reader.columnLabels);
            while(reader.next()){
                for (int i = 0; i < reader.columnLabels.size(); i++) {
                    if (reader.get(i) == null) cnt++;
                    System.out.printf("%20s ", reader.get(i));
                }
                System.out.print('\n');
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assert cnt == 21;
    }
}
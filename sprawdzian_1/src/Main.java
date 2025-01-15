import CSVReader.CSVReader;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            CSVReader reader = new CSVReader("csv/products.csv", ";");
            while (reader.next()) {
                String productName = reader.get("product_name");
                String department = reader.get("department");
                String aisle = reader.get("aisle");

                System.out.printf("productName: %s, department: %s, aisle: %s\n", productName, department, aisle);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
package adminUnits;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class AdminUnitListTest {

    @Test
    void lab7() {
        System.out.println("TEST: LAB 7");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        AdminUnitList aul = new AdminUnitList();
        aul.read("./csv/admin-units.csv");

        System.out.println("# TEST 1a - odczyt pliku admin-units.csv");
        aul.list(ps);
        long cnt = baos.toString().lines().count();
        assert cnt == 15258;
        baos.reset();
        System.out.printf("poprawna iloœæ krotek: %d%n", cnt);

        System.out.println("\n# TEST 1b - odczyt pliku admin-units.csv z offsetem i limitem");
        aul.list(ps, 4 , 10);
        cnt = baos.toString().lines().count();
        assert cnt == 10;
        System.out.printf("poprawna iloœæ krotek: %d%n", cnt);
        String record = baos.toString().lines().limit(1).toList().getFirst();
        assert record.equals("(name=Bêdkowice, admin_level=8, area=5.99101, population=1359.0, density=226.767, parent_name=gmina Wielka Wieœ, children_names=[Kawiory], bbox=(19.721401 50.185283, 19.773614 50.185283, 19.773614 50.157705, 19.721401 50.157705,19.721401 50.185283))");
        System.out.printf("pierwsza krotka poprawna: %s%n", record);
        baos.reset();

        System.out.println("\n# TEST 2a - select by name - bez regex");
        AdminUnitList biala = aul.selectByName("Bia³a", false);
        biala.list(ps);
        cnt = baos.toString().lines().count();
        assert cnt == 30;
        System.out.printf("poprawna iloœæ krotek: %d%n", cnt);
        baos.reset();

        System.out.println("\n# TEST 2b - select by name - regex");
        AdminUnitList biala2 = aul.selectByName("^Bia³a.*", true);
        biala2.list(ps);
        cnt = baos.toString().lines().count();
        assert cnt == 20;
        System.out.printf("poprawna iloœæ krotek: %d%n", cnt);
        baos.reset();
    }

    @Test
    void lab8() {
        System.out.println("\n");
        System.out.println("TEST: LAB 8");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        AdminUnitList aul = new AdminUnitList();
        aul.read("./csv/admin-units.csv");

        AdminUnitList unit = aul.selectByName("województwo opolskie", false);
        unit.list(System.out);

        System.out.println("\n# TEST 1a - znajdowanie jednostek tego samego poziomu");
        AdminUnitList allUnits = aul.getAllFromAdminLevel(unit.units.getFirst());
        assert allUnits.units.size() == 16;
        System.out.println("poprawna iloœæ jednostek tego samego poziomu: 16");

        System.out.println("\n# TEST 1b - znajdowanie jednostek s¹siaduj¹cych");
        double t1 = System.nanoTime();
        unit = aul.selectByName("Pi¹tnica Poduchowna", false);
        unit.list(System.out);
        allUnits = aul.getAllFromAdminLevel(unit.units.getFirst());
        AdminUnitList neighbors = aul.getNeighbors(unit.units.getFirst());
        double t2 = System.nanoTime();
        assert neighbors.units.size() == 2;
        System.out.printf("poprawna iloœæ s¹siadów: %d%n%n", neighbors.units.size());
        System.out.printf(Locale.US,"czas wyszukiwania = t2-t1 = %f sek.\n", TimeUnit.MILLISECONDS.convert((long) (t2-t1), TimeUnit.NANOSECONDS)/1000.0);

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("wszyscy.txt"));
            writer.write(allUnits.getWKT());
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("\nJednostka: ");
        System.out.println(unit.getWKT());
        System.out.print("S¹siedzi: ");
        System.out.println(neighbors.getWKT());

//        https://wktmap.com/
    }
}
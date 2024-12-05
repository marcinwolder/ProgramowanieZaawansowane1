package adminUnits;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class AdminUnitQueryTest {
    AdminUnitQuery query;
    AdminUnitList list;

    @BeforeEach
    void setUpQuery() {
        list = new AdminUnitList();
        list.read("./csv/admin-units.csv");
        query = new AdminUnitQuery();
    }

    @Test
    void selectFrom() {
        query.selectFrom(list);
        assert query.src.equals(list);
    }

    @Test
    void where() {
        Predicate<AdminUnit> pred = (a)->a.name.equals("województwo opolskie");
        query.selectFrom(list).where(pred);
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert query.p.equals(pred);
        assert filtered.units.size() == 1;
    }

    @Test
    void and() {
        Predicate<AdminUnit> pred1 = (a)->a.name.contains("województwo");
        Predicate<AdminUnit> pred2 = (a)->a.area < 10_000;
        query.selectFrom(list).where(pred1).and(pred2);
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert filtered.units.size() == 1;
    }

    @Test
    void or() {
        Predicate<AdminUnit> pred1 = (a)->a.name.contains("województwo");
        Predicate<AdminUnit> pred2 = (a)->a.name.equals("Kraków");
        query.selectFrom(list).where(pred1).or(pred2);
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert filtered.units.size() == 17;
    }

    @Test
    void limit() {
        query.selectFrom(list).limit(1);
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert filtered.units.getFirst().name.equals("Bêb³o");
    }

    @Test
    void offset() {
        query.selectFrom(list).limit(1).offset(4);
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert filtered.units.getFirst().name.equals("Bêdkowice");
    }

    @Test
    void sort() {
        query.selectFrom(list).limit(1).sort();
        AdminUnitList filtered = query.execute();
        filtered.list(System.out);
        assert filtered.units.getFirst().name.equals("Abisynia");
    }

    @Test
    void customTest1() {
        query.selectFrom(list)
                .where(a->a.area>1000)
                .or(a->a.name.startsWith("Sz"))
                .sort((a,b)->Double.compare(a.area,b.area))
                .limit(100);
        query.execute().list(System.out);
    }
    @Test
    void customTest2() {
        query.selectFrom(list)
                .where(a->!a.children.isEmpty())
                .or(a->a.name.equals("Prudnik"))
                .sort();
        query.execute().list(System.out);
    }
    @Test
    void customTest3() {
        AdminUnit unit = list.filter(a->a.name.equals("Opole")).units.getFirst();
        query.selectFrom(list)
                .where(a->a.bbox.intersects(unit.bbox))
                .sort();
        query.execute().list(System.out);
    }
    @Test
    void customTest4() {
        query.selectFrom(list)
                .where(a->a.name.startsWith("powiat"))
                .and(a->a.parent.name.equals("województwo ma³opolskie"))
                .sort((o1,o2)->-Double.compare(o1.population,o2.population));
        query.execute().list(System.out);
    }
}

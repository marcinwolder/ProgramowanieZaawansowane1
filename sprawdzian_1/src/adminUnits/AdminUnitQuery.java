package adminUnits;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AdminUnitQuery {
    AdminUnitList src;
    Predicate<AdminUnit> p = a->true;
    Comparator<AdminUnit> cmp;
    int limit = Integer.MAX_VALUE;
    int offset = 0;

    /**
     * Ustawia listê jako przetwarzane Ÿród³o
     * @param src
     * @return this
     */
    public AdminUnitQuery selectFrom(AdminUnitList src){
        this.src = src;
        return this;
    }

    /**
     *
     * @param pred - ustawia predykat p
     * @return this
     */
    public AdminUnitQuery where(Predicate<AdminUnit> pred){
        this.p = pred;
        return this;
    }

    /**
     * Wykonuje operacjê p = p and pred
     * @param pred
     * @return this
     */
    public AdminUnitQuery and(Predicate<AdminUnit> pred){
        this.p = this.p.and(pred);
        return this;
    }
    /**
     * Wykonuje operacjê p = p or pred
     * @param pred
     * @return this
     */
    public AdminUnitQuery or(Predicate<AdminUnit> pred){
        this.p = this.p.or(pred);
        return this;
    }

    /**
     * Ustawia komparator cmp
     * @param cmp
     * @return this
     */
    public AdminUnitQuery sort(Comparator<AdminUnit> cmp){
        this.cmp = cmp;
        return this;
    }
    /**
     * Ustawia komparator cmp na standardowy komparator dla jêzyka polskiego
     * @return this
     */
    public AdminUnitQuery sort(){
        this.cmp = new Comparator<>() {
            private final Collator collator = Collator.getInstance(Locale.of("pl", "PL"));

            @Override
            public int compare(AdminUnit o1, AdminUnit o2) {
                return collator.compare(o1.name, o2.name);
            }
        };
        return this;
    }

    /**
     * Ustawia limit
     * @param limit
     * @return this
     */
    public AdminUnitQuery limit(int limit){
        this.limit = limit;
        return this;
    }
    /**
     * Ustawia offset
     * @param offset
     * @return this
     */
    public AdminUnitQuery offset(int offset){
        this.offset = offset;
        return this;
    }

    /**
     * Wykonuje zapytanie i zwraca wynikow¹ listê
     * @return przefiltrowana i opcjonalnie posortowana lista (uwzglêdniamy tak¿e offset/limit)
     */
    public AdminUnitList execute(){
        this.src.units = this.src.units.stream()
                .filter(this.p)
                .collect(Collectors.toList());
        if (this.cmp != null) this.src.units = this.src.units.stream()
                .sorted(this.cmp)
                .collect(Collectors.toList());
        this.src.units = this.src.units.stream()
                .skip(this.offset)
                .limit(this.limit)
                .collect(Collectors.toList());
        return this.src;
    }
}

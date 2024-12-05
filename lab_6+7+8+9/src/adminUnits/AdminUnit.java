package adminUnits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminUnit {
    public String name;
    public Integer adminLevel;
    public Double population;
    public Double area;
    public Double density;
    protected Long parentId;
    public AdminUnit parent;
    public List<AdminUnit> children = new ArrayList<AdminUnit>();
    public BoundingBox bbox = new BoundingBox();

    public AdminUnit(String name, Integer adminLevel, Double population, Double area, Double density) {
        this.name = name;
        this.adminLevel = adminLevel;
        this.population = population;
        this.area = area;
        this.density = density;
    }

    public String toString() {
        String[] children_names = new String[children.size()];
        for (int i = 0; i < children.size(); i++) {
            children_names[i] = children.get(i).name;
        }
        return "(name=" + name + ", admin_level=" + adminLevel + ", area=" + area + ", population=" + population + ", density=" + density + ", parent_name=" + (parent == null ? null : parent.name) + ", children_names=" + Arrays.toString(children_names) + ", bbox=" + bbox.toString() + ")";
    }

    void fixMissingValues() {
        if (this.parent != null) {
            this.parent.fixMissingValues();
            if (this.density == null) {
                this.density = this.parent.density;
            }
            if (this.population == null && this.area != null && this.density != null) {
                this.population = (double) Math.round(this.area*this.density);
            }
        }
    }
}

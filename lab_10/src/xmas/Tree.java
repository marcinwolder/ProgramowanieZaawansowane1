package xmas;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tree implements XmasShape{
    int x;
    int y;
    double scale;
    int levels;
    List<XmasShape> shapes = new ArrayList<>();

    public Tree(int x, int y, int levels, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.levels = levels;

        shapes.add(new Log(0, (this.levels*2-1)*30, 40, 70));
        for (int i = 0; i < this.levels; i++) {
            shapes.add(new Branch(0, i*30, 1+i*0.2));
        }

    }
    public Tree(int x, int y, int levels) {
        this(x, y, levels, 1);
    }
    public Tree(int x, int y) {
        this(x, y, 5, 1);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        for(XmasShape b : shapes)
            b.draw(g2d);
    }
}

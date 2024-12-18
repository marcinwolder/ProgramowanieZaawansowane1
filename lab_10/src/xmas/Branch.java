package xmas;

import java.awt.*;

public class Branch implements XmasShape{
    int x;
    int y;
    double scale;
    double brightness;

    public Branch(int x, int y, double scale) {
        this.x = x;
        this.y = y;
        this.scale = scale;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
        g2d.rotate(Math.toRadians(45));
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(73, 126, 56));
        g2d.fillPolygon(new int[]{0, 100, 0}, new int[]{0, 0, 100}, 3);
    }
}

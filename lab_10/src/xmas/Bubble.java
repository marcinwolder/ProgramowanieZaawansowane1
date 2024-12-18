package xmas;

import java.awt.*;

public class Bubble implements XmasShape {
    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    public Bubble(int x, int y) {
        this(x, y, 1.0, new Color(255, 255, 255), new Color(255, 255, 255));
    }
    public Bubble(int x, int y, double scale) {
        this(x, y, scale, new Color(255, 255, 255), new Color(255, 255, 255));
    }
    public Bubble(int x, int y, double scale, Color lineColor, Color fillColor) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.lineColor = lineColor;
        this.fillColor = fillColor;
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(this.fillColor);
        g2d.fillOval(0,0,100,100);
        g2d.setColor(this.lineColor);
        g2d.drawOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x, y);
        g2d.scale(scale, scale);
    }
}
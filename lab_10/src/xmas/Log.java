package xmas;

import java.awt.*;

public class Log implements XmasShape{
    int x;
    int y;
    int w;
    int h;

    public Log(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-(w/2), y-(h/2));
    }

    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(new Color(106, 60, 37));
        g2d.fillRect(0, 0, w, h);
    }
}

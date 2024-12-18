package xmas;

import java.awt.*;
import java.util.Random;

public class Light implements XmasShape{
    int x;
    int y;
    double radius;

    public Light(int x, int y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x-radius, y-radius);
    }

    @Override
    public void render(Graphics2D g2d) {
        Random rand = new Random();
        int color_type = rand.nextInt(0, 4);
        switch (color_type) {
            case 0:
                g2d.setColor(Color.red);
                break;
            case 1:
                g2d.setColor(Color.blue);
                break;
            case 2:
                g2d.setColor(Color.green);
                break;
            case 3:
                g2d.setColor(Color.yellow);
                break;
        }
        g2d.fillOval(0, 0, (int) radius*2, (int) radius*2);
    }
}

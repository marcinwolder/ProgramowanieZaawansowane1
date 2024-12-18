import xmas.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Choinka");
        DrawPanel panel = new DrawPanel();

        panel.add(new Tree(500, 200));

        panel.add(new Light(540, 280, 5));
        panel.add(new Light(520, 290, 5));
        panel.add(new Light(500, 300, 5));
        panel.add(new Light(480, 310, 5));
        panel.add(new Light(460, 320, 5));
        panel.add(new Light(440, 330, 5));

        panel.add(new Light(560, 360, 5));
        panel.add(new Light(540, 370, 5));
        panel.add(new Light(520, 380, 5));
        panel.add(new Light(500, 390, 5));
        panel.add(new Light(480, 400, 5));
        panel.add(new Light(460, 410, 5));
        panel.add(new Light(440, 420, 5));
        panel.add(new Light(420, 430, 5));
        panel.add(new Light(400, 440, 5));

        panel.add(new Bubble(530, 300, 0.2, new Color(0, 0, 0), new Color(255, 255, 255)));
        panel.add(new Bubble(510, 400, 0.2, new Color(0, 0, 0), new Color(255, 255, 255)));
        panel.add(new Bubble(440, 350, 0.2, new Color(0, 0, 0), new Color(255, 255, 255)));
        panel.add(new Bubble(490, 220, 0.2, new Color(0, 0, 0), new Color(255, 255, 255)));
        panel.add(new Bubble(560, 420, 0.2, new Color(0, 0, 0), new Color(255, 185, 60)));
        panel.add(new Bubble(480, 270, 0.2, new Color(0, 0, 0), new Color(255, 185, 60)));
        panel.add(new Bubble(520, 340, 0.2, new Color(0, 0, 0), new Color(255, 185, 60)));

        frame.setContentPane(panel);
        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
package xmas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawPanel extends JPanel {
    List<XmasShape> shapes = new ArrayList<>();

    public DrawPanel() {
        setBackground(new Color(28, 29, 48));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(XmasShape s:shapes){
            s.draw((Graphics2D)g);
        }
    }

    public DrawPanel add(XmasShape shape) {
        this.shapes.add(shape);
        return this;
    }
    public DrawPanel add(XmasShape[] shapes) {
        this.shapes.addAll(List.of(shapes));
        return this;
    }
}
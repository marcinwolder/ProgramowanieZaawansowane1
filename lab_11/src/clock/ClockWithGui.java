package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.time.LocalTime;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class ClockWithGui extends JPanel {
    LocalTime time = LocalTime.now();
    int radius = 200;
    int centerX;
    int centerY;

    ClockWithGui() {
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(centerX, centerY);

        g2d.setColor(Color.WHITE);
        g2d.drawOval(-radius, -radius, 2 * radius, 2 * radius);
        int outerRadius = radius + 50;
        g2d.drawOval(-outerRadius, -outerRadius, 2 * outerRadius, 2 * outerRadius);

        String[] numbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII"};
        for (int i = 0; i < 12; i++) {
            double angle = 2 * Math.PI / 12 * (i + 1);
            int textRadius = radius + 25;
            int textX = (int) (textRadius * Math.sin(angle));
            int textY = (int) (textRadius * -Math.cos(angle));

            g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            String number = numbers[i];
            FontMetrics fm = g2d.getFontMetrics();
            int textWidth = fm.stringWidth(number);
            int textHeight = fm.getAscent();

            g2d.drawString(number, textX - textWidth / 2, textY + textHeight / 2);
        }

        for (int i = 0; i < 60; i++) {
            g2d.setTransform(originalTransform);
            g2d.translate(centerX, centerY);
            double angle = 2 * Math.PI / 60 * i;
            g2d.rotate(angle);

            int tickStart = radius - 10;
            int tickEnd = tickStart + (i % 5 == 0 ? 20 : 10);
            g2d.drawLine(0, -tickStart, 0, -tickEnd);
        }

        g2d.setTransform(originalTransform);
        g2d.translate(centerX, centerY);
        g2d.rotate((time.getHour() + (float)time.getMinute()/60) % 12 * 2 * Math.PI / 12);
        g2d.setStroke(new BasicStroke(10, CAP_ROUND, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -100);

        g2d.setTransform(originalTransform);
        g2d.translate(centerX, centerY);
        g2d.rotate((time.getMinute() + (float)time.getSecond()/60) * 2 * Math.PI / 60);
        g2d.setStroke(new BasicStroke(6, CAP_ROUND, JOIN_MITER));
        g2d.drawLine(0, 0, 0, -150);

        g2d.setTransform(originalTransform);
        g2d.translate(centerX, centerY);
        g2d.rotate((time.getSecond() + (float)time.getNano()/1000000000) * 2 * Math.PI / 60);
        g2d.setStroke(new BasicStroke(2, CAP_ROUND, JOIN_MITER));
        g2d.setColor(Color.RED);
        g2d.drawLine(0, 0, 0, -150);
    }

    class ClockThread extends Thread {
        @Override
        public void run() {
            while (true) {
                time = LocalTime.now();
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                repaint();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Zegar");
        ClockWithGui clockWithGui = new ClockWithGui();
        frame.setContentPane(clockWithGui);
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);

        ClockThread clockThread = clockWithGui.new ClockThread();
        clockThread.start();
    }
}

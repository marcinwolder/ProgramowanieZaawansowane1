package xmas;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;

public class TestDrawPanel extends JPanel {
    public TestDrawPanel() {
        setBackground(new Color(156, 156, 255));
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setFont(new Font("Helvetica", Font.BOLD, 18));

//        TEKST
        g.drawString("Hello World", 20, 20);

//        LINIA
        g.drawLine(10,10,100,100);

//        ELIPSA
        g.setColor(Color.yellow);
        g.fillOval(100,101,30,30);
        g.setColor(Color.black);
        g.drawOval(100,101,30,30);

//        WIELOBOK
        int[] x = {120, 220, 360, 240};
        int[] y = {320, 200, 330, 90};
        g.fillPolygon(x,y,x.length);

//        OBRAZ
        Image img = Toolkit.getDefaultToolkit().getImage("img/top_footballer.jpg");
        g.drawImage(img, 400, 0, img.getWidth(this)/8, img.getHeight(this)/8, this);

//        Graphics2D
        Graphics2D g2d= (Graphics2D)g;

// zachowaj macierz przekształcenia
        AffineTransform mat = g2d.getTransform();
// przesuń początek układu
        g2d.translate(100,100);
// zastosuj skalowanie
        g2d.scale(.2,.2);
// narysuj linie
        for(int i=0;i<12;i++){
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
//oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);

        g2d.translate(200,200);
// zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        Font font = new Font("Serif", Font.PLAIN, 96);
        g2d.setFont(font);
        for(int i=0;i<12;i++){
            g2d.drawString("Happy new year",150,0);
            g2d.rotate(2*Math.PI/12);
        }

        // zachowaj macierz przekształcenia
        mat = g2d.getTransform();
        // przesuń początek układu
        g2d.translate(200,200);
        // zastosuj skalowanie
        g2d.scale(.2,.2);
        g2d.setStroke(new BasicStroke(50, CAP_ROUND, JOIN_MITER));
        for(int i=0;i<12;i++){
            //g2d.drawString("Happy new year",150,0);
            g2d.drawLine(0,0,100,100);
            g2d.rotate(2*Math.PI/12);
        }
        //oddtwórz poprzednie ustawienia transformacji układu współrzędnych
        g2d.setTransform(mat);
        mat = g2d.getTransform();
        GradientPaint grad = new GradientPaint(0,0,new Color(47, 220, 51),0,100, new Color(0,10,0));
        g2d.setPaint(grad);
        g2d.translate(0,50);
        g2d.scale(0.7,0.5);
        int[] x2 = {286, 286, 223, 0};
        int[] y2 = {0, 131, 89, 108};
        g2d.fillPolygon(x2,y2,x2.length);
        g2d.translate(670,0);
        g2d.scale(-1,1);
        g2d.fillPolygon(x2,y2,x2.length);
        g2d.setTransform(mat);
    }
}
package adminUnits;

import java.io.StringWriter;
import java.util.*;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;

    /**
     * Powi�ksza BB tak, aby zawiera� punkt (x, y)
     * Je�eli by� wcze�niej pusty - w�wczas ma zawiera� wy��cznie ten punkt
     * @param x double - wsp�rz�dna x
     * @param y double - wsp�rz�dna y
     */
    void addPoint(double x, double y){
        if (isEmpty()) {
            xmin = x;
            xmax = x;
            ymin = y;
            ymax = y;
        } else {
            xmin = Math.min(xmin, x);
            xmax = Math.max(xmax, x);
            ymin = Math.min(ymin, y);
            ymax = Math.max(ymax, y);
        }
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x, y)
     * @param x double
     * @param y double
     * @return boolean
     */
    boolean contains(double x, double y){
        if (isEmpty()) return false;
        return (x>=xmin && x<=xmax && y>=ymin && y<=ymax);
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb BoundingBox
     * @return boolean
     */
    boolean contains(BoundingBox bb){
        if (isEmpty()) return false;
        return contains(bb.xmin, bb.ymin) && contains(bb.xmax, bb.ymax);
    }

    /**
     * Sprawdza, czy dany BB przecina si� z bb
     * @param bb BoundingBox
     * @return boolean
     */
    boolean intersects(BoundingBox bb){
        if (ymax < bb.ymin || ymin > bb.ymax) {
            return false;
        }
        if (xmax < bb.xmin || xmin > bb.xmax) {
            return false;
        }
        return true;
    }
    /**
     * Powi�ksza rozmiary tak, aby zawiera� bb oraz poprzedni� wersj� this
     * Je�eli by� pusty - po wykonaniu operacji ma by� r�wny bb
     * @param bb BoundingBox
     * @return BoundingBox
     */
    BoundingBox add(BoundingBox bb){
        if (isEmpty()) {
            xmin = bb.xmin;
            xmax = bb.xmax;
            ymin = bb.ymin;
            ymax = bb.ymax;
        }
        xmin = Math.min(xmin, bb.xmin);
        xmax = Math.max(xmax, bb.xmax);
        ymin = Math.min(ymin, bb.ymin);
        ymax = Math.max(ymax, bb.ymax);
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return boolean
     */
    boolean isEmpty(){
        return (xmin == null && ymin == null && xmax == null && ymax == null);
    }

    /**
     * Sprawdza czy
     * 1) typem o jest BoundingBox
     * 2) this jest r�wny bb
     * @return boolean
     */
    public boolean equals(Object o){
        if (o.getClass() == BoundingBox.class) {
            BoundingBox bb = (BoundingBox)o;
            return (Objects.equals(xmin, bb.xmin) && Objects.equals(xmax, bb.xmax)
                    && Objects.equals(ymin, bb.ymin) && Objects.equals(ymax, bb.ymax));
        }
        return false;
    }

    /**
     * Oblicza i zwraca wsp�rz�dn� x �rodka
     * @return Double if !isEmpty() wsp�rz�dna x �rodka else wyrzuca wyj�tek
     */
    Double getCenterX(){
        if (isEmpty()) throw new RuntimeException("Not implemented");
        return xmin + (xmax - xmin) / 2;
    }
    /**
     * Oblicza i zwraca wsp�rz�dn� y �rodka
     * @return Double if !isEmpty() wsp�rz�dna y �rodka else wyrzuca wyj�tek
     * (sam dobierz typ)
     */
    Double getCenterY(){
        if (isEmpty()) throw new RuntimeException("Not implemented");
        return ymin + (ymax - ymin) / 2;
    }

    /**
     * Oblicza odleg�o�� pomi�dzy �rodkami this bounding box oraz bbx
     * @param bbx prostok�t, do kt�rego liczona jest odleg�o��
     * @return Double if !isEmpty odleg�o��, else wyrzuca wyj�tek lub zwraca maksymaln� mo�liw� warto�� double
     * Ze wzgl�du na to, �e s� to wsp�rz�dne geograficzne, zamiast odleg�o�ci u�yj wzoru haversine
     * (ang. haversine formula)
     *
     * Gotowy kod mo�na znale�� w Internecie...
     */
    Double distanceTo(BoundingBox bbx){
        if (isEmpty()) throw new RuntimeException("Not implemented");
        double R = 6372.8;

        Double lat1 = this.getCenterY();
        Double lon1 = this.getCenterX();
        Double lat2 = bbx.getCenterY();
        Double lon2 = bbx.getCenterX();


        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        double dLat = lat2 - lat1;
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public String toString() {
        return String.format(Locale.US, "(%f %f, %f %f, %f %f, %f %f,%f %f)",
                xmin, ymax, xmax, ymax, xmax, ymin, xmin, ymin, xmin, ymax);
    }
}

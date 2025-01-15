package adminUnits;

import java.io.StringWriter;
import java.util.*;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;

    /**
     * Powiêksza BB tak, aby zawiera³ punkt (x, y)
     * Je¿eli by³ wczeœniej pusty - wówczas ma zawieraæ wy³¹cznie ten punkt
     * @param x double - wspó³rzêdna x
     * @param y double - wspó³rzêdna y
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
     * Sprawdza, czy dany BB przecina siê z bb
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
     * Powiêksza rozmiary tak, aby zawiera³ bb oraz poprzedni¹ wersjê this
     * Je¿eli by³ pusty - po wykonaniu operacji ma byæ równy bb
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
     * 2) this jest równy bb
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
     * Oblicza i zwraca wspó³rzêdn¹ x œrodka
     * @return Double if !isEmpty() wspó³rzêdna x œrodka else wyrzuca wyj¹tek
     */
    Double getCenterX(){
        if (isEmpty()) throw new RuntimeException("Not implemented");
        return xmin + (xmax - xmin) / 2;
    }
    /**
     * Oblicza i zwraca wspó³rzêdn¹ y œrodka
     * @return Double if !isEmpty() wspó³rzêdna y œrodka else wyrzuca wyj¹tek
     * (sam dobierz typ)
     */
    Double getCenterY(){
        if (isEmpty()) throw new RuntimeException("Not implemented");
        return ymin + (ymax - ymin) / 2;
    }

    /**
     * Oblicza odleg³oœæ pomiêdzy œrodkami this bounding box oraz bbx
     * @param bbx prostok¹t, do którego liczona jest odleg³oœæ
     * @return Double if !isEmpty odleg³oœæ, else wyrzuca wyj¹tek lub zwraca maksymaln¹ mo¿liw¹ wartoœæ double
     * Ze wzglêdu na to, ¿e s¹ to wspó³rzêdne geograficzne, zamiast odleg³oœci u¿yj wzoru haversine
     * (ang. haversine formula)
     *
     * Gotowy kod mo¿na znaleŸæ w Internecie...
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

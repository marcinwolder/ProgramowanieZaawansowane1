package adminUnits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;

    BoundingBox(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4)  {
        double[] xArr = new double[]{x1, x2, x3, x4};
        xmin = Arrays.stream(xArr).min().getAsDouble();
        xmax = Arrays.stream(xArr).max().getAsDouble();
        double[] yArr = new double[]{y1, y2, y3, y4};
        ymin = Arrays.stream(yArr).min().getAsDouble();
        ymax = Arrays.stream(yArr).max().getAsDouble();
    }
}

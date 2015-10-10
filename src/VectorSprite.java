
import java.awt.Polygon;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Steve
 */
import java.awt.*;

public class VectorSprite {

    double xposition;
    double yposition;
    double xspeed;
    double yspeed;
    double angle;
    Polygon shape;
    Polygon drawshape;
    double rotationspeed;
    double thrust;
    double maxspeed;
    boolean active = true;
    int resetcounter;
    int life;
    int delay;
    int delaytime;

    public void paint(Graphics G) {
        G.drawPolygon(drawshape);
    }

    public void updatePosition() {

        xposition += xspeed;
        yposition += yspeed;
        int x, y;
        for (int i = 0; i < shape.npoints; i++) {
            x = (int) Math.round(shape.xpoints[i] * Math.cos(angle) - shape.ypoints[i] * Math.sin(angle));
            y = -(int) Math.round(shape.xpoints[i] * Math.sin(angle) + shape.ypoints[i] * Math.cos(angle));
            drawshape.xpoints[i] = x;
            drawshape.ypoints[i] = y;
        }
        drawshape.invalidate();
        drawshape.translate((int) Math.round(xposition), (int) Math.round(yposition));
        wraparound();
        resetcounter--;
    }

    public void wraparound() {
        if (xposition < 0) {
            xposition = 900;
        }
        if (yposition < 0) {
            yposition = 600;
        }
        if (xposition > 900) {
            xposition = 0;
        }
        if (yposition > 600) {
            yposition = 0;
        }
    }
}
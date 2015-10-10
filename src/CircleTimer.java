import java.awt.*;

public class CircleTimer extends VectorSprite {

    double r = 50;
    int numpoints = 62;
    int delaytime;

    public CircleTimer(double x, double y, double r, int t) {
        shape = new Polygon();
        drawshape = new Polygon();
        this.r = r;
        delaytime = t;
        shape.addPoint(0, 0);
        drawshape.addPoint(0, 0);
        for (int i = 0; i < numpoints; i++) {
            shape.addPoint((int) (Math.round(
                    Math.cos((Math.PI / 30) * i) * r)),
                    (int) Math.round(Math.sin((Math.PI / 30) * i) * r));
            drawshape.addPoint((int) (Math.round(
                    Math.cos((Math.PI / 30) * i) * r)),
                    (int) Math.round(Math.sin((Math.PI / 30) * i) * r));
        }
        xposition = x;
        yposition = y;
    }

    @Override
    public void paint(Graphics G) {
        G.fillPolygon(drawshape);
    }

    @Override
    public void updatePosition() {
        if (numpoints > delaytime) {
            numpoints -= delaytime;
            shape = new Polygon();
            drawshape = new Polygon();
            shape.addPoint(0, 0);
            drawshape.addPoint(0, 0);
            for (int i = 0; i < numpoints; i++) {
                shape.addPoint(
                        (int) (Math.round(Math.cos((Math.PI / 30) * i) * r)),
                        (int) Math.round(Math.sin((Math.PI / 30) * i) * r));
                drawshape.addPoint((int) (Math.round(Math.cos((Math.PI / 30) * i) * r)),
                        (int) Math.round(Math.sin((Math.PI / 30) * i) * r));
            }
        } else {
            active = false;
        }
        super.updatePosition();
    }
}
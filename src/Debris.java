import java.awt.*;

public class Debris extends VectorSprite {

    Color color;

    public Debris(double x, double y, int type) {
        Color[] c = new Color[3];
        if (type == 1) {
            c[0] = Color.RED;
            c[1] = Color.ORANGE;
        }
        if (type == 2) {
            c[0] = Color.DARK_GRAY;
            c[1] = Color.GRAY;
        }
        if (type == 3) {
            c[0] = Color.BLUE;
            c[1] = Color.WHITE;

        }
        color = c[(int) (Math.random() * 2)];
        MakeDebris(x, y, color);
    }

    public Debris(double x, double y, Color c) {
        MakeDebris(x, y, c);
    }

    public void MakeDebris(double x, double y, Color c) {

        color = c;
        shape = new Polygon();
        shape.addPoint(2, 0);
        shape.addPoint(-2, 2);
        shape.addPoint(-2, -2);
        drawshape = new Polygon();
        drawshape.addPoint(2, 0);
        drawshape.addPoint(-2, 2);
        drawshape.addPoint(-2, -2);
        xposition = x;
        yposition = y;
        double h;
        double l;
        l = Math.random() * 2 * Math.PI;
        h = Math.random() * 7 + 5;
        xspeed = Math.cos(l) * h;
        yspeed = -Math.sin(l) * h;
        rotationspeed = Math.random() * 0.5 - 0.1;
    }

    @Override
    public void updatePosition() {
        angle += rotationspeed;
        super.updatePosition();
    }

    @Override
    public void wraparound() {
        if (xposition < 0) {
            active = false;
        }
        if (yposition < 0) {
            active = false;
        }
        if (xposition > 900) {
            active = false;
        }
        if (yposition > 600) {
            active = false;
        }
    }
}
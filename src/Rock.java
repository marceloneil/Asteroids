import java.awt.Polygon;

public class Rock extends VectorSprite {

    int size;

    public Rock() {

        double h;
        double a;
        a = Math.random() * 2 * Math.PI;
        h = Math.random() * 300 + 500;
        xposition = 450 + Math.cos(a) * h;
        yposition = 300 + Math.sin(a) * h;

        MakeRock(xposition, yposition, (int) (Math.random() * 3 + 1));
    }

    public Rock(double x, double y, int s) {
        MakeRock(x, y, s);
    }

    public void MakeRock(double x, double y, int s) {
        size = s;
        shape = new Polygon();
        shape.addPoint(-5 * size, 20 * size);
        shape.addPoint(5 * size, 20 * size);
        shape.addPoint(20 * size, 5 * size);
        shape.addPoint(20 * size, -5 * size);
        shape.addPoint(5 * size, -20 * size);
        shape.addPoint(-5 * size, -20 * size);
        shape.addPoint(-20 * size, -5 * size);
        shape.addPoint(-20 * size, 5 * size);
        drawshape = new Polygon();
        drawshape.addPoint(-5 * size, 20 * size);
        drawshape.addPoint(5 * size, 20 * size);
        drawshape.addPoint(20 * size, 5 * size);
        drawshape.addPoint(20 * size, -5 * size);
        drawshape.addPoint(5 * size, -20 * size);
        drawshape.addPoint(-5 * size, -20 * size);
        drawshape.addPoint(-20 * size, -5 * size);
        drawshape.addPoint(-20 * size, 5 * size);

        double h;
        double a;
        a = Math.random() * 2 * Math.PI;
        h = Math.random() * 5 + 5;
        xspeed = Math.cos(a) * h;
        yspeed = -Math.sin(a) * h;


        xposition = x;
        yposition = y;

        rotationspeed = Math.random() * 0.2 - 0.1;

    }

    @Override
    public void updatePosition() {
        angle += rotationspeed;
        super.updatePosition();
    }
}
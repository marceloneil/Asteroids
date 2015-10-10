import java.awt.Polygon;

public class PlainBullet extends VectorSprite {

    int bullettype = 1;

    public PlainBullet(double x, double y, double a) {
        shape = new Polygon();
        shape.addPoint(-6, -3);
        shape.addPoint(0, -3);
        shape.addPoint(6, 0);
        shape.addPoint(0, 3);
        shape.addPoint(-6, 3);
        drawshape = new Polygon();
        drawshape.addPoint(-6, -3);
        drawshape.addPoint(0, -3);
        drawshape.addPoint(6, 0);
        drawshape.addPoint(0, 3);
        drawshape.addPoint(-6, 3);
        xposition = x;
        yposition = y;
        maxspeed = 20;
        angle = a;
        xspeed = Math.cos(a) * maxspeed;
        yspeed = -Math.sin(a) * maxspeed;
        life = 50;

    }

    @Override
    public void updatePosition() {
        life -= 1;
        if (life < 0) {
            active = false;
        }
        super.updatePosition();
    }
}
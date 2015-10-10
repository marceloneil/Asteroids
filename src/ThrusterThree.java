import java.awt.Graphics;
import java.awt.Polygon;

public class ThrusterThree extends VectorSprite {

    public ThrusterThree() {
        shape = new Polygon();
        shape.addPoint(1, 6);
        shape.addPoint(5, 9);
        shape.addPoint(-3, 10);
        drawshape = new Polygon();
        drawshape.addPoint(1, 6);
        drawshape.addPoint(5, 9);
        drawshape.addPoint(-3, 10);
        active = false;
    }

    @Override
    public void updatePosition() {
        xposition = Asteroids.ship.xposition;
        yposition = Asteroids.ship.yposition;
        angle = Asteroids.ship.angle;
        super.updatePosition();
    }

    @Override
    public void paint(Graphics G) {
        G.fillPolygon(drawshape);
    }
}
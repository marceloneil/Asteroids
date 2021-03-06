import java.awt.*;

public class ThrusterOne extends VectorSprite {

    public ThrusterOne() {
        shape = new Polygon();
        shape.addPoint(-21, 3);
        shape.addPoint(-21, 11);
        shape.addPoint(-32, 7);
        drawshape = new Polygon();
        drawshape.addPoint(-21, 3);
        drawshape.addPoint(-21, 11);
        drawshape.addPoint(-32, 7);
        active = false;
    }

    @Override
    public void updatePosition() {
        xposition = Asteroids.ship.xposition;
        yposition = Asteroids.ship.yposition;
        angle = Asteroids.ship.angle;
        super.updatePosition();
    }

    public void paint(Graphics G) {
        G.fillPolygon(drawshape);
    }
}
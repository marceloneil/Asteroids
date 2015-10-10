import java.awt.Polygon;

public class SpaceShip extends VectorSprite {

    public SpaceShip() {
        shape = new Polygon();
        shape.addPoint(20, 0);
        shape.addPoint(10, 6);
        shape.addPoint(0, 6);
        shape.addPoint(-4, 10);
        shape.addPoint(-18, 10);
        shape.addPoint(-20, 12);
        shape.addPoint(-20, 2);
        shape.addPoint(-18, 4);
        shape.addPoint(-12, 3);
        shape.addPoint(-10, 0);
        shape.addPoint(-12, -3);
        shape.addPoint(-18, -4);
        shape.addPoint(-20, -2);
        shape.addPoint(-20, -12);
        shape.addPoint(-18, -10);
        shape.addPoint(-4, -10);
        shape.addPoint(0, -6);
        shape.addPoint(10, -6);
        drawshape = new Polygon();
        drawshape.addPoint(20, 0);
        drawshape.addPoint(10, 6);
        drawshape.addPoint(0, 6);
        drawshape.addPoint(-4, 10);
        drawshape.addPoint(-18, 10);
        drawshape.addPoint(-20, 12);
        drawshape.addPoint(-20, 2);
        drawshape.addPoint(-18, 4);
        drawshape.addPoint(-12, 3);
        drawshape.addPoint(0, 0);
        drawshape.addPoint(-12, -3);
        drawshape.addPoint(-18, -4);
        drawshape.addPoint(-20, -2);
        drawshape.addPoint(-20, -12);
        drawshape.addPoint(-18, -10);
        drawshape.addPoint(-4, -10);
        drawshape.addPoint(0, -6);
        drawshape.addPoint(10, -6);
        xposition = 450;
        yposition = 300;
        rotationspeed = Math.PI / 20;
        thrust = 1;
        maxspeed = 15;
        active = true;
        delaytime = 10;
        angle = Math.PI / 2;
        resetcounter = -50;
    }

    public void accelerate() {
        xspeed += Math.cos(angle) * thrust;
        yspeed += -Math.sin(angle) * thrust;
        if (Math.sqrt(xspeed * xspeed + yspeed * yspeed) > maxspeed) {
            xspeed = Math.cos(angle) * maxspeed;
            yspeed = -Math.sin(angle) * maxspeed;
        }
    }

    public void decelerate() {
        xspeed -= Math.cos(angle) * thrust;
        yspeed -= -Math.sin(angle) * thrust;
        if (Math.sqrt(xspeed * xspeed + yspeed * yspeed) > maxspeed) {
            xspeed = Math.cos(angle) * -maxspeed;
            yspeed = -Math.sin(angle) * -maxspeed;
        }

    }

    public void turnleft() {
        angle += rotationspeed;
    }

    public void turnright() {
        angle -= rotationspeed;
    }

    public void ResetShip() {
        if (!active) {
            delay = 50;
        }
        if (!active && IsRespawnSafe()) {
            xspeed = 0;
            yspeed = 0;
            xposition = 450;
            yposition = 300;
            rotationspeed = 0.15;
            thrust = 1;
            maxspeed = 10;
            active = true;
        }
    }

    public boolean IsRespawnSafe() {
        double a, b, c;
        for (int i = 0; i < Asteroids.rocklist.size(); i++) {
            a = Asteroids.rocklist.get(i).xposition - 450;
            b = Asteroids.rocklist.get(i).yposition - 300;
            c = Math.sqrt(a * a + b * b);
            if (c < 100) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void updatePosition() {
        delay--;
        super.updatePosition();
    }
}
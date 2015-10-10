import java.awt.*;

public class HomingMissile extends PlainBullet {

    VectorSprite outline = new VectorSprite();
    VectorSprite thruster = new VectorSprite();

    public HomingMissile(double x, double y, double a) {
        super(x, y, a);
        bullettype = 2;

        shape = new Polygon();
        shape.addPoint(10, 0);
        shape.addPoint(7, 3);
        shape.addPoint(-7, 3);
        shape.addPoint(-10, 5);
        shape.addPoint(-10, 0);
        shape.addPoint(-7, 0);
        shape.addPoint(-10, 0);
        shape.addPoint(-10, -5);
        shape.addPoint(-7, -3);
        shape.addPoint(7, -3);
        drawshape = new Polygon();
        drawshape.addPoint(10, 0);
        drawshape.addPoint(7, 3);
        drawshape.addPoint(-7, 3);
        drawshape.addPoint(-10, 5);
        drawshape.addPoint(-10, 0);
        drawshape.addPoint(-7, 0);
        drawshape.addPoint(-10, 0);
        drawshape.addPoint(-10, -5);
        drawshape.addPoint(-7, -3);
        drawshape.addPoint(7, -3);


        delay = 10;


        outline.shape = new Polygon();
        outline.shape.addPoint(10, 0);
        outline.shape.addPoint(7, 3);
        outline.shape.addPoint(-7, 3);
        outline.shape.addPoint(-10, 5);
        outline.shape.addPoint(-10, 0);
        outline.shape.addPoint(-7, 0);
        outline.shape.addPoint(-10, 0);
        outline.shape.addPoint(-10, -5);
        outline.shape.addPoint(-7, -3);
        outline.shape.addPoint(7, -3);
        outline.drawshape = new Polygon();
        outline.drawshape.addPoint(10, 0);
        outline.drawshape.addPoint(7, 3);
        outline.drawshape.addPoint(-7, 3);
        outline.drawshape.addPoint(-10, 5);
        outline.drawshape.addPoint(-10, 0);
        outline.drawshape.addPoint(-7, 0);
        outline.drawshape.addPoint(-10, 0);
        outline.drawshape.addPoint(-10, -5);
        outline.drawshape.addPoint(-7, -3);
        outline.drawshape.addPoint(7, -3);
        thruster.shape = new Polygon();
        thruster.shape.addPoint(-10, 3);
        thruster.shape.addPoint(-15, 0);
        thruster.shape.addPoint(-10, -3);
        thruster.drawshape = new Polygon();
        thruster.drawshape.addPoint(-10, 3);
        thruster.drawshape.addPoint(-15, 0);
        thruster.drawshape.addPoint(-10, -3);
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

    public void paint(Graphics G) {
        float[] myC = new float[3];
        Color.RGBtoHSB(253, 128, 15, myC);
        Color myColor1 = Color.getHSBColor(myC[0], myC[1], myC[2]);
        G.setColor(myColor1);
        G.fillPolygon(thruster.drawshape);
        G.setColor(Color.WHITE);
        G.fillPolygon(drawshape);
        G.setColor(Color.RED);
        G.drawPolygon(outline.drawshape);
    }

    @Override
    public void updatePosition() {
        delay--;
        if (delay < 0) {


            if (life <= 0 && active) {
                for (int l = 0; l < 20; l++) {
                    Asteroids.debrislist.add(new Debris(
                            xposition, yposition, 1));
                }
                active = false;
            }

            //find the closest rock
            double cx;
            double cy;
            if (Asteroids.rocklist.size() > 0) {
                cx = Asteroids.rocklist.get(0).xposition;
                cy = Asteroids.rocklist.get(0).yposition;
                for (int i = 1; i < Asteroids.rocklist.size(); i++) {
                    double a, b, c, d, e, f;
                    a = cx - xposition;
                    b = cy - yposition;
                    c = Math.sqrt(a * a + b * b);
                    d = Asteroids.rocklist.get(i).xposition - xposition;
                    e = Asteroids.rocklist.get(i).yposition - yposition;
                    f = Math.sqrt(d * d + e * e);
                    if (f < c) {
                        cx = Asteroids.rocklist.get(i).xposition;
                        cy = Asteroids.rocklist.get(i).yposition;
                    }
                }
                //find the angle
                double opp, adj;
                opp = cy - yposition;
                adj = cx - xposition;
                angle = Math.atan(-opp / adj);
                if (adj < 0) {
                    angle += Math.PI;
                }
            } else {
                angle += 0.5;
            }
            //calculate the new speed
            xspeed = Math.cos(angle) * maxspeed;
            yspeed = -Math.sin(angle) * maxspeed;
        }


        super.updatePosition();
        outline.xposition = xposition;
        outline.yposition = yposition;
        outline.angle = angle;
        outline.updatePosition();
        thruster.xposition = xposition;
        thruster.yposition = yposition;
        thruster.angle = angle;
        thruster.updatePosition();

    }
}
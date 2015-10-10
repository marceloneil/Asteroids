import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.*;

public class Asteroids extends Applet implements KeyListener, ActionListener {
//General Declarations Where we create the area for our global variables

    Timer timer;
    public static SpaceShip ship;
    ThrusterOne t1;
    ThrusterTwo t2;
    ThrusterThree t3;
    ThrusterFour t4;
    Image offscreen;
    Graphics offg;
    int level = 1;
    int leveldelay = -1;
    long fpsCount;
    int lives = 3;
    int bestcombo;
    int prevlevel = 1;
    AudioClip laser, shiphit, asteroidhit, thruster, laser2, mario;
    boolean keyright, keyleft, keyup, keydown, keyspace, music, musicreload;
    public static ArrayList<Rock> rocklist;
    public static ArrayList<PlainBullet> pblist;
    public static ArrayList<Debris> debrislist;
    public static ArrayList<CircleTimer> ctlist;
    public static ArrayList<CircleTimer> ctlist2;
    int gtype = 1;
    Font font1;
    int moveword = 0;
    int movewordy = 0;
    boolean partysover = false;
    int combo;

    @Override
    public void init() {

        this.setSize(900, 600);
        this.addKeyListener(this);
        ship = new SpaceShip();
        t1 = new ThrusterOne();
        t2 = new ThrusterTwo();
        t3 = new ThrusterThree();
        t4 = new ThrusterFour();
        laser = getAudioClip(getCodeBase(), "laser79.wav");
        laser2 = getAudioClip(getCodeBase(), "missile.wav");
        shiphit = getAudioClip(getCodeBase(), "explode1.wav");
        asteroidhit = getAudioClip(getCodeBase(), "explode0.wav");
        thruster = getAudioClip(getCodeBase(), "thruster.wav");
        mario = getAudioClip(getCodeBase(), "mario.wav");
        musicreload = true;
        ctlist = new ArrayList<CircleTimer>();
        ctlist2 = new ArrayList<CircleTimer>();
        pblist = new ArrayList<PlainBullet>();
        debrislist = new ArrayList<Debris>();
        timer = new Timer(20, this);
        timer.start();
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        rocklist = new ArrayList<Rock>();
        ship.xspeed = 0;
        ship.yspeed = 0;
        keyright = false;
        keyleft = false;
        keyup = false;
        keydown = false;
        partysover = false;
        gtype = 1;
        font1 = new Font("impact", Font.BOLD, 30);
        for (int i = 0; i < level; i++) {
            rocklist.add(new Rock());
        }
    }

    @Override
    public void paint(Graphics G) {
        offg.setColor(Color.black);
        /*
         float[] myC = new float[3];
         Color.RGBtoHSB(r, g, b, myC);
         Color myColor1 = Color.getHSBColor(myC[0], myC[1], myC[2]);
         */
        offg.fillRect(0, 0, 900, 600);
        offg.setColor(Color.WHITE);
        if (ship.active && ship.resetcounter < 0) {
            if ((ship.resetcounter > -50 && (ship.resetcounter + 100) % 5 > 2) || ship.resetcounter <= -50) {
                ship.paint(offg);
            }
        }
        for (int i = 0; i < rocklist.size(); i++) {
            rocklist.get(i).paint(offg);
        }
        for (int i = 0; i < pblist.size(); i++) {
            if (pblist.get(i).active) {
                pblist.get(i).paint(offg);
            }
        }
        for (int i = 0; i < debrislist.size(); i++) {
            if (debrislist.get(i).active) {
                offg.setColor(debrislist.get(i).color);
                debrislist.get(i).paint(offg);
            }
        }
        float[] myC = new float[3];
        Color.RGBtoHSB(252, 28, 3, myC);
        Color myColor1 = Color.getHSBColor(myC[0], myC[1], myC[2]);
        offg.setColor(myColor1);
        if (t1.active && t2.active && ship.active && ship.resetcounter <= -50) {
            t1.paint(offg);
            t2.paint(offg);
        }
        if (t3.active && t4.active && ship.active && ship.resetcounter <= -50) {
            t3.paint(offg);
            t4.paint(offg);
        }
        offg.setColor(Color.WHITE);
        offg.setFont(font1);
        if (rocklist.isEmpty() && ship.resetcounter < -50 && lives > 0) {
            offg.drawString("You Win Level " + prevlevel, 350, -50 + movewordy);
        }
        if (lives == 00) {
            offg.drawString("You Lose", 400, -50 + movewordy);
            offg.drawString("Best Combo = " + bestcombo, 360, 700 - movewordy);
        }
        if (ship.resetcounter > -50) {
            offg.drawString("Kamikaze!", 50 - moveword, 60);
        }
        for (int i = 0; i < ctlist.size(); i++) {
            if (ctlist.get(i).active) {
                ctlist.get(i).paint(offg);
            }
        }
        for (int i = 0; i < ctlist2.size(); i++) {
            if (ctlist2.get(i).active) {
                ctlist2.get(i).paint(offg);
            }
        }
        if (combo > 1) {
            offg.drawString("Combo " + combo, 50, 550);
        }
        if (lives > 0) {
            offg.drawString("Level " + level, 750, 550);
            offg.drawString(lives + " Lives", 650, 60);
        }
        G.drawImage(offscreen, 0, 0, this);
        repaint();
    }

    @Override
    public void update(Graphics G) {
        paint(G);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keydown = true;
            t3.active = true;
            t4.active = true;
            if (!music) {
                music = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            t1.active = true;
            t2.active = true;
            keyup = true;
            if (!music) {
                music = true;
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyleft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyright = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keyspace = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            ship.xspeed = 0;
            ship.yspeed = 0;
            t1.active = true;
            t2.active = true;
            t3.active = true;
            t4.active = true;
            music = true;

        }
        if (e.getKeyCode() == KeyEvent.VK_N) {
            if (gtype == 1) {
                gtype += 1;
            } else {
                gtype = 1;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keydown = false;
            t3.active = false;
            t4.active = false;
            if (music) {
                music = false;
                musicreload = true;
                thruster.stop();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keyup = false;
            t1.active = false;
            t2.active = false;
            if (music) {
                music = false;
                musicreload = true;
                thruster.stop();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keyleft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keyright = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keyspace = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            t1.active = false;
            t2.active = false;
            t3.active = false;
            t4.active = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            mario.stop();
        }
        if (e.getKeyCode() == KeyEvent.VK_B) {
            music = false;
            musicreload = true;
            thruster.stop();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (System.currentTimeMillis() > fpsCount) {
            fpsCount = System.currentTimeMillis() + (1000 / 60);
            keycheck();
            for (int i = 0; i < pblist.size(); i++) {
                pblist.get(i).updatePosition();
            }
            for (int i = 0; i < debrislist.size(); i++) {
                debrislist.get(i).updatePosition();
            }
            if (ship.resetcounter > 0) {
                moveword = 0;
            } else {
                moveword += 7;
            }
            if ((!rocklist.isEmpty()) || ship.resetcounter > -50) {
                movewordy = 0;
            } else {
                if (movewordy < 350) {
                    movewordy += 10;
                }
            }
            removeoldbullets();
            removeoldrocks();
            removeolddebris();
            endparty();
            end();
            if (musicreload) {
                musicstart();
            }
            newlevel();
            ship.updatePosition();
            t1.updatePosition();
            t2.updatePosition();
            t3.updatePosition();
            t4.updatePosition();
            for (int i = 0; i < ctlist.size(); i++) {
                ctlist.get(i).updatePosition();
            }
            for (int i = 0; i < ctlist2.size(); i++) {
                ctlist2.get(i).updatePosition();
            }
            for (int i = 0; i < rocklist.size(); i++) {
                rocklist.get(i).updatePosition();
            }
            checkcollision();
            ship.ResetShip();
            /*
             for(int i=0; i<100;i++){
             rocklist.add(new Rock());
             }
             */
        }
    }

    public void keycheck() {
        if (ship.active) {
            if (keyright == true) {
                ship.turnright();
            }
            if (keyleft == true) {
                ship.turnleft();
            }
            if (keyup == true) {
                ship.accelerate();
            }
            if (keydown == true) {
                ship.decelerate();
            }
            if (keyspace == true && ship.delay < 0 && ship.active) {
                if (gtype == 1) {
                    pblist.add(new PlainBullet(ship.xposition, ship.yposition, ship.angle));
                    laser.play();
                    ship.delay = ship.delaytime;
                }
                if (gtype == 2) {
                    pblist.add(new HomingMissile(ship.xposition, ship.yposition, ship.angle));
                    laser2.play();
                    ctlist.add(new CircleTimer(840, 60, 40, 2));
                    ship.delay = ship.delaytime * 3;
                }


            }
        }
    }

    public boolean collision(VectorSprite V1, VectorSprite V2) {
        int x, y;
        for (int i = 0; i < V1.drawshape.npoints; i++) {
            x = V1.drawshape.xpoints[i];
            y = V1.drawshape.ypoints[i];
            if (V2.drawshape.contains(x, y)) {
                return true;
            }
        }
        for (int i = 0; i < V2.drawshape.npoints; i++) {
            x = V2.drawshape.xpoints[i];
            y = V2.drawshape.ypoints[i];
            if (V1.drawshape.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    public void checkcollision() {

        for (int i = 0; i < rocklist.size(); i++) {
            if (collision(ship, rocklist.get(i)) && ship.resetcounter < -50 && ship.active && rocklist.get(i).active) {
                ship.resetcounter = 50;
                ship.active = false;
                keyleft = false;
                keyright = false;
                keyup = false;
                keydown = false;
                keyspace = false;
                lives--;
                rocklist.get(i).active = false;
                shiphit.play();
                asteroidhit.play();
                for (int muffin = 0; muffin < 2; muffin++) {
                    if (rocklist.get(i).size > 1) {
                        rocklist.add(new Rock(rocklist.get(i).xposition,
                                rocklist.get(i).yposition, rocklist.get(i).size - 1));
                    }
                }
                for (int l = 0; l < 100; l++) {
                    debrislist.add(new Debris(rocklist.get(i).xposition, rocklist.get(i).yposition, 2));
                }
                for (int l = 0; l < 100; l++) {
                    debrislist.add(new Debris(ship.xposition, ship.yposition, Color.RED));
                }

                for (int l = 0; l < ctlist2.size(); i++) {
                    ctlist2.remove(l);
                    if (bestcombo < combo) {
                        bestcombo = combo;
                    }
                    combo = 1;

                }
            }
        }
        for (int i = 0; i < rocklist.size(); i++) {
            for (int potato = 0; potato < pblist.size(); potato++) {
                if (collision(rocklist.get(i), pblist.get(potato))) {
                    for (int l = 0; l < 100; l++) {
                        debrislist.add(new Debris(rocklist.get(i).xposition, rocklist.get(i).yposition, 2));
                    }
                    if (pblist.get(potato).bullettype == 1) {
                        for (int l = 0; l < 2; l++) {
                            debrislist.add(new Debris(pblist.get(potato).xposition, pblist.get(potato).yposition, 2));
                        }
                    } else if (pblist.get(potato).bullettype == 2) {
                        for (int l = 0; l < 20; l++) {
                            debrislist.add(new Debris(pblist.get(potato).xposition, pblist.get(potato).yposition, 1));
                        }
                    }
                    asteroidhit.play();
                    if (ctlist2.size() > 0) {
                        if (ctlist2.get(ctlist2.size() - 1).active) {
                            ctlist2.add(new CircleTimer(225, 540, 30, 1));
                            combo += 1;
                        } else {
                            combo = 1;
                        }
                    } else {
                        combo = 1;
                    }
                    pblist.get(potato).active = false;
                    rocklist.get(i).active = false;
                    for (int muffin = 0; muffin < 2; muffin++) {
                        if (rocklist.get(i).size > 1) {
                            rocklist.add(new Rock(rocklist.get(i).xposition,
                                    rocklist.get(i).yposition, rocklist.get(i).size - 1));
                            ctlist2.add(new CircleTimer(225, 540, 30, 1));

                        }
                    }
                }
            }
        }
    }

    public void removeoldbullets() {
        for (int i = 0; i < pblist.size(); i++) {

            if (!pblist.get(i).active) {
                if (pblist.get(i).bullettype == 1) {
                    for (int l = 0; l < 2; l++) {
                        debrislist.add(new Debris(pblist.get(i).xposition, pblist.get(i).yposition, 2));
                    }
                } else if (pblist.get(i).bullettype == 2) {
                    for (int l = 0; l < 20; l++) {
                        debrislist.add(new Debris(pblist.get(i).xposition, pblist.get(i).yposition, 1));
                    }
                }
                pblist.remove(i);
            }
        }
    }

    public void removeoldrocks() {
        for (int i = 0; i < rocklist.size(); i++) {
            if (!rocklist.get(i).active) {

                rocklist.remove(i);
            }
        }
    }

    public void removeolddebris() {
        for (int i = 0; i < debrislist.size(); i++) {
            if (!debrislist.get(i).active) {
                debrislist.remove(i);
            }
        }
    }

    public void removect() {
        for (int i = 0; i < ctlist.size(); i++) {
            if (!ctlist.get(i).active) {
                ctlist.remove(i);
            }
        }
    }

    public void removect2() {
        for (int i = 0; i < ctlist2.size(); i++) {
            if (!ctlist2.get(i).active) {
                ctlist2.remove(i);
            }
        }
    }

    public void endparty() {
        if (rocklist.isEmpty() && ship.resetcounter < -50 && partysover == false && lives > 0) {
            leveldelay = 200;
            level++;
            lives++;
            for (int l = 0; l < 250; l++) {
                debrislist.add(new Debris(50, 50, 3));
                debrislist.add(new Debris(100, 50, 3));
                debrislist.add(new Debris(150, 50, 3));
                debrislist.add(new Debris(200, 50, 3));
                debrislist.add(new Debris(250, 50, 3));
                debrislist.add(new Debris(300, 50, 3));
                debrislist.add(new Debris(350, 50, 3));
                debrislist.add(new Debris(400, 50, 3));
                debrislist.add(new Debris(450, 50, 3));
                debrislist.add(new Debris(500, 50, 3));
                debrislist.add(new Debris(550, 50, 3));
                debrislist.add(new Debris(600, 50, 3));
                debrislist.add(new Debris(650, 50, 3));
                debrislist.add(new Debris(700, 50, 3));
                debrislist.add(new Debris(750, 50, 3));
                debrislist.add(new Debris(800, 50, 3));
                debrislist.add(new Debris(850, 50, 3));
                debrislist.add(new Debris(50, 100, 3));
                debrislist.add(new Debris(50, 150, 3));
                debrislist.add(new Debris(50, 200, 3));
                debrislist.add(new Debris(50, 250, 3));
                debrislist.add(new Debris(50, 300, 3));
                debrislist.add(new Debris(50, 350, 3));
                debrislist.add(new Debris(50, 400, 3));
                debrislist.add(new Debris(50, 450, 3));
                debrislist.add(new Debris(50, 500, 3));
                debrislist.add(new Debris(50, 550, 3));
                debrislist.add(new Debris(100, 550, 3));
                debrislist.add(new Debris(150, 550, 3));
                debrislist.add(new Debris(200, 550, 3));
                debrislist.add(new Debris(250, 550, 3));
                debrislist.add(new Debris(300, 550, 3));
                debrislist.add(new Debris(350, 550, 3));
                debrislist.add(new Debris(400, 550, 3));
                debrislist.add(new Debris(450, 550, 3));
                debrislist.add(new Debris(500, 550, 3));
                debrislist.add(new Debris(550, 550, 3));
                debrislist.add(new Debris(600, 550, 3));
                debrislist.add(new Debris(650, 550, 3));
                debrislist.add(new Debris(700, 550, 3));
                debrislist.add(new Debris(750, 550, 3));
                debrislist.add(new Debris(800, 550, 3));
                debrislist.add(new Debris(850, 100, 3));
                debrislist.add(new Debris(850, 150, 3));
                debrislist.add(new Debris(850, 200, 3));
                debrislist.add(new Debris(850, 250, 3));
                debrislist.add(new Debris(850, 300, 3));
                debrislist.add(new Debris(850, 350, 3));
                debrislist.add(new Debris(850, 400, 3));
                debrislist.add(new Debris(850, 450, 3));
                debrislist.add(new Debris(850, 500, 3));
                debrislist.add(new Debris(850, 550, 3));
                debrislist.add(new Debris(850, 550, 3));
                partysover = true;
            }
        }

    }

    public void end() {
        if (lives == 0) {
            for (int i = 0; i < rocklist.size(); i++) {
                rocklist.remove(i);
            }
            for (int l = 0; l < 1; l++) {
                debrislist.add(new Debris(50, 50, Color.RED));
                debrislist.add(new Debris(100, 50, Color.RED));
                debrislist.add(new Debris(150, 50, Color.RED));
                debrislist.add(new Debris(200, 50, Color.RED));
                debrislist.add(new Debris(250, 50, Color.RED));
                debrislist.add(new Debris(300, 50, Color.RED));
                debrislist.add(new Debris(350, 50, Color.RED));
                debrislist.add(new Debris(400, 50, Color.RED));
                debrislist.add(new Debris(450, 50, Color.RED));
                debrislist.add(new Debris(500, 50, Color.RED));
                debrislist.add(new Debris(550, 50, Color.RED));
                debrislist.add(new Debris(600, 50, Color.RED));
                debrislist.add(new Debris(650, 50, Color.RED));
                debrislist.add(new Debris(700, 50, Color.RED));
                debrislist.add(new Debris(750, 50, Color.RED));
                debrislist.add(new Debris(800, 50, Color.RED));
                debrislist.add(new Debris(850, 50, Color.RED));
                debrislist.add(new Debris(50, 100, Color.RED));
                debrislist.add(new Debris(50, 150, Color.RED));
                debrislist.add(new Debris(50, 200, Color.RED));
                debrislist.add(new Debris(50, 250, Color.RED));
                debrislist.add(new Debris(50, 300, Color.RED));
                debrislist.add(new Debris(50, 350, Color.RED));
                debrislist.add(new Debris(50, 400, Color.RED));
                debrislist.add(new Debris(50, 450, Color.RED));
                debrislist.add(new Debris(50, 500, Color.RED));
                debrislist.add(new Debris(50, 550, Color.RED));
                debrislist.add(new Debris(100, 550, Color.RED));
                debrislist.add(new Debris(150, 550, Color.RED));
                debrislist.add(new Debris(200, 550, Color.RED));
                debrislist.add(new Debris(250, 550, Color.RED));
                debrislist.add(new Debris(300, 550, Color.RED));
                debrislist.add(new Debris(350, 550, Color.RED));
                debrislist.add(new Debris(400, 550, Color.RED));
                debrislist.add(new Debris(450, 550, Color.RED));
                debrislist.add(new Debris(500, 550, Color.RED));
                debrislist.add(new Debris(550, 550, Color.RED));
                debrislist.add(new Debris(600, 550, Color.RED));
                debrislist.add(new Debris(650, 550, Color.RED));
                debrislist.add(new Debris(700, 550, Color.RED));
                debrislist.add(new Debris(750, 550, Color.RED));
                debrislist.add(new Debris(800, 550, Color.RED));
                debrislist.add(new Debris(850, 100, Color.RED));
                debrislist.add(new Debris(850, 150, Color.RED));
                debrislist.add(new Debris(850, 200, Color.RED));
                debrislist.add(new Debris(850, 250, Color.RED));
                debrislist.add(new Debris(850, 300, Color.RED));
                debrislist.add(new Debris(850, 350, Color.RED));
                debrislist.add(new Debris(850, 400, Color.RED));
                debrislist.add(new Debris(850, 450, Color.RED));
                debrislist.add(new Debris(850, 500, Color.RED));
                debrislist.add(new Debris(850, 550, Color.RED));
                debrislist.add(new Debris(850, 550, Color.RED));
            }
        }
    }

    public void newlevel() {
        if (leveldelay > -1) {
            leveldelay -= 1;
        } else {
            prevlevel = level;

        }
        if (leveldelay == 0) {
            for (int i = 0; i < debrislist.size(); i++) {
                debrislist.remove(i);
            }
            for (int i = 0; i < level; i++) {
                rocklist.add(new Rock());
            }
            partysover = false;
        }
    }

    public void musicstart() {
        if (music && musicreload) {
            thruster.loop();
            musicreload = false;
        }
    }
}
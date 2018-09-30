package io.thethelab;

import processing.core.PApplet;
import processing.core.PImage;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Window4 extends PApplet {

    public static void main(String[] args) {
        PApplet.main("io.thethelab.Window4");
    }

    public void settings() {
        size(960, 640);
    }


    private PlayerCircle playerCircle;
    private ArrayList<MissileCircle> missileCircles;
    private static final int NUMOFMISSILES = 10;

    private LocalTime start;
    private LocalTime now;
    private Duration between;

    private boolean NORTH;
    private boolean SOUTH;
    private boolean EAST;
    private boolean WEST;

    public void setup() {
        // 원충돌 어떻게 할까?
        playerCircle = new PlayerCircle(this, 3);
        missileCircles = new ArrayList<>();
        for (int i = 0; i < NUMOFMISSILES; i++) {
            missileCircles.add(new MissileCircle(this, playerCircle.getX(), playerCircle.getY()));
        }

//        start = LocalTime.of(0, 0, 0);
        start = LocalTime.now();


    }

    public void draw() {
        background(0);
        fill(255);

        now = LocalTime.now();
        between = Duration.between(start, now);
        text(between.toString(), 850, 28);

        if (keyPressed && key == '0') { // 재시작
            this.setup();
            return;
        }
        if (playerCircle.getHeartCount() <= 0)
            return; // 게임 끝

        for (int i = 0; i < NUMOFMISSILES; i++) {
            if (Math.sqrt(Math.pow(playerCircle.getX() - missileCircles.get(i).getX(), 2)
                    + Math.pow(playerCircle.getY() - missileCircles.get(i).getY(), 2))
                    <= (playerCircle.getR() + missileCircles.get(i).getR()) / 2) {
                playerCircle.subHeartCount();
                if (playerCircle.getHeartCount() <= 0)
                    return; // 게임 끝
                else {
                    //생명 남았을시 재생성
                    playerCircle = new PlayerCircle(this, playerCircle.getHeartCount());
                    missileCircles = new ArrayList<>();
                    for (int j = 0; j < NUMOFMISSILES; j++) {
                        missileCircles.add(new MissileCircle(this, playerCircle.getX(), playerCircle.getY()));
                    }
                    return;
                }
            }

            if (missileCircles.get(i).getX() < -100 || missileCircles.get(i).getX() > 1060
                    || missileCircles.get(i).getY() < -100 || missileCircles.get(i).getY() > 740)
                missileCircles.set(i, new MissileCircle(this,
                        playerCircle.getX(), playerCircle.getY()));

            missileCircles.get(i).update();
            missileCircles.get(i).render();
        }


        if (WEST) {
            playerCircle.moveLeft();
        }
        if (EAST) {
            playerCircle.moveRight();
        }
        if (NORTH) {
            playerCircle.moveUp();
        }
        if (SOUTH) {
            playerCircle.moveDown();
        }
        if (!keyPressed) {
            playerCircle.vx = 3.0f;
            playerCircle.vy = 3.0f;
        }
        playerCircle.render();
    }

    public void keyPressed() {
        final int k = keyCode;

        if (k == UP    | k == 'W')   NORTH = true;
        else if (k == DOWN  | k == 'S')   SOUTH = true;
        else if (k == LEFT  | k == 'A')   WEST  = true;
        else if (k == RIGHT | k == 'D')   EAST  = true;

//        redraw();   //  queue draw()
    }

    public void keyReleased() {
        final int k = keyCode;

        if      (k == UP    | k == 'W')   NORTH = false;
        else if (k == DOWN  | k == 'S')   SOUTH = false;
        else if (k == LEFT  | k == 'A')   WEST  = false;
        else if (k == RIGHT | k == 'D')   EAST  = false;

//        redraw();   //  queue draw()
    }


}













//        ellipse(mouseX, mouseY, 20, 20);
//
//        if (Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2)) < (r + 20) / 2) {
//            System.out.println("Col!");
//        }









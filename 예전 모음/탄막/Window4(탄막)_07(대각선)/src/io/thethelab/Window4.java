package io.thethelab;

import processing.core.PApplet;

import java.time.Duration;
import java.time.LocalTime;

public class Window4 extends PApplet {

    public static void main(String[] args) {
        PApplet.main("io.thethelab.Window4");
    }

    public void settings() {
        size(960, 640);
    }


    private PlayerCircle playerCircle;
    private MissileCircle missileCircle;

    LocalTime start;
    LocalTime now;
    Duration between;

    boolean NORTH;
    boolean SOUTH;
    boolean EAST;
    boolean WEST;

    public void setup() {
        // 원충돌 어떻게 할까?
        playerCircle = new PlayerCircle(this, 3);
        missileCircle = new MissileCircle(this, playerCircle.getX(), playerCircle.getY());

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

        if (Math.sqrt(Math.pow(playerCircle.getX() - missileCircle.getX(), 2)
                + Math.pow(playerCircle.getY() - missileCircle.getY(), 2))
                <= (playerCircle.getR() + missileCircle.getR())/2) {
            playerCircle.subHeartCount();
            if (playerCircle.getHeartCount() <= 0)
                return; // 게임 끝
            //생명 남았을시 재생성
            playerCircle = new PlayerCircle(this, playerCircle.getHeartCount());
            missileCircle = new MissileCircle(this, playerCircle.getX(), playerCircle.getY());
        }


        if (missileCircle.getX() < -100 || missileCircle.getX() > 1060
                || missileCircle.getY() < -100 || missileCircle.getY() > 740)
            missileCircle = new MissileCircle(this,
                    playerCircle.getX(), playerCircle.getY());

        missileCircle.update();
        missileCircle.render();


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









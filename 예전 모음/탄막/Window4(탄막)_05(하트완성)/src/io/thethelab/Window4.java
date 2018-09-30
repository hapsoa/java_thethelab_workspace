package io.thethelab;

import processing.core.PApplet;

public class Window4 extends PApplet {

    public static void main(String[] args) {
        PApplet.main("io.thethelab.Window4");
    }

    public void settings() {
        size(960, 640);
    }


    private PlayerCircle playerCircle;
    private MissileCircle missileCircle;
    private int heartCount;
    private int tick = 0;

    public void setup() {
        // 원충돌 어떻게 할까?
        playerCircle = new PlayerCircle(this, 3);
        missileCircle = new MissileCircle(this, playerCircle.getX(), playerCircle.getY());
    }

    public void draw() {
        background(0);
        fill(255);

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


        if (keyPressed && keyCode == LEFT)
            playerCircle.moveLeft();
        if (keyPressed && keyCode == RIGHT)
            playerCircle.moveRight();
        if (keyPressed && keyCode == UP)
            playerCircle.moveUp();
        if (keyPressed && keyCode == DOWN)
            playerCircle.moveDown();
        playerCircle.render();

        tick++;
    }


}













//        ellipse(mouseX, mouseY, 20, 20);
//
//        if (Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2)) < (r + 20) / 2) {
//            System.out.println("Col!");
//        }









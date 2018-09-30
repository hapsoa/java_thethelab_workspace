package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Window extends PApplet {

    StartPoint startPoint  = new StartPoint(this, mouseX, mouseY);
    List<Point> firePoints;

    public void settings() {
        size(960, 640);
    }

    public void setup() {
        background(0);

    }

    public void draw() {
        background(0);
        if (keyPressed) {

            startPoint = new StartPoint(this, mouseX, mouseY);
            firePoints = new ArrayList<>();

        }

        if (startPoint.getVy() > 0) { //위로 올라가는 속도가 있을때
            // 1.점이 위로 올라간다. (점의 위치들을 저장)
            startPoint.lx.add(startPoint.getX());
            startPoint.ly.add(startPoint.getY());

            startPoint.update();
            // 2.점의 저장된 위치에 따라 라인을 그려간다.
            fill(255, 255, 255);
            startPoint.render();

//            startPoint.renderLine();
            //꼬리 그리기
            for (int i = 1; i < startPoint.lx.size(); i++) {
                stroke(255);
                line(startPoint.lx.get(i-1), startPoint.ly.get(i-1),
                        startPoint.lx.get(i), startPoint.ly.get(i));
            }
            if (startPoint.lx.size() > 30) { //꼬리 없애기
                startPoint.lx.remove(0);
                startPoint.ly.remove(0);
            }

        }
        else { //올라가는 점이 사라질 때
            if (key == '1') {
                if (firePoints.size() == 0) {
                    for (int i = 0; i < 100; i++)
                        firePoints.add(new FirePoint(this,
                                startPoint.getX(), startPoint.getY()));
                }


                for (Point p : firePoints) {
                    if (p.getY() < 640) {
                        p.update();
                        p.render();
                    }
                }
            }
            //2번 누를 때
            if (key == '2') {
                if (firePoints.size() == 0) {
                    for (int i = 0; i < 100; i++)
                        firePoints.add(new UpFirePoint(this,
                                startPoint.getX(), startPoint.getY()));
                }

                for (Point p : firePoints) {
                    if (p.getY() < 640) {
                        p.update();
                        p.render();
                    }
                }
            }
        }



    }


}

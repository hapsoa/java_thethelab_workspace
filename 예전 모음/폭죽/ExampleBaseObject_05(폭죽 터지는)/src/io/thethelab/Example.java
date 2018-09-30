package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;


public class Example extends PApplet {
    public static void main(String args[]) {
        PApplet.main("Example");
    }

    public void settings() {
        // 시스템
        size(960, 640);

    }

//    List<ExamplePoint> points;
    List<Rect> rects;
    List<Circle> circles;

    public void setup() {
//        points = new ArrayList<>();
        rects = new ArrayList<>();
        circles = new ArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            points.add(new ExamplePoint(this));
//        }
    }


    // 반복문
    // 한번 돌때 단위 시간이 진행됨.
    // 30번의 루프가 일어나므로 , 0.033..... 초
    // 1초에 10px 오른쪽


    // 처음엔 속도가 0
    // 가속도가 오른쪽으로 10px 1초에
    // 1초 : 10px = 0.033초 : x
    private float pastMouseX = mouseX;

    public void draw() {
        rects.add(new Rect(this, pastMouseX));
        circles.add(new Circle(this));
        background(0);
        colorMode(HSB);
        fill(255, 255, 255);

//        for(int i = 0 ; i < points.size(); i ++){
//            points.get(i).update();
//            points.get(i).render();
//        }

        for (int i = 0; i < rects.size(); i++) {
            if (Math.abs(rects.get(i).vx) > 0.1f) {
                rects.get(i).update();
                rects.get(i).render();
            }
        }

        for (int i = 0; i < rects.size(); i++) {
            if (Math.abs(rects.get(i).vx) <= 0.1f) {
                rects.get(i).destroy();

                if (rects.size() > 200)
                    rects.remove(i);
            }
        }

        for (int i = 0; i < circles.size(); i++) {

            circles.get(i).update();
            circles.get(i).render();

        }

        for (int i = 0; i < circles.size(); i++) {
            if (circles.get(i).y < 0)
                circles.remove(i);
        }

        pastMouseX = mouseX;
    }

}


package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Window extends PApplet {
    public void settings() {
// 윈도우 크기 등 시스템 변수 초기화
        size(960, 640);
    }


    public void setup() {
// 코드 작성시 필요한 객체 생성 및 초기화 부분

    }


    class Point {
        public int x;
        public int y;

        Point (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private int tick = 0;

    public void draw() {
        // 실제 로직이 들어가는 부분

        ArrayList<Point> points = new ArrayList<>();

        points.add(new Point(mouseX, mouseY));
        //마우스 위치에 점을 찍는다.


        for (int i = 1; i < tick ; i++) {
            points.add(new Point(points.get(i-1).x + 20, points.get(i-1).y + 20));
            //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
            stroke(255, 0, 0);
            line(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y);
        }

        tick++;



//        points.add(new Point(points.get(0).x + 20, points.get(0).y + 20));
//        //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
//        line(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y);
//
//
//        points.add(new Point(points.get(1).x + 2, points.get(1).y + 20));
//        line(points.get(1).x, points.get(1).y, points.get(2).x, points.get(2).y);
//
//
//        points.add(new Point(points.get(2).x + 20, points.get(2).y + 2));
//        line(points.get(2).x, points.get(2).y, points.get(3).x, points.get(3).y);





    }
}

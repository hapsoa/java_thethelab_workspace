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
        background(0);
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
    ArrayList<Point> points = new ArrayList<>();

    public void draw() {
        // 실제 로직이 들어가는 부분
        background(0, 0.5f);
        blendMode(ADD);
//        colorMode(HSB, 255); //이게 문제네

        points.add(new Point(mouseX, mouseY));
        //마우스 위치에 점을 찍는다.

        for (int i = 0; i < points.size(); i++) {
            ArrayList<Point> onePoint = new ArrayList<>();

            onePoint.add(points.get(i));

            for (int j = 1; j < (tick + i) % 20; j++) {
                onePoint.add(new Point(onePoint.get(j-1).x + 20, onePoint.get(j-1).y + 20));
                //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
                stroke(255, 0, 0);
                line(onePoint.get(j-1).x, onePoint.get(j-1).y, onePoint.get(j).x, onePoint.get(j).y);
            }


        }

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x > 960 || points.get(i).x < 0 ||
                    points.get(i).y > 640 || points.get(i).y < 0)
                points.remove(i);
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

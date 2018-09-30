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

    public void draw() {
        // 실제 로직이 들어가는 부분

        ArrayList<Point> points = new ArrayList<>();

        //points.add(new Point());
        Point point = new Point(mouseX, mouseY);
        //마우스 위치에 점을 찍는다.
        point.x = mouseX;
        point.y = mouseY;

        Point point2 = new Point();
        //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
        point2.x = point.x + 20;
        point2.y = point.y + 20;

        line(point.x, point.y, point2.x, point2.y);

        Point point3= new Point();

        point3.x = point2.x + 2;
        point3.y = point2.y + 20;

        line(point2.x, point2.y, point3.x, point3.y);

        Point point4= new Point();

        point4.x = point3.x + 2;
        point4.y = point3.y + 20;

        line(point3.x, point3.y, point4.x, point4.y);


    }
}

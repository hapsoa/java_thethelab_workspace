import processing.core.PApplet;

import java.util.ArrayList;

public class Window extends PApplet {
    public void settings() {
// 윈도우 크기 등 시스템 변수 초기화
        size(960, 640);
    }

    // 1. 마우스를 따라서 점이 생성이 된다 .
// 2. 생성된 점이 아래로 비처럼 흘러 내린다.
// ** 점을 찍고 그 점이 움직여야 함.
    class Point {
        // 위치
        public float x;
        public float y;
        public float raduis;

        // 색상
        public float r;
        public float g;
        public float b;

        // 속도
        public float accelx = 0;
        public float accely = 0;

        public float deltaAccelx = 0;
        public float deltaAccely = 0;
    }

    private ArrayList<Point> points = new ArrayList<>();

    public void setup() {
        // 코드 작성시 필요한 객체 생성 및 초기화 부분
        background(0);
    }


    int tick = 0;

    public void draw() {
// 실제 로직이 들어가는 부분
// Color , RGB, HSL
// Blend

        background(0, 0.5f);
        System.out.println(mouseX + " , " + mouseY);
        blendMode(ADD);
        colorMode(HSB, 255);
        tick++;
        for (int i = 0; i < 1; i++) {
            Point point = new Point();
            point.x = (int) (mouseX + Math.random() * 20 - 10);
            point.y = (int) (mouseY + Math.random() * 20 - 10);
            point.raduis = (float) (Math.random() * 10 + 5);

            point.r = (255 + tick) % 255 + (int) (Math.random() * 5);
            point.g = 255;
            point.b = 255;

            point.deltaAccelx = (float) (Math.random() * 0.1f) + -0.05f;
            point.deltaAccely = (float) (Math.random() * 0.1f) + -0.05f;

            points.add(point);
        }



        for (int i = 0; i < points.size(); i++) {
            Point pastPoint = new Point();
            Point point = points.get(i);

            pastPoint.x = point.x;
            pastPoint.y = point.y;

            stroke(point.r, point.g, point.b, 125);

            //line(points.get(i - 1).x, points.get(i - 1).y, point.x, point.y);

            //for (int j = 0; j < 50; j++) {
                point.x += point.accelx;
                point.y += point.accely;

                point.accelx += point.deltaAccelx;
                point.accely += point.deltaAccely;

                line(pastPoint.x, pastPoint.y, point.x, point.y);
            //}


            //이렇게 하면 Arraylist 메모리가 사라지나?
            if (point.y > 640)
                points.remove(i);
        }


    }
}

//    ArrayList<Point> points = new ArrayList<>();
//
//        points.add(new Point(mouseX, mouseY));
//                //마우스 위치에 점을 찍는다.
//
//
//                for (int i = 1; i < 30; i++) {
//        points.add(new Point(points.get(i-1).x + 20, points.get(i-1).y + 20));
//        //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
//        line(points.get(i-1).x, points.get(i-1).y, points.get(i).x, points.get(i).y);
//        }





















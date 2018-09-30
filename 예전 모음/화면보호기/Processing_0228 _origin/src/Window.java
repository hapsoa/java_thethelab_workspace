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
        for (int i = 0; i < 2; i++) {
            Point point = new Point();
            point.x = (int) (mouseX + Math.random() * 20 - 10);
            point.y = (int) (mouseY + Math.random() * 20 - 10);
            point.raduis = (float) (Math.random() * 10 + 5);

            point.r = (255 + tick) % 255 + (int) (Math.random() * 5);
            point.g = 255;
            point.b = 255;

            point.deltaAccelx = (float) (Math.random() * 0.1f) + -0.05f;
            point.deltaAccely = (float) (Math.random() * 0.1f) + 0.1f;

            points.add(point);
        }

        for (int i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            fill(point.r, point.g, point.b, 125);
            ellipse(point.x, point.y, point.raduis, point.raduis);
            point.x += point.accelx;
            point.y += point.accely;

            point.accelx += point.deltaAccelx;
            point.accely += point.deltaAccely;
        }


    }
}
















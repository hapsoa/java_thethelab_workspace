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

        Point() {

        }

        Point (float x, float y) {
            this.x = x;
            this.y = y;
        }
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

        //점 하나 하나에 관해서 작동
        for (int i = 0; i < points.size(); i++) {
            ArrayList<Point> onePoint = new ArrayList<>();

            //선의 색, 굵기 설정
            stroke(points.get(i).r, points.get(i).g, points.get(i).b);
            strokeWeight(10);

            // 한개의 점이 이동
            onePoint.add(points.get(i));
            for (int j = 1; j < (tick + i); j++) {
                //랜덤 가속도
                onePoint.get(j-1).deltaAccelx = (float) (Math.random() * 0.1f) + -0.05f;
                onePoint.get(j-1).deltaAccely = (float) (Math.random() * 0.1f) + -0.05f;

                //가속도를 저장한다.
                points.get(i).accelx += onePoint.get(j-1).deltaAccelx;
                points.get(i).accely += onePoint.get(j-1).deltaAccely;


                //다음 프레임에 점이 이동한다. 점의 위치를 저장한다.
                onePoint.add(new Point(onePoint.get(j-1).x + points.get(i).accelx,
                        onePoint.get(j-1).y + points.get(i).accely));


                line(onePoint.get(j-1).x, onePoint.get(j-1).y,
                        onePoint.get(j).x, onePoint.get(j).y);

//                if (onePoint.get(j-1).x > 960 || onePoint.get(j-1).x < 0 ||
//                        onePoint.get(j-1).y > 640 || onePoint.get(j-1).y < 0)
//                    onePoint = null;
            }


        }

        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).x > 960 || points.get(i).x < 0 ||
                    points.get(i).y > 640 || points.get(i).y < 0)
                points.remove(i);
        }




    }
}























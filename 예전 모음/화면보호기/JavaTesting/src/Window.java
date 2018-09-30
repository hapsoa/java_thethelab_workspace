import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    List<Point> points = new ArrayList<>();
    public void settings() {
        // 시스템
        size(960, 640);

    }

    public void setup() {

    }


    // 이친구가 반복된다.
    // 30 FPS

    public void draw() {
        background(0);

        if(mousePressed) {
            for (int i = 0; i < 2; i++) {
                Point p = new Point(this, mouseX, mouseY);
                points.add(p);

            }
        }

        for(Point point : points){
            point.update();
            point.render();
        }


    }

}


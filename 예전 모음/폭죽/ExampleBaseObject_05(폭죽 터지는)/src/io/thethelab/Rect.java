package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Rect extends ExampleBaseObject{
    private float pastMouseX;
    private ArrayList<Circle> circles = new ArrayList<>(100);

    public Rect(PApplet p, float pastMouseX) {
        super(p);
        x = pApplet.mouseX;
        y = pApplet.mouseY;
        vx = pApplet.mouseX - pastMouseX;
        ax = 2.0f;

        for (int i = 0; i < 100; i++) {
            circles.add(new Circle(pApplet));
        }
    }

    @Override
    public void update() {
        x += vx;
        if (vx > 0) {
            vx -= ax;
            ax *= 0.95f;
        } else if(vx < 0) {
            vx += ax;
            ax *= 0.95f;
        }
//        x += vx;
//        vx *= ax;
//        ax *= 0.9f;

    }

    @Override
    public void render() {
        pApplet.rect(x-5, y-5, 5, 5);
    }

    public void destroy() {
        for (int i = 0; i < circles.size(); i++) {
            circles.get(i).x += circles.get(i).vx;
            circles.get(i).y += circles.get(i).vy;

            circles.get(i).vx += circles.get(i).ax;
            circles.get(i).vy += circles.get(i).ay;

            circles.get(i).ax += Math.random() * 1.0f - 0.5f;
            circles.get(i).ay += Math.random() * 1.0f - 0.5f;

            circles.get(i).render();
        }
//        Circle circle1 = new Circle(pApplet);
//        Circle circle2 = new Circle(pApplet);
//        circle1.x = this.x;
//        circle1.y = this.y;
//        circle2.x = this.x;
//        circle2.y = this.y;
//
//        circle1.render();
//        circle2.render();
    }
}

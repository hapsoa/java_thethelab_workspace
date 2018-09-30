package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class Rect extends ExampleBaseObject{
    private float pastMouseX;


    public Rect(PApplet p, float pastMouseX) {
        super(p);
        x = pApplet.mouseX;
        y = pApplet.mouseY;
        vx = pApplet.mouseX - pastMouseX;
        ax = 2.0f;


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
    }

    @Override
    public void render() {
        pApplet.rect(x, y, 5, 5);
    }


    public void destroy(ArrayList<ArrayList<Circle>> circlesList) {
        ArrayList<Circle> circles = new ArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            circles.add(new Circle(pApplet));
            circles.get(i).x = this.x;
            circles.get(i).y = this.y;
        }
        circlesList.add(circles);

    }


}

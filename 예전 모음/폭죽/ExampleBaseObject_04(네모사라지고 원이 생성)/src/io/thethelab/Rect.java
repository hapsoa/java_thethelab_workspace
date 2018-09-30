package io.thethelab;

import processing.core.PApplet;

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
//        x += vx;
//        vx *= ax;
//        ax *= 0.9f;

    }

    @Override
    public void render() {
        pApplet.rect(x-5, y-5, 5, 5);
    }

    public void destroy() {
        Circle circle1 = new Circle(pApplet);
        Circle circle2 = new Circle(pApplet);
        circle1.x = this.x;
        circle1.y = this.y;
        circle2.x = this.x;
        circle2.y = this.y;

        circle1.render();
        circle2.render();
    }
}

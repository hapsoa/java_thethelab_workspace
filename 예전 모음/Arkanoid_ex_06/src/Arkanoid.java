import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;


public class Arkanoid extends PApplet {

    public static void main(String[] args) {

        Arkanoid.main("Arkanoid");
    }

    private List<View> views = new ArrayList<>();
    boolean left, right;
    int tick;

    public void settings() {
        size(480, 800);
    }

    public void setup() {
        Map map = new Map(this);
        views.add(map);
    }

    public void draw() {
        tick++;
        background(255);

        for (View view : views) {
            view.update();
            view.render();
        }
    }

    public void keyPressed(){
        if(keyCode == LEFT)
            left = true;
        else if(keyCode == RIGHT)
            right = true;
    }

    public void keyReleased(){
        if(keyCode == LEFT)
            left = false;
        if(keyCode == RIGHT)
            right = false;
    }

}

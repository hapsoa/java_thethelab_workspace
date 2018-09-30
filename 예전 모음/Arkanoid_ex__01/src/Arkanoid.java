import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

import static java.awt.event.KeyEvent.VK_UP;

public class Arkanoid extends PApplet {

    public static void main(String[] args) {

        Arkanoid.main("Arkanoid");
    }

    Map map;
    List<View> views = new ArrayList<>();
    int tick;

    public void settings() {
        size(480, 800);
    }

    public void setup() {
        map = new Map(this);
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


}

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Arkanoid3 extends PApplet implements Constants{

    public static void main(String[] args) {
        Arkanoid3.main("Arkanoid3");
    }

    public static KeyEventManager keyEventManager;
    private List<View> views = new ArrayList<>();
    Map map;
    UI ui;
    int tick;

    public void settings(){
        size(WIDTH, HEIGHT);
    }

    public void setup(){
        keyEventManager = new KeyEventManager(this);
        map = new Map(this);
        ui = new UI(this);
        views.add(map);
        views.add(ui);

    }

    public void draw(){

        tick++;
        DataHelper.setTick(tick);

        background(0);
        for (View view : views) {
            view.update();
            view.render();
        }

        keyEventManager.update();
        CollisionManager.update();

    }

    public void keyPressed(){
        keyEventManager.setKeyPress(keyCode);
    }

    public void keyReleased(){
        keyEventManager.setKeyRelease(keyCode);
    }
}

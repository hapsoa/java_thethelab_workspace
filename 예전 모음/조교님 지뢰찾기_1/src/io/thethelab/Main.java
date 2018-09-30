package io.thethelab;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class Main extends PApplet {

    public static void main(String[] args) {
        Main.main("io.thethelab.Main");
    }

    private Map mineMap = new Map(this);
    private int status = 1;



    public void settings() {
        size(900,900);

    }

    public void setup() {
        background(0);


    }

    public void draw() {
        mineMap.render();


    }

    public void keyPressed(KeyEvent event){
        mineMap.click(event.getKey());
    }


}

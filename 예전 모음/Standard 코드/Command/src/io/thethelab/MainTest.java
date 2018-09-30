package io.thethelab;

import processing.core.PApplet;
import processing.event.KeyEvent;

public class MainTest extends PApplet {


    public static void main(String[] args) {
        PApplet.main("io.thethelab.MainTest");
    }

    public void settings() {
        size(600, 600);
    }

    Command command;

    public void setup() {
        command = new Command(this);

        command.addKeyEvent('a', new Command.Actions() {
            @Override
            public void onRelease() {
                System.out.println("A released!");
            }

            @Override
            public void onPress() {
                System.out.println("A pressed!");
            }
        });

        command.addKeyEvent('s', new Command.Actions() {
            @Override
            public void onRelease() {
                System.out.println("S released!");
            }

            @Override
            public void onPress() {
                System.out.println("S pressed!");
            }
        });

        command.addKeyEvent('d', new Command.Actions() {
            @Override
            public void onRelease() {
                System.out.println("D released!");
            }

            @Override
            public void onPress() {
                System.out.println("D pressed!");
            }
        });


    }

    public void keyPressed(KeyEvent event) {

    }

    public void keyReleased(KeyEvent event) {

    }

    public void draw() {
        command.update();
    }

}
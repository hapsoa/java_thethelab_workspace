package io.thethelab;

import processing.core.PApplet;
import processing.event.KeyEvent;


public class MainTest extends PApplet {

    Command command ;
    public static void main(String args[]) {

        PApplet.main("io.thethelab.MainTest");

    }


    public void settings() {
        size(600, 600);
    }

    public void setup() {
        command = new Command(this);


        command.addKeyEvent('a', new Command.Actions() {
            @Override
            public void onRelease() {

            }

            @Override
            public void onPress() {
                System.out.println(" A pressed!");
            }
        });

        command.addKeyEvent('s', new Command.Actions() {
            @Override
            public void onRelease() {

            }

            @Override
            public void onPress() {
                System.out.println(" S pressed!");
            }
        });


        command.addKeyEvent('d', new Command.Actions() {
            @Override
            public void onRelease() {

            }

            @Override
            public void onPress() {
                System.out.println(" D pressed!");
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent event) {

    }

    @Override
    public void keyReleased(KeyEvent event) {

    }



    public void draw() {
        command.update();

    }
}

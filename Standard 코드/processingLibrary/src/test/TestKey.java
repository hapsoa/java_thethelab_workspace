package test;

import Manager.KeyEventManager;
import Manager.View;
import processing.core.PApplet;

public class TestKey extends PApplet {

    public static void main(String[] args) {
        TestKey.main("test.TestKey");
    }

    private Rect rect = new Rect(this, View.RECT_INSIDE);
    private boolean isKeyPressed;
    public static KeyEventManager keyEventManager;


    public void settings() {
        size(500, 500);
    }

    public void setup() {
        keyEventManager = new KeyEventManager(this);
        keyEventManager.setOnPressListener(LEFT, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.x--;

                if (isFirst) {
                    rect.setRed(0);
                }
//                System.out.println(duration);
                else
                    rect.setRed(255);

            }

        });

        keyEventManager.setOnPressListener(38, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.y--;
                if (isFirst) {
                    rect.setRed(0);
                }
//                System.out.println(duration);

            }


        });
        keyEventManager.setOnPressListener(39, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.x++;
            }

        });
        keyEventManager.setOnPressListener(40, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.y++;
            }

        });

        keyEventManager.setOnReleaseListener(40, new KeyEventManager.OnReleaseListener() {

            @Override
            public void onRelease(float duration) {

                rect.setGreen(255);
                rect.setRed(255);
            }

        });


        keyEventManager.setOnPressListener('b', new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                System.out.println("on");
            }
        });

        keyEventManager.setOnReleaseListener('a', new KeyEventManager.OnReleaseListener() {

            @Override
            public void onRelease(float duration) {

                System.out.println("1");
                rect.setGreen(0);
                rect.setRed(255);
            }

        });

    }

    public void draw() {
        background(0);
        rect.update();
        rect.render();

//        if(keyPressed)
//        {
//            keyEventManager.setKeyPress(keyCode);
//            isKeyPressed = true;
//        }
//        else if (isKeyPressed) {
//            keyEventManager.setKeyRelease(keyCode);
//            isKeyPressed = false;
//        }
//
        keyEventManager.update();
    }

    public void keyPressed() {

            keyEventManager.setKeyPress(keyCode);

            keyEventManager.setKeyPress(key);
    }

    public void keyReleased() {

            keyEventManager.setKeyRelease(keyCode);

            keyEventManager.setKeyRelease(key);
    }

}

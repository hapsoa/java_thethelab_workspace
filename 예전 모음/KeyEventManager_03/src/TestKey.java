import processing.core.PApplet;

import java.security.KeyManagementException;
import java.util.List;

public class TestKey extends PApplet {

    public static void main(String[] args) {
        TestKey.main("TestKey");
    }

    private Rect rect = new Rect(this);
    private boolean isKeyPressed;
    public static KeyEventManager keyEventManager;


    public void settings(){
        size(500, 500);
    }

    public void setup(){
        keyEventManager = new KeyEventManager(this);
        keyEventManager.setOnPressListener(37, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.x--;

                if(isFirst){
                    rect.setRed(0);                }
//                System.out.println(duration);
                else
                    rect.setRed(255);

            }

        });

        keyEventManager.setOnPressListener(38, new KeyEventManager.OnPressListener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.y--;
                if(isFirst){
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

                System.out.println("1");
            rect.setGreen(255);
            rect.setRed(255);
            }


        });
    }

    public void draw(){
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

    public void keyPressed(){
        keyEventManager.setKeyPress(keyCode);
    }

    public void keyReleased(){
        keyEventManager.setKeyRelease(keyCode);
    }

}

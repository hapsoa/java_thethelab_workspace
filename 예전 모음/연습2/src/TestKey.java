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
        keyEventManager.setListener(37, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.x--;
                System.out.println(duration);

            }

            @Override
            public void onRelease(float duration) {

            }
        });

        keyEventManager.setListener(38, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.y--;
                System.out.println(duration);

            }

            @Override
            public void onRelease(float duration) {

            }
        });
        keyEventManager.setListener(39, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.x++;
                System.out.println(isFirst+ " , " +  duration);
            }

            @Override
            public void onRelease(float duration) {

            }
        });
        keyEventManager.setListener(40, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                rect.position.y++;
                System.out.println(duration);

            }

            @Override
            public void onRelease(float duration) {

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

        keyEventManager.update();

    }

    public void keyPressed(){
        keyEventManager.setKeyPress(keyCode);
    }

    public void keyReleased(){
        keyEventManager.setKeyRelease(keyCode);
    }

}
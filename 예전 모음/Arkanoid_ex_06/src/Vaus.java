import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Vaus extends View implements Constants {

    private Vector2 vector2;
    private int i;
    private boolean left, right;
    private boolean isKeyPressed;
    private List<PImage> vausImage = SpriteManager.getImages(VAUS);

    Vaus(Arkanoid pApplet) {
        super(pApplet);
        vector2 = new Vector2(WIDTH / 2, HEIGHT - 200);
        vector2.x += 45;
    }

    @Override
    public void standBy() {

    }

    public Vector2 getVaus() {
        return vector2;
    }

    public float getX(){
        return vector2.x;
    }

    @Override
    public void render() {

        if (pApplet.tick % 10 == 0) {
            i = (i + 1) % VAUS_IMAGE_COUNT;
        }
        pApplet.image(vausImage.get(i), vector2.x , vector2.y, VAUS_SIZE_X, VAUS_SIZE_Y);

    }

    @Override
    public void update() {

        if (pApplet.left) {
            vector2.x -= 5;

        }
        if (pApplet.right) {
            vector2.x += 5;

        }
//        if (pApplet.keyPressed) {
//            // 키 누르고 있을 때
//            if (pApplet.keyCode == pApplet.LEFT) {
//                vector2.x -= 2;
//                left = true;
//            } else if (pApplet.keyCode == pApplet.RIGHT) {
//                vector2.x += 2;
//                right = true;
//            }
//
//
//            isKeyPressed = true;
//        } else if (isKeyPressed) {
//            // 키를 뗄 떼
//
//            if (pApplet.keyCode == pApplet.LEFT) {
//
//                left = false;
//            } else if (pApplet.keyCode == pApplet.RIGHT) {
//
//                right = false;
//            }
//
//            isKeyPressed = false;
//        }

//        if (pApplet.keyPressed) {
//            if (pApplet.keyCode == pApplet.LEFT) {
//                vector2.x -= 2;
//            } else if (pApplet.keyCode == pApplet.RIGHT) {
//                vector2.x += 2;
//            }
//        }
    }



}

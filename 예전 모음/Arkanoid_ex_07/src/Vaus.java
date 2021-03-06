import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Vaus extends MovableView implements Constants {

    private int i;
    private List<PImage> vausImage = SpriteManager.getImages(VAUS);
    private boolean extendingState;

    Vaus(Arkanoid pApplet) {
        super(pApplet);
        position.x = WIDTH/2 + 45;
        position.y = HEIGHT - 200;
        size.x = VAUS_SIZE_X;
        size.y = VAUS_SIZE_Y;
        extendingState = false;
    }



    public Vector2 getVaus() {
        return position;
    }

    public float getX(){
        return position.x;
    }

    @Override
    public void render() {

        if (pApplet.tick % 10 == 0) {
            i = (i + 1) % VAUS_IMAGE_COUNT;
        }
        pApplet.image(vausImage.get(i), position.x , position.y, size.x, size.y);

    }

    @Override
    public void update() {

        if (pApplet.left) {
            position.x -= 10;

        }
        if (pApplet.right) {
            position.x += 10;

        }
//
    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Ball) {

            if (CollisionManager.collisionRectCircleOutside(
                    position, view.position, ((Ball) view).getRadius(),
                    (int)size.x, (int)size.y)) {



            }

        }

    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getSize(){
        return size;
    }

    public void extendSizeX(){
        if (!extendingState) {
            size.x *= 1.5f;
            extendingState = true;
        }
    }

    public void setExtendingState(boolean extendingState) {
        this.extendingState = extendingState;
    }
}

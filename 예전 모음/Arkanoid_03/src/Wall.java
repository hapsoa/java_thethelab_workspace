import processing.core.PImage;

import java.util.List;

public class Wall extends View implements Constants {


    List<PImage> wallImages = SpriteManager.getImages(BACKGROUND_IMAGES);

    Wall(Arkanoid pApplet) {
        super(pApplet, CollisionManager.Shape.RECT_INSIDE);
        position = new Vector2(WALL_LEFT, 0);
        size = new Vector2(WALL_RIGHT, HEIGHT);

        CollisionManager.allocate(3, this);
    }

    @Override
    public void render() {
        pApplet.image(wallImages.get(0), position.x, position.y, size.x, size.y);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {

    }


    public float getY(){
        return position.y;
    }
}

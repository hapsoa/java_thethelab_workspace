import processing.core.PImage;

import java.util.List;

public class Wall extends View implements Constants {

    private Vector2 wall;

    List<PImage> wallImages = SpriteManager.getImages(BACKGROUND_IMAGES);

    Wall(Arkanoid pApplet) {
        super(pApplet);
        wall = new Vector2(WALL_LEFT, 0);
    }

    @Override
    public void render() {
        pApplet.image(wallImages.get(0), wall.x, wall.y, WALL_RIGHT, HEIGHT);
    }

    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {

    }

    public Vector2 getWall() {
        return wall;
    }

    public float getY(){
        return wall.y;
    }
}

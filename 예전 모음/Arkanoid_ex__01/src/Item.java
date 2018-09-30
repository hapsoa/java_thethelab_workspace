import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class Item extends View implements Constants {

    private float x, y;
    private int item;
    private Vector2 vector2;
    private List<PImage> images;
    private int i;

    Item(Arkanoid pApplet, int item, float x, float y) {
        super(pApplet);
        vector2 = new Vector2(x, y);
        this.item = item;
        loadItem();
    }

    public void loadItem() {
        images = SpriteManager.getImages(item);
    }

    @Override
    public void standBy() {

    }

    @Override
    public void render() {

        pApplet.fill(20, 55, 200);
        if (pApplet.tick % 20 == 0) {
            i = (i + 1 )% ITEM_SPRITE_COUNT;
        }
        pApplet.image(images.get(i), vector2.x+45, vector2.y, ITEM_SIZE_X, ITEM_SIZE_Y);

    }

    @Override
    public void update() {
        vector2.y += 3;
    }
}

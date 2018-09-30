import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Block extends View implements Constants {

    private boolean visible;
    private int item;
    private int index;
    private int color;
    private int life;
    private boolean itemState = false;
    Vector2 v;
    private List<PImage> blockimages;
    private List<Item> items;


    Block(Arkanoid pApplet) {
        super(pApplet);
        this.visible = VISIBLE;
        blockimages = SpriteManager.getImages(BLOCK_IMAGES);
        if(items != null) items = pApplet.map.items;
    }

    public void setIndex(int index, int count) {
        this.index = index;
        v = new Vector2(Util.getPosXByIndex(index, count), Util.getPosYByIndex(index, count));
        v.x += 45;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setColor(int color) {
        this.color = color;
    }


    public boolean getItemState() {
        return itemState;
    }

    public void setItemState(boolean itemState) {
        this.itemState = itemState;
    }

    public void makeItem(List<Item> items) {


        if (item > 0) {
            Item I = new Item(pApplet, item, v.x, v.y);
            items.add(I);
            CollisionManager.allocate(I, 5);
        }
    }

    @Override
    public void render() {
        if (color == 0)
            visible = INVISIBLE;
        if (visible) {


            if (color == BLOCK_WHITE)
                pApplet.image(blockimages.get(0), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);
            else if (color == BLOCK_ORANGE)
                pApplet.image(blockimages.get(1), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);
            else if (color == BLOCK_SKYBLUE)
                pApplet.image(blockimages.get(2), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);

            else if (color == BLOCK_GREEN)
                pApplet.image(blockimages.get(3), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);

            else if (color == BLOCK_RED)
                pApplet.image(blockimages.get(4), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);

            else if (color == BLOCK_BLUE)
                pApplet.image(blockimages.get(5), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);

            else if (color == BLOCK_PINK)
                pApplet.image(blockimages.get(6), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);

            else if (color == BLOCK_YELLOW)
                pApplet.image(blockimages.get(7), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);
            else if (color == BLOCK_HARD)
                pApplet.image(blockimages.get(8), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);
            else if (color == BLOCK_IMMORTAL)
                pApplet.image(blockimages.get(9), v.x, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);


        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public Vector2 getV() {
        return v;
    }

    public float getY() {
        return v.y;
    }

    public float getX() {
        return v.x;
    }

//    public boolean collision(int mx, int my, int r){
//        if(mx > v.x - r && mx < v.x + BLOCK_SIZE_X + r
//                && my > v.y - r && my < v.y + BLOCK_SIZE_Y + r){
//            if((Math.abs((BLOCK_SIZE_X/2 + v.x) - mx) > BLOCK_SIZE_X / 2)
//                    && (Math.abs((v.y + BLOCK_SIZE_Y / 2) - my) > BLOCK_SIZE_Y / 2)){
//                if((mx - v.x) * (mx - v.x) + (my - v.y) * (my - v.y) < (r * r)
//                        || (mx - v.x) * (mx - v.x) + (my - (v.y + BLOCK_SIZE_Y)) * (my - (v.y + BLOCK_SIZE_Y)) < (r * r)
//                        || (mx - (v.x + BLOCK_SIZE_X)) * (mx - (v.x + BLOCK_SIZE_X)) + (my - v.y) * (my - v.y) < (r * r)
//                        || (mx - (v.x + BLOCK_SIZE_X)) * (mx - (v.x + BLOCK_SIZE_X))
//                        + (my - (v.y + BLOCK_SIZE_Y)) * (my - (v.y + BLOCK_SIZE_Y)) < (r * r)){
//                    return true;
//                }else
//                    return false;
//            }
//            else
//                return true;
//        }
//        return false;
//    }


    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Ball) {

            if (CollisionManager.collisionRectCircleOutside(
                    getV(), ((Ball)view).getPosition(),
                    ((Ball)view).getRadius() / 2, BLOCK_SIZE_X, BLOCK_SIZE_Y)) {
                if (getVisible()) {
                    setVisible(INVISIBLE);
                }
                if (!getItemState()) {
                    setItemState(true);
                    makeItem(items);
                }
            }

        }

    }


}

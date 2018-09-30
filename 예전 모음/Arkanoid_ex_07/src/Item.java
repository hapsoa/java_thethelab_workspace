import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class Item extends MovableView implements Constants {

    private int item;
    private List<PImage> images;
    private int i;
    private boolean visible;
    private int time;


    Item(Arkanoid pApplet, int item, float x, float y) {
        super(pApplet);
        position.x = x;
        position.y = y;
        size.x = ITEM_SIZE_X;
        size.y = ITEM_SIZE_Y;
        visible = true;
        this.item = item;
        loadItem();
    }

    public int getItem(){
        return item;
    }

    public void loadItem() {
        images = SpriteManager.getImages(item);
    }

    @Override
    public void render() {

        if(visible) {
            pApplet.fill(20, 55, 200);
            if (pApplet.tick % 10 == 0) {
                i = (i + 1) % ITEM_SPRITE_COUNT;
            }
            pApplet.image(images.get(i), position.x, position.y, size.x, size.y);

        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public Vector2 getSize(){
        return size;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void update() {
//        time++;
//        if (time > 900) {
//            deActivateItem(map);
//        }
        position.y += 3;
    }

    @Override
    public void onCollision(View view) {

        if (view instanceof Vaus) {

            if (CollisionManager.collisionRectRectOutSide(
                    getPosition(), ((Vaus)view).getPosition(),
                    getSize(), ((Vaus)view).getSize()
            )) { // 아이템이랑 바우스 만남~
                position.add(-getPosition().x, -getPosition().y);

                setVisible(false);
                activateItem(pApplet.map);
            }

        }

    }

    public int getTime(){
        return time;
    }

    public void activateItem(Map map){
        time = 0;


        if(item == ITEM_PLAYER){

        }
        else if(item == ITEM_LASER){

        }
        else if(item == ITEM_EXTEND){
            map.getVaus().extendSizeX();
        }
        else if(item == ITEM_CLASP){

        }
        else if(item == ITEM_SLOW){

        }
        else if(item == ITEM_BONUS){

        }
        else if(item == ITEM_DOOM){

        }

    }

}

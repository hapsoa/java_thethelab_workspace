import processing.core.PApplet;

import java.util.List;

public class Block extends View implements Constants{

    private boolean visible;
    private int item;
    private int index;
    private int color;
    private int life;
    private boolean itemState = false;
    private float x, y;
    Vector2 v;

    Block(Arkanoid pApplet) {
        super(pApplet);
        this.visible = VISIBLE;

    }

    public void setIndex(int index, int count){
        this.index = index;
        v = new Vector2(Util.getPosXByIndex(index,count),Util.getPosYByIndex(index, count) );

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

    @Override
    public void standBy() {

    }

    public boolean getItemState() {
        return itemState;
    }

    public void setItemState(boolean itemState) {
        this.itemState = itemState;
    }

    public void makeItem(List<Item> items){

        if(item == ITEM_PLAYER){
            items.add(new Item(pApplet, item, v.x,v.y));
        }
        else if(item == ITEM_LASER){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
        else if(item == ITEM_EXTEND){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
        else if(item == ITEM_CLASP){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
        else if(item == ITEM_SLOW){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
        else if(item == ITEM_BONUS){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
        else if(item == ITEM_DOOM){
            items.add(new Item(pApplet, item, v.x,v.y));

        }
    }

    @Override
    public void render() {
        if(color == 0)
            visible = INVISIBLE;
        if(visible) {

            if (color == BLOCK_WHITE)
                pApplet.fill(153, 153, 153);
            else if (color == BLOCK_RED)
                pApplet.fill(255, 153, 0);
            else if (color == BLOCK_YELLOW)
                pApplet.fill(0, 153, 0);
            else if(color == BLOCK_IMMORTAL)
                pApplet.fill(255, 255,0);
            else if(color == 20)
                pApplet.fill(150, 150, 150);

            pApplet.rect(v.x + 45, v.y, BLOCK_SIZE_X, BLOCK_SIZE_Y);
        }
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean collision(int mx, int my, int r){
        if(mx > v.x - r && mx < v.x + BLOCK_SIZE_X + r
                && my > v.y - r && my < v.y + BLOCK_SIZE_Y + r){
            if((Math.abs((BLOCK_SIZE_X/2 + v.x) - mx) > BLOCK_SIZE_X / 2)
                    && (Math.abs((v.y + BLOCK_SIZE_Y / 2) - my) > BLOCK_SIZE_Y / 2)){
                if((mx - v.x) * (mx - v.x) + (my - v.y) * (my - v.y) < (r * r)
                        || (mx - v.x) * (mx - v.x) + (my - (v.y + BLOCK_SIZE_Y)) * (my - (v.y + BLOCK_SIZE_Y)) < (r * r)
                        || (mx - (v.x + BLOCK_SIZE_X)) * (mx - (v.x + BLOCK_SIZE_X)) + (my - v.y) * (my - v.y) < (r * r)
                        || (mx - (v.x + BLOCK_SIZE_X)) * (mx - (v.x + BLOCK_SIZE_X))
                        + (my - (v.y + BLOCK_SIZE_Y)) * (my - (v.y + BLOCK_SIZE_Y)) < (r * r)){
                    return true;
                }else
                    return false;
            }
            else
                return true;
        }
        return false;
    }


    @Override
    public void update() {

    }


}

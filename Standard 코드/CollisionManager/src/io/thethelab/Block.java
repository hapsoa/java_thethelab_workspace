package io.thethelab;

import processing.core.PImage;

import java.util.List;

public class Block extends View implements Constants {

    private boolean visible;
    private int item;
    private int index;
    private int color;
    private int life;
    private boolean itemState = false;


    Block(Window pApplet) {
        super(pApplet, RECT_OUTSIDE);
        this.visible = VISIBLE;
        size.x = 30;
        size.y = 15;

        CollisionManager.allocate(2, this);
    }

    Block(Window pApplet, float posX, float posY,
          float sizeX, float sizeY) {
        super(pApplet, RECT_OUTSIDE, posX, posY, sizeX, sizeY);
        this.visible = VISIBLE;

        CollisionManager.allocate(2, this);
    }

    public void setIndex(int index, int horizonLength) {
        this.index = index;
        position = new Vector2(Util.getPosXByIndex(index, horizonLength), Util.getPosYByIndex(index, horizonLength));

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


    @Override
    public void render() {

        if (visible) {
            pApplet.fill(0, 255, 0);
            pApplet.rect(position.x, position.y, size.x, size.y);
        }

    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean getVisible() {
        return visible;
    }

    public Vector2 getV() {
        return position;
    }

    public float getY() {
        return position.y;
    }

    public float getX() {
        return position.x;
    }


    @Override
    public void update() {

    }

    @Override
    public void onCollision(View view) {
        if (view instanceof Ball) {
            System.out.println("Block is collided by ball");

            if (visible) {
                setVisible(INVISIBLE);
            }

        }

        if (view instanceof Rect) {

            System.out.println("Block is collided by rect");

            if (visible) {
                setVisible(INVISIBLE);
            }

        }
    }


}

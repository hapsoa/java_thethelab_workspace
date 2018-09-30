package io.thethelab;

import processing.core.PApplet;

abstract public class View extends PApplet {

    protected PApplet pApplet;
    protected int x, y;
    protected int width, height;
    protected OnClickListener onClickListener;

    public View(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private boolean isCollision(int x, int y) {
        return (x > this.x && x < this.x + width &&
                y > this.y && y < this.y + height);
    }

    abstract public void render();


    public interface OnClickListener {
        void onClick();
    }



    public void setOnClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    public void mouseClicked(int x, int y) {
        if (isCollision(x, y)) {
            if (onClickListener != null) onClickListener.onClick();

            System.out.println("Click!");
        }
    }




}

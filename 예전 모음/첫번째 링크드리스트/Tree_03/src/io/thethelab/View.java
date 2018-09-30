package io.thethelab;

import processing.core.PApplet;

abstract public class View {
    PApplet pApplet;
    int x, y;
    int width, height;

    View(PApplet pApplet, int x, int y, int width, int height) {
        this.pApplet = pApplet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    abstract public void render();

    public void mouseClicked() {
        if (isCollision(pApplet.mouseX, pApplet.mouseY)) {
            if (onClickListener != null) onClickListener.onClick();

            System.out.println("Click!");
        }
    }

    public boolean isCollision(int mouseX, int mouseY) {
        if (mouseX > x && mouseX < x + width &&
                mouseY > y && mouseY < y + height)
            return true;
        else
            return false;
    }

    interface OnClickListener {
        void onClick();
    }
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }


}

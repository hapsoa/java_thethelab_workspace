package io.thethelab;

import processing.core.PApplet;

public class Button extends View {


    public Button(PApplet pApplet, int x, int y, int width, int height) {
        super(pApplet, x, y, width, height);
    }

    public interface OnClickListener {
        void onClick();
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener clickListener) {
        this.onClickListener = clickListener;
    }

    @Override
    public void clicked() {
        if (onClickListener != null) onClickListener.onClick();

        System.out.println("Click!");
    }

    @Override
    public void render() {
        pApplet.fill(255, 70, 255);
        pApplet.rect(x, y, width, height);
    }


}

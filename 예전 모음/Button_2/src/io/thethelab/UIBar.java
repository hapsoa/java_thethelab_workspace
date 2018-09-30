package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;

public class UIBar extends View {

    public UIBar(PApplet pApplet) {
        super(pApplet, 0, 0, Constant.WIDTH, Constant.BAR_HEIGHT);
    }

    private ArrayList<View> buttons = new ArrayList<>();

    public void addButton(Button.OnClickListener onClickListener) {
        Button btn = new Button(pApplet, 10 + 40 * buttons.size(), 10,
                30, 30);
        btn.setOnClickListener(onClickListener);

        buttons.add(btn);
    }

    @Override
    public void render() {
        pApplet.fill(255, 255, 255);
        pApplet.rect(x, y, width, height);

        for (View view : buttons) {
            view.render();
        }
    }

    public void mouseClicked(int x, int y) {
        for (View view : buttons) {
            view.mouseClicked(x, y);
        }
    }


}

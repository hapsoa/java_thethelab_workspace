package io.thethelab;

public class UIBar extends View {

    private int width, height;

    public UIBar(Window pApplet) {
        super(pApplet);
        width = Constants.X_COUNT*Constants.X_COUNT;
        height = 50;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        pApplet.fill(255);
        pApplet.rect(0, 0, width, height);
    }
}

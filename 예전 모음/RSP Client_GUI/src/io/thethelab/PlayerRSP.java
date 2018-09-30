package io.thethelab;

import processing.core.PApplet;

public class PlayerRSP extends View {

    private int rsp;

    PlayerRSP(PApplet pApplet) {
        super(pApplet, SHAPE_NONE);
        position.x = 200;
        position.y = 200;
        size.x = 200;
        size.y = 200;
        rsp = Constants.RSP_NONE;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {
        switch (rsp) {
            case Constants.ROCK:
                pApplet.image(SpriteManager.getImage(Constants.RSP_IMAGES, 2),
                        position.x - size.x / 2, position.y - size.y / 2,
                        size.x, size.y);
                break;
            case Constants.SCISSORS:
                pApplet.image(SpriteManager.getImage(Constants.RSP_IMAGES, 0),
                        position.x - size.x / 2, position.y - size.y / 2,
                        size.x, size.y);
                break;
            case Constants.PAPER:
                pApplet.image(SpriteManager.getImage(Constants.RSP_IMAGES, 4),
                        position.x - size.x / 2, position.y - size.y / 2,
                        size.x, size.y);
                break;
            default:
                break;

        }


    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isMouseCollision(float mouseX, float mouseY) {
        return false;
    }
}

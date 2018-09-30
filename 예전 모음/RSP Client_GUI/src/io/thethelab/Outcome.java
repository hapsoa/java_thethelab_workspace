package io.thethelab;

import processing.core.PApplet;

public class Outcome extends View {

    private int outcome;

    Outcome(PApplet pApplet) {
        super(pApplet, SHAPE_NONE);
        position.x = 400;
        position.y = 350;
        size.x = 300;
        size.y = 100;
        outcome = Constants.OUTCOME_NONE;
    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        switch (outcome) {
            case Constants.WIN :
                pApplet.image(SpriteManager.getImage(Constants.OUTCOME_IMAGES, 0),
                        position.x-size.x/2, position.y-size.y/2,
                        size.x, size.y);
                break;
            case Constants.LOSE :
                pApplet.image(SpriteManager.getImage(Constants.OUTCOME_IMAGES, 1),
                        position.x-size.x/2, position.y-size.y/2,
                        size.x, size.y);
                break;
            case Constants.DRAW :
                pApplet.image(SpriteManager.getImage(Constants.OUTCOME_IMAGES, 2),
                        position.x-size.x/2, position.y-size.y/2,
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

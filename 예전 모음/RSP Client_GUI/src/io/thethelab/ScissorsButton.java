package io.thethelab;

import processing.core.PApplet;

public class ScissorsButton extends View {
    ScissorsButton(PApplet pApplet) {
        super(pApplet, SHAPE_NONE);
        position.x =140;
        position.y =530;
        size.x =50;
        size.y =50;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick() {
                System.out.println("scissors clicked");
            }
        });

    }

    @Override
    public void onUpdate() {

    }

    @Override
    public void render() {

        pApplet.image(SpriteManager.getImage(Constants.RSP_IMAGE, 2),
                position.x - size.x/2, position.y - size.y/2,
                size.x, size.y);

    }

    @Override
    public void onCollision(View view) {

    }

    @Override
    public boolean isMouseCollision(float mouseX, float mouseY) {
        if (Math.abs(mouseX - position.x) < size.x/2 &&
                Math.abs(mouseY - position.y) < size.y/2) {
            return true;
        }
        else
            return false;

    }
}

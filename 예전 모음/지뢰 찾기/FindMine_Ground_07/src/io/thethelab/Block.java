package io.thethelab;

public class Block extends View {
    private int x, y;
    int property;

    Block(Window pApplet, int x, int y, int property) {
        super(pApplet);
        this.x = x;
        this.y = y;
        this.property = property;
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        pApplet.fill(150);
        pApplet.rect(x - Constant.BLOCK_SIZE/2, y - Constant.BLOCK_SIZE/2,
                Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);

        if (property == Constant.BOMB) {
            pApplet.image(Window.bombImage, x-6, y-6);
        }
        else if (property == Constant.HIDDEN) {
            pApplet.fill(100);
            pApplet.rect(x - Constant.BLOCK_SIZE/2, y - Constant.BLOCK_SIZE/2,
                    Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
        }
        else if (property == Constant.FLAG) {
            pApplet.fill(100);
            pApplet.rect(x - Constant.BLOCK_SIZE/2, y - Constant.BLOCK_SIZE/2,
                    Constant.BLOCK_SIZE, Constant.BLOCK_SIZE);
            pApplet.image(Window.flagImage, x-6, y-6);
        }
        else if (property == Constant.NO_BOMB) {

        }
        else {
            pApplet.fill(255);
            pApplet.text(property, x, y);
        }
    }

    boolean isCollided(int mouseX, int mouseY) {
        return Math.abs(x-mouseX) < Constant.BLOCK_SIZE/2 &&
                Math.abs(y-mouseY) < Constant.BLOCK_SIZE/2;
    }


}

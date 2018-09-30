package io.thethelab;

public class SaveButton extends View implements ButtonUtil {



    public SaveButton(Window pApplet) {
        super(pApplet);
    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        return false;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        if (isCollided(pApplet.mouseX, pApplet.mouseY)) {
            // 갖다대기 효과
        }
        pApplet.image(ResourceManagers.loadImage(ResourceManagers.SAVE_BUTTON),
                150,10,32, 32);
    }
}

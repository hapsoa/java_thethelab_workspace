package io.thethelab;

public class Smile extends View implements ButtonUtil {

    int x, y;

    public Smile(Window pApplet) {
        super(pApplet);
        x = Constants.X_COUNT*Constants.BLOCK_SIZE/2;
        y = Constants.UI_BAR_HEIGHT/2;
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {
        // 재시작한다
        if (isCollided(pApplet.mouseX, pApplet.mouseY)) {
            pApplet.mineMap.reset();
        }
    }

    @Override
    public void render() {
        // 스마일을 그린다
        pApplet.image(ResourceManagers.loadImage(ResourceManagers.SMILE),
                x - 16, y - 16);

        // 인터랙션시 o_o 죽으면 x_x 완료하면 ㅁ_ㅁ
        if (pApplet.isPressed && pApplet.key == 'd') { // 인터랙션시 o_o
            pApplet.image(ResourceManagers.loadImage(ResourceManagers.SMILE_WONDER),
                    x - 16, y - 16);
        }

        // 죽으면 x_x
        if (pApplet.isFailed) { // 인터랙션시 o_o
            pApplet.image(ResourceManagers.loadImage(ResourceManagers.SMILE_DEAD),
                    x - 16, y - 16);
        }

        // 완료하면 ㅁ_ㅁ
        if (pApplet.isSuccess) {
            pApplet.image(ResourceManagers.loadImage(ResourceManagers.SMILE_SUNGLASS),
                    x - 16, y - 16);
        }

    }

    @Override
    public boolean isCollided(int mouseX, int mouseY) {
        if (Math.sqrt(Math.pow(mouseX-x,2) + Math.pow(mouseY-y,2)) < 16)
            return true;
        else
            return false;
    }
}

package io.thethelab;


class UIBar extends View {

    private int smile_x = Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE / 2 - 15;
    private int smile_y = Constants.UI_HEIGHT / 2 - 15;

    UIBar(Window pApplet) {
        super(pApplet);

    }

    @Override
    void update() {
        //평소에
        if (!pApplet.mineMap.isFailed) {
            //^_^
            Constants.SMILE = ResourceManager.loadImage(ResourceManager.SMILE_HAPPY);

            if(pApplet.mineMap.isSuccess) { // 성공했을 때, 선글라스
                Constants.SMILE =
                        ResourceManager.loadImage(ResourceManager.SMILE_SUNGLASS);
            }

        } else { // 죽으면 X_X
            Constants.SMILE = ResourceManager.loadImage(ResourceManager.SMILE_DEAD);
        }
    }

    @Override
    void render() {


        pApplet.image(Constants.SMILE, smile_x, smile_y);
    }

    @Override
    void standBy() {
        if (!pApplet.mineMap.isFailed) {
            if ((pApplet.key == 'd' || pApplet.key == 'r')) {
                // O_O
                Constants.SMILE = ResourceManager.loadImage(ResourceManager.SMILE_WONDER);
            }
        }
    }
}

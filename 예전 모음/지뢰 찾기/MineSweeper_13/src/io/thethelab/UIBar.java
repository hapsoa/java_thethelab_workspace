package io.thethelab;


import processing.core.PImage;

class UIBar extends View {

    private int smile_x = Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE / 2 - 15;
    private int smile_y = Constants.UI_HEIGHT / 2 - 15;

    PImage mineTenNumber;
    PImage mineOneNumber;

    UIBar(Window pApplet) {
        super(pApplet);
        updateMineLeftView();
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

        updateMineLeftView();


    }

    @Override
    void render() {
        pApplet.image(Constants.SMILE, smile_x, smile_y);

        // 남은 폭탄 수
        pApplet.image(mineTenNumber, 5, 15);
        pApplet.image(mineOneNumber, 30, 15);

        // 지난 시간

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

    private void updateMineLeftView() {
        int numOfFlag = 0;
        for (Block block : pApplet.mineMap.blocks) {
            if (block.isFlag)
                numOfFlag++;
        }

        if (Constants.MINE_COUNT-numOfFlag >= 0) {
            mineTenNumber = ResourceManager.loadImage(
                    (Constants.MINE_COUNT - numOfFlag) / 10);
            mineOneNumber = ResourceManager.loadImage(
                    (Constants.MINE_COUNT - numOfFlag) % 10);
        }
    }
}

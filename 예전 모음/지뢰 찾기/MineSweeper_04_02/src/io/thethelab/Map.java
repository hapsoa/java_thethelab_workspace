package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {
    List<Block> blocks = new ArrayList<>();
    int[] mine = new int[Constants.MINE_COUNT];
    boolean isFailed;
    boolean isSuccess;
    int numOfRightFlag;

    public Map(PApplet pApplet) {
        super(pApplet);
        for (int i = 0; i < Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y;
             i++) {
            blocks.add(new Block(pApplet, false, i));
        }

        shuffleMineIndex();

        for (Block block : blocks) {
            block.makeConnection(blocks);
        }

        isFailed = false;
        isSuccess = false;
        numOfRightFlag = 0;

    }

    private void shuffleMineIndex() {
        int indexSize = Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y;
        int[] mineIndexes = new int[Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y];
        int src;
        int dst;
        int temp;

        for (int i = 0; i < indexSize; i++) {
            mineIndexes[i] = i;
        }

        for (int i = 0; i < indexSize * 3; i++) {
            src = (int) (Math.random() * indexSize);
            dst = (int) (Math.random() * indexSize);

            temp = mineIndexes[src];
            mineIndexes[src] = mineIndexes[dst];
            mineIndexes[dst] = temp;
        }

        for (int i = 0; i < Constants.MINE_COUNT; i++) {
            blocks.get(mineIndexes[i]).setMine();
        }

    }


    @Override
    void update() {

        int index = Utils.getIndexByPos(pApplet.mouseX, pApplet.mouseY);

        if (pApplet.key == 'a') {
            blocks.get(index).visible = true;

            if (blocks.get(index).property == Constants.MINE)
                isFailed = true;

            if (blocks.get(index).property == Constants.N0_MINE) { // N0_MINE 이면
                //주위가 다 열린다.
                openAround(blocks.get(index));

            }
        }

        if (pApplet.key == 's') {

            // 폭탄 수와 주변 깃발 수가 같을 때
            if (blocks.get(index).visible &&
                    blocks.get(index).neighborMineCount ==
                            blocks.get(index).getNumOfFlagAround()) {

                if (blocks.get(index).isFlagAndMineSame()) { // ㅈㅣ뢰와 깃발이 올바르면
                    //주위가 다 열린다.
                    openAround(blocks.get(index));
                } else {
                    // 올바르지 않으면 죽는다.

                    isFailed = true;
                }


            } else { // 폭탄 수와 깃발 수가 다를 때, 빛난다
                for (Block block : blocks.get(index).aroundBlocks) {
                    if (!block.visible && !block.isFlag) {
                        pApplet.fill(200);
                        pApplet.rect(block.x, block.y,
                                Constants.BLOCK_SIZE, Constants.BLOCK_SIZE);
                    }
                }
            }

        }

        if (pApplet.key == 'd' && !blocks.get(index).visible) {
            if (blocks.get(index).isFlag) {
                blocks.get(index).isFlag = false;

                if (blocks.get(index).property == Constants.MINE)
                    numOfRightFlag--;
            }
            else {
                blocks.get(index).isFlag = true;

                if (blocks.get(index).property == Constants.MINE)
                    numOfRightFlag++;
            }

        }

        if (Constants.MINE_COUNT == numOfRightFlag) {
            isSuccess = true;
        }

    }

    private void openAround(Block oneBlock) {
        for (Block block : oneBlock.aroundBlocks) {
            if (!block.visible && !block.isFlag) {
                block.visible = true;

                // 0인 곳은 자동으로 다 열어준다.
                if (block.property == Constants.N0_MINE)
                    openAround(block);
            }

        }
    }

    @Override
    void render() {
        for (Block block : blocks) {
            block.render();
        }
        if (isFailed) {
            for (Block block : blocks)
                block.visible = true;

            pApplet.fill(0);
            pApplet.text("Failed",
                    (Constants.BLOCK_COUNT_X*Constants.BLOCK_SIZE)/2,
                    (Constants.BLOCK_COUNT_Y*Constants.BLOCK_SIZE)/2);
        }
        if (isSuccess) {
            for (Block block : blocks)
                block.visible = true;

            pApplet.fill(0);
            pApplet.text("Success!",
                    (Constants.BLOCK_COUNT_X*Constants.BLOCK_SIZE)/2,
                    (Constants.BLOCK_COUNT_Y*Constants.BLOCK_SIZE)/2);
        }
    }

    @Override
    void standBy() {

    }
}

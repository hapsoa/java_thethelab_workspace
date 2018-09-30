package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {
    List<Block> blocks = new ArrayList<>();
    int[] mine = new int[Constants.MINE_COUNT];

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
            src = (int)(Math.random() * indexSize);
            dst = (int)(Math.random() * indexSize);

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
        }

        if (pApplet.key == 's') {

//            if(blocks.get(index).neighborMineCount ==
//                    blocks.get(index).numOfFlagAround) {
//
//            } else { // 폭탄 수와 깃발 수가 다를 때
//
//            }

        }

        if (pApplet.key == 'd' && !blocks.get(index).visible) {
            if (blocks.get(index).isFlag)
                blocks.get(index).isFlag = false;
            else
                blocks.get(index).isFlag = true;
        }

    }



    @Override
    void render() {
        for (Block block : blocks) {
            block.render();
        }
    }
}

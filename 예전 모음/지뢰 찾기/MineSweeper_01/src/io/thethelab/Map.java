package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {
    List<Block> blocks = new ArrayList<>();
    int[] mine = new int[Constants.MINE_COUNT];

    public Map(PApplet pApplet) {
        super(pApplet);
        for (int i = 0; i < (Constants.BLOCK_COUNT_X+2) * (Constants.BLOCK_COUNT_Y+2);
             i++) {
            blocks.add(new Block(pApplet, false, i));
        }

        shuffleMineIndex();

    }

    private void shuffleMineIndex() {
        int indexSize = Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y;
        int[] mineIndexes = new int[Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y];
        int src = (int)(Math.random() * indexSize);
        int dst = (int)(Math.random() * indexSize);
        int temp;

        for (int i = 0; i < indexSize; i++) {
            mineIndexes[i] = i;
        }

        for (int i = 0; i < indexSize * 3; i++) {
            temp = mineIndexes[src];
            mineIndexes[src] = mineIndexes[dst];
            mineIndexes[dst] = temp;
        }

        int i = Constants.BLOCK_COUNT_X+2;
        while (i < Constants.MINE_COUNT) {
            if (!Utils.isLeft(mineIndexes[i]) || !Utils.isRight(mineIndexes[i])) {
                blocks.get(mineIndexes[i]).setMine();
                i++;
            }
        }

    }


    @Override
    void update() {

    }

    @Override
    void render() {
        for (Block block : blocks) {
            block.render();
        }
    }
}

package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Block extends View {

    int x, y;
    int index;
    int property;
    boolean isHidden;
    boolean isFlag;

    List<Block> aroundBlocks = new ArrayList<>();

    public Block(Window pApplet, int index) {
        super(pApplet);

        this.index = index;
        x = Utils.getPosXByIndex(index);
        y = Utils.getPosYByIndex(index);
        property = 0;
        isHidden = true;
    }

    void makeConnection(List<Block> blocks) {
        aroundBlocks.clear();

        if (!Utils.isLeft(index) && !Utils.isUp(index))
            aroundBlocks.add(blocks.get(index - 1 - Constants.X_COUNT));
        if (!Utils.isUp(index))
            aroundBlocks.add(blocks.get(index - Constants.X_COUNT));
        if (!Utils.isUp(index) && !Utils.isRight(index))
            aroundBlocks.add(blocks.get(index - Constants.X_COUNT + 1));
        if (!Utils.isLeft(index))
            aroundBlocks.add(blocks.get(index - 1));
        if (!Utils.isRight(index))
            aroundBlocks.add(blocks.get(index + 1));
        if (!Utils.isLeft(index) && !Utils.isDown(index))
            aroundBlocks.add(blocks.get(index - 1 + Constants.X_COUNT));
        if (!Utils.isDown(index))
            aroundBlocks.add(blocks.get(index + Constants.X_COUNT));
        if (!Utils.isRight(index) && !Utils.isDown(index))
            aroundBlocks.add(blocks.get(index + 1 + Constants.X_COUNT));

        // 주위 폭탄수 계산
        if (property != Constants.NO_BOMB) {
            property = 0;
            for (Block block : aroundBlocks) {
                if (block.property == Constants.BOMB)
                    property++;
            }
        }
    }

    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        if (isHidden) {
            pApplet.fill(180);
            pApplet.rect(x, y, Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
        } else {
            pApplet.fill(120);
            pApplet.rect(x, y, Constants.BLOCK_SIZE,Constants.BLOCK_SIZE);
            pApplet.fill(255);
//            pApplet.text
        }

    }





}

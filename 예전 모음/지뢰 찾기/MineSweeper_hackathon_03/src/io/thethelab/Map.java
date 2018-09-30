package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    List<Block> blocks = new ArrayList<>();

    public Map(Window pApplet) {
        super(pApplet);

        for (int i = 0; i < Constants.X_COUNT * Constants.Y_COUNT; i++) {
            blocks.add(new Block(pApplet, i));
        }

        shuffle();

        for (Block block : blocks) {
            block.makeConnection(blocks);
        }

    }

    private void shuffle() {
        int max = Constants.X_COUNT * Constants.Y_COUNT;
        int[] mine = new int[max];

        int src;
        int dst;
        int temp;

        for (int i = 0; i < mine.length; i++) {
            mine[i] = i;
        }

        // 지뢰 index 를 섞는다
        for (int i = 0; i < max * 3; i++) {
            src = (int) (Math.random() * max);
            dst = (int) (Math.random() * max);

            temp = mine[src];
            mine[src] = mine[dst];
            mine[dst] = temp;
        }

        // 지뢰 수만큼 집어넣는다
        for (int i = 0; i < pApplet.numOfMine; i++) {
            blocks.get(mine[i]).property = Constants.BOMB;
        }
    }

    @Override
    public void standBy() {
        int idx = Utils.getIndexByPos(pApplet.mouseX, pApplet.mouseY);

        if (pApplet.key == 'd') {
            blocks.get(idx).isLighter = true;
        }


    }

    @Override
    public void update() {
        int idx = Utils.getIndexByPos(pApplet.mouseX, pApplet.mouseY);

        if (pApplet.key == 'w') {
            blocks.get(idx).isHidden = false;

            // 블록 열었을 때 0이면
            if (blocks.get(idx).property == Constants.NO_BOMB) {
                openAround(blocks.get(idx));
            }

            // 폭탄 클릭시 패배
            if (blocks.get(idx).property == Constants.BOMB)
                pApplet.isFailed = true;

        }

        if (pApplet.key == 'd') {
            checkAround(idx);
        }

        if (pApplet.key == 'r') {
            if (!blocks.get(idx).isFlag)
                blocks.get(idx).isFlag = true;
            else
                blocks.get(idx).isFlag = false;
        }
    }

    @Override
    public void render() {

        // 패배하지 않았을 시
        if (pApplet.isFailed) {
            for (Block block : blocks) {
                block.isHidden = false;
            }
        }
        for (Block block : blocks) {
            block.render();
        }
    }

    private void checkAround(int idx) {
        // 폭탄 수와 깃발 수가 같으면
        if (!blocks.get(idx).isHidden &&
                blocks.get(idx).property == blocks.get(idx).getNumOfAroundFlag()) {
            // 폭탄에 깃발로 잘 꽂으면
            if (blocks.get(idx).isMineAndFlagTrue()) {
                // 주변 연다
                openAround(blocks.get(idx));
            } else { // 패배한다.
                System.out.println("lose");
                pApplet.isFailed = true;
            }
        } else {
            // 주변 반짝인다
            blocks.get(idx).isLighter = true;
        }
    }

    void openAround(Block oneBlock) {
        for (Block block : oneBlock.aroundBlocks) {
            if (!block.isFlag && block.isHidden) {
                block.isHidden = false;

                // 주변 폭탄수가 0이고,
                if (block.property == Constants.NO_BOMB) {
                    openAround(block);
                }
            }
        }
    }

}

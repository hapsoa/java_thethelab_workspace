package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    List<Block> blocks = new ArrayList<>();
    int numOfRightFlag;


    public Map(Window pApplet) {
        super(pApplet);

        for (int i = 0; i < Constants.X_COUNT * Constants.Y_COUNT; i++) {
            blocks.add(new Block(pApplet, i));
        }

        shuffle();

        for (Block block : blocks) {
            block.makeConnection(blocks);
        }

        numOfRightFlag = 0;
    }

    void reset() {
        System.out.println("reset!");
        blocks.clear();

        for (int i = 0; i < Constants.X_COUNT * Constants.Y_COUNT; i++) {
            blocks.add(new Block(pApplet, i));
        }

        shuffle();

        for (Block block : blocks) {
            block.makeConnection(blocks);
        }

        pApplet.isFailed = false;
        pApplet.isSuccess = false;

        numOfRightFlag = 0;
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
            for (Block block : blocks.get(idx).aroundBlocks) {
                block.isLighter = true;
            }
        }


    }

    @Override
    public void update() {
        // 블록 안의 좌표 안에만
        if (pApplet.mouseY > Constants.UI_BAR_HEIGHT) {
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

            if (pApplet.key == 'r' && blocks.get(idx).isHidden) {
                if (!blocks.get(idx).isFlag) {
                    // 깃발 꼽을 때
                    blocks.get(idx).isFlag = true;

                    // numOfRightFlag의 수가 올라간다
                    if (blocks.get(idx).property == Constants.BOMB)
                        numOfRightFlag++;

                    if (numOfRightFlag == pApplet.numOfMine &&
                            numOfRightFlag == getNumOfCurrentFlags()) {
                        pApplet.isSuccess = true;
                        System.out.println("success");
                    }
                } else {
                    blocks.get(idx).isFlag = false;

                    if (blocks.get(idx).property == Constants.BOMB) {
                        numOfRightFlag--;
                    }
                }
            }
        }
    }

    int getNumOfCurrentFlags() {
        int numOfFlag = 0;
        for (Block block : blocks) {
            if (block.isFlag)
                numOfFlag++;
        }
        return numOfFlag;
    }

    @Override
    public void render() {

        // 패배 시
        if (pApplet.isFailed) {
            for (Block block : blocks) {
                block.isHidden = false;
            }
        }
        else { // 패배하지 않고
            // 승리 시
            if (pApplet.isSuccess) {
                //승리
                for (Block block : blocks) {
                    block.isHidden = false;
                }
            }
        }


        // 그린다.
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
            for (Block block : blocks.get(idx).aroundBlocks) {
                block.isLighter = true;
            }

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

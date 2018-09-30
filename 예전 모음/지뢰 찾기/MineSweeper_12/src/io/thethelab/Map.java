package io.thethelab;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class Map extends View {
    private List<Block> blocks = new ArrayList<>();
    boolean isFailed;
    boolean isSuccess;
    private int numOfRightFlag;

    private Path fp = Paths.get("save.csv");
    private File fp2 = new File("save.csv");

    Map(Window pApplet) {
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

        if (pApplet.key == 'w' && !blocks.get(index).visible) {
            delete(blocks.get(index));
        }

        if (pApplet.key == 'd') {

            // 폭탄 수와 주변 깃발 수가 같을 때
            if (blocks.get(index).visible &&
                    blocks.get(index).neighborMineCount ==
                            blocks.get(index).numOfFlagAround) {

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

        if (pApplet.key == 'r' && !blocks.get(index).visible) {
            if (blocks.get(index).isFlag) {
                blocks.get(index).isFlag = false;

                for (Block block : blocks.get(index).aroundBlocks) {
                    block.numOfFlagAround--;
                }

                if (blocks.get(index).property == Constants.MINE)
                    numOfRightFlag--;
            } else {
                blocks.get(index).isFlag = true;

                for (Block block : blocks.get(index).aroundBlocks) {
                    block.numOfFlagAround++;
                }

                if (blocks.get(index).property == Constants.MINE)
                    numOfRightFlag++;
            }

        }

        // 저장하기
        if (pApplet.key == 'v') {
            try (BufferedWriter bw = Files.newBufferedWriter((fp))) {
                System.out.println("save");
                bw.write(this.toString());


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (pApplet.key == 'l') {
            try (BufferedReader br = Files.newBufferedReader(fp)) {
                String str;

                str = br.readLine();

                String[] strings = str.split(",");

                for (int i = 0; i < blocks.size(); i++) {
                    String blockStr[] = strings[i].split("_");
                    blocks.get(i).visible = Boolean.parseBoolean(blockStr[0]);
                    blocks.get(i).property = Integer.parseInt(blockStr[1]);
                    blocks.get(i).index = Integer.parseInt(blockStr[2]);
                    blocks.get(i).isFlag = Boolean.parseBoolean(blockStr[3]);
                }

                numOfRightFlag = Integer.parseInt(strings[strings.length-1]);

                for (Block block : blocks) {
                    block.makeConnection(blocks);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (Constants.MINE_COUNT == numOfRightFlag) {
            isSuccess = true;
        }

    }

    private void delete(Block block) {
        block.visible = true;

        if (block.property == Constants.MINE)
            isFailed = true;

        if (block.property == Constants.N0_MINE) { // N0_MINE 이면
            //주위가 다 열린다.
            openAround(block);

        }
    }

    private void openAround(Block oneBlock) {
        for (Block block : oneBlock.aroundBlocks) {
            if (!block.visible && !block.isFlag) {
                block.visible = true;

                // 0인 곳은 자동으로 다 열어준다.
                if (block.property == Constants.N0_MINE &&
                        block.visible)
                    openAround(block);
            }

        }
    }

    @Override
    void render() {

        for (Block block : blocks) {
            block.render();
        }

        if (!isFailed) {

            if (isSuccess) {
                for (Block block : blocks)
                    block.visible = true;

                pApplet.fill(0);
                pApplet.text("Success!",
                        (Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE) / 2,
                        (Constants.BLOCK_COUNT_Y * Constants.BLOCK_SIZE) / 2);
            }
        } else {  //실패하면
            for (Block block : blocks)
                block.visible = true;

            pApplet.fill(0);
            pApplet.text("Failed",
                    (Constants.BLOCK_COUNT_X * Constants.BLOCK_SIZE) / 2,
                    (Constants.BLOCK_COUNT_Y * Constants.BLOCK_SIZE) / 2);
        }


    }

    @Override
    void standBy() {
        int index = Utils.getIndexByPos(pApplet.mouseX, pApplet.mouseY);


        if (pApplet.key == 'd') {
//            System.out.println("hi "  + pApplet.key);

            for (Block block : blocks.get(index).aroundBlocks) {
                if (!block.visible && !block.isFlag)
                    block.renderLighter();
            }

        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Block block : blocks) {
            builder.append(block.toString());
            builder.append(",");
        }
        builder.append(numOfRightFlag);

        return builder.toString();
    }
}

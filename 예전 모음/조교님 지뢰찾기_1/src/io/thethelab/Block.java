package io.thethelab;

import com.sun.tools.javac.comp.Check;
import processing.core.PApplet;


import java.util.ArrayList;

public class Block implements Constants {


    private int visibleState;
    private int blockState;
    private int x, y;
    private PApplet pApplet;
    private int index;
    private int neighborMineCount;

    ArrayList<Block> connections = new ArrayList<>();

    public Block(PApplet pApplet, int index) {
        this.pApplet = pApplet;
        this.blockState = 1;
        setIndex(index);
        this.visibleState = this.BLOCK_INVISIBLE;

        // 블럭의 크기에 따라서, 그려질 위치가 다르게 되므로
        // x, y 좌표에 대해 직접 받아 오는것은 위험한다.
        // 우리가 받아야 하는건 블럭의 인덱스 .
        // 인덱스를 알면, 좌표를 안다.


        // 연결된 친구들은 초기에
        // connections.add 해둔다 .

    }

    private void makeConnectionTopBottom(ArrayList<Block> blocks) {
        //
        //
    }

    public int getBlockState() {
        return blockState;
    }


    public void makeConnection(ArrayList<Block> blocks) {
        if (!Util.isLeft(index) && !Util.isTop(index)) {
            connections.add(blocks.get(index - 1 - BLOCK_COUNT_X));
        }
        if (!Util.isTop(index)) {
            connections.add(blocks.get(index - BLOCK_COUNT_X));
        }
        if (!Util.isRight(index) && !Util.isTop(index)) {
            connections.add(blocks.get(index + 1 - BLOCK_COUNT_X));
        }

        if (!Util.isLeft(index)) {
            connections.add(blocks.get(index - 1));
        }

        if (!Util.isRight(index)) {
            connections.add(blocks.get(index + 1));
        }

        if (!Util.isLeft(index) && !Util.isBottom(index)) {
            connections.add(blocks.get(index - 1 + BLOCK_COUNT_X));
        }

        if (!Util.isBottom(index)) {
            connections.add(blocks.get(index + BLOCK_COUNT_X));
        }

        if (!Util.isBottom(index) && !Util.isRight(index)) {
            connections.add(blocks.get(index + 1 + BLOCK_COUNT_X));
        }

        for (Block block : connections) {
            if (block.getBlockState() == BLOCK_STATE_MINE)
                this.neighborMineCount++;
        }
    }


    public void setIndex(int index) {
        this.index = index;
        this.x = Util.getPosXByIndex(index);
        this.y = Util.getPosYByIndex(index);
    }

    // 초기 설정 함수.
    public void setBlockState(int state) {
        this.blockState = state;
    }

    // 옆 체크 .
//    public void click() {
//        for (CheckBox conn : connections) {
//            conn.click();
//        }
//
//    }

    public void click(){
        if(this.visibleState == BLOCK_VISIBLE) return;
        this.visibleState = BLOCK_VISIBLE;



        if(this.neighborMineCount == 0 && this.blockState != BLOCK_STATE_MINE){
            for(Block block : connections){
                block.click();
            }
        }

    }
    public void render() {

        this.pApplet.stroke(100);
        this.pApplet.strokeWeight(2);

        if(this.visibleState == BLOCK_INVISIBLE){
            this.pApplet.fill(200);
            this.pApplet.rect(this.x, this.y,
                    BLOCK_SIZE_X,
                    BLOCK_SIZE_Y);

        } else {
            this.pApplet.fill(100);
            this.pApplet.stroke(50);
            this.pApplet.rect(this.x, this.y,
                    BLOCK_SIZE_X,
                    BLOCK_SIZE_Y);

            if (this.blockState == BLOCK_STATE_MINE) {
                this.pApplet.fill(255, 0, 0);
                this.pApplet.ellipse(this.x + BLOCK_SIZE_X / 2,
                        this.y + BLOCK_SIZE_X / 2,
                        10, 10);
            } else {
                this.pApplet.fill(0);
                this.pApplet.text("" + this.neighborMineCount,
                        this.x,
                        this.y + BLOCK_SIZE_Y);
            }
        }


    }


}

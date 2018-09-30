package io.thethelab;

import com.sun.tools.javac.comp.Check;
import processing.core.PApplet;

import java.util.ArrayList;

public class Map {


    private ArrayList<Block> blocks = new ArrayList<>();
    private PApplet pApplet;

    public Map(PApplet pApplet) {
        this.pApplet = pApplet;
        for (int i = 0; i < Constants.BLOCK_COUNT_X * Constants.BLOCK_COUNT_Y; i++) {
            blocks.add(new Block(pApplet, i));
        }

        initMine();

        for (Block block : blocks)
            block.makeConnection(blocks);


    }

    private void initMine() {
        // 맵 사이즈 만큼,
        // 지뢰를 만들어서
        // 섞는다.
        //
        // n개면 앞에 n개를 가지고 그 지뢰 타일 을 맵에 넣는다

        int[] mines = new int[blocks.size()];
        for (int i = 0; i < blocks.size(); i++) {
            mines[i] = i;
            // mines[0] = 0 , 1 = 1 , 2 = 2
        }


        int shuffle = blocks.size() * 3;
        for (int i = 0; i < shuffle; i++) {
            int src = (int) (Math.random() * blocks.size());
            int dst = (int) (Math.random() * blocks.size());

            int temp = mines[src];
            mines[src] = mines[dst];
            mines[dst] = temp;
            // 2 , 1, 3 .....
        }

        // 마인 갯수
        for (int i = 0; i < Constants.MINE_COUNT; i++) {
            blocks.get(mines[i])
                    .setBlockState(Constants.BLOCK_STATE_MINE);
        }
    }

    public void update() {


    }

    public void render() {
        for (Block box : blocks) {
            box.render();
        }
    }

    public void click(char keycode) {
        if (keycode == 'a') {
            int index = Util.getIndexByPos(this.pApplet.mouseX,
                    this.pApplet.mouseY);

            blocks.get(index).click();
        }
    }

}

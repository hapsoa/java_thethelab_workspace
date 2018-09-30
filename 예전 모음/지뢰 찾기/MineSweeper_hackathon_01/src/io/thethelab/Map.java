package io.thethelab;

import java.util.ArrayList;
import java.util.List;

public class Map extends View{

    List<Block> blocks = new ArrayList<>();

    public Map(Window pApplet) {
        super(pApplet);

        for (int i = 0; i < Constants.X_COUNT*Constants.Y_COUNT; i++) {
            blocks.add(new Block(pApplet, i));
        }

        shuffle();

        for (Block block : blocks) {
            block.makeConnection(blocks);
        }

    }

    private void shuffle() {
        int max = Constants.X_COUNT*Constants.Y_COUNT;
        int[] mine = new int[max];

        int src;
        int dst;
        int temp;

        for (int i = 0; i < mine.length; i++) {
            mine[i] = i;
        }

        // 지뢰 index 를 섞는다
        for (int i = 0; i < max*3; i++) {
            src = (int)(Math.random()*max);
            dst = (int)(Math.random()*max);

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

    }

    @Override
    public void update() {
        int idx = Utils.getIndexByPos(pApplet.mouseX, pApplet.mouseY);

        blocks.get(idx).isHidden = false;
    }

    @Override
    public void render() {
        for (Block block : blocks) {
            block.render();
        }
    }
}

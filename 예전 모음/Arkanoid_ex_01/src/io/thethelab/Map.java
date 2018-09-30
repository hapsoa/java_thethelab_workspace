package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Map extends View {

    List<Block> blocks = new ArrayList<>();
    Ball ball = new Ball(pApplet);

    String strings;
    int dataSize;

    public Map(PApplet pApplet) {
        super(pApplet);

        for (int i = 0; i < 25; i++) {
            blocks.add(new Block(pApplet, i));
        }


        strings = FileManagers.readFile("blocks.csv");
        setBlocks(strings);
    }


    private void setBlocks(String strings) {

        String[] lineString = strings.split("\n");

        List<String> stringList = new ArrayList<>();

        for (String str : lineString) {
            String[] oneStrings = str.split(",");

            for (String strr : oneStrings) {
                stringList.add(strr);
            }
        }

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).color = Integer.parseInt(stringList.get(i));
            if (blocks.get(i).color != 0)
                blocks.get(i).visible = true;
        }

        dataSize = stringList.size();
    }


    @Override
    public void standBy() {

    }

    @Override
    public void update() {

    }

    @Override
    public void render() {

        for (Block block : blocks) {
            block.render();
        }

        ball.render();

        System.out.println(isCollidedBlockAndBall(ball, blocks));

        if (isCollidedBlockAndBall(ball, blocks)) {

        }
    }

    public boolean isCollidedBlockAndBall(Ball ball, List<Block> blocks) {


        Vector2 blockVector;

        for (Block block : blocks) {

            if (block.visible) {

                blockVector = block.vector2;

                if (ball.vector2.x > blockVector.x - ball.radius &&
                        ball.vector2.x < blockVector.x + Constants.BLOCK_X + ball.radius &&
                        ball.vector2.y > blockVector.y - ball.radius &&
                        ball.vector2.y < blockVector.y + Constants.BLOCK_Y + ball.radius
                        ) {

                    block.color = 0;
                    block.visible = false;

                    return true;

                }

            }
        }

        return false;
    }




}

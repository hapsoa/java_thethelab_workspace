package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map extends View {

    Block[][] map;
    private int sizeX;
    private int sizeY;
    private int numOfBomb;

    private int[][] propertyArray;

    Map(Window pApplet, int sizeX, int sizeY, int numOfBomb) {
        super(pApplet);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.numOfBomb = numOfBomb;

        map = new Block[sizeY][sizeX];
        setProperty();

        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j] = new Block(pApplet,
                        Constant.BLOCK_SIZE/2 + j*Constant.BLOCK_SIZE,
                        Constant.BLOCK_SIZE/2 + i*Constant.BLOCK_SIZE,
                        getProperty(i, j));
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        for (int i = 0; i < map.length; i++)
            for (int j = 0; j < map[0].length; j++)
                map[i][j].render();

    }

    private void setProperty() {
        int max = sizeX * sizeY;
        List<Integer> propertyList;
        propertyList = new ArrayList<>();

        for (int i = 0; i < max; i++) {
            if (numOfBomb > 0) {
                propertyList.add(Constant.BOMB);
                numOfBomb--;
            }
            else {
                propertyList.add(Constant.NO_BOMB);
            }
        }

        //폭탄 셔플
        Collections.shuffle(propertyList);

        // 폭탄 옆 숫자 집어넣는다.
        propertyArray = new int[sizeY][sizeX];
        for(int i = 0; i < propertyArray.length; i++) {
            for (int j = 0; j < propertyArray[0].length; j++) {
                propertyArray[i][j] = propertyList.get(sizeX*i + j);
            }
        }

        fillNum();

    }

    private void fillNum() {
        int sum;

        for(int i = 0; i < propertyArray.length; i++) {
            for (int j = 0; j < propertyArray[0].length; j++) {

                if (propertyArray[i][j] != Constant.BOMB) {
                    sum = 0;

                    if (i-1 >= 0 && j-1 >= 0 &&
                            propertyArray[i-1][j-1] == Constant.BOMB)
                        sum++;
                    if (i-1 >= 0 &&
                            propertyArray[i-1][j] == Constant.BOMB)
                        sum++;
                    if (i-1 >= 0 && j+1 < propertyArray[0].length &&
                            propertyArray[i-1][j+1] == Constant.BOMB)
                        sum++;
                    if (j-1 >= 0 &&
                            propertyArray[i][j-1] == Constant.BOMB)
                        sum++;
                    if (j+1 < propertyArray[0].length &&
                            propertyArray[i][j+1] == Constant.BOMB)
                        sum++;
                    if (i+1 < propertyArray.length && j-1>=0 &&
                            propertyArray[i+1][j-1] == Constant.BOMB)
                        sum++;
                    if (i+1 < propertyArray.length &&
                            propertyArray[i+1][j] == Constant.BOMB)
                        sum++;
                    if (i+1 < propertyArray.length &&
                            j+1 < propertyArray[0].length &&
                            propertyArray[i+1][j+1] == Constant.BOMB)
                        sum++;

                    propertyArray[i][j] = sum;
                }


            }
        }
    }

    private int getProperty(int i, int j) {
        return propertyArray[i][j];
    }
}












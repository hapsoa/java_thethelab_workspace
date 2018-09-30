package io.thethelab;


public class Util implements Constants{

    public static int getIndexByPos(int x, int y) {
        // x를 가로로 할건지, y를 가로로 할건지.
        // [x][y], [y][x]
        return x / Constants.BLOCK_SIZE_X +
                y / Constants.BLOCK_SIZE_Y
                        * Constants.BLOCK_COUNT_X;
    }

    public static int getPosXByIndex(int index) {
        return index % Constants.BLOCK_COUNT_X
                * Constants.BLOCK_SIZE_X;

    }

    public static int getPosYByIndex(int index) {
        return index / Constants.BLOCK_COUNT_X
                * Constants.BLOCK_SIZE_Y;
    }

    public static boolean isLeft(int index){
        return index % BLOCK_COUNT_X == 0;
    }
    public static boolean isRight(int index){
        return index % BLOCK_COUNT_X ==
                BLOCK_COUNT_X - 1;
    }
    public static boolean isTop(int index){
        return index / BLOCK_COUNT_X == 0;
    }
    public static boolean isBottom(int index){
        return index / BLOCK_COUNT_X ==
                BLOCK_COUNT_Y - 1;
    }
}

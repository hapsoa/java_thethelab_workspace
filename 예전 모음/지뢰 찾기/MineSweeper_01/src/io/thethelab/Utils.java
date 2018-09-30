package io.thethelab;

public class Utils {
    public static int getPosXByIndex(int index) {
        return (index % (Constants.BLOCK_COUNT_X+2)) * Constants.BLOCK_SIZE;
    }

    public static int getPosYByIndex(int index) {
        return (index / (Constants.BLOCK_COUNT_X+2)) * Constants.BLOCK_SIZE;
    }

    public static boolean isLeft(int index) {
        if (index % (Constants.BLOCK_COUNT_X+2) == 0)
            return true;
        else
            return false;
    }

    public static boolean isRight(int index) {
        if (index % (Constants.BLOCK_COUNT_X+2) == Constants.BLOCK_COUNT_X+1)
            return true;
        else
            return false;

    }

}

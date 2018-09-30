package io.thethelab;

public class Utils {
    public static int getPosXByIndex(int index) {
        return (index % Constants.BLOCK_COUNT_X) * Constants.BLOCK_SIZE;
    }

    public static int getPosYByIndex(int index) {
        return (index / Constants.BLOCK_COUNT_X) * Constants.BLOCK_SIZE;
    }

    public static int getIndexByPos(int mouseX, int mouseY) {
        return ((mouseY / Constants.BLOCK_SIZE) * Constants.BLOCK_COUNT_X) +
                (mouseX / Constants.BLOCK_SIZE);
    }


    public static boolean isLeft(int index) {
        if (index % Constants.BLOCK_COUNT_X == 0)
            return true;
        else
            return false;
    }

    public static boolean isRight(int index) {
        if (index % Constants.BLOCK_COUNT_X == Constants.BLOCK_COUNT_X-1)
            return true;
        else
            return false;

    }

    public static boolean isUp(int index) {
        if (index < Constants.BLOCK_COUNT_X)
            return true;
        else
            return false;
    }

    public static boolean isDown(int index) {
        if (index >= Constants.BLOCK_COUNT_X * (Constants.BLOCK_COUNT_Y-1))
            return true;
        else
            return false;

    }

}

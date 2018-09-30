package io.thethelab;

class Utils {
    static int getPosXByIndex(int index) {
        return (index % Constants.BLOCK_COUNT_X) * Constants.BLOCK_SIZE;
    }

    static int getPosYByIndex(int index) {
        return (index / Constants.BLOCK_COUNT_X) * Constants.BLOCK_SIZE;
    }

    static int getIndexByPos(int mouseX, int mouseY) {
        return ((mouseY / Constants.BLOCK_SIZE) * Constants.BLOCK_COUNT_X) +
                (mouseX / Constants.BLOCK_SIZE);
    }


    static boolean isLeft(int index) {
        return (index % Constants.BLOCK_COUNT_X == 0);
    }

    static boolean isRight(int index) {
        return (index % Constants.BLOCK_COUNT_X == Constants.BLOCK_COUNT_X-1);
    }

    static boolean isUp(int index) {
        return (index < Constants.BLOCK_COUNT_X);
    }

    static boolean isDown(int index) {
        return (index >= Constants.BLOCK_COUNT_X * (Constants.BLOCK_COUNT_Y-1));
    }

}

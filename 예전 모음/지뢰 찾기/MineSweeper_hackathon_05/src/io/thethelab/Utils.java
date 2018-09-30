package io.thethelab;

class Utils {
    static int getPosXByIndex(int index) {
        return (index % Constants.X_COUNT) * Constants.BLOCK_SIZE;
    }

    static int getPosYByIndex(int index) {
        return (index / Constants.X_COUNT) * Constants.BLOCK_SIZE
                + Constants.UI_BAR_HEIGHT;
    }

    static int getIndexByPos(int mouseX, int mouseY) {
        return ((mouseY-Constants.UI_BAR_HEIGHT)/Constants.BLOCK_SIZE) * Constants.X_COUNT +
                (mouseX/Constants.BLOCK_SIZE);
    }

    static boolean isLeft(int index) {
        return index % Constants.X_COUNT == 0;
    }

    static boolean isRight(int index) {
        return index % Constants.X_COUNT == Constants.X_COUNT-1;
    }

    static boolean isUp(int index) {
        return index / Constants.X_COUNT == 0;
    }

    static boolean isDown(int index) {
        return index / Constants.X_COUNT == Constants.Y_COUNT-1;
    }
}

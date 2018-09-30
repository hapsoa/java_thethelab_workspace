package io.thethelab;

public class Utils {

    public static float getPosXByIndex(int index) {
        return index % Constants.BLOCK_X_COUNT *
                Constants.BLOCK_X;
    }

    public static float getPosYByIndex(int index) {
        return index / Constants.BLOCK_X_COUNT *
                Constants.BLOCK_Y;
    }

    public static int getIndexByPos(Vector2 vector2) {
        return ((((int)vector2.y/Constants.BLOCK_Y) * Constants.BLOCK_X_COUNT) +
                ((int)vector2.x/Constants.BLOCK_X));


    }

}

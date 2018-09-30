package io.thethelab;

public class Util {

    public static int getPosXByIndex(int index, int horizonLength){
        return index % horizonLength * Constants.BLOCK_SIZE_X;
    }

    public static int getPosYByIndex(int index, int horizonLength){
        return index / horizonLength * Constants.BLOCK_SIZE_Y;
    }



}

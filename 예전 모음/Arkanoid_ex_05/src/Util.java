public class Util {

    public static int getPosXByIndex(int index, int count){
        return index % count * Constants.BLOCK_SIZE_X;
    }

    public static int getPosYByIndex(int index, int count){
        return index / count * Constants.BLOCK_SIZE_Y;
    }

}

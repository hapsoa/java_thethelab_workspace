public class Util {

    public static int getPosXByIndex(int index, int lengthX, int sizeX, int leftSpace) {
        return index % lengthX * sizeX + sizeX / 2 + leftSpace;
    }

    public static int getPosYByIndex(int index, int lengthX, int sizeY) {
        return index / lengthX * sizeY + sizeY / 2;
    }

}
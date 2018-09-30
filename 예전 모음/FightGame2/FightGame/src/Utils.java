public class Utils {

    static int initRand(int start, int end) { return (int) (Math.random() * (end - start + 1)) + start; }
    static Vector2D get2Dfrom1D(int idx, int x){
        return new Vector2D(idx % x, idx / x);
    }

}

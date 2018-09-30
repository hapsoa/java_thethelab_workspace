public class Color {
    private int r, g, b;

    Color(){
        r = Utils.initRand(0, 255);
        g = Utils.initRand(0, 255);
        b = Utils.initRand(0, 255);
    }

    Color(int c){
        r = c;
        g = c;
        b = c;
    }

    int getR() { return r; }
    int getG() { return g; }
    int getB() { return b; }

    @Override
    public Color clone(){
        try{
            return (Color)super.clone();

        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    static Color getRandomColor(){
        return new Color(Utils.initRand(0, 255));
    }

}

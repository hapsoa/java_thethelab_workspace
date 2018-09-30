import java.util.List;

public class DataHelper {

    private static Vaus vaus;

    private static int tick;

    private static List<Item> items;

    private static int life;

    private static List<Laser> lasers;

    private static Ball ball;

    public static Vaus getUsageVaus(){
        if(vaus == null)
            throw new IllegalArgumentException("객체가 초기화 되지 않았습니다.");
        return vaus;
    }

    public static void setUsageVaus(Vaus v){
        vaus = v;
    }

    public static int getTick(){
        return tick;
    }


    public static void setTick(int t){
        tick = t;
    }

    public static List<Item> getItems(){
        return items;
    }

    public static void setItems(List<Item> i){
        items = i;
    }

    public static int getLife(){
        return life;
    }

    public static void setLife(int l){
        life = l;
    }

    public static List<Laser> getLasers(){
        return lasers;
    }

    public static void setLasers(List<Laser> ls){
        lasers = ls;
    }

    public static Ball getBall(){
        return ball;
    }

    public static void setBall(Ball b){
        ball = b;
    }

}

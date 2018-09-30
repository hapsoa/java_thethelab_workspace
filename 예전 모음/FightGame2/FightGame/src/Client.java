import com.google.gson.Gson;
import processing.core.PApplet;
import processing.core.PImage;

class Client {

    transient private PApplet pApplet;
    transient PImage image;
    transient private Color color;
    transient private Vector2D size;

    String name;
    private Vector2D position;
    int hp;
    String direction;
    int score;
    String state;



    Client(PApplet pApplet, String name) {
        this.pApplet = pApplet;
        this.name = name;
        hp = 100;
        score = 0;
        size = Constants.PLAYER_SIZE.clone();
        state = "STOP";
        direction = "UP";
        Vector2D idx = (new Vector2D(Utils.initRand(0, (int) Constants.MAP_NUMBER.x),
                Utils.initRand(0, (int) Constants.MAP_NUMBER.y)));
        Vector2D.getMultiplied(idx, size).println();
        position = Vector2D.getAdded(Vector2D.getMultiplied(idx, size), Vector2D.getMultiplied(size, 0.5f));
        color = new Color();
    }

    String getClientObjectAsString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }



    void render() {
        pApplet.fill(color.getR(), color.getG(), color.getB());
        pApplet.ellipse(position.x - size.x / 2, position.y - size.y / 2, size.x, size.y);
    }
}
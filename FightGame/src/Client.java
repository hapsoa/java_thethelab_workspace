import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import processing.core.PApplet;

class Client {

    transient Vector2D size;
    transient PApplet pApplet;
    String user;
    volatile Vector2D position;
    private int healthPoint;
    String direction;
    private int score;
    String state;


    Client(String user) {
        this.user = user;
        healthPoint = Constants.HP_MAX;
        score = 0;
        size = Constants.PLAYER_SIZE.clone();
        state = "Stop";
        direction = "UP";
//        Vector2D idx = (new Vector2D(Utils.initRand(0, (int) Constants.MAP_NUMBER.x),
//                Utils.initRand(0, (int) Constants.MAP_NUMBER.y)));

        Vector2D idx = (new Vector2D(5, 5));
        Vector2D.getMultiplied(idx, size).println();
        position = Vector2D.getAdded(Vector2D.getMultiplied(idx, size), Vector2D.getMultiplied(size, 0.5f));
    }

    Client(PApplet pApplet, String user) {
        this(user);
        this.pApplet = pApplet;
    }

    JsonObject getClientAsJsonObject(){
        return new JsonParser().parse(new Gson().toJson(this)).getAsJsonObject();
    }

    void update(){

        if(state.equals("Move")){
            switch (direction.replaceAll("\"", "")){
                case "UP" :
                    position.y -= 3;
                    break;
                case "DOWN" :
                    position.y += 3;
                    break;
                case "RIGHT" :
                    position.x += 3;
                    break;
                case "LEFT" :
                    position.x -= 3;
                    break;
                default:
            }
        }
    }

    void render(){
        pApplet.fill(150, 78, 22);
        pApplet.ellipse(position.x, position.y, size.x, size.y);
    }


    void overwrite(Client client){

        this.position = client.position.clone();
        this.healthPoint = client.healthPoint;
        this.direction = client.direction;
        this.score = client.score;
        this.state = client.state;
    }


}
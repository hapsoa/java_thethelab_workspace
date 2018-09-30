import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import processing.core.PApplet;
import processing.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Window extends PApplet {

    private KeyEventManager keyEventManager;
    private SocketConnector socketConnector;
    private List<Client> clientList;

    @Override
    public void settings() { size((int) Constants.WINDOW_SIZE.x, (int) Constants.WINDOW_SIZE.y); }

    @Override
    public void setup() {
        background(255);
        clientList = new CopyOnWriteArrayList<>();
        setupKeyEventManager();
        setupSocketConnector();
        sendJoin("DD");
    }

    private void setupKeyEventManager(){
        keyEventManager = new KeyEventManager();
        setKeyEventManagerListeners();
    }

    private void setupSocketConnector(){
        socketConnector = new SocketConnector("localhost", 5000);
        SocketConnector.Receiver receiver = s -> {

            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(s);
            String type = object.get("type").toString().replaceAll("\"", "");
            JsonObject body = object.getAsJsonObject("body");
            JsonArray users = object.getAsJsonArray("users");

            switch (type) {
                case "Join":
                    String name = body.get("user").toString().replaceAll("\"", "");
                    clientList.add(new Client(this, name));
                    break;
                case "Update":
                    JsonObject clientObject;
                    String user;
                    for(int i = 0 ; i < users.size() ; i++){
                        clientObject = users.get(i).getAsJsonObject();

                        user = clientObject.get("user").toString().replaceAll("\"", "");

                        Client test = new Gson().fromJson(clientObject, Client.class);
                        for(Client client : clientList){
                            if(client.user.equals(user)){
                                client.overwrite(test);
                                break;
                            }
                        }
                    }
                default:
            }
        };
        socketConnector.setReceiver(receiver);
        socketConnector.startReceiver();
        socketConnector.connect();
    }

    private void sendJoin(String s) {
        JsonObject object = new JsonObject();
        object.addProperty("type", "Join");
        JsonObject body = new JsonObject();
        body.addProperty("user", s);
        object.add("body", body);
        socketConnector.send(new Gson().toJson(object));
    }

    private void setKeyEventManagerListeners() {
        keyEventManager.setListener(Constants.KEYCODE_LEFT, new KeyEventManager.Listener() {
            @Override
            public void onPress() { sendMovement("LEFT"); }

            @Override
            public void onRelease() {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_RIGHT, new KeyEventManager.Listener() {
            @Override
            public void onPress() {
                sendMovement("RIGHT");
            }

            @Override
            public void onRelease() {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_UP, new KeyEventManager.Listener() {
            @Override
            public void onPress() {
                sendMovement("UP");
            }

            @Override
            public void onRelease() {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_DOWN, new KeyEventManager.Listener() {
            @Override
            public void onPress() {
                sendMovement("DOWN");
            }

            @Override
            public void onRelease() {
                sendStop();
            }
        });
    }

    private void sendMovement(String direction) {

        JsonObject object = new JsonObject();
        JsonObject body = new JsonObject();
        object.addProperty("type", "Move");
        body.addProperty("direction", direction);
        object.add("body", body);
        socketConnector.send(new Gson().toJson(object));

    }

    private void sendStop() {
        JsonObject object = new JsonObject();
        object.addProperty("type", "Stop");
        socketConnector.send(new Gson().toJson(object));
    }

    @Override
    public void draw() {
        background(255);

        keyEventManager.update();


        for (int i = 0; i < Acceptor.map.body.map.size(); i++) {

            Color c = new Color(200);
            Vector2D idx = Utils.get2Dfrom1D(i, (int) Constants.MAP_NUMBER.x);
            Vector2D tmp = Vector2D.getAdded(Vector2D.getMultiplied(
                    idx, Constants.MAP_SIZE), Vector2D.getMultiplied(Constants.MAP_SIZE, 0.5f));

            fill(c.getR(), c.getG(), c.getB());
            rect(tmp.x - Constants.MAP_SIZE.x / 2f,
                    tmp.y - Constants.MAP_SIZE.y / 2f,
                    Constants.MAP_SIZE.x,
                    Constants.MAP_SIZE.y
            );
        }

        //clientList.forEach(Client::update);
        clientList.forEach(Client::render);


    }

    @Override
    public void keyPressed(KeyEvent event) {
        keyEventManager.setKeyPress(event.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keyEventManager.setKeyRelease(event.getKeyCode());
    }
}

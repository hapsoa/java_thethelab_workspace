import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import processing.core.PApplet;
import processing.event.KeyEvent;

import java.io.IOException;

public class Window extends PApplet {

    private volatile Client myClient;

    private KeyEventManager keyEventManager;
    private SocketConnector socketConnector;

    @Override
    public void settings() {
        size((int) Constants.WINDOW_SIZE.x, (int) Constants.WINDOW_SIZE.y);
    }

    @Override
    public void setup() {
        background(255);
        keyEventManager = new KeyEventManager();
        myClient = new Client(this, "HyeonSik");
        socketConnector = new SocketConnector("localhost", 5000);
        setKeyEventManagerListeners();

        SocketConnector.Receiver receiver = s -> {
            System.out.println("Socket Connector Received: " + s);

            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject) parser.parse(s);
            Gson gson = new Gson();
            String type = object.get("type").toString().replaceAll("\"", "");
            JsonObject body = object.getAsJsonObject("body");

            switch (type) {
                case "Map":
                    Server.map = gson.fromJson(object.toString(), Map.class);
                    break;
                case "Join":
                    String clientName = body.get("user").toString().replaceAll("\"", "");
                    //이미 조인이 없다면,
                    Server.clientList.add(new Client(this, clientName));
                    System.out.println("clientList added: " + Server.clientList.size());
                    break;
                default:
            }
        };
        socketConnector.setReceiver(receiver);

        try {
            socketConnector.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendJoin();


    }


    private void sendJoin() {

        myClient.state = "Join";
        JsonObject object = new JsonObject();
        object.addProperty("type", myClient.state);
        JsonObject body = new JsonObject();
        body.addProperty("user", myClient.name);
        object.add("body", body);
        System.out.println("send join" + new Gson().toJson(object));
        socketConnector.send(new Gson().toJson(object));
    }

    private void setKeyEventManagerListeners() {
        keyEventManager.setListener(Constants.KEYCODE_LEFT, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                sendMovement("LEFT");
            }

            @Override
            public void onRelease(float duration) {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_RIGHT, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                sendMovement("RIGHT");
            }

            @Override
            public void onRelease(float duration) {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_UP, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                sendMovement("UP");
            }

            @Override
            public void onRelease(float duration) {
                sendStop();
            }
        });
        keyEventManager.setListener(Constants.KEYCODE_DOWN, new KeyEventManager.Listener() {
            @Override
            public void onPress(boolean isFirst, float duration) {
                sendMovement("DOWN");
            }

            @Override
            public void onRelease(float duration) {
                sendStop();
            }
        });
    }

    private void sendMovement(String direction) {
        myClient.state = "Move";
        myClient.direction = direction;
        JsonObject object = new JsonObject();
        JsonObject body = new JsonObject();
        object.addProperty("type", myClient.state);
        body.addProperty("direction", myClient.direction);
        object.add("body", body);
        System.out.println("send Movement");
        socketConnector.send(new Gson().toJson(object));
    }

    private void sendStop() {
        myClient.state = "Stop";
        JsonObject object = new JsonObject();
        object.addProperty("type", myClient.state);
        socketConnector.send(new Gson().toJson(object));
    }

    @Override
    public void draw() {
        background(255);

        keyEventManager.update();

        if (Server.map != null) {
            for (int i = 0; i < Server.map.body.map.size(); i++) {

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
        }
        myClient.render();
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

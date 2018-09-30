package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Window extends PApplet{

    private SocketConnector socketConnector;

//    private KeyEventManager keyEventManager = new KeyEventManager(this);

    public Window() throws IOException {

        socketConnector = new SocketConnector("localhost", 5000);
        socketConnector.connect();
        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(String s) {
                System.out.println(s);
                byte[] bytes = s.getBytes();

                // 처음 있는 숫자 4자리를 읽는다.



            }
        });

        socketConnector.startReceiver();

        maven.JSONObject jsonObject2 = new maven.JSONObject();
        jsonObject2.put("type", "connect");

        maven.JSONObject stateJsonObject2 = new maven.JSONObject();
        stateJsonObject2.put("name", "jaejong");
        stateJsonObject2.put("character", 10);

        maven.JSONObject positionJsonObject2 = new maven.JSONObject();
        positionJsonObject2.put("x", 10.0f);
        positionJsonObject2.put("y", 20.0f);

        stateJsonObject2.put("position", positionJsonObject2);

        jsonObject2.put("state", stateJsonObject2);

        System.out.println(jsonObject2.toString());
        System.out.println(jsonObject2.get("type"));

        socketConnector.send(jsonObject2.toString());


    }



    public void setup() {
    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        background(255);
    }

    public void keyPressed() {

        maven.JSONObject jsonObject2 = new maven.JSONObject();
        jsonObject2.put("type", "connect");

        maven.JSONObject stateJsonObject2 = new maven.JSONObject();
        stateJsonObject2.put("name", "jaejong");
        stateJsonObject2.put("character", 10);

        maven.JSONObject positionJsonObject2 = new maven.JSONObject();
        positionJsonObject2.put("x", 10.0f);
        positionJsonObject2.put("y", 20.0f);

        stateJsonObject2.put("position", positionJsonObject2);

        jsonObject2.put("state", stateJsonObject2);

        System.out.println(jsonObject2.toString());
        System.out.println(jsonObject2.get("type"));

        try {
            socketConnector.send(jsonObject2.toString());
            System.out.println("send");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void keyReleased() {

    }


    @Override
    public void exit() {
        System.out.println("exit");
        socketConnector.cancel();
        super.exit();
    }
}

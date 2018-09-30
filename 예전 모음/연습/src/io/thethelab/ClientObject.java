package io.thethelab;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClientObject {


    int type;
    State state;

    ClientObject(){
        type = 1124;
        state = new State();
    }


    class State{

        String name;
        int character;
        int direction;
        Vector2D position;

        State(){
            name = "Tom";
            character = 1;
            direction = 2;
            position = new Vector2D(20, 50);
        }

    }

    public static void main(String[] args) {
        Gson gson = new Gson();

        ClientObject object = new ClientObject();

        String json = gson.toJson(object);

//System.out.println(json);

        JsonParser parser = new JsonParser();
        JsonObject jsonObject =(JsonObject) parser.parse(json);

        System.out.println(jsonObject);

        System.out.println(jsonObject.get("type").getAsInt());
        System.out.println(jsonObject.get("state"));

        System.out.println(jsonObject.getAsJsonObject("state").get("name"));

    }

}
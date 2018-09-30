package io.thethelab;


import maven.JSONArray;
import maven.JSONObject;

public class Utils {

    public static JSONObject getConnectionMessage() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "connect");

        JSONObject stateJsonObject = new JSONObject();
        stateJsonObject.put("name", "jaejong");
        stateJsonObject.put("character", 10);

        JSONObject positionJsonObject = new JSONObject();
        positionJsonObject.put("x", 10.0f);
        positionJsonObject.put("y", 20.0f);

        stateJsonObject.put("position", positionJsonObject);

        jsonObject.put("state", stateJsonObject);


        return jsonObject;
    }

    public static JSONObject getMoveMessage() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "move");

        JSONObject stateJsonObject = new JSONObject();
        stateJsonObject.put("name", "jaejong");
        stateJsonObject.put("character", 10);
        stateJsonObject.put("direction", 4);

        JSONObject positionJsonObject = new JSONObject();
        positionJsonObject.put("x", 10.0f);
        positionJsonObject.put("y", 20.0f);

        stateJsonObject.put("position", positionJsonObject);

        jsonObject.put("state", stateJsonObject);


        return jsonObject;

    }

    public static JSONObject getAcceptMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("type", "accept");

        JSONArray stateJsonArray = new JSONArray();
        stateJsonArray.put(new JSONObject().put("name", "hello")
                                .put("character", 10)
                                .put("position", new JSONObject()
                                            .put("x", 30)
                                            .put("y", 40))
        );
        stateJsonArray.put(new JSONObject().put("name", "hello")
                .put("character", 10)
                .put("position", new JSONObject()
                        .put("x", 30)
                        .put("y", 40))
        );
        jsonObject.put("state", stateJsonArray);


        return jsonObject;
    }

}

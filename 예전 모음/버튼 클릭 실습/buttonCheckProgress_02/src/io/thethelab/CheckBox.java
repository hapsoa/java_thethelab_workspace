package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class CheckBox extends View {

    private int x, y, r, g, b;
    private String text;

    private CheckBox(int x, int y, int r, int g, int b, String text) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.text = text;
    }

    public static CheckBox fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new CheckBox(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getString("text"));
    }

    @Override
    public void update() {

    }

    @Override
    public void render(PApplet pApplet) {

    }

    @Override
    public void mouseClicked() {

    }
}

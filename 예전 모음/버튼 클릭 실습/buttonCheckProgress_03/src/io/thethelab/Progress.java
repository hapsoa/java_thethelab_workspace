package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Progress extends View {
    private int x, y, r, g, b, value;

    private Progress(int x, int y, int r, int g, int b, int value) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.value = value;
    }

    public static Progress fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new Progress(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getInt("value"));
    }

    @Override
    public void update(PApplet pApplet) {

    }

    @Override
    public void render(PApplet pApplet) {

    }

    @Override
    public void mouseClicked(PApplet pApplet) {

    }
}

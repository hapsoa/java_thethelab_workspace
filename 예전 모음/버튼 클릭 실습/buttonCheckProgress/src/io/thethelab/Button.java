package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class Button {

    private int x, y, r, g, b;
    private String text;
    private PApplet pApplet;

    private Button(int x, int y, int r, int g, int b, String text) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.text = text;
    }

    public static Button fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new Button(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getString("text"));
    }

    public void render(PApplet pApplet) {
        pApplet.fill(this.r, this.g, this.b);
        pApplet.rect(this.x, this.y, 80, 40);
        pApplet.fill(255);
        pApplet.text(this.text, this.x + 10, this.y + 10);

    }

    public boolean collisionTest(int mouseX, int mouseY){

        return false;
    }
}

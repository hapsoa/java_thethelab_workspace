package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.awt.*;

public class CheckBox extends Button {

    private int x, y, r, g, b;
    private String text;
    private PApplet pApplet;
    private int buttonSize = 20;
    private boolean checked;

    private CheckBox(int x, int y, int r, int g, int b, String text, boolean checked) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g = g;
        this.b = b;
        this.text = text;
        this.checked = checked;
    }

    public static Button fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");
        //TODO : color가 없으면 기본 흰색으로 주자.

        return new CheckBox(
                json.getInt("x"),
                json.getInt("y"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getString("text"),
                json.getBoolean("checked"));
    }

    public void render(PApplet pApplet) {
        pApplet.fill(this.r, this.g, this.b);
        pApplet.rect(this.x, this.y, buttonSize, buttonSize);
        pApplet.fill(255);
        pApplet.text(this.text, this.x + 30, this.y + 10);

    }

    public boolean collisionTest(int mouseX, int mouseY){
        if (mouseX > x && mouseX < x + buttonSize
                && mouseY > y && mouseY < y + buttonSize)
            return true;
        else
            return false;
    }

    public void renderTrue(PApplet pApplet) {
        pApplet.fill(0, 255, 0);
        pApplet.rect(x + 10, y + 10, buttonSize/2, buttonSize/2);
    }

    public boolean check() {
        if (checked == true)
            checked = false;
        else
            checked = true;

        return checked;
    }

    public boolean getChecked() {
        return checked;
    }
}

























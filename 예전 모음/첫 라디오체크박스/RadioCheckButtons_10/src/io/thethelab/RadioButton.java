package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONObject;

public class RadioButton extends Button {
    private int x, y, r, g, b;
    private String text;
    private int buttonSize = 20;
    private boolean checked;
    private String type;



    private RadioButton(String type, int r, int g, int b, boolean checked) {
        this.type = type;
        this.r = r;
        this.g = g;
        this.b = b;
        this.checked = checked;
    }

    public static Button fromJSON(JSONObject json) {
        JSONObject colorObject = json.getJSONObject("color");

        return new RadioButton(
                json.getString("Type"),
                colorObject.getInt("r"),
                colorObject.getInt("g"),
                colorObject.getInt("b"),
                json.getBoolean("checked"));
    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.fill(this.r, this.g, this.b);
        pApplet.ellipse(this.x, this.y, buttonSize, buttonSize);
        pApplet.fill(255);
        pApplet.text(this.text, this.x + 30, this.y + 10);

    }

    @Override
    public boolean collisionTest(int mouseX, int mouseY){
        if (mouseX > x-buttonSize/2 && mouseX < x + buttonSize/2
                && mouseY > y-buttonSize/2 && mouseY < y + buttonSize/2)
            return true;
        else
            return false;
    }

    @Override
    public void renderTrue(PApplet pApplet) {
        pApplet.fill(0, 255, 0);
        pApplet.ellipse(x, y, buttonSize/2, buttonSize/2);
    }

    @Override
    public boolean getChecked() {
        return checked;
    }

    @Override
    public boolean check() {
        if (checked == true)
            checked = false;
        else
            checked = true;

        return checked;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}

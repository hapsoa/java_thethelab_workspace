package io.thethelab;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.util.ArrayList;

public class ButtonExample extends PApplet {
    public static void main(String[] args) {
        PApplet.main("io.thethelab.ButtonExample");
    }

    private ArrayList<Button> buttons = new ArrayList<>();

    public void settings() {
        size(800, 600);
        JSONArray array = loadJSONArray("./button.json");
        int size = array.size();
        for (int i = 0; i < size; i++) {
            Button b;
            JSONObject obj = array.getJSONObject(i);
            // 체크박스 라면
            if (obj.getString("Type").equals("CheckBox")) {
                b = CheckBox.fromJSON(obj);
                buttons.add(b);
            }
            // 라디오버튼 이라면
            if (obj.getString("Type").equals("RadioGroup")) {
                for(int j = 0; j < obj.getJSONArray("childs").size(); j++)
                {
                    JSONObject child = obj.getJSONArray("childs")
                                            .getJSONObject(j);
                    //TODO
                    b = RadioButton.fromJSON(obj);
                    b.setX(child.getInt("x"));
                    b.setY(child.getInt("y"));
                    b.setText(child.getString("text"));
                    buttons.add(b);
                    RadioButtonGroup.insertRadioButtonGroup(b);
                }
            }
        }
    }

    public void setup() {
        background(0);

    }

    public void draw() {
        background(0);
        // advanced for loop
        for (Button button : buttons) {
            button.render(this);

            if(button.getChecked())
                button.renderTrue(this);

        }

    }

    public void mouseReleased() {
        for (Button button : buttons) {

            // 체크박스 라면
            if (button.getType().equals("CheckBox")) {
                if (button.collisionTest(mouseX, mouseY))
                    button.check();
            }
            // 라디오 버튼그룹이라면
            if (button.getType().equals("RadioGroup")) {
//                for (Button inButton : buttons) {
//                    if (button.getType().equals("RadioGroup"))
//                       button.setChecked(false);
//                }

                if (button.collisionTest(mouseX, mouseY)) {
                    button.check();
                    RadioButtonGroup.lastChecked(button);
                    RadioButtonGroup.check();
                }

               //똑같은 버튼 또 누른다면 버튼 체크 해제

            }
        }
    }
}

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
    private ArrayList<Button> radioButtons1 = new ArrayList<>();
    private ArrayList<Button> radioButtons2 = new ArrayList<>();
    private RadioButtonGroup radioGroup1;
    private RadioButtonGroup radioGroup2;

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
                    b = RadioButton.fromJSON(obj);
                    b.setX(child.getInt("x"));
                    b.setY(child.getInt("y"));
                    b.setText(child.getString("text"));
                    buttons.add(b);
                    radioButtons1.add(b);
                    radioGroup1 = new RadioButtonGroup(radioButtons1);
                }
            }

            if (obj.getString("Type").equals("RadioGroup2")) {
                for(int j = 0; j < obj.getJSONArray("childs").size(); j++)
                {
                    JSONObject child = obj.getJSONArray("childs")
                            .getJSONObject(j);
                    b = RadioButton.fromJSON(obj);
                    b.setX(child.getInt("x"));
                    b.setY(child.getInt("y"));
                    b.setText(child.getString("text"));
                    buttons.add(b);
                    radioButtons2.add(b);
                    radioGroup2 = new RadioButtonGroup(radioButtons2);
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

//        for(ButtonGroup group : buttongroups){
//            group.checkinside()
//        }
        for (Button button : buttons) {

            // 체크박스 라면
            if (button.getType().equals("CheckBox")) {
                if (button.collisionTest(mouseX, mouseY))
                    button.check();
            }
            // 라디오 버튼그룹이라면
            if (button.getType().equals("RadioGroup")) {
                if (button.collisionTest(mouseX, mouseY)) {
                    button.check();
                    radioGroup1.lastChecked(button);
                    radioGroup1.check();
                }
            }

            if (button.getType().equals("RadioGroup2")) {
                if (button.collisionTest(mouseX, mouseY)) {
                    button.check();
                    radioGroup2.lastChecked(button);
                    radioGroup2.check();
                }
            }
        }
    }


}

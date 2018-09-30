package io.thethelab;

import java.util.ArrayList;

public class RadioButtonGroup {
    private ArrayList<Button> radioButtons;
    private static ArrayList<Boolean> checks = new ArrayList<>();
    private Button lastButton;

    public RadioButtonGroup(ArrayList<Button> radioButtons) {
        this.radioButtons = radioButtons;
    }

//    public static void insertRadioButtonGroup(Button button) {
//        radioButtons.add(button);
//        checks.add(button.getChecked());
//    }

    public void check() {

        for (Button button : radioButtons) {
            if (button == lastButton) {
                button.setChecked(true);
            }
            if (button != lastButton) {
                button.setChecked(false);
            }
        }

    }

    public void lastChecked(Button button) {
        lastButton = button;
    }
}

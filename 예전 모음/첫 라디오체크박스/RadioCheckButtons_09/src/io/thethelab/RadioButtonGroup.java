package io.thethelab;

import java.util.ArrayList;

public class RadioButtonGroup {
    private static ArrayList<Button> radioButtons =
            new ArrayList<>();
    private static ArrayList<Boolean> checks = new ArrayList<>();
    private static Button lastButton;

    public RadioButtonGroup(RadioButton radioButton) {

    }

    public static void insertRadioButtonGroup(Button button) {
        radioButtons.add(button);
        checks.add(button.getChecked());
    }

    public static void check() {

        for (Button button : radioButtons) {
            if (button == lastButton) {
                button.setChecked(true);
            }
            if (button != lastButton) {
                button.setChecked(false);
            }
        }

    }

    public static void lastChecked(Button button) {
        lastButton = button;
    }
}

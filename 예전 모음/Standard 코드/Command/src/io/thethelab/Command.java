package io.thethelab;

import processing.core.PApplet;

import java.util.HashMap;
import java.util.Set;

class Command {

    private PApplet pApplet;
    private HashMap<Character, Action> commands;

    Command(PApplet pApplet) {
        this.pApplet = pApplet;
        this.commands = new HashMap<>();
    }

    public interface Actions {
        void onRelease();
        void onPress();
    }

    private class Action {

        boolean isPressed;
        Actions actions;

        Action(Actions actions) {
            this.actions = actions;
        }

        void press() {
            if (!this.isPressed)
                actions.onPress();
            this.isPressed = true;
        }
        public void release() {
            if (this.isPressed)
                actions.onRelease();
            this.isPressed = false;


        }

    }

    void update() {
        Set<Character> keys = commands.keySet();

        if(pApplet.keyPressed) {

            for (Character c : keys) {
                if (c == pApplet.key) {
                    commands.get(c).press();
                    break;
                }
            }
        }
        else {

            for (Character c : keys) {
                if (c == pApplet.key) {
                    commands.get(c).release();
                    break;
                }
            }
        }
    }

    void addKeyEvent(char key, Actions action) {
        this.commands.put(key,
                new Action(action));
    }
}




















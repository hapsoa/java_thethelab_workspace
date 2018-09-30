package io.thethelab;

import processing.core.PApplet;

import java.util.HashMap;
import java.util.Set;

public class Command {

    private HashMap<Character, Action> commands;

    private PApplet pApplet;


    public Command(PApplet pApplet) {
        this.pApplet = pApplet;
        this.commands = new HashMap<>();
    }

    // ON_PRESS, ON_RELEASE,
    public interface Actions {
        void onRelease();
        void onPress();
    }

    private class Action {
        public boolean isPressed;
        Actions actions;

        public Action(Actions actions){
            this.actions = actions;
        }

        public void press(){
            if(!this.isPressed) actions.onPress();
            this.isPressed = true;
        }
        public void release(){
            this.isPressed = false;
        }

    }


    // 해야되는건
    // 입력이 들어오면 그친구의 값을 바꿔준다.

    public void update() {
        if (pApplet.keyPressed) {
            Set<Character> keys = commands.keySet();

            for(Character c : keys){
                if(c == pApplet.key){
                    commands.get(c).press();
                    //
                    break;
                }
            }
        }
    }

    public void release(){

    }


    public void addKeyEvent(char key, Actions action) {
        this.commands.put(key,
                new Action(action));
    }


}

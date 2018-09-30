package window;

import manager.KeyEventManager;
import manager.View;

import java.util.List;

public class DataHelper {

    private static KeyEventManager keyEventManager;
    private static int tick;
    private static List<View> views;
    private static List<Character> characters;
    private static Character myCharacter;

    public static KeyEventManager getKeyEventManager() {
        return keyEventManager;
    }

    public static void setKeyEventManager(KeyEventManager keyEventManager) {
        DataHelper.keyEventManager = keyEventManager;
    }

    public static int getTick() {
        return tick;
    }

    public static void setTick(int tick) {
        DataHelper.tick = tick;
    }

    public static List<View> getViews() {
        return views;
    }

    public static void setViews(List<View> views) {
        DataHelper.views = views;
    }

    public static List<Character> getCharacters() {
        return characters;
    }

    public static void setCharacters(List<Character> characters) {
        DataHelper.characters = characters;
    }

    public static Character getMyCharacter() {
        return myCharacter;
    }

    public static void setMyCharacter(Character myCharacter) {
        DataHelper.myCharacter = myCharacter;
    }
}

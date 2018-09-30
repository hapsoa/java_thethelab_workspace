package io.thethelab;

public class DataHelper {

    private static KeyEventManager keyEventManager;
    private static int tick;

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
}

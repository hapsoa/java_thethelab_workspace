package io.thethelab;

import java.util.List;

public class DataHelper {

    private static List<View> views;
    private static KeyEventManager keyEventManager;



    public static List<View> getViews() {
        return views;
    }
    public static void setViews(List<View> views) {
        DataHelper.views = views;
    }

    public static KeyEventManager getKeyEventManager() {
        return keyEventManager;
    }

    public static void setKeyEventManager(KeyEventManager keyEventManager) {
        DataHelper.keyEventManager = keyEventManager;
    }



}

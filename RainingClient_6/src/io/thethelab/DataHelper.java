package io.thethelab;

import manager.KeyEventManager;
import manager.SocketConnector;
import manager.View;

import java.util.List;

public class DataHelper {

    private static List<View> views;
    private static KeyEventManager keyEventManager;
    private static SocketConnector socketConnector;



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

    public static SocketConnector getSocketConnector() {
        return socketConnector;
    }

    public static void setSocketConnector(SocketConnector socketConnector) {
        DataHelper.socketConnector = socketConnector;
    }
}

package io.thethelab;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

// Singleton
// : 오직 한개의 객체를 생성하고, 언제 어디서든 동일한 방법을 통해 객체를 접근하는 방법
public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    private List<Session> sessions = new CopyOnWriteArrayList<>();

    private GameModel() {

    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void broadcast(byte[] packet) {
        for (Session e : sessions) {
            e.write(packet);
        }
    }

    public void addSession(Session session) {
        sessions.add(session);
    }
    public void removeSession(Session session) {
        sessions.remove(session);
    }

}


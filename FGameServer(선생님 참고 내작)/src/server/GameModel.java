package server;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();
    private List<Session> sessions = new CopyOnWriteArrayList<>();

    private Session removingSession;

    private GameModel() {
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    public void broadcast(String packet) {
//        System.out.println(sessions.size());
        for (Session e : sessions) {

            try {
                e.write(packet);
            } catch (IOException e1) {
                removingSession = e;
            }

        }

        if (removingSession != null) {
            removeSession(removingSession);
            removingSession = null;
        }



    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public void removeSession(Session session) {
        sessions.remove(session);
    }
}
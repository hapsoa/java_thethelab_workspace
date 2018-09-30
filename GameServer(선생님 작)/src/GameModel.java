import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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
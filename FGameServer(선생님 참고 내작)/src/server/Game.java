package server;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends TimerTask {
    private List<User> users = new CopyOnWriteArrayList<>();
    private Map map = new Map();

    public Game() {
        Timer updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(this, 0, 100);
    }

    @Override
    public void run() {
        for (User e : users) {
            e.update();
        }

        broadcast();
    }

    private void broadcast() {
        User[] buf = new User[users.size()];
        users.toArray(buf);

        UpdateResponse response = new UpdateResponse(buf);

        GameModel.getInstance().broadcast(response.toPacket());
        System.out.println(response.toPacket());
    }

    public void join(User user) {
        users.add(user);
    }
    public void removeUser(User user) {
        users.remove(user);
        System.out.println("removed");
    }


    public Map getMap() {
        return map;
    }
}
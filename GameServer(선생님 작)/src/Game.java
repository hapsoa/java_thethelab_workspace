import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game extends TimerTask {
    private List<User> users = new CopyOnWriteArrayList<>();

    private void broadcast() {
        System.out.println(users.size());
        User[] buf = new User[users.size()];
        users.toArray(buf);

        UpdateResponse response = new UpdateResponse(buf);
        byte[] packet = response.toPacket();

        GameModel.getInstance().broadcast(packet);
    }

    public void join(User user) {
        users.add(user);
    }

    @Override
    public void run() {
        for (User e : users) {
            e.update();
        }

        broadcast();
    }

    public void removeUser(User user) {
        users.remove(user);
    }
}
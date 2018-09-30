import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;

public class Acceptor implements Runnable {
    private int port;

    Acceptor(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        Game game = new Game();
        Timer updateTimer = new Timer();
        updateTimer.scheduleAtFixedRate(game, 0, 100);

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {

                Socket socket = serverSocket.accept();

                Session session = new Session(socket, game);
                session.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



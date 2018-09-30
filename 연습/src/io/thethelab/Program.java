package io.thethelab;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// 2개의 프로토콜
// 추가 => ADD,
// 삭제 => DEL,

// 객체,메소드
// => 스레드 안전한가?
// => 여러 개의 스레드가 동시에 동일한 객체의 메소드를 호출할 경우
// 정상적으로 동작하는가?

// Lock.class => Reflection
// Mutex(MUTual EXclusion): 상호 배제
// Mutex에 의해서 하나의 스레드만 접근 가능한 코드 구간: 임계 영역(Critical Section)
final class Lock {
    private Lock() {}
}


interface SessionListener {
    void onDisconnect(Session session);
}

class Session extends Thread {
    private Socket socket;
    private SessionListener listener;
    private Broadcaster broadcaster;

    Session(Socket socket, Broadcaster broadcaster) {
        this.socket = socket;
        this.broadcaster = broadcaster;
    }


    void setSessionListener(SessionListener listener) {
        this.listener = listener;
    }

    OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();

            byte[] buf = new byte[256];
            while (true) {
                int len = is.read(buf);
                if (len == -1)
                    break;

                String word = new String(buf, 0, len);
                broadcaster.removeWord(word);
            }

            if (listener != null)
                listener.onDisconnect(this);

            System.out.println("Connection Thread end");

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

class Broadcaster extends Thread {
    private List<String> words;
    private List<Session> sessions;

    Broadcaster(List<Session> sessions) {
        this.sessions = sessions;
        this.words = Arrays.asList(
                "hello",
                "world",
                "show",
                "me",
                "the", "money"
        );
    }

    void addWord(String word) {
        synchronized (Lock.class) {
            broadcast("ADD," + word);
        }
    }

    void removeWord(String word) {
        synchronized (Lock.class) {
            if (words.contains(word)) {
                broadcast("DEL," + word);
            }
        }
    }

    void broadcast(String message) {
// 복제해서 사용해야 한다.
        for (Session session : sessions) {
            try {
                OutputStream os = session.getOutputStream();
                os.write(message.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                TimeUnit.SECONDS.sleep(3);

                int index = random.nextInt(words.size());
                addWord(words.get(index));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class Acceptor extends Thread {
    private int port;

    // ArrayList 모든 연산이 스레드 안전하게 동작하는 것을 보장한다.
    private List<Session> sessions = new ArrayList<>();

    Acceptor(int port) {
        this.port = port;
    }

    void addSession(Session s) {
        synchronized (Lock.class) {
            sessions.add(s);
        }
    }

    void removeSession(Session s) {
        synchronized (Lock.class) {
            sessions.remove(s);
        }
    }

    @Override
    public void run() {
        try {
            Broadcaster broadcaster = new Broadcaster(sessions);
            broadcaster.start();

            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            while (true) {
                Socket socket = serverSocket.accept();

                Session session = new Session(socket, broadcaster);
                session.setSessionListener(s -> {
                    System.out.println("onDisconnect");
                    removeSession(s);
                });

                addSession(session);
                session.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public class Program {
    public static void main(String[] args) {
        Acceptor acceptor = new Acceptor(5000);
        acceptor.start();
    }
}








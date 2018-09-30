import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Acceptor extends Thread {

    private int port;
    static volatile Map map = new Map();

    private volatile static List<SocketConnector> connectors = new CopyOnWriteArrayList<>();
    private static volatile ConcurrentHashMap<SocketConnector, String> data = new ConcurrentHashMap<>();
    static volatile List<Client> clients = new CopyOnWriteArrayList<>();

    Acceptor(int port){
        this.port = port;
    }

    @Override
    public void run(){
        try{

            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            while(true){
                Socket socket = serverSocket.accept();
                SocketConnector socketConnector = new SocketConnector(socket);
                connectors.add(socketConnector);

                socketConnector.connect();
                socketConnector.send(map.getMapObjectAsString());

                socketConnector.setReceiver(s -> {
                    if(socketConnector.isCancelled) connectors.remove(socketConnector);
                    else broadcast(s, socketConnector);
                });

                socketConnector.startReceiver();

            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void broadcast(String s, SocketConnector socketConnector) {

        JsonParser parser = new JsonParser();

        System.out.println("Broadcast " + s);


        JsonObject object = (JsonObject) parser.parse(s);
        JsonObject body = object.getAsJsonObject("body");

        String type = object.get("type").toString().replaceAll("\"", "");

        switch (type) {
            case "Join":
                String name = body.get("user").toString().replaceAll("\"", "");
                clients.add(new Client(name));
                data.put(socketConnector, name);
                break;
            case "Kill":
                break;
            case "Move":
                for (Client client : clients) {
                    if (client.user.equals(data.get(socketConnector))) {
                        client.direction = body.get("direction").toString().replaceAll("\"", "");
                        client.state = type;
                        break;
                    }
                }
                break;
            case "Stop":
                for (Client client : clients) {
                    if (client.user.equals(data.get(socketConnector))) {
                        client.state = type;
                        break;
                    }
                }
                break;
            case "Hit":
                //유저의 이름으로 클라이언트를 찾고 방향으로 위치 인덱스를 찾아서 거기 사람놈들이 있으면 맞는다
                break;
            case "ItemCreate":
                break;
            case "ItemConsume":
                break;

        }
        for (SocketConnector socketConnector1 : connectors) socketConnector1.send(s);
    }

    void broadcast(String s) {
        for(SocketConnector socketConnector1 : connectors) socketConnector1.send(s);
    }
}

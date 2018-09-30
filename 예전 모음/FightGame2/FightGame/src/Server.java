import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Server {

    static Map map = new Map();
    static volatile List<Client> clientList = new ArrayList<>();
    private static volatile HashMap<String, SocketConnector> data = new HashMap<>();
    private static ServerSocketConnector serverSocketConnector = null;
    Server(){}
    private static void broadcast(String s, SocketConnector socketConnector) {



        System.out.println("Broadcast " + s);
        System.out.println(clientList.size());
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject) parser.parse(s);
        JsonObject body = object.getAsJsonObject("body");
        String type = object.get("type").toString().replaceAll("\"", "");

        switch (type) {
            case "Join":
                data.put(body.get("user").toString(), socketConnector);
                break;
            case "Kill":
                break;
            case "Move":
                break;
            case "Hit":
                //유저의 이름으로 클라이언트를 찾고 방향으로 위치 인덱스를 찾아서 거기 사람놈들이 있으면 맞는다
                break;
            case "ItemCreate":
                break;
            case "ItemConsume":
                break;

        }
        serverSocketConnector.send(s);
    }
    public static void main(String[] args) {
        serverSocketConnector = new ServerSocketConnector(5000);
        serverSocketConnector.setReceiver(Server::broadcast);
        Socket socket;

        new Thread(() -> {
            while(true){
                for(Client client : clientList){
                    System.out.println(clientList.size());
                }
                //serverSocketConnector.send(array.toString());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (true) {
            try {
                socket = serverSocketConnector.serverSocket.accept();
                SocketConnector socketConnector = new SocketConnector(socket);
                serverSocketConnector.add(socketConnector);
                serverSocketConnector.connect(socketConnector);
                serverSocketConnector.send(map.getMapObjectAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
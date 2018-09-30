import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

public class ServerAccept {

    public static void main(String[] args) {
        Acceptor acceptor = new Acceptor(5000);

        new Thread(() -> {
            while(true){
                JsonArray userArray = new JsonArray();
                for(Client client : Acceptor.clients){
                    client.update();// 600, 300  -> 오른쪽 방향               // 60번을 오른쪽으롬 감  --> 720, 300

                    userArray.add(client.getClientAsJsonObject());
                }
                JsonObject dataObject = new JsonObject();
                dataObject.addProperty("type", "Update");
                dataObject.add("users", userArray);
                acceptor.broadcast(new Gson().toJson(dataObject));
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        acceptor.start();


    }
}

package io.thethelab;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import model.AttackRequest;
import model.JoinRequest;
import model.MoveRequest;
import model.StopRequest;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class Session extends Thread {
    private Socket socket;
    private Game game;

    private User user;

    Session(Socket socket, Game game) {
        this.socket = socket;
        this.game = game;
    }

    // Session onJoinRequest
    private void onJoinRequest(JoinRequest request) {
        String name = request.getUser();
        user = new User(name);

        game.join(user);
    }

    @Override
    public void run() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        GameModel gameModel = GameModel.getInstance();
        gameModel.addSession(this);

        try (InputStream is = socket.getInputStream();
             DataInputStream dis = new DataInputStream(is)) {

            int readBytes;
            byte[] buf = new byte[8912];

            while (true) {
                int len = dis.readUnsignedShort();

                readBytes = dis.read(buf, 0, len);
                if (readBytes == -1)
                    break;

                String json = new String(buf, 0, len);
                JsonObject jsonObject = gson.fromJson(json, JsonObject.class);

                String type = jsonObject.get("type").getAsString();

                switch (type) {
                    case "Join": {
                        JoinRequest request = gson.fromJson(json, JoinRequest.class);
                        onJoinRequest(request);
                        break;
                    }
                    case "Move": {
                        MoveRequest request = gson.fromJson(json, MoveRequest.class);
                        onMoveRequest(request);
                    }
                    case "Attack": {
                        AttackRequest request = gson.fromJson(json, AttackRequest.class);
                        onAttackRequest(request);
                        break;
                    }
                    case "Stop": {
                        StopRequest request = gson.fromJson(json, StopRequest.class);
                        onStopRequest(request);
                        break;
                    }
                }

            }
        } catch (EOFException | SocketException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }

        gameModel.removeSession(this);
        game.removeUser(user);

        System.out.println("Session disconnected");

    }

    private void onStopRequest(StopRequest request) {
        user.stop();
    }

    private void onAttackRequest(AttackRequest request) {
        user.attack();
    }

    private void onMoveRequest(MoveRequest request) {
        user.move(request.getDirection());
    }

    public void write(byte[] packet) {
        try {
            OutputStream os = socket.getOutputStream();
            os.write(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
































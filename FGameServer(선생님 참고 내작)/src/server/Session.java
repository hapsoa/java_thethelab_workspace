package server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import manager.SocketConnector;
import protocolModel.AttackRequest;
import protocolModel.JoinRequest;
import protocolModel.MoveRequest;
import protocolModel.StopRequest;

import java.io.IOException;
import java.net.Socket;

public class Session {
    private SocketConnector socketConnector;

    private Game game; // Broadcaster 역할
    private User user;

    GameModel gameModel = GameModel.getInstance();

    public Session(Socket socket, Game game) {

        socketConnector = new SocketConnector(socket);
        this.game = game;
    }

    public void connect() {
        gameModel.addSession(this);
        Session thisSession = this;

        socketConnector.connect();

        socketConnector.setReceiver(new SocketConnector.Receiver() {

            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            @Override
            public void onReceive(String s) {
                System.out.println(s);
                if (!socketConnector.isCancelled) {
                    JsonObject jsonObject = gson.fromJson(s, JsonObject.class);

                    String type = jsonObject.get("type").getAsString();
                    switch (type) {
                        case "Join": {
                            JoinRequest request = gson.fromJson(s, JoinRequest.class);
                            onJoinRequest(request);
                            break;
                        }

                        case "Move": {
                            MoveRequest request = gson.fromJson(s, MoveRequest.class);
                            onMoveRequest(request);
                            break;
                        }

                        case "Attack": {
                            AttackRequest request = gson.fromJson(s, AttackRequest.class);
                            onAttackRequest(request);
                            break;
                        }

                        case "Stop": {
                            StopRequest request = gson.fromJson(s, StopRequest.class);
                            onStopRequest(request);
                            break;
                        }
                    }

                }
                else {
                    gameModel.removeSession(thisSession);
                    game.removeUser(user);

                    System.out.println("Session disconnected");
                }

            }
        });


        socketConnector.startReceiver();
    }

    private void onJoinRequest(JoinRequest request) {
        String name = request.getUser();
        user = new User(name);

        game.join(user);


        try { // 참가하면서 바로 맵정보를 보내준다.
            socketConnector.send(game.getMap().toPacket());
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void write(String packet) throws IOException {
        socketConnector.send(packet);
    }





}

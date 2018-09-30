package window;

import manager.SocketConnector;
import maven.JSONObject;

import java.io.IOException;

public class ClientNetwork {

    private SocketConnector socketConnector;

    public ClientNetwork() throws IOException {

        socketConnector = new SocketConnector("localhost", 5000);
        socketConnector.connect();
        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(String s) {
                System.out.println(s);

                // jsonObject를 분해한다.

                String name;
                int characterFigure;
                int charDirection;
                float positionX;
                float positionY;

                JSONObject jsonObject = new JSONObject(s);
                String type = jsonObject.getString("type");

                if (type.equals("move")) {
                     name = jsonObject.getJSONObject("state").getString("name");
                     characterFigure = jsonObject.getJSONObject("state").getInt("character");
                     charDirection = jsonObject.getJSONObject("state").getInt("direction");
                     positionX = jsonObject.getJSONObject("state").getJSONObject("position").getFloat("x");
                     positionY = jsonObject.getJSONObject("state").getJSONObject("position").getFloat("y");

                    for (Character character : DataHelper.getCharacters()) {
                        if (name.equals(character.getCharacterName())) {
                            character.setCharDirection(charDirection);
                            character.position.x = positionX;
                            character.position.y = positionY;
                        }
                    }

                }
                else if (type.equals("connect")) {
                    name = jsonObject.getJSONObject("state").getString("name");
                    characterFigure = jsonObject.getJSONObject("state").getInt("character");
                    positionX = jsonObject.getJSONObject("state").getJSONObject("position").getFloat("x");
                    positionY = jsonObject.getJSONObject("state").getJSONObject("position").getFloat("y");

                    for (Character character : DataHelper.getCharacters()) {
                        if (name.equals(character.getCharacterName())) {
                            character.position.x = positionX;
                            character.position.y = positionY;
                        }
                    }
                }
                else if (type.equals("disconnect")) {
                    name = jsonObject.getJSONObject("state").getString("name");
                    for (Character character : DataHelper.getCharacters()) {
                        if (name.equals(character.getCharacterName())) {
                            //disconnect 처리. 오류 가능성이 있음.
                            DataHelper.getViews().remove(character);
                            DataHelper.getCharacters().remove(character);
                        }
                    }
                }
                else if (type.equals("accept")) {

                    for(Object j : jsonObject.getJSONArray("state")) {
                        JSONObject jo = (JSONObject) j;
                        DataHelper.getViews().add(new Character(this, ))
                    }

                }
                else if (type.equals("reject")) {

                }

                String protocolStrings[] = s.split("-");
                if (protocolStrings.length != 4)
                    throw new IllegalStateException("클라이언트 메세지 수신 오류!");
                boolean hasCharacter = false;

                System.out.println(DataHelper.getCharacters().size());

                for (Character character : DataHelper.getCharacters()) {
                    // 내 캐릭터가 아니고, 프로토콜과 같은 캐릭터 아이디에다가 업데이트한다.
                    if (Integer.parseInt(protocolStrings[0]) != myCharacter.characterId &&
                            Integer.parseInt(protocolStrings[0]) ==
                                    character.characterId) {

                        character.charDirection = Integer.parseInt(protocolStrings[1]);
                        character.position.x = Float.parseFloat(protocolStrings[2]);
                        character.position.y = Float.parseFloat(protocolStrings[3]);
                    }

                    if (Integer.parseInt(protocolStrings[0]) ==
                            character.characterId) {
                        hasCharacter = true;
                    }
                }

                // 캐릭터 아이디가 기존에 없으면
                if (!hasCharacter) {
                    System.out.println("new c");
                    makeNewCharacter(protocolStrings[0], protocolStrings[1],
                            protocolStrings[2], protocolStrings[3]);
                }

            }
        });

        //서버에 클라이언트가 연결됬다는 신호를 보냄.
        socketConnector.startReceiver();

        socketConnector.send(Utils.getConnectionMessage().toString());

    }

    public void makeNewCharacter(String charId, String charDir, String posX,
                                 String posY) {
        Character newCharacter = new Character(this, Integer.parseInt(charId),
                Integer.parseInt(charDir), Float.parseFloat(posX), Float.parseFloat(posY));
        views.add(newCharacter);
        characters.add(newCharacter);
    }
}

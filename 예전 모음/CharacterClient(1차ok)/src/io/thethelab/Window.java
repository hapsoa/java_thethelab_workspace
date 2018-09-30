package io.thethelab;

import processing.core.PApplet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Window extends PApplet{

    private SocketConnector socketConnector;

    private List<View> views = new CopyOnWriteArrayList<>();
    private Character myCharacter;
    private List<Character> characters = new ArrayList<>();

    private int tick;

    public Window() throws IOException {

        socketConnector = new SocketConnector("localhost", 5000);
        socketConnector.connect();
        socketConnector.setReceiver(new SocketConnector.Receiver() {
            @Override
            public void onReceive(String s) {
                System.out.println(s);
                String protocolStrings[] = s.split("-");
                if (protocolStrings.length != 4)
                    throw new IllegalStateException("클라이언트 메세지 수신 오류!");
                boolean hasCharacter = false;

                System.out.println(characters.size());

                for (Character character : characters) {
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

        socketConnector.startReceiver();

    }

    public void makeNewCharacter(String charId, String charDir, String posX,
                                 String posY) {
        Character newCharacter = new Character(this, Integer.parseInt(charId),
                Integer.parseInt(charDir), Float.parseFloat(posX), Float.parseFloat(posY));
        views.add(newCharacter);
        characters.add(newCharacter);
    }

    public void setup() {
        // 이 캐릭터만 내가 움직일 수 있다.
        myCharacter = new Character(
                this, Constants.BROWN_CHARACTER);
        views.add(myCharacter);
        characters.add(myCharacter);



    }

    public void settings() {
        size(640, 480);
    }

    public void draw() {
        background(255);

        // 클라이언트 추가시 (조종안되는) 캐릭터 화면에 추가.

        for (View view : views) {
            view.update();
            view.render();
        }

    }

    public void keyPressed() {
        tick++;
        DataHelper.setTick(tick);

        final int k = keyCode;

        if (k == UP    | k == 'W')   myCharacter.isNorth = true;
        else if (k == DOWN  | k == 'S')   myCharacter.isSouth = true;
        else if (k == LEFT  | k == 'A')   myCharacter.isWest  = true;
        else if (k == RIGHT | k == 'D')   myCharacter.isEast = true;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(myCharacter.characterId));
        stringBuilder.append("-");
        stringBuilder.append(String.valueOf(myCharacter.charDirection));
        stringBuilder.append("-");
        stringBuilder.append(String.valueOf(myCharacter.position.x));
        stringBuilder.append("-");
        stringBuilder.append(String.valueOf(myCharacter.position.y));
        try {
            socketConnector.send(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void keyReleased() {
        final int k = keyCode;

        if      (k == UP    | k == 'W')   myCharacter.isNorth = false;
        else if (k == DOWN  | k == 'S')   myCharacter.isSouth = false;
        else if (k == LEFT  | k == 'A')   myCharacter.isWest = false;
        else if (k == RIGHT | k == 'D')   myCharacter.isEast = false;


    }


    @Override
    public void exit() {
        System.out.println("exit");
        socketConnector.cancel();
        super.exit();
    }
}

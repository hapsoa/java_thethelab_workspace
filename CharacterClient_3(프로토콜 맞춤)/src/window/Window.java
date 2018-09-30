package window;

import manager.KeyEventManager;
import manager.SocketConnector;
import manager.View;
import maven.JSONObject;
import processing.core.PApplet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class Window extends PApplet{

    ClientNetwork clientNetwork;

    private List<View> views = new CopyOnWriteArrayList<>();
    private Character myCharacter;
    private List<Character> characters = new ArrayList<>();

    private int tick;

    private KeyEventManager keyEventManager = new KeyEventManager(this);

    public Window() throws IOException {

        // 소켓 커넥터 있던 자리
        clientNetwork = new ClientNetwork();

    }



    public void setup() {
        // 이 캐릭터만 내가 움직일 수 있다.
        DataHelper.setKeyEventManager(keyEventManager);
        myCharacter = new Character(
                this, Constants.BROWN_CHARACTER);
        views.add(myCharacter);
        DataHelper.setViews(views);
        DataHelper.setCharacters(characters);
        DataHelper.setMyCharacter(myCharacter);

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

        try {
            socketConnector.send(Utils.getMoveMessage().toString());
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

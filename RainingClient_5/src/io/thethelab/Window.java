package io.thethelab;

import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public void settings() {
        size(800, 600);
    }

    private List<View> views = new ArrayList<>();
    private InputBox inputBox;
    private int numOfWords=0;

    public static KeyEventManager keyEventManager;

    public void setup() {

        DataHelper.setViews(views);
        keyEventManager = new KeyEventManager(this);
        DataHelper.setKeyEventManager(keyEventManager);

        inputBox = new InputBox(this);
        views.add(inputBox);
    }

    public void draw() {
        background(150);

        for (View view : views) {
            view.update();
            view.render();
        }



        // receivedWordList에 단어가 추가되었을시,
        // views에 rainingWord를추가한다.

        // 로우스트링을 쪼개서 관리할 만한 클래스가 필요해.


        if (WordData.wordList.size() > numOfWords) { // 문제의 소지 코드!!

            views.add(new RainingWord(this, WordData.wordList.get(numOfWords)));
            numOfWords++;

        }


        WordData.updateDelRainingWorListByServer();
//        System.out.println(WordData.wordList);
//        System.out.println(WordData.deletingRainingWordList);
        // views에서 제거
        views.removeAll(WordData.deletingRainingWordList);

        WordData.deletingRainingWordList.clear();




    }

    public void keyPressed() {

        inputBox.inputKey(key);

    }

    public void keyReleased() {

    }

}

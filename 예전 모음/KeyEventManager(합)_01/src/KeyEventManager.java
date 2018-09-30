import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyEventManager {
    // 먼저 관찰자를 설정 할 수 있습니다

    private PApplet pApplet;
    private HashMap<Integer, KeyStruct> listeners = new HashMap<>();

    private class KeyStruct {
        public List<Listener> listenerList = new ArrayList<>();
        public long duration;
        public boolean isFlag;
        public boolean isPress;
    }

    public KeyEventManager(PApplet pApplet) {
        this.pApplet = pApplet;
        boolean isFirst;
    }


    public interface Listener {
        void onPress(boolean isFirst, float duration);

        void onRelease(float duration);
    }


    public void update() {
        for (int key : listeners.keySet()) {

            KeyStruct struct = listeners.get(key);

            if (struct.isPress) {
                for (Listener l : struct.listenerList) {
                    l.onPress(!struct.isFlag,
                            System.currentTimeMillis() - struct.duration);
                }
                struct.isFlag = true;
            } else if (struct.isFlag) {
                System.out.println("hi");
                for (Listener l : struct.listenerList) {
                    l.onRelease(System.currentTimeMillis() - struct.duration);
                }
                struct.isFlag = false;
            }

        }



    }

    public void setKeyPress(int key) {


        if(!listeners.containsKey(key)) return;
        if(listeners.get(key).isPress) return;


        listeners.get(key).isPress = true;
        listeners.get(key).isFlag = false;
        listeners.get(key).duration = System.currentTimeMillis();


    }

    public void setKeyRelease(int key) {
        listeners.get(key).isPress = false;

    }

    public void removeListener(int key, Listener listener) {

    }

    public void removeListener(char key, Listener listener) {

    }

    public void setListener(int key, Listener listener) {

//        if (!pApplet.keyPressed) return;
        if (!listeners.containsKey(key)) {
            listeners.put(key, new KeyStruct());

        }
        listeners.get(key).listenerList.add(listener);
    }

    public void setListener(char key, Listener listener) {
        if (listeners.containsKey((int) key))
            listeners.get((int) key).listenerList.add(listener);

        listeners.put((int) key, new KeyStruct());
    }
}

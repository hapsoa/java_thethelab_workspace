import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KeyEventManager {

    private HashMap<Integer, KeyStruct> listeners;
    private class KeyStruct {
        boolean flag;
        boolean pressed;
        long birthTime;
        long pressedTime;
        List<Listener> listenerList;

        KeyStruct(){
            flag = false;
            pressed = false;
            listenerList = new ArrayList<>();
        }
    }

    public KeyEventManager() {
        listeners = new HashMap<>();
    }

    public interface Listener {
        void onPress(boolean isFirst, float duration);
        void onRelease(float duration);
    }

    public void update() {

        for(Integer i : listeners.keySet()){
            KeyStruct keyStruct = listeners.get(i);
            for(Listener listener : keyStruct.listenerList){
                if(keyStruct.pressed) {
                    keyStruct.pressedTime = System.currentTimeMillis() - keyStruct.birthTime;
                    listener.onPress(!keyStruct.flag, keyStruct.pressedTime);
                    keyStruct.flag = true;
                }
                else if(keyStruct.flag){
                    listener.onRelease(keyStruct.pressedTime);
                    keyStruct.flag = false;
                    keyStruct.pressedTime = 0;
                }
            }
        }
    }
    public void setKeyPress(int key) {

        KeyStruct keyStruct = listeners.get(key);
        if(keyStruct != null && !keyStruct.pressed){
            keyStruct.birthTime = System.currentTimeMillis();
            keyStruct.pressedTime = 0;
            keyStruct.pressed = true;
            keyStruct.flag = false;
        }
    }

    public void setKeyRelease(int key) {

        KeyStruct keyStruct = listeners.get(key);
        if (keyStruct != null) keyStruct.pressed = false;
    }

    public void removeListener(int key, Listener listener) {
        if (listeners.containsKey(key)) listeners.get(key).listenerList.remove(listener);
    }

    public void setListener(int key, Listener listener) {
        if(!listeners.containsKey(key)){ listeners.put(key, new KeyStruct()); }
        listeners.get(key).listenerList.add(listener);
    }

}
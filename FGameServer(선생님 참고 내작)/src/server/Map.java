package server;

import protocolModel.Packet;

public class Map implements Packet {

    class Body {
        class Item {
            private String item;
            float x;
            float y;

            Item() {
                item = "hpPotion";
                x = 0;
                y = 0;
            }

        }

        private int[] map;
        private Item[] items;

        Body() {
            map = new int[1024];  //32*32
            for (Integer i : map) {
                i = 0;
            }

            items = new Item[50];
            for (Item item : items) {
                item = new Item();
            }
        }

    }

    private final String type;
    private Body body;


    public Map() {
        type = "Map";
        body = new Body();
    }



}

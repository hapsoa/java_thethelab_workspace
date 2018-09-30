class Item {

    private Vector2D position;
    String item;

    Item(Vector2D position){
        this.item = Constants.ITEM + "(" + position.x + ", " + position.y + ")";
        this.position = position.clone();
    }

}
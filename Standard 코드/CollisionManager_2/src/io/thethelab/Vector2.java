package io.thethelab;

public class Vector2 {

    public float x, y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public Vector2 add(float x, float y){
        this.x += x;
        this.y += y;
        return this;
    }

    public static Vector2 add(Vector2 a, Vector2 b){
        return new Vector2(a.x + a.y , b.x + b.y);
    }

    public Vector2 mul(float x, float y){
        this.x *= x;
        this.y *= y;
        return this;
    }

    public double distance(Vector2 a){
        return (Math.sqrt((x - a.x) * (x - a.x) + ((y - a.y) * (y - a.y))));
    }

    public Vector2 divide(float x, float y){
        this.x /= x;
        this.y /= y;
        return this;
    }

    public float magnitude(){
        return (float)(Math.sqrt((x *  x) + (y *  y)));
    }

    public Vector2 nomalize(){
        Vector2 vector2 = new Vector2(x, y);

        vector2.x = x / magnitude();
        vector2.y = y / magnitude();
        return vector2;
    }



}

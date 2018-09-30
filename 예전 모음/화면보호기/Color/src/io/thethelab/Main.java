package io.thethelab;

/*
class Color {
    private int red;
    private int green;
    private int blue;

    private Color(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    static Color rgb(int red, int green, int blue) {
        return new Color(red, green, blue);
    }

    static Color hsb(int hue, int saturation, int brightness) {
        int red = 0;
        int green = 0;
        int blue = 0;
        return new Color(red, green, blue);
    }


    Color(int red) {}
    Color(int green) {}


    private static final Color RED = new Color(255, 0, 0);

    static Color red() {
        return RED;
    }

    // => 생성자의 한계를 처리하기 위해 사용하는 설계 방법
    // 1. 생성자의 이름은 변경이 불가능하다.
    // 2. 생성자를 직접 이용할 경우, 캐시 같은 객체 생성에 관련된 최적화를 수행할 수 없다.
    // 3. 생성자의 오버로딩은 한계가 있다.

}
*/
/*
public class Main {

    public static void main(String[] args) {
	// write your code here
        Color c1 = Color.rgb(255, 0, 0);
        Color c3 = Color.hsb((255, 0, 0));
        Color c2 = Color.red();
    }
}

*/

//Builder -> Design Pattern
// => 생성자의 인자가 많을 경우, 빌더를 고려해라.

/*
class User {
    private String name;
    private int age;
    private int height;
    private int weight;

    static class Builder {
        private String name;
        private int age;
        private int height;
        private int weight;

        Builder name(String name) {
            this.name = name;
            return this;
        }

        Builder age(int age) {
            this.age = age;
            return this;
        }

        Builder height(int height) {
            this.height = height;
            return this;
        }

        Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        User build() {
            return new User(this);
        }
    }

    private User(Builder b) {
        this.name = b.name;
        this.age = b.age;
        this.height = b.height;
        this.weight = b.weight;
    }

    public String toString() {
        return name + "(" + age + ")";
    }

}

public class Main {

    public static void main(String[] args) {
        // write your code here
        User user = new User.Builder()
                            .name("Tom")
                            .age(42)
                            .height(180)
                            .weight(80).build();
    }
}
*/

class User {
    private String name;
    private int age;
    private int height;
    private int weight;

    User() {
        this("Unnmaed", 0,0, 0);
    }

    User(String name, int age, int height, int weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }
}

















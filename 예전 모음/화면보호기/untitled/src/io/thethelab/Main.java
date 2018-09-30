package io.thethelab;

// 1. Collection => 자료구조
// Array,       List
// ArrayList<T>     LinkedList<T>

// Stack, Queue
// Set
// Map(Dictionary)

// 정적 배열
// Rect[] rect = new Rect[100];
// 동적 배열 - ArrayList
//  : 스스로 크기가 늘어나는 배열
//  1 -> 2-> 4 -> 8-> 16

//---------------------------
// 1. 클래스로 만들면 편리하다.
// 2. A와 B를 묶어서 관리하고 싶다.
//      => 공통의 부모를 만들면 된다.
//  Rect is a Shape
//  Circle is a Shape
// 3. 자식의 공통된 기능을 부모의 레퍼런스를 통해서 이용하고자 할 경우에
//    반드시 해당 기능은 부모로부터 비롯되어야 한다.
//      => LSP
// 4. OCP
//  => 개방 폐쇄의 법칙
//  => 새로운 기능이 추가되어도 기존 클래스는 수정되면 안된다.
// 5. 다형성은 OCP를 만족한다.
// 6. 복제의 기능을 다형적으로 구현하면 된다.

//Shape rect = new Rect();
//  : 암묵적 업캐스팅
//  => 부모 타입의 레퍼런스로 자식 객체를 참조할 수 있다.

// abstract class vs interface
//  1. abstract 클래스는 구현을 제공할 수 있다.
//        => 이젠 아니다.
//      ; Java 8 에서는 인터페이스가 기본 구현 메소드를 제공할 수 있다.

//  2. abstract 클래스는 필드를 제공할 수 있다.

import java.util.ArrayList;
import java.util.Scanner;

abstract class Shape {
    public abstract void draw();

    public  abstract Shape copy();
}

class Rect extends Shape {
    public void draw() { System.out.println("draw Rect"); }

    public Shape copy() { return new Rect(); }
}

class Circle extends Shape {
    public void draw() { System.out.println("draw Circle"); }

    public Shape copy() { return new Circle(); }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Shape> shapes = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Rect");
            System.out.println("2. Circle");
            System.out.println("3. Duplicate");
            System.out.println("5. Draw");

            System.out.println("입력 > ");
            int cmd = scanner.nextInt();
        }
    }
}


































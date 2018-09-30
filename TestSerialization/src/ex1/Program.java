package ex1;

// Project(Solution)
// - Module(Project): Protobuf

// User 객체 -> File, Network
// 직렬화
// java -> binary
// gson -> JSON

// User 객체가 직렬화를 지원하게 하기 위해서는
// 인터페이스 하나만 구현하면 됩니다.


// 객체 직렬화/역직렬화 방법
// 1) JSON: Gson
// => Text 형식
// { "name": "Tom", "age": 42 }

// 2) Binary
// : 효율적으로 저장할 수 있다.
// => Google Protocol Buffers
// 1) 효율적이다.
// 2) 다양한 언어에 코드를 직접 작성할 필요가 없다.
// protoc를 이용해서 생성하면 된다.
// 3) 타입이 명확하게 지정되어야 한다.
// '타입 안정성'이 있다.

// Server: Java, Python, Node.js
// Client: Browser(Javascript), Android(Java/Kotlin), iOS(ObjC/Swift)

// Google Protocol Buffers
// : 프로토콜 작성을 위한 언어를 정의한다.

// "join"
// username: String
// $ message.proto
// $ protoc message.proto --java_out=.
// $ protoc message.proto --cpp_out=.


import ex1.protobuf.Chat;

import java.io.*;


public class Program {
    public static void main(String[] args) throws Exception {
        Chat.User user = Chat.User.newBuilder()
                .setName("Tom")
                .setAge(42)
                .setAddress("Suwon")
                .build();

        FileOutputStream fos = new FileOutputStream("user.out");
        user.writeTo(fos);

        FileInputStream fis = new FileInputStream("user.out");
        Chat.User copy = Chat.User.parseFrom(fis);
        System.out.println(copy);


    }


}





package com.hello.core.Singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService(); // static 선언으로 클래스 영영역에 할당됨.

    private SingletonService(){                    // 1. 생성자를 private로 만들어, SingletonService의 여러번 생성을 막아
    }
    public static SingletonService getInstance() { // 2. static 메서드, 클래스로 바로 쓸 수 있게
        return instance;
    }
    public void logic(){                          // 3. 인스턴스 메서드, 인스턴스를 통해서만 쓸 수있게, instance. -> 으로 접근케 한다.
        System.out.println("싱글톤 객체 로직 호출");
    }
}

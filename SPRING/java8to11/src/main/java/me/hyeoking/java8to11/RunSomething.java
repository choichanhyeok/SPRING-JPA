package me.hyeoking.java8to11;


@FunctionalInterface // 함수형 인터페이스를 강제하기 위한 애노테이션
public interface RunSomething {
    void doIt();
    // 사실 abstract void doIt();이다. 인터페이스에선 저 부분이 생략할 수 있어 안 써 준 것 뿐

    // java 8 부터는 interface에 정의할 수 있는 메서드의 형태가 더 다양해졌는데, 아래 두가지 이다.
    // 1. static 메서드
    // 2. default 메서드

    static void printName(){ // public도 생략된다.
        System.out.println("Hyeok");
    }

    default void printAge(){
        System.out.println("26");
    }

}

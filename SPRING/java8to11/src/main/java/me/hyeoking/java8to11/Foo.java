package me.hyeoking.java8to11;

public class Foo {
    // java 8 이전에는 함수형 인터페이슬흘 구현해서 사용했음
    public static void main(String[] args){

//        // 1. 익명 내부 클래스 (anonymous inner class)
//        RunSomething runSomething = new RunSomething() {
//            @Override
//            public void doIt() {
//                System.out.println("run");
//            }
//        };
        // 2. 람다를 이용해 함수형 인터페이스를 간단히 정의할 수 있음

        RunSomething runSomething = () -> {
            System.out.println("run"); // 한 줄인 경우 이렇게 람다 형태로 사용 가능
        }; // 이는 자바에서 함수를 first Class Object로 사용할 수 있어서 가능한 일이다.

        RunSomething runSomething2 = new RunSomething() {
            @Override
            public void doIt() {
                System.out.println("run1");
                System.out.println("run2");
            }                         // 여러줄인 경우 이렇게 내부적으로 사용
        };


    }

}

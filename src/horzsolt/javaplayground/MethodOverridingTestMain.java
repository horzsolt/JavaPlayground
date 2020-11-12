package horzsolt.javaplayground;

public class MethodOverridingTestMain {
    public static void main(String[] args) {
        A a = new B();
        a.method1();
        //a.method2();
    }
}

class A {
    public void method1() {
        System.out.println("Method1 in class A");
    }
}

class B extends A {

    public void method1() {
        System.out.println("Method1 in class B");
    }

    public void method2() {
        System.out.println("Method2 in class B");
    }
}

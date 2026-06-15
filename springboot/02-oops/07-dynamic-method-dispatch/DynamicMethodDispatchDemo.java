class A {
    public void show() {
        System.out.println("Hello, World!");
    }
}

class B extends A {
    public void show() {
        System.out.println("Hello from B");
    }
}

class C extends B {
    public void show() {
        System.out.println("Hello from C");
    }
}

public class DynamicMethodDispatchDemo {
    public static void main(String[] args) {
        // The method that runs is decided at runtime based on the object's actual type,
        // not the reference type.
        A obj = new A();
        obj.show();

        obj = new B();
        obj.show();

        obj = new C();
        obj.show();
    }
}

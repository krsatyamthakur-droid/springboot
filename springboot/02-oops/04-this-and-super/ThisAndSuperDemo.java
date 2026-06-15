class A extends Object {
    public A() {
        System.out.println("A's constructor called");
    }

    public A(int x) {
        super(); // Calls Object's no-arg constructor (every class implicitly extends Object)
        System.out.println("A's parameterized constructor called with value: " + x);
    }
}

class B extends A {

    public B() {
        super(); // Calls A's no-arg constructor
        System.out.println("B's constructor called");
    }

    public B(int x) {
        this(); // Calls B's own no-arg constructor
        System.out.println("B's constructor called with value: " + x);
    }
}

public class ThisAndSuperDemo {
    public static void main(String[] args) {
        // Creating an object of class B
        B obj = new B(5);
    }
}

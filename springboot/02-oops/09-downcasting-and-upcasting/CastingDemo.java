class A {
    public void show() {
        System.out.println("in A show");
    }
}

class B extends A {
    public void show1() {
        System.out.println("in B show");
    }
}

public class CastingDemo {
    public static void main(String[] args) {
        // upcasting: a subclass object referred to via a superclass reference
        A obj = (A) new B();
        obj.show();

        // downcasting: a superclass reference cast back to the subclass type
        B obj1 = (B) new B();
        obj1.show1();
    }
}

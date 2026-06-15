// Polymorphism means "many forms" - a method can behave differently depending on
// the object it is called on, just like a person can behave differently in front
// of family versus friends.

// THERE ARE TWO TYPES OF POLYMORPHISM
// 1. Compile-time polymorphism (method overloading) - EARLY BINDING
// 2. Runtime polymorphism (method overriding) - LATE BINDING

// overloading: same method name, different parameters
class A {
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }
}

// overriding: subclass redefines the same method signature
class B extends A {
    @Override
    public int add(int a, int b) {
        return a * b;
    }

    @Override
    public double add(double a, double b) {
        return a * b;
    }
}

public class PolymorphismDemo {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.add(2, 3));       // overloading: int version -> 5
        System.out.println(a.add(2.5, 3.5));   // overloading: double version -> 6.0

        A b = new B();
        System.out.println(b.add(2, 3));       // overriding: B's version -> 6
        System.out.println(b.add(2.5, 3.5));   // overriding: B's version -> 8.75
    }
}

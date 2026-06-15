// Annotations provide metadata about the program.
// They give additional information to the compiler or the runtime environment.

class A {
    public void display() {
        System.out.println("Showing A");
    }
}

class B extends A {
    @Override
    public void display() {
        System.out.println("Showing B");
    }
}

public class AnnotationsDemo {
    public static void main(String[] args) {
        B obj = new B();
        obj.display();
    }
}

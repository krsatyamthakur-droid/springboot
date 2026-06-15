abstract class A {
    public abstract void show();
}

public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        // anonymous inner class - a one-off implementation of A with no class name
        A obj = new A() {
            @Override
            public void show() {
                System.out.println("in A show");
            }
        };

        obj.show();
    }
}

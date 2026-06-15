class A {
    public void show() {
        System.out.println("Showing A");
    }

    // inner class - can only be instantiated through an instance of the outer class
    class B {
        public void config() {
            System.out.println("Configuring B");
        }
    }
}

public class InnerClassDemo {
    public static void main(String[] args) {
        A obj = new A();
        obj.show();

        A.B obj1 = obj.new B();
        obj1.config();
    }
}

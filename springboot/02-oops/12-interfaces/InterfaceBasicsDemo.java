// class -> interface  => implements
// interface -> interface => extends

interface A {
    int age = 3453; // fields in an interface are implicitly public, static and final
    String area = "Mirzapur";

    void show();

    void config();
}

interface B {
    void run();
}

interface T extends B {
    void test();
}

class C implements A, B {
    public void show() {
        System.out.println("Showing A");
    }

    public void config() {
        System.out.println("Configuring A");
    }

    public void run() {
        System.out.println("Running B");
    }
}

public class InterfaceBasicsDemo {
    public static void main(String[] args) {
        A obj = new C();
        obj.show();
        obj.config();
        System.out.println(A.area);
        System.out.println(A.age);
    }
}

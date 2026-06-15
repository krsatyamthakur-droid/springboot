// TYPES OF INTERFACES
// 1. Marker Interface: an interface with no methods or fields, used to mark classes
//    (e.g. java.io.Serializable - marks a class as serializable/deserializable
//    so its objects can be converted to/from a byte stream and saved to disk).
// 2. Functional Interface: an interface with a single abstract method (SAM),
//    used for lambda expressions.
// 3. Tagging Interface: similar to a marker interface, used to provide metadata.

@FunctionalInterface
interface A {
    void config(int i, int j);
}

public class FunctionalInterfaceDemo {
    public static void main(String[] args) {
        // A functional interface has exactly one abstract method, so it can be
        // implemented with a lambda expression instead of an anonymous class:
        //
        // A obj = new A() {
        //     public void config(int i, int j) {
        //         System.out.println("Configuring A" + (i + j));
        //     }
        // };

        A obj = (i, j) -> System.out.println("Configuring A" + (i + j)); // lambda expression
        obj.config(343, 353);
    }
}

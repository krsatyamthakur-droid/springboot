
// //    (e.g. java.io.Serializable - marks a class as serializable/deserializable
// //    so its objects can be converted to/from a byte stream and saved to disk).
// // 2. Functional Interface: an interface with a single abstract method (SAM),
// //    used for lambda expressions.
// // 3. Tagging Interface: similar to a marker interface, used to provide metadata.

// @FunctionalInterface
// interface A {
//     void config(int i, int j);
// }

// public class FunctionalInterfaceDemo {
//     public static void main(String[] args) {
//         // A functional interface has exactly one abstract method, so it can be
//         // implemented with a lambda expression instead of an anonymous class:
//         //
//         // A obj = new A() {
//         //     public void config(int i, int j) {
//         //         System.out.println("Configuring A" + (i + j));
//         //     }
//         // };

//         A obj = (i, j) -> System.out.println("Configuring A" + (i + j)); // lambda expression
//         obj.config(343, 353);
//     }
// }
//@FunctionalInterface
interface A{
    int add(int i,int j);

}

public class FunctionalInterfaceDemo{
    public static void main(String[] args){
        System.out.println("Hello World");
    
    // using lambda expression as simple as that 
   // A obj2 = (i) -> System.out.println("Hello from c" + i);
  //  obj2.show(51);

A obj=(a,b) -> a+b;
int result=obj.add(435,35);
System.out.println(result);
    }
    }
// exception
// Error 
// 1 compile time error 
// 2 Run time error - exception
// 3 logical error


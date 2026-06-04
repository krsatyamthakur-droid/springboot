@FunctionalInterface
interface A{
   void config(int i,int j);

}
// class B implements A{
//    public void config(){
//        System.out.println("Configuring B");
//    }
// }
public class demo{
    public static void main(String[] args) {
        // Functional Interface
        // A functional interface is an interface with a single abstract method.
        // It is used to represent a function or a behavior.
        // Example: Runnable interface in Java is a functional interface.
            // A obj=new A(){
            //     public void config(){
            //         System.out.println("Configuring A");
            //     }
            // };
            // obj.config();


            A obj=(i,j) -> System.out.println("Configuring A" + (i + j)); // lamda expression 
            obj.config(343,353);
            

    }
    }





// // // class -interface => implements
// // // interface - interface => extends



// // interface A{
// //     int age=3453; // final and static by default 
// //     String area="Mirzapur";

// //     void show();
// //     void config();

// // } 
// // interface B{
// //     void run();
// // }
// // interface T extends B{
// //     void test();
// // }
// // class C implements A,B{
// // public void show(){
// //     System.out.println("Showing A");
// // }
// //    public void config(){
// //        System.out.println("Configuring A");
// //    }
// //    public void run(){
// //        System.out.println("Running B");
// //    }
// //    }

// // public class demo{
// //     public static void main(String[] args){
// //         A obj = new C();
// //         obj.show();
// //         obj.config();
// //         System.out.println(A.area);
// //         System.out.println(A.age);
// //     }
// // }


// // NEED for interface

// interface computer{
//    void code();

// }
// class laptop implements computer{
//     public void code(){
//         System.out.println("code run compile");

//     }
// }
// class desktop implements  computer{
//     public void code(){
//         System.out.println("code run compile faster");

//     }
// }

// class Developer{
//   public  void devapp(computer lap){
//    lap.code();
//    }
// }
// public class demo{

//     public static void main(String[] args){
//         computer lap=new laptop();
//        Developer satyam=new Developer();
//        satyam.devapp(lap);

//     }
// }

// TYPES OF INTERFACES
// 1. Marker Interface--> An interface with no methods or fields, used to mark classes
// 2. Functional Interface: An interface with a single abstract method(SAM), used for lambda expressions
// 3. Tagging Interface: Similar to marker interfaces, used to provide metadata
  

  // serialization meaning the process of converting an object into a byte stream and deserialization is the reverse process
  // Both processes are used in Java for saving and retrieving object states
  // To make a class serializable, it must implement the Serializable interface
  // The Serializable interface is a marker interface, meaning it has no methods
  // It is used to indicate that a class can be serialized
  // meaning means that objects of this class store the variable into hard drive 
  
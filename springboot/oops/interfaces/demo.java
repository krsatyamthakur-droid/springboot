



// // class -interface => implements
// // interface - interface => extends



// interface A{
//     int age=3453; // final and static by default 
//     String area="Mirzapur";

//     void show();
//     void config();

// } 
// interface B{
//     void run();
// }
// interface T extends B{
//     void test();
// }
// class C implements A,B{
// public void show(){
//     System.out.println("Showing A");
// }
//    public void config(){
//        System.out.println("Configuring A");
//    }
//    public void run(){
//        System.out.println("Running B");
//    }
//    }

// public class demo{
//     public static void main(String[] args){
//         A obj = new C();
//         obj.show();
//         obj.config();
//         System.out.println(A.area);
//         System.out.println(A.age);
//     }
// }


// NEED for interface

interface computer{
   void code();

}
class laptop implements computer{
    public void code(){
        System.out.println("code run compile");

    }
}
class desktop implements  computer{
    public void code(){
        System.out.println("code run compile faster");

    }
}

class Developer{
  public  void devapp(computer lap){
   lap.code();
   }
}
public class demo{

    public static void main(String[] args){
        computer lap=new laptop();
       Developer satyam=new Developer();
       satyam.devapp(lap);

    }
}
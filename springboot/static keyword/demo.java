


class Mobile{
    int price;
    String name;
    static  String network;

public void displayDetails() {
    System.out.println("Mobile Name: " + name);
    System.out.println("Mobile Price: " + price);
    System.out.println("Mobile Network: " + network);
}
static{
    network="james";
    System.out.println("Static block executed. Network set to: " + network);
}
public Mobile(){
    // constructor
    price=0;
    name=null;
    network=null;
}
public static void show1(){
    System.out.println("Static method show1 executed.");
}
}
public class demo{
    public static void main(String[] args) {
   Mobile m=new Mobile();
   m.price=10000;
   m.name="Samsung";
   m.network="5G";

//    Mobile m1=new Mobile();
//    m1.price=15000;
//    m1.name="OnePlus";
//    m1.network="5G";

   m.network="4G"; // changing the static variable value

    m.displayDetails();
   // m1.displayDetails();

    //static variable can be accessed using class name
   System.out.println("Network: " + Mobile.network);
   // static methods


   Mobile M3=new Mobile();
   M3.price=20000;
   M3.name="iPhone";
   M3.network="5G";

   M3.displayDetails();
   Mobile.show1();

   }

  
}
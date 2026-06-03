
//  //Instead of using random strings or numbers, you can use enums to make your code safer and 
//  //easier to understand.

// enum Status{ // status is class 
//     Running,Failed,Success,Pending; // all this is a object  // names constant

// }

// public class demo{
//     public static void main(String[] args){
//    int i=34;
//    Status s=Status.Running;
//    System.out.println(s);
//    Status b=Status.Failed;
//    System.out.println(b);

//    Status[] all=Status.values();
//    System.out.println(all[0]);
//    for(Status d:all){
//      System.out.println(d+":"+ d.ordinal());
//    }
//    if(s==Status.Running){
//     System.out.println("all good");
//    }
//    else if(s==Status.Success){
//     System.out.println("good");
//    }
//    else if(s==Status.Failed){
//     System.out.println("try again");
//    }
//    else if (s==Status.Pending){
//     System.out.println("please wait ");
//    }
//   switch (s) {
//       case Running:
//          System.out.println("all good");
//         break;
//       case Failed:
//            System.out.println("try again");
//            break;
//         case Pending:
//               System.out.println("please wait ");
//               break;

//   }
//     }
// }

enum laptop{
    Macbook(2000),Hp(1800),Dell(1500),XPS,ThinkPad(1900),Surface(2100);

    public  int price;
    private laptop( int price){
        this.price=price;
    }

   private laptop(){
       // Default constructor
       this.price=1000;
   }

}

public class demo{
    public static void main(String[] args){
        laptop lap=laptop.Macbook;
        System.out.println(lap.price);
        for(laptop l:laptop.values()){
            System.out.println(l+":"+l.price);
        }
    }
}
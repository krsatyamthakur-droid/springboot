class calc{
    public int add(int a,int b){
        return a+b;

    }
}
class advcalc extends calc{
    public int add(int a,int b){
     return a+b+1;
    }
}
// class A{
//     public void show(){
//     System.out.println("hello james of A series ");
//     }
    // public void config(){
    //     System.out.println("this is config of A");
    // }
// }
// class B extends advcalc{
//     public void show (){
//    System.out.println("this is show in B");

//     }  
// }
public class demo{
    public static void main(String[] args) {
       // B obj=new B();
       // obj.show();
       // obj.config();
       advcalc obj=new advcalc();
     int result= obj.add(9,4);
     System.out.println(result);


    }
    }


// class A{
//     public void show(){
//         System.out.println("Showing A");
//     }
// }

// public class demo{
//     public static void main(String[] args){
//         A obj=new A(){
//             public void show(){
//                 System.out.println("Showing anonymous inner class");
//             }
//         };
//         obj.show();
//     }
// }
abstract class A{
    public abstract void show();
}
class B extends A{


    @Override
    public void show() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void show1(){
        System.out.println("in B show");
    }

}
public class demo{
    public static void main(String[] args) {
    A obj=new A(){
        @Override
        public void show() {
            System.out.println("in A show");
        }
    };
   obj.show();

    }
}

class Calc{
    public void show(){
     System.out.println("This is a Calculator");   
    }
    public void add(int a,int b){
        System.out.println(a+b);
    }
}
class Advcalc extends  Calc{
   public void show(){
    System.out.println("This is an Advanced Calculator");
   }
}

public class demo{
    // final - variable ,method,class 
    public static void main(String[] args){
       final  int x=10;
        System.out.println(x);
        Advcalc obj=new Advcalc();
        obj.add(4,5);
        obj.show();
    }
    

}
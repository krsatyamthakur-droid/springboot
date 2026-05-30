class A{
    int age;
    public void show(){
        System.out.println("Showing A");
    }
    class B{
  public void config(){
    System.out.println("Configuring B");
  }
    }
}
public class demo{
    public static void main(String[] args){
        A obj=new A();
        obj.show();
      A.B obj1=obj.new B();
      obj1.config();

    }
}

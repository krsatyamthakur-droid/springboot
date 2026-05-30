class A{
public void show(){
System.out.println("in a show");
}
}
class B extends A{
public void show1(){
System.out.println("in B show");
}
}

public class demo{
    public static void main(String[] args) {
    //   double d=343.325;
    //   int i=(int)d;
    //   System.out.println(i);  
    // 
    A obj= (A) new B();
    obj.show();
    B obj1= (B) new B();
    obj1.show1();

    }
}

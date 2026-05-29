class A extends Object{
    public A(){
        System.out.println("A's constructor called");
    }
    public A(int x){
        super(); // Calls the parameterized constructor of class A
        System.out.println("A's parameterized constructor called with value: " + x);
    }
}
class B extends A{
  
    public B(){
        super();
        System.out.println("B's constructor called");
    }
    public B(int x){
        this(); // Calls the default constructor of class B
        System.out.println("B's constructor called with value: " + x);
    }
}

public class demo{
    public static void main(String[] args){
      // Creating an object of class B
      B obj = new B(5);
    }
}
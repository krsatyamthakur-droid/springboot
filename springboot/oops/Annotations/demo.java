// Annotations means providing metadata about the program 
// It helps in giving additional information to the compiler or the runtime environment.

class A{
    public void ShowTheDataWhichBelongsToThisClass(){
        System.out.println("Showing A");
    }
}
class B extends A{
    @Override
    public void ShowTheDataWhichBelongsToThisClass(){
        System.out.println("Showing B");
    }
}

public class demo{
    public static void main(String[] args){
    B obj=new B();
    obj.ShowTheDataWhichBelongsToThisClass();
    }
}
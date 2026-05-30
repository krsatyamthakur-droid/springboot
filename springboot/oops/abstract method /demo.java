abstract class car{
    public abstract void drive();
    public void playmusic(){
        System.out.println("Playing music");
    }

}
class drive extends car{
    public void drive(){
        System.out.println("Driving a car");
    }
}
public class demo{
    public static void main(String[] args){
        // Creating an object of the abstract class
        // This is not allowed, as abstract classes cannot be instantiated
        // Shape shape = new Shape();
        // you cannot create an object of an abstract class
        // We can use a subclass to create an object
        car c=new drive();
        c.drive();

    }
}
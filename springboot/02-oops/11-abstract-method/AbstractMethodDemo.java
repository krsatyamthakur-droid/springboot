abstract class Car {
    public abstract void drive();

    public void playMusic() {
        System.out.println("Playing music");
    }
}

class Sedan extends Car {
    public void drive() {
        System.out.println("Driving a car");
    }
}

public class AbstractMethodDemo {
    public static void main(String[] args) {
        // Car c = new Car(); // not allowed - abstract classes cannot be instantiated
        // We use a subclass to create an object instead
        Car c = new Sedan();
        c.drive();
        c.playMusic();
    }
}

// Enums can have fields and constructors, just like regular classes.
enum Laptop {
    MACBOOK(2000), HP(1800), DELL(1500), XPS, THINKPAD(1900), SURFACE(2100);

    private final int price;

    Laptop(int price) {
        this.price = price;
    }

    Laptop() {
        this.price = 1000; // default price when none is specified (e.g. XPS)
    }

    public int getPrice() {
        return price;
    }
}

public class EnumWithConstructorDemo {
    public static void main(String[] args) {
        Laptop lap = Laptop.MACBOOK;
        System.out.println(lap.getPrice());

        for (Laptop l : Laptop.values()) {
            System.out.println(l + ":" + l.getPrice());
        }
    }
}

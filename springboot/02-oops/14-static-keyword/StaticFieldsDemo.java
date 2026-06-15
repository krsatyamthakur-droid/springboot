class Mobile {
    int price;
    String name;
    static String network; // shared across all instances of Mobile

    static {
        network = "james";
        System.out.println("Static block executed. Network set to: " + network);
    }

    public Mobile() {
        // Note: only instance fields should be reset here.
        // Resetting a static field in every constructor would overwrite the
        // shared value for ALL instances each time a new Mobile is created.
        price = 0;
        name = null;
    }

    public void displayDetails() {
        System.out.println("Mobile Name: " + name);
        System.out.println("Mobile Price: " + price);
        System.out.println("Mobile Network: " + network);
    }

    public static void show1() {
        System.out.println("Static method show1 executed.");
    }
}

public class StaticFieldsDemo {
    public static void main(String[] args) {
        Mobile m = new Mobile();
        m.price = 10000;
        m.name = "Samsung";
        m.network = "5G";

        m.network = "4G"; // changing the static variable value affects every instance

        m.displayDetails();

        // static variable can be accessed using the class name
        System.out.println("Network: " + Mobile.network);

        Mobile m3 = new Mobile();
        m3.price = 20000;
        m3.name = "iPhone";

        // m3 sees the same "4G" network, because the field is static (shared)
        m3.displayDetails();

        // static methods can be called using the class name
        Mobile.show1();
    }
}

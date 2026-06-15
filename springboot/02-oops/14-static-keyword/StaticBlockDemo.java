class Mobile {
    int price;
    String name;
    static String company;

    static {
        company = "Samsung";
    }

    public Mobile() {
        price = 10000;
        name = "phone";
    }

    public void show1() {
        System.out.println(price);
    }
}

public class StaticBlockDemo {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // the static block runs once, before any object is created
        System.out.println("Company: " + Mobile.company);

        Mobile m = new Mobile();
        m.show1();
    }
}

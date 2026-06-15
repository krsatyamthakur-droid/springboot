import java.util.Objects;

// Every class implicitly extends Object and inherits toString(), equals() and
// hashCode(). Overriding them properly lets objects be compared and printed
// in a meaningful way.
class Laptop {
    String brand;
    int price;

    @Override
    public String toString() {
        return "Brand: " + brand + ", Price: " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Laptop)) {
            return false;
        }
        Laptop that = (Laptop) o;
        return this.price == that.price && Objects.equals(this.brand, that.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, price);
    }
}

public class ObjectClassDemo {
    public static void main(String[] args) {
        Laptop obj = new Laptop();
        obj.brand = "Macbook";
        obj.price = 100000;

        Laptop obj1 = new Laptop();
        obj1.brand = "Macbook";
        obj1.price = 1000000;

        System.out.println(obj);
        System.out.println(obj1);

        boolean result = obj.equals(obj1);
        System.out.println(result); // false - prices differ
    }
}

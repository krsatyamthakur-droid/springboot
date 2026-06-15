public class InheritanceDemo {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        BasicCalculator c = new BasicCalculator();
        AdvancedCalculator ac = new AdvancedCalculator();
        ScientificCalculator sc = new ScientificCalculator();

        System.out.println(c.add(3453, 353));
        System.out.println(c.subtract(3923, 2234));

        System.out.println(ac.multiply(34, 23));
        System.out.println(ac.divide(34, 23));

        System.out.println(sc.power(34, 8));
    }
}

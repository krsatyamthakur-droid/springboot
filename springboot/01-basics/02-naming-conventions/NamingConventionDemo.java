public class NamingConventionDemo {

    // constant - capital letters with underscores, e.g. PI
    static final double PI = 3.14159;

    public void show() {
        System.out.println("Hello, World!");
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        // camel casing naming convention
        String firstName = "John";
        String lastName = "Doe";
        String fullName = firstName + " " + lastName;
        System.out.println(fullName);

        // class and interface - capital letter (PascalCase)
        // variable and method - camelCase, starting with lowercase
        // constant - all capital letters
        System.out.println(PI);

        new NamingConventionDemo().show(); // anonymous object
    }
}

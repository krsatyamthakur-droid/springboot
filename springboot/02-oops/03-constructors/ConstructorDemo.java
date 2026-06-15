class Human {
    private int age; // instance variable
    private String name; // instance variable

    // A constructor in Java is a special method that runs automatically when an object is created.
    public Human() { // default constructor
        age = 0;
        name = "Unknown";
        System.out.println("Human object created");
    }

    public Human(int age, String name) { // parameterized constructor
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }
}

public class ConstructorDemo {
    public static void main(String[] args) {
        Human h = new Human(353, "ankita");
        Human h2 = new Human();

        System.out.println(h.getAge());
        System.out.println(h.getName());

        System.out.println(h2.getAge());
        System.out.println(h2.getName());
    }
}

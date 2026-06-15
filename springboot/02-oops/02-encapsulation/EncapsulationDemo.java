class Human {
    private int age = 345;
    private String name = "satyam";

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

public class EncapsulationDemo {
    public static void main(String[] args) {
        System.out.println("Hello World");
        Human h = new Human();

        System.out.println(h.getAge());
        System.out.println(h.getName());

        // private fields can only be changed through public setters
        h.setAge(25);
        h.setName("ankita");

        System.out.println(h.getAge());
        System.out.println(h.getName());
    }
}

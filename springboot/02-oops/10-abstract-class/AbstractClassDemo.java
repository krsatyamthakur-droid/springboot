abstract class Shape {
    public abstract void draw();

    public abstract double area();
}

class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a circle");
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

public class AbstractClassDemo {
    public static void main(String[] args) {
        // Shape shape = new Shape(); // not allowed - abstract classes cannot be instantiated
        Shape shape = new Circle(5);
        shape.draw();
        System.out.println(shape.area());
    }
}

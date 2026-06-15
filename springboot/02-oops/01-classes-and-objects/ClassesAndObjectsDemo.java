class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

public class ClassesAndObjectsDemo {
    public static void main(String[] args) {
        int x = 34;
        int y = 3434;
        Calculator c = new Calculator();
        int result = c.add(x, y);
        System.out.println(result);
    }
}

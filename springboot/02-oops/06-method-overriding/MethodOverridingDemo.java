class Calculator {
    public int add(int a, int b) {
        return a + b;
    }
}

// AdvancedCalculator overrides add() to change its behavior
class AdvancedCalculator extends Calculator {
    @Override
    public int add(int a, int b) {
        return a + b + 1;
    }
}

public class MethodOverridingDemo {
    public static void main(String[] args) {
        AdvancedCalculator obj = new AdvancedCalculator();
        int result = obj.add(9, 4);
        System.out.println(result);
    }
}

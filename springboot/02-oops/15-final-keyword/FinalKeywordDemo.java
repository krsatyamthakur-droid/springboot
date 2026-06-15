// final can be applied to: variables, methods, and classes

class Calculator {
    public void show() {
        System.out.println("This is a Calculator");
    }

    // a final method cannot be overridden by a subclass
    public final void info() {
        System.out.println("Calculator v1.0");
    }

    public void add(int a, int b) {
        System.out.println(a + b);
    }
}

class AdvancedCalculator extends Calculator {
    @Override
    public void show() {
        System.out.println("This is an Advanced Calculator");
    }

    // public void info() { } // not allowed - info() is final in Calculator
}

// a final class cannot be extended at all
final class Constants {
    static final double PI = 3.14159;
}
// class MyConstants extends Constants { } // not allowed - Constants is final

public class FinalKeywordDemo {
    public static void main(String[] args) {
        final int x = 10; // a final variable's value cannot be reassigned
        System.out.println(x);

        AdvancedCalculator obj = new AdvancedCalculator();
        obj.add(4, 5);
        obj.show();
        obj.info();

        System.out.println(Constants.PI);
    }
}

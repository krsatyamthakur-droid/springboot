// A custom (checked) exception
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ExceptionDemo {

    static void withdraw(int balance, int amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Cannot withdraw " + amount + ", balance is only " + balance);
        }
        System.out.println("Withdrew " + amount);
    }

    public static void main(String[] args) {
        // Runtime error example: ArithmeticException (divide by zero)
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Runtime error caught: " + e.getMessage());
        } finally {
            System.out.println("This always runs, error or not");
        }

        // Custom checked exception example
        try {
            withdraw(1000, 5000);
        } catch (InsufficientBalanceException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }
}

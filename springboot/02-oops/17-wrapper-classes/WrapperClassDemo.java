// Wrapper classes are used to convert primitive data types into objects.
// For example, int can be converted to Integer, char to Character, etc.
// This is useful when working with collections that can only store objects.
public class WrapperClassDemo {
    public static void main(String[] args) {
        int num = 10;

        // converting int to Integer
        Integer obj = num; // Autoboxing
        int num2 = obj.intValue(); // Unboxing
        System.out.println("Integer object: " + num2);

        String str = "3432423";
        int num3 = Integer.parseInt(str);
        System.out.println("Parsed integer: " + num3);
    }
}

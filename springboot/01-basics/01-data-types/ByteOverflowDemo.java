public class ByteOverflowDemo {
    public static void main(String[] args) {
        // byte b = 127;
        // int a = b;
        // System.out.println(a);

        // 257 does not fit in a byte (-128 to 127), so it overflows and wraps around to 1
        int a = 257;
        byte b = (byte) a;
        System.out.println(b);
    }
}

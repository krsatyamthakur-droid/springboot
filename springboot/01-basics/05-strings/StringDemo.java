public class StringDemo {
    public static void main(String[] args) {
        System.out.println("Hello World");

        String names = new String("rahul");
        String name = "Satyam";

        System.out.println(name);
        System.out.println(name.length());
        System.out.println(name.toUpperCase());
        System.out.println(name.charAt(4));
        System.out.println(name.indexOf("y"));

        // hashcode
        System.out.println(names.hashCode());
        System.out.println(name.hashCode());

        // StringBuffer - mutable sequence of characters
        StringBuffer sb = new StringBuffer("hello");
        sb.append(" world");
        sb.insert(6, "big ");
        System.out.println(sb);

        StringBuffer sb2 = new StringBuffer("bond");
        sb2.append("james");
        sb2.insert(9, "bond");
        System.out.println(sb2);
        System.out.println(sb2.length());
    }
}

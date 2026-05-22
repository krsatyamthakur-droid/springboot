public class strings{
    public static void main(java.lang.String[] args){
        System.out.println("Hello World");
        String name="Satyam";
        System.out.println(name);
        System.out.println(name.length());
        System.out.println(name.toUpperCase());
        System.out.println(name.charAt(4));
        System.out.println(name.indexOf("y"));
     StringBuffer sb=new StringBuffer("hello");
     sb.append(" world");
     sb.insert(6, "big ");
     

     System.out.println(sb);
    }   
}
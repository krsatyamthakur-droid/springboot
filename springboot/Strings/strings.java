public class strings{
    public static void main(java.lang.String[] args){

        String names=new String("rahul");
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
     // hashcode
     System.out.println(names.hashCode());
     System.out.println(name.hashCode());


     // Stringbuffer
     StringBuffer sb1=new StringBuffer("hello");
     sb1.append(" world");
     sb1.insert(6, "big ");
     System.out.println(sb1);

     StringBuffer sb2=new StringBuffer("bond");
     sb2.append("james");
     sb2.insert(9,"bond");  
     System.out.println(sb2);
    System.out.println(sb2.length());

    }   
}
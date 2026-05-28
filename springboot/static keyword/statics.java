class Mobile{
    int price;
    String name;
    static String company;

    static{
        company = "Samsung";
    }

    public Mobile(){
        price =10000;
        name ="phone";
    }   

    public void show1(){
        System.out.println(price);
    }
}
    
public class statics{
    public static void main(String[] args){
        System.out.println("Hello, World!");

        Mobile m = new Mobile();
        Class.forName("Mobile");
        m.show1();
    }
}
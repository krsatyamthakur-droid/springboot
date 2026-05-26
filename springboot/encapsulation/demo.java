class human{
   private  int age=345;
   private String name="satyam";

   public int getage(){
    return age;
   }
   public String getname(){
    return name;
   }
   public void setage(int age,human h){
    h.age=age;
   }
   public void setname(String name,human h){
    h.name=name;
   }
} 
public class demo{
    public static void main(String[] args){
        System.out.println("Hello World");
        human h=new human();
        
        System.out.println(h.getage());
        System.out.println(h.getname());
    }
}
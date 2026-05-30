

class laptop{
  String brand;
  int price;

  public String toString(){
      return "Brand: "+brand+", Price: "+price;
  }
  public boolean equals(laptop that){
     if(this.brand.equals(that.brand) && this.price==that.price){
        return true;
     }
     else{
        return false;
     }
  }
}
public class demo{
    public static void main(String[] args){
   laptop obj=new laptop();
   obj.brand="Macbook";
   obj.price=100000;

   laptop obj1=new laptop();
   obj1.brand="Macbook";
   obj1.price=1000000;
   boolean result=obj.equals(obj1);
   System.out.println(result);

    }

}
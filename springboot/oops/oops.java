class cal{
    int a;
    int b;

    public int add(int a,int b){
       System.out.println(a+b);
       return a*b;
    }
}
public class oops{
    public static void main(String[] args){
        int x=34;
        int y=3434;
        cal c=new cal();
      int result =  c.add(x,y);
      System.out.println(result);

    }
}
    

public class demo{
    public static void main(String[] args){
        System.out.println("Hello, World!");
        calc c = new calc();
        Advancecalc ac = new Advancecalc();
        veryadvcalc vac=new veryadvcalc();
        System.out.println(c.add(3453,353));
        System.out.println(c.subs(3923,2234));

        System.out.println(ac.mul(34, 23));
        System.out.println(ac.div(34, 23));

        System.out.println(vac.pow(34,8));
    }
}
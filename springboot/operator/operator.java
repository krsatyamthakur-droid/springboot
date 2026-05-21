public class operator{
    public static void main(String[] args){
        int a = 34;
        int b = 3433434;

        int add= a+b;
        System.out.println(add);

        int sub = b-a;
        System.out.println(sub);

        int multi = a*b;
        System.out.println(multi);

        int divi = a % b;
        System.out.println(divi);

        int mod = a % b;
        System.out.println(mod);

        int d=5;
        int e=10;
        boolean isEqual = (d == e);
        System.out.println(isEqual);

        boolean isNotEqual = (d != e);
        System.out.println(isNotEqual); 

        // relational operator
        boolean isGreater = (d > e);
        System.out.println(isGreater);

        boolean isLess = (d < e);
        System.out.println(isLess);

        boolean isGreaterOrEqual = (d >= e);
        System.out.println(isGreaterOrEqual);

        boolean isLessOrEqual = (d <= e);
        System.out.println(isLessOrEqual);  

        // logical operator
        boolean logicalAnd = (d < e) && (d == 5);
        System.out.println(logicalAnd);

        boolean logicalOr = (d > e) || (d == 5);
        System.out.println(logicalOr);

        boolean logicalNot = !(d == e);
        System.out.println(logicalNot);

        // logical operator
        boolean logicalXor = (d < e) ^ (d == 5);
        System.out.println(logicalXor);     
         
         // examples
         int f = 15;
         int g = 20;

         int addExample = f + g;
         System.out.println(addExample);

         int subExample = g - f;
         System.out.println(subExample);

         int multiExample = f * g;
         System.out.println(multiExample);

         int divExample = g / f;
         System.out.println(divExample);

         int modExample = g % f;
         System.out.println(modExample);


        boolean logicalAndExample = (f < g) && (f == 15);
        System.out.println(logicalAndExample);

        boolean logicalOrExample = (f > g) || (f == 15);
        System.out.println(logicalOrExample);

        boolean logicalNotExample = !(f == g);
        System.out.println(logicalNotExample);

        boolean logicalXorExample = (f < g) ^ (f == 15);
        System.out.println(logicalXorExample);

        // conditional statements
        if (f > g) {
            System.out.println("f is greater than g");
        } else {
            System.out.println("f is not greater than g");
        }

        // ternary operator
        String result = (f > g) ? "f is greater than g" : "f is not greater than g";
        System.out.println(result);

        // switch
        int day = 5;
        String dayName;

        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid day";
                break;
        }

        System.out.println(dayName);
    }
}
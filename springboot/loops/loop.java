public class loop{
    public static void main(String[] args){
        // for loop
        for(int i=0; i<5; i++){
            System.out.println("Hello World");
        }

        // while loop
        int j = 0;
        while(j<5){
            System.out.println("Hello World");
            j++;
        }

        // do-while loop
        int k = 0;
        do{
            System.out.println("Hello World");
            k++;
        }while(k<5);

        int g=1;
        while (true) {
            System.out.println("hi"+g);
            g++;
        }
    }
}
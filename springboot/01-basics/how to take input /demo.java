// TOPIC: Taking input in Java + Exception Handling
//
// 1) Scanner - Java's easiest way to read input from the keyboard (System.in).
//    You create one wrapped around System.in, then call methods like nextInt(),
//    nextLine(), nextDouble() etc. depending on the type of value you expect.
//
// 2) try / catch / finally - Java's way of handling errors ("exceptions")
//    without crashing the whole program.
//      - try:     the risky code you want to attempt goes here.
//      - catch:   runs ONLY if something inside try throws an exception -
//                 lets you recover gracefully instead of crashing.
//      - finally: runs ALWAYS, whether or not an exception happened
//                 (great for cleanup like closing files/scanners).
//    You can skip catch and just use finally if you don't want to handle the
//    error yourself but still need the cleanup code to run.
public class demo {
    public static void main(String[] args) {
    //    Scanner scn=new Scanner(System.in);
    //    int n=scn.nextInt();
    //    System.out.println(n);
      int i=2;
        int j=0;
    try {
       j=18/i;
       System.out.println("hello james ");

    } catch (Exception e) {
        // Only runs if the try block throws, e.g. division by zero
        System.out.println("Something went wrong");
    }
    finally{
        // Always runs, even if an exception was thrown above
    }

    }
}

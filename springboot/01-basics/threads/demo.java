import java.util.*;

// TOPIC: Threads in Java
//
// A Thread is an independent path of execution - it lets your program do
// more than one thing "at the same time" (concurrently). Without threads,
// everything runs one statement after another, in a single line.
//
// Runnable is a functional interface with one method, run(), that describes
// the work a thread should do. You can implement it as a class (see the
// commented-out A/B classes below, the "old style") or, more simply, as a
// lambda expression (the active code below).
//
// Creating `new Thread(runnable)` does NOT start the work - calling
// `.start()` does. Once started, the JVM's scheduler decides how to
// interleave obj1's and obj2's loops, which is why "james bond" and
// "henry cavil" print in an unpredictable, mixed order each run.
// Thread.sleep(10) just pauses that thread for 10ms between prints, making
// the interleaving easier to see.

// class A implements Runnable{
//     public void run(){
//         for(int i=0;i<90;i++){
//             System.out.println("james bond");
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//         }
//     }
// }
//class B implements Runnable{
    // public void run     (){
    //     for(int i=0;i<90;i++){
    //         System.out.println("henry cavil");
    //         try {
    //             Thread.sleep(10);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }
//}
public class demo{
    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);

        // thread -> dividing the work into pieces
        Runnable obj1 = () -> {
            for (int i = 0; i < 90; i++) {
                System.out.println("james bond");
                try {
                    Thread.sleep(10);
               } catch (InterruptedException e) {
                    e.printStackTrace();
               }
            }
        };
        Runnable obj2=() ->{
            for(int i=0;i<90;i++){
                    System.out.println("henry cavil");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        };
        Thread t1=new Thread(obj1);
        Thread t2=new Thread(obj2);
        t1.start();
        t2.start();
    }
    }


//         int arr[]=new int[4];
//         arr[0]=10;
//         arr[1]=20;
//         arr[2]=30;
//         arr[3]=40;
//         for(int i=0;i<arr.length;i++){
//             System.out.println(arr[i]);
//         }
//         // print the array in reverse order
//         for(int i=arr.length-1;i>=0;i--){
//             System.out.println(arr[i]);
//         }
//         // mutil dimensional array
//         int arr2[][]=new int[2][3];
//         arr2[0][0]=1;
//         arr2[0][1]=2;
//         arr2[0][2]=3;
//         arr2[1][0]=4;
//         arr2[1][1]=5;
//         arr2[1][2]=6;
//         for(int i=0;i<arr2.length;i++){
//             for(int j=0;j<arr2[i].length;j++){
//                 System.out.print(arr2[i][j]+" ");
//             }
//             System.out.println();
//         }
//         // jagged array
//         int arr3[][]=new int[2][];
//         arr3[0]=new int[3];
//         arr3[1]=new int[2];
//         arr3[0][0]=1;
//         arr3[0][1]=2;
//         arr3[0][2]=3;
//         arr3[1][0]=4;
//         arr3[1][1]=5;
//         for(int i=0;i<arr3.length;i++){
//             for(int j=0;j<arr3[i].length;j++){
//                 System.out.print(arr3[i][j]+" ");
//             }
//             System.out.println();
//         }

//         // enhance for loop
//         for(int[] num : arr3){
//             for(int n: num) {
//                 System.out.println(n);
//             }
//         }
//     }
// }

class Student {
    int rollNo;
    String name;
    
    }
    public class array{
        public static void main(String[] args){
            Student s1=new Student();
            s1.rollNo=34;
            s1.name="satyam";

            Student s2=new Student();
            s2.rollNo=4;
            s2.name="amit";

            Student s3=new Student();
            s3.rollNo=39;
            s3.name="rahul";


            Student students[]=new Student[3];
            students[0]=s1;
            students[1]=s2;
            students[2]=s3;

          for(int i=0;i<students.length;i++){
            System.out.println(students[i].rollNo+" "+students[i].name);
          }

        }
    }
   
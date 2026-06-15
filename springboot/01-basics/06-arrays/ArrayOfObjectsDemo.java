class Student {
    int rollNo;
    String name;
}

public class ArrayOfObjectsDemo {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.rollNo = 34;
        s1.name = "satyam";

        Student s2 = new Student();
        s2.rollNo = 4;
        s2.name = "amit";

        Student s3 = new Student();
        s3.rollNo = 39;
        s3.name = "rahul";

        Student students[] = new Student[3];
        students[0] = s1;
        students[1] = s2;
        students[2] = s3;

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].rollNo + " " + students[i].name);
        }
    }
}

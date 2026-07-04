// TOPIC: Java Collections Framework
//
// The Collections Framework is a set of ready-made data structures so you
// don't have to build lists/sets/maps by hand:
//   - Collection / List : an ordered group that allows duplicates
//                          (e.g. ArrayList - a resizable array)
//   - Set                : a group with NO duplicates (e.g. HashSet - no
//                          order guarantee, TreeSet - always sorted)
//   - Map                : key -> value pairs, like a dictionary
//                          (e.g. HashMap - fast lookup by key)
//   - Collections.sort() : sorts a List in place, either using the
//                          natural order (Comparable) or a custom
//                          Comparator you provide
//   - Comparator          : lets you define your OWN sorting rule instead
//                          of relying on the default order - useful when
//                          you want to sort objects (like Student) by a
//                          specific field, e.g. by marks or by name.
//
// The commented block below is an earlier, simpler experiment with these
// ideas (arrays -> List/Set/Map, then sorting with Comparator). The active
// code at the bottom builds on the same Comparator idea with a Student
// class, showing 4 different ways to sort: anonymous class, lambda,
// Comparator.comparing, and chained comparators.

// class Student{
//     int age;
//     String name;
//     public Student(int age, String name) {
//         this.age = age;
//         this.name = name;
//     }
// }
// public class demo {

//     public static void main(String[] args) {
//     //     System.out.println("Hello, World!");
//     //     Collection<Integer> nums = new ArrayList<Integer>();
//     //     nums.add(6);
//     //     nums.add(8);
//     //     nums.add(234);
//     //     for(Object n: nums){
//     //     int num = (Integer) n;
//     //     System.out.println(num*2);
//     //     }
//     //   List<Integer> nums2 = new ArrayList<Integer>();
//     //   for(Object n: nums){
//     //       int num = (Integer) n;
//     //       nums2.add(num*2);
//     //   }
//     //   System.out.println(nums2);    

//     //   Set<Integer> nums3 = new HashSet<Integer>();
//     //   for(Object n: nums){
//     //       int num = (Integer) n;
//     //       nums3.add(num*2);
//     //   }
//     //   System.out.println(nums3);

//     //   Collection<Integer> nums4 = new TreeSet<Integer>();
//     //   for(Object n:nums){
//     //     System.out.println(n);
//     //   }

//     //     // key value pair 
//     //     // map
//     //     Map<String, Integer> nums5 = new HashMap<>();
//     //     nums5.put("satyam",2);
//     //     nums5.put("rahul",3);
//     //     nums5.put("aadi",4);
//     //     System.out.println(nums5);

//     //     System.out.println(nums5.get("satyam"));

//     //     // sorting 
//     //     List<Integer> sort=new ArrayList<>();
//     //     sort.addAll(nums);
//     //     Collections.sort(sort);
//     //     System.out.println(sort);


//         // comparator
//         // it is used to own method of sorting like you can create your own method of sorting
//     //    List<Student> name=new ArrayList<Student>();
//     //    name.add(new Student("satyam",34));
//     //    name.add(new Student("rahul",30));
//     //    name.add(new Student("aadi",40));

//     //    Collections.sort(name, new Comparator<Student>() {
//     //        @Override
//     //        public int compare(Student o1, Student o2) {
//     //            return o2.age - o1.age; // descending order
//     //        }
//     //    });
    

//     }
// }
import java.util.*;

class Student {
    String name;
    int marks;

    Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return name + " (" + marks + ")";
    }
}

public class demo{
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>(List.of(
            new Student("Rahul", 85),
            new Student("Aman", 92),
            new Student("Priya", 78)
        ));

        // 1. Anonymous class (old style) - a Comparator written as a full
        //    class body; negative/zero/positive return tells sort() the
        //    relative order of a vs b.
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                return a.marks - b.marks; // ascending by marks
            }
        });
        System.out.println("By marks: " + students);

        // 2. Lambda - same idea as #1, far less boilerplate since
        //    Comparator is a functional interface (one abstract method).
        students.sort((a, b) -> a.name.compareTo(b.name));
        System.out.println("By name: " + students);

        // 3. Comparator.comparing (cleanest) - just tell it WHICH field to
        //    sort by, and it builds the comparator for you.
        students.sort(Comparator.comparing(s -> s.marks));
        System.out.println("By marks again: " + students);

        // 4. Descending + tie-breaker chaining - reversed() flips the
        //    order, thenComparing() breaks ties using a second field.
        students.sort(
            Comparator.comparingInt((Student s) -> s.marks)
                      .reversed()
                      .thenComparing(s -> s.name)
        );
        System.out.println("Marks desc, then name: " + students);
    }
}

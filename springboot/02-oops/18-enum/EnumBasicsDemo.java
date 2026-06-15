// Instead of using random strings or numbers, enums make code safer and easier
// to understand by giving a fixed set of named constants.
enum Status {
    RUNNING, FAILED, SUCCESS, PENDING; // each of these is an object of type Status
}

public class EnumBasicsDemo {
    public static void main(String[] args) {
        Status s = Status.RUNNING;
        System.out.println(s);

        Status[] all = Status.values();
        System.out.println(all[0]);
        for (Status d : all) {
            System.out.println(d + ":" + d.ordinal());
        }

        switch (s) {
            case RUNNING:
                System.out.println("all good");
                break;
            case FAILED:
                System.out.println("try again");
                break;
            case PENDING:
                System.out.println("please wait");
                break;
            default:
                System.out.println("status: " + s);
                break;
        }
    }
}

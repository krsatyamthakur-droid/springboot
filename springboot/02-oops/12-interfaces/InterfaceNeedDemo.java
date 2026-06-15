// WHY DO WE NEED INTERFACES?
// A Developer should be able to work with any Computer (Laptop, Desktop, ...)
// without knowing which concrete class it is. The interface lets us depend on
// the abstraction "Computer" instead of a specific implementation.

interface Computer {
    void code();
}

class Laptop implements Computer {
    public void code() {
        System.out.println("code run compile");
    }
}

class Desktop implements Computer {
    public void code() {
        System.out.println("code run compile faster");
    }
}

class Developer {
    public void devApp(Computer computer) {
        computer.code();
    }
}

public class InterfaceNeedDemo {
    public static void main(String[] args) {
        Computer laptop = new Laptop();
        Developer satyam = new Developer();
        satyam.devApp(laptop);

        Computer desktop = new Desktop();
        satyam.devApp(desktop);
    }
}

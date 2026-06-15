package com.example;

// Packages group related classes together and avoid naming conflicts.
// Run with (from the 22-packages directory):
//   javac com/example/tools/Calculator.java com/example/PackagesDemo.java
//   java com.example.PackagesDemo
import com.example.tools.Calculator;

public class PackagesDemo {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.add(10, 20));
    }
}

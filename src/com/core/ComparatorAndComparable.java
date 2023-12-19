package com.core;

import java.util.ArrayList;
import java.util.Collections;

public class ComparatorAndComparable {
    public static void main(String[] args) {
        var e1 = new Employee("name1", 36);
        var e2 = new Employee("name3", 56);
        var e3 = new Employee("name2", 56);
        var employees = new ArrayList<Employee>() {{
            add(e1);
            add(e2);
            add(e3);
        }};
        //Collections.sort(employees); //Avoid changing on source(employees)
        //System.out.println("Sorted list: " + employees);
        System.out.println("Comparison using comparable interface");
        employees.stream().sorted().forEach(System.out::println);
        System.out.println("Comparison using comparator interface");
        employees.stream().sorted((emp1, emp2) -> emp2.age - emp1.age).forEach(System.out::println);
    }
}

class Employee implements Comparable<Employee> {
    String name;
    int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(Employee o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

package com.core;

/*
    Declare the class as final so it can’t be extended.
    Make all of the fields private so that direct access is not allowed.
    Don’t provide setter methods for variables.
    Make all mutable fields final so that a field’s value can be assigned only once.
    Initialize all fields using a constructor method performing deep copy.
    Perform cloning of objects in the getter methods to return a copy rather than returning the actual object reference.
 */

import java.util.HashMap;

public class ImmutableClass {
    public static void main(String[] args) {
        var marks = new HashMap<String, Integer>();
        marks.put("Math", 100);
        marks.put("English", 89);
        Student s1 = new Student("Sam", new Address("address-1", "address-2"), marks);
    }
}

final class Student {
    private final String name;
    private final Address address;
    private final HashMap<String, Integer> marks;

    public Student(String name, Address address, HashMap<String, Integer> marks) {
        this.name = name;
        this.address = new Address(address.getAddressLine1(), address.getAddressLine2());

        var tempMap = new HashMap<String, Integer>();

        for (String key : marks.keySet()) {
            tempMap.put(key, marks.get(key));
        }
        this.marks = tempMap;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return new Address(address.getAddressLine1(), address.getAddressLine2());
    }

    public HashMap<String, String> getTestMap() {
        return (HashMap<String, String>) marks.clone(); //TODO: Is shallow copy sufficient ?
    }
}

class Address {
    private final String addressLine1;

    private final String addressLine2;

    public Address(String addressLine1, String addressLine2) {
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

}

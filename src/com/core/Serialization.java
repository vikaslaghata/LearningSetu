package com.core;

import java.io.*;

public class Serialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge(20);
        person.setName("Joe");
        Address address = new Address();
        address.setHouseNumber(121);
        person.setAddress(address);

        FileOutputStream fileOutputStream
                = new FileOutputStream("myfile.txt");
        ObjectOutputStream objectOutputStream
                = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(person);
        objectOutputStream.flush();
        objectOutputStream.close();

        FileInputStream fileInputStream
                = new FileInputStream("myfile.txt");
        ObjectInputStream objectInputStream
                = new ObjectInputStream(fileInputStream);
        Person p2 = (Person) objectInputStream.readObject();
        objectInputStream.close();

        System.out.println("Person name: " + p2.getName() + " age= " + p2.getAge() + " and address= " + p2.getAddress());
    }
}

class Person implements Serializable {
    //The JVM associates a version (long) number with each serializable class.
    // We use it to verify that the saved and loaded objects have the same attributes, and thus are compatible on serialization.
    //If the attributes are not the same then InvalidClassExceptions throws, to avoid this we provide own versionUID.
    private static final long serialVersionUID = 1L;

    private int age;
    private String name;

    // private transient Address address; // use transient if you don't want to serialize Address
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
     * Below commented optional writeObject and readObject methods are to get more control over serialization process.
     */
   /* @Serial
    //Serial annotation indicates that an annotated field or method is part of the serialization mechanism, optionally
    // require for compile time checks
    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        System.out.println("Serializing using Person writeObject");
        oos.defaultWriteObject();
        oos.writeObject(address.getHouseNumber());
    }

    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        System.out.println("deserializing using Person readObject");
        ois.defaultReadObject();
        Integer houseNumber = (Integer) ois.readObject();
        Address a = new Address();
        a.setHouseNumber(houseNumber);
        this.setAddress(a);
    }*/

}

//When a class implements the java.io.Serializable interface, all its sub-classes are
// serializable as well or else a NotSerializableException will be thrown. Here Address must implement Serializable
// or set it to transient in Employee class.
class Address implements Serializable {

    private int houseNumber;

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "houseNumber=" + houseNumber +
                '}';
    }
}

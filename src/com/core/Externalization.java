package com.core;

import java.io.*;

//Check Serialization.java before this.
//Externalizable extends from the java.io.Serializable marker interface.
//Any class that implements Externalizable interface should override the writeExternal(), readExternal() methods.
//In case of Externalizable, it’s the programmer who should take care of the whole serialization and also deserialization process.
public class Externalization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String OUTPUT_FILE = "myfile.txt";

        var person2 = new Person2();
        person2.setName("Ram manohar");
        person2.setAge(34);

        //Try with resources.
        try (var fileOutputStream = new FileOutputStream(OUTPUT_FILE);
             var objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            person2.writeExternal(objectOutputStream);
        }

        try (FileInputStream fileInputStream = new FileInputStream(OUTPUT_FILE);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            var p2 = new Person2();
            p2.readExternal(objectInputStream);
            System.out.println("Person name: " + p2.getName() + " age= " + p2.getAge());
        }
    }
}

class Person2 implements Externalizable {
    private static final long serialVersionUID = 1L;

    private int age;
    private String name;

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

    @Override
    //Any class that implements Externalizable interface should override the writeExternal(), readExternal() methods.
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);//write String
        out.writeInt(age);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = in.readUTF();
        this.age = in.readInt();
    }

}

//Similar to serializable sub-class must implement Externalizable but contray to serializable we need to implement the read/write methods as well.
class Employee2 extends Person2 implements Externalizable {

    private static final long serialVersionUID = 1L;

    private String climate;
    private Double population;

    // getters, setters

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // super.writeExternal(out), super.readExternal(in) called to save/restore the parent class fields as well.
        super.writeExternal(out);
        out.writeUTF(climate);
    }

    @Override
    public void readExternal(ObjectInput in)
            throws IOException, ClassNotFoundException {

        super.readExternal(in);
        this.climate = in.readUTF();
    }
}
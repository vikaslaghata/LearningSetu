package com.core;

public class SingletonClass {
    public static void main(String[] args) {
        //Singleton ss = new Singleton(); // compilation error
        Singleton ss = Singleton.getInstance();
    }
}

class Singleton {
    private Singleton() {

    }

    private static Singleton obj;

    public static Singleton getInstance() {
        if (obj == null) {
            obj = new Singleton();
        }
        return obj;
    }
}

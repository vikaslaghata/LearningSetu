package com.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Generics {
    public static void main(String[] args) {
        //Class level generic
        var stringDao = new GeneralDAO<String>();
        stringDao.save("Something");
        String result = stringDao.get(12345L);
        //--------------------------------------------------------------
        //generic class with more than one type of parameter
        var pair = new Pair<String, Integer>("Something", 420);// constructor is forcing to pass exact parameter types.
        //--------------------------------------------------------------
        // By extends, we can restrict the type parameter to be subtypes of a concrete type.
        // var daoForNumbers = new GeneralDAOForNumbers<String>(); //String is not within bound
        var daoForNumbers = new GeneralDAOForNumbers<Integer>(); // Integer is ok because Integer extends Number
        var daoForNumbers2 = new GeneralDAOForNumbers<Number>();
        // You can also do multiple bounds: class WindowApp<T extends JFrame & Runnable>
        //--------------------------------------------------------------
        List<Integer> integers = Arrays.asList(0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1);
        int countResult = count(integers, 1);

    }

    //Methods also can be generic:
    public static <T extends Number> int count(Collection<? extends Number> col, T item) { // ( ? extend ) is the wild card
        //public static <T extends Number> int count(Collection<T> col, T item) { //another way

        return 0;
    }
}

class GeneralDAO<T> {
    public void save(T entity) {
        // code to save entity details to database
    }

    public T get(long id) {
        // code to get details from database...
        // ...and return a T object
        T t = null; // new T() will not work.
        return t;
    }
}

class Pair<T, U> {
    T first;
    U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }
}

class GeneralDAOForNumbers<T extends Number> {
    public void save(T entity) {
        // code to save entity details to database
    }
}

package com.datastructure;

public class MyLinkedList {
    public static void main(String[] args) {
        var list = new MyList();
        list.add(10);
        list.add(20);
        list.add(30);
        list.print();
        //list.print();
    }
}

class MyList {
    private Node head = null;

    public void add(int value) {
        var newNode = new Node(value, null);
        if (head == null) {
            head = newNode;
        } else {
            Node pointer = head;
            while (pointer.next != null) {
                pointer = pointer.next;
            }
            pointer.next = newNode;
        }
    }

    public void print() {
        Node pointer = head;
        while (pointer != null) {
            System.out.println(pointer.value);
            pointer = pointer.next;
        }
    }

    class Node {
        int value;
        Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }
}

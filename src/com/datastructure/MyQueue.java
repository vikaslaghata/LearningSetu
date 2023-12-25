package com.datastructure;

//Creating A Linked List Node
class Node {
    int data;       // the integer data
    Node next;      // the pointer to the next node

    public Node(int data) {
        // this is used to set the data in the allocated node and return it
        this.data = data;
        this.next = null;
    }
}

class Queue {
    private static Node rear = null, front = null;
    private static int count = 0;

    // This function is used to dequeue the front element in the queue
    public static int dequeue()
    // this function is used to delete the queue elements at the beginning
    {
        if (front == null) {
            System.out.println("\nQueue is in Underflow condition");
            System.exit(-1);
        }

        Node temp = front;
        System.out.printf("The element which you want to remove from the queue is %d\n", temp.data);

        // is used to point to the front to the next node
        front = front.next;

        // if the list becomes empty
        if (front == null) {
            rear = null;
        }

        // we are decreasing the count of nodes the  by 1
        count -= 1;

        // it is used to return the removed item
        return temp.data;
    }

    // this function is used to add an item to the queue
    public static void enqueue(int item)
    // insertion of the elements at the end of the queue
    {
        // used to allocate a new node in a heap
        Node node = new Node(item);
        System.out.format("Inserting %d\n", item);

        // this is used to check a special case: when the queue is empty
        if (front == null) {
            // initialize both front and rear(nodes pointing at both the front and rear nodes)
            front = node;
            rear = node;
        } else {
            // update the rear pointer of the queue
            rear.next = node;
            rear = node;
        }

        // this is used to increase the node's count by 1
        count += 1;
    }

    // this function returns the top or the front/peek element in a queue
    public static int peek() {
        // it is used to check for an empty queue
        if (front == null) {
            System.exit(-1);
        }

        return front.data;
    }

    // This function is used to check if the queue is empty or not
    public static boolean isEmpty() {
        return rear == null && front == null;
    }

    // This function is used to return the size of the queue
    private static int size() {
        return count;
    }
}

class MyQueue {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);
        q.enqueue(5);
        System.out.printf("The front element of the queue is %d\n", q.peek());

        q.dequeue();
        q.dequeue();
        q.dequeue();
        q.dequeue();
        // to check whether the queue is empty or not
        if (q.isEmpty()) {
            System.out.println("The queue is empty");
        } else {
            System.out.println("The queue is not empty");
        }
    }
}

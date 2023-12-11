package ait.deadlock.example;

public class DeadLock {

    public static void main(String[] args) {
        final Object monitor = new Object();;
        final Object monitor2 = new Object();;


        Thread t1 = new Thread() {
            public void run() {
                synchronized (monitor) {
                    System.out.println("Thread 1: locked resource 1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1: Waiting for lock 2...");

                    synchronized (monitor2) {
                        System.out.println("Thread 1: locked resource 2");
                    }
                }
            }
        };


        Thread t2 = new Thread() {
            public void run() {
                synchronized (monitor2) {
                    System.out.println("Thread 2: locked resource 2");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 2: Waiting for lock 1...");

                    synchronized (monitor) {
                        System.out.println("Thread 2: locked resource 1");
                    }
                }
            }
        };


        t1.start();
        t2.start();
    }
}

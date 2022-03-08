package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixThread extends AccessMatrix {
    static int tid;
    int threadName;
    int numRequests = random.nextInt((10-5)+1)+5;
    int yield;
    int CurrentDomain;
    MatrixThread(int tid) {
        this.tid = tid;
        threadName = ThreadLocalRandom.current().nextInt(3, 8);
        yield = ThreadLocalRandom.current().nextInt(3,8);
        for (int i = 0; i < domainRange; i++) {
            Thread.currentThread().setName("Thread: " + threadNum);
        }
        this.CurrentDomain=tid+objectsRange;
    }

    public static int getTid() {
        return MatrixThread.tid;
    }
    public static String threadName(){
        return Thread.currentThread().getName();
    }
    int getCurrentDomain() {
        return CurrentDomain;
    }
    public void read(){
        //need to change to actual file number, but this is a temp solution
        System.out.println(Thread.currentThread().getName() + " resource contains " + charArray[random.nextInt(objectsRange)]);
    }
    public void write(){
        int randomString = random.nextInt(6);
        if(randomString == 0){
            System.out.println("Thread-"+threadNum + " writes 'Green' to resource F" + getCurrentDomain());
        }
        else if(randomString == 1){
            System.out.println("Thread-"+threadNum + " writes 'Red' to resource F" + getCurrentDomain());
        }
        else if(randomString == 2){
            System.out.println("Thread-"+threadNum + " writes 'Yellow' to resource F" + getCurrentDomain());
        }
        else if(randomString == 3){
            System.out.println("Thread-"+threadNum + " writes 'Blue' to resource F" + getCurrentDomain());
        }
        else if(randomString == 4){
            System.out.println("Thread-"+threadNum + " writes 'Purple' to resource F" + getCurrentDomain());
        }
        else {
            System.out.println("Thread-"+threadNum + " writes 'Rainbow' to resource F" + getCurrentDomain());

        }
    }

    @Override
    public void run() {

        for (int runs = 0; runs < numRequests; runs++) {
            //randomNum is "X" from the project specs
            int randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange));

            if (randomNum <= objectsRange) {

                int operation = random.nextInt(2);
                if (operation == 0) {
                    System.out.println(threadName() + " attempting to read resource: ");
                    lock[randomNum].lock();
                    read();
                    System.out.println(Thread.currentThread().getName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock[randomNum].unlock();
                    System.out.println(threadName() + " Operation complete");
                }
                else {
                    System.out.println(threadName() + " attempting to write to resource F" + randomNum);
                    lock[randomNum].lock();
                    write();
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock[randomNum].unlock();
                    System.out.println(threadName() + " Operation complete");
                }
            }
            //semaphore locks must be used to access objects properly
            if (objectsRange < randomNum && randomNum <= objectsRange + domainRange) {
                while (randomNum == CurrentDomain) {
                    randomNum = ThreadLocalRandom.current().nextInt(domainRange);
                }

                //else
                //switch to domain (domain + objects) - randomNum
                lock[randomNum].lock();
                System.out.println(threadName() + " is trying to switch from D" + CurrentDomain +" to D" + CurrentDomain);
                if (matrix[0][randomNum].equals("A")) {
                    System.out.println(threadName() + " Operation successful, switching to D"+MatrixThread.getTid());
                    //switch to the row needed
                    //setMatrixPosition(domainRange, objectsRange + domainRange);
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock[randomNum].unlock();
                    System.out.println(threadName() + " Operation complete");
                } else {
                    System.out.println(threadName() + " Operation failed, permission denied");
                }
            }
        }
    }
}

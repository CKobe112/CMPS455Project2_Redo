package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixThread extends AccessMatrix {
    static int tid;
    int threadName;
    int numRequests = random.nextInt((10-5)+1)+5;
    int yield;
    int CurrentDomain;
    int randomObject;
    MatrixThread(int tid) {
        MatrixThread.tid = tid;
        threadName = ThreadLocalRandom.current().nextInt(3, 8);
        yield = ThreadLocalRandom.current().nextInt(3,8);
        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);

        for (int i = 0; i < domainRange; i++) {
            Thread.currentThread().setName("Thread-" + tid);
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
        System.out.println(threadName() + " resource contains " + charArray[random.nextInt(objectsRange)]);
    }
    public void write(){
        int randomString = random.nextInt(6);
        if(randomString == 0){
            System.out.println(threadName()+ " writes 'Green' to resource F" + randomObject);
        }
        else if(randomString == 1){
            System.out.println(threadName() + " writes 'Red' to resource F" + randomObject);
        }
        else if(randomString == 2){
            System.out.println(threadName() + " writes 'Yellow' to resource F" + randomObject);
        }
        else if(randomString == 3){
            System.out.println(threadName() + " writes 'Blue' to resource F" + randomObject);
        }
        else if(randomString == 4){
            System.out.println(threadName() + " writes 'Purple' to resource F" + randomObject);
        }
        else {
            System.out.println(threadName() + " writes 'Rainbow' to resource F" + randomObject);

        }
    }

    @Override
    public void run() {

        for (int runs = 0; runs < numRequests; runs++) {
            //randomNum is "X" from the project specs
            int randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange));
            int swap = (domainRange+objectsRange)-randomNum;
            if (randomNum <= objectsRange) {
                //generate another number between 0 and 1
                randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange)+1);
                int operation = random.nextInt(2);
                if (operation == 0) {
                    lock[randomObject].lock();
                    System.out.println(threadName() + " attempting to read resource F" + randomObject);
                    read();
                    System.out.println(Thread.currentThread().getName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");
                }
                else {
                    lock[randomObject].lock();
                    System.out.println(threadName() + " attempting to write to resource F" + randomObject);
                    write();
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    System.out.println(threadName() + " Operation complete");
                    lock[randomObject].unlock();
                }
            }
            if (objectsRange < randomNum && randomNum <= (objectsRange + domainRange)) {
                int randomRow = ThreadLocalRandom.current().nextInt(domainRange);
                int randomColumn = ThreadLocalRandom.current().nextInt(domainRange+objectsRange);
                while (randomNum == CurrentDomain) {
                    randomNum = ThreadLocalRandom.current().nextInt(domainRange);
                }
                while(randomColumn <= objectsRange){
                    randomColumn = ThreadLocalRandom.current().nextInt(domainRange+objectsRange);
                }

                //else
                System.out.println(threadName() + " is trying to switch from D" + CurrentDomain +" to D" + swap);
                if (matrix[randomRow][CurrentDomain].equals("A  ")) {
                    lock[randomObject].lock();
                    System.out.println(threadName() + " Operation successful, switching to D"+swap);
                    //switch to the row needed
                    //setMatrixPosition(domainRange, objectsRange + domainRange);
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    System.out.println(threadName() + " Operation complete");
                    lock[randomObject].unlock();
                } else {
                    System.out.println(threadName() + " Operation failed, permission denied");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                }
            }
        }
    }
}

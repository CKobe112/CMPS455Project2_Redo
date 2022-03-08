package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixThread extends AccessMatrix {
    static int tid;
    int threadName;
    int numRequests = random.nextInt((10-5)+1)+5;
    int yield;
    static int CurrentDomain;
    int randomObject;
    MatrixThread(int tid) {
        MatrixThread.tid = tid;
        threadName = ThreadLocalRandom.current().nextInt(3, 8);
        yield = ThreadLocalRandom.current().nextInt(3,8);
        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
        //keeps track of domains on end of matrix, meaning tid + objectsRange would give use the positions after the objects
        CurrentDomain = tid + objectsRange;
        //need to initialize each thread to a domain from start

        for (int i = 0; i < domainRange; i++) {
            Thread.currentThread().setName("Thread-" + tid);
        }

    }

    public static int getTid() {
        return MatrixThread.tid;
    }
    public static String threadName(){
        return Thread.currentThread().getName();
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
            //domain we need to switch to when requested
            //do not want to check for columns that are before the domains
//            while (swap < objectsRange){
//                randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange));
//                swap = CurrentDomain - randomNum;
//            }

            if (randomNum <= objectsRange) {
                //generate another number between 0 and 1
                randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange));
                int operation = random.nextInt(2);

                if (operation == 0) {
                    lock[randomObject].lock();
                    System.out.println(threadName() + " attempting to read resource F" + randomObject);
                    //check read rights
                    if (Arbitrator.checkReadPerm()) {
                        read();
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                    }

                    else{
                        System.out.println(threadName() + " does not have right to read");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                    }

                    System.out.println(Thread.currentThread().getName() + " Yielding " + yield + " times");

                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");
                }
                else {
                    lock[randomObject].lock();
                    System.out.println(threadName() + " attempting to write to resource F" + randomObject);
                    if(Arbitrator.checkWritePerm()) {
                        write();
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                    }
                    else{
                        System.out.println(threadName() + " does not have permission to write");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                    }
                    System.out.println(threadName() + " Yielding " + yield + " times");


                    System.out.println(threadName() + " Operation complete");
                    lock[randomObject].unlock();
                }
            }
            if (objectsRange < randomNum && randomNum <= (objectsRange + domainRange)) { //checking to see if parsing domains

                int randomColumn = ThreadLocalRandom.current().nextInt(domainRange+objectsRange);

                while (randomNum == CurrentDomain) {
                    randomNum = ThreadLocalRandom.current().nextInt((domainRange + objectsRange));
                }
                randomNum = randomNum - objectsRange;
                while(randomColumn <= objectsRange){
                    randomColumn = ThreadLocalRandom.current().nextInt(domainRange+objectsRange);
                }

                System.out.println(threadName() + " is trying to switch from D" + tid +" to D" + randomNum);
                //check if has permission to switch
                if(Arbitrator.checkSwitch()) {
                    lock[randomObject].lock();

                    //set matrix position, i think swap should be equal to something else
                    CurrentDomain = randomNum;
                    System.out.println(threadName() + " Operation successful, switching to D" + randomNum);

                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();
                    }


                    System.out.println(threadName() + " Operation complete");
                    lock[randomObject].unlock();
                }
                else {
                    System.out.println(threadName() + " Operation failed, permission denied");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();

                    }
                }
            }
        }
    }
}
//write method is not working, perhaps need to assign domain to each thread
//swapping position also not working, little confused
// is this the correct way to implement random number of requests per thread
//
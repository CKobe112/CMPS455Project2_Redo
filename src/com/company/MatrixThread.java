//Begin code changes by Austin Mestayer

package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class MatrixThread extends AccessMatrix {
    static int tid;
    int numRequests = random.nextInt((10-5)+1)+5;
    int yield;
    static int CurrentDomain;
    int randomObject;
    MatrixThread(int tid) {
        MatrixThread.tid = tid;
        yield = ThreadLocalRandom.current().nextInt(3,8);
        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
        //keeps track of domains on end of matrix, meaning tid + objectsRange would give use the positions after the objects
        CurrentDomain = tid + objectsRange;
    }
    public static String threadName(){
        return MatrixThread.currentThread().getName();
    }

    public static int getTid() {
        return tid;
    }
    public static int getCurrentDomain() {
        return CurrentDomain;
    }

//    public static void updateThreadName(){
//        MatrixThread.currentThread().setName("[Thread: " + MatrixThread.getTid() + "(D"+ (MatrixThread.getCurrentDomain() - objectsRange) + ")]");
//    }

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
            int randomNum = ThreadLocalRandom.current().nextInt(0,objectsRange + domainRange);
            if (randomNum <= objectsRange) {
                //generate another number between 0 and 1
                int operation = random.nextInt(2);

                if (operation == 0) {
                    System.out.println(threadName() + " attempting to read resource F" + randomObject);
                    //check read rights
                    lock[randomObject].lock();
                    if (Arbitrator.checkReadPerm((CurrentDomain - objectsRange),randomObject)) {

                        read();
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                    }

                    else{
                        System.out.println(threadName() + " does not have right to read");
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");
                }
                else {

                    System.out.println(threadName() + " attempting to write to resource F" + randomObject);
                    lock[randomObject].lock();
                    if(Arbitrator.checkWritePerm((CurrentDomain - objectsRange),randomObject)) {

                        write();
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                    }
                    else{
                        System.out.println(threadName() + " does not have permission to write");
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                    }

                }
            }
            if (objectsRange < randomNum && randomNum <= (objectsRange + domainRange)) {

                while (randomNum == CurrentDomain) {
                    if((objectsRange > domainRange)){
                        randomNum = ThreadLocalRandom.current().nextInt(domainRange,(objectsRange + domainRange));

                    }
                    if (objectsRange < domainRange){
                        randomNum = ThreadLocalRandom.current().nextInt(objectsRange,(objectsRange + domainRange)) ;

                    }
                }
                int swap = domainRange + objectsRange - randomNum ;
                System.out.println(threadName() + " is trying to switch from D" + tid +" to D" + swap);
                //check if has permission to switch
                lock[randomObject].lock();
                if(Arbitrator.checkSwitch(tid,swap)) {
                    //update CurrentDomain
                    CurrentDomain = swap;
                    tid = swap;
                    System.out.println(threadName() + " Operation successful,           switching to D" + CurrentDomain);
                    //updateThreadName();
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();
                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");

                }
                else {
                    System.out.println(threadName() + " Operation failed, permission denied");
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();

                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");

                }

            }
        }
    }

}
//End Code Changes by Austin Mestayer

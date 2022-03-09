//Begin code changes by Austin Mestayer

package com.company;

import javax.swing.plaf.ColorUIResource;
import java.util.concurrent.ThreadLocalRandom;

public class MatrixThread extends AccessMatrix {
    static int tid;
    int numRequests = random.nextInt((10-5)+1)+5;
    int yield;
    static int CurrentDomain;
    int randomObject;
    MatrixThread(int tid, int CurrentDomain) {
        MatrixThread.tid = tid;
        MatrixThread.CurrentDomain = CurrentDomain;
        yield = ThreadLocalRandom.current().nextInt(3,8);
        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
        //keeps track of domains on end of matrix, meaning tid + objectsRange would give use the positions after the objects
    }
    public static String threadName(){
        return MatrixThread.currentThread().getName();
    }

    public void read(){
        //need to change to actual file number, but this is a temp solution
        System.out.println(threadName() + " resource contains " + charArray[random.nextInt(objectsRange)]);
    }
    public void write(){
        int randomString = random.nextInt(6);
        if(randomString == 0){
            System.out.println(threadName()+ " writes 'Green' to resource F" + randomObject);
            charArray[randomObject] = "Green";
        }
        else if(randomString == 1){
            System.out.println(threadName() + " writes 'Red' to resource F" + randomObject);
            charArray[randomObject] = "Red";
        }
        else if(randomString == 2){
            System.out.println(threadName() + " writes 'Yellow' to resource F" + randomObject);
            charArray[randomObject] = "Yellow";
        }
        else if(randomString == 3){
            System.out.println(threadName() + " writes 'Blue' to resource F" + randomObject);
            charArray[randomObject] = "Blue";
        }
        else if(randomString == 4){
            System.out.println(threadName() + " writes 'Purple' to resource F" + randomObject);
            charArray[randomObject] = "Purple";
        }
        else {
            System.out.println(threadName() + " writes 'Rainbow' to resource F" + randomObject);
            charArray[randomObject] = "Rainbow";

        }

    }
    public static void setCurrentDomain(int currentDomain) {
        CurrentDomain = currentDomain;
    }

    @Override
    public void run() {
        MatrixThread.yield();
        for (int runs = 0; runs < numRequests; runs++) {
            //randomNum is "X" from the project specs
            int randomNum = ThreadLocalRandom.current().nextInt(0,(objectsRange + domainRange));
            if (randomNum <= objectsRange) {
                //generate another number between 0 and 1
                int operation = random.nextInt(2);

                if (operation == 0) {
                    System.out.println(threadName() + " attempting to read resource F" + randomObject);
                    //check read rights
                    lock[randomObject].lock();
                    if (Arbitrator.checkReadPerm(tid,randomObject)) {

                        read();
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
                    }

                    else{
                        System.out.println(threadName() + " does not have right to read");
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
                    }

                }
                else {

                    System.out.println(threadName() + " attempting to write to resource F" + randomObject);
                    lock[randomObject].lock();
                    if(Arbitrator.checkWritePerm(tid,randomObject)) {

                        write();
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }

                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
                    }
                    else{
                        System.out.println(threadName() + " does not have permission to write");
                        System.out.println(threadName() + " Yielding " + yield + " times");
                        for (int i = 0; i < yield; i++) {
                            MatrixThread.yield();
                        }
                        lock[randomObject].unlock();
                        System.out.println(threadName() + " Operation complete");
                        randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
                    }

                }
            }
            else if (objectsRange < randomNum && randomNum <= (objectsRange + domainRange)) {

                while (randomNum == CurrentDomain) {
                    randomNum = ThreadLocalRandom.current().nextInt(objectsRange,(objectsRange + domainRange));
                }
                randomNum = randomNum -  objectsRange;

                System.out.println(threadName() + " is trying to switch from D" + (CurrentDomain - objectsRange) +" to D" + randomNum);
                //check if has permission to switch
                lock[randomObject].lock();
                if(Arbitrator.checkSwitch(tid,randomNum)) {
                    System.out.println(threadName() + " Operation successful, switching to D" + randomNum);
                    //update CurrentDomain
                    setCurrentDomain(randomNum);
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();
                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");
                    randomObject = ThreadLocalRandom.current().nextInt(objectsRange);
                }
                else {
                    System.out.println(threadName() + " Operation failed, permission denied");
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        MatrixThread.yield();

                    }
                    lock[randomObject].unlock();
                    System.out.println(threadName() + " Operation complete");
                    randomObject = ThreadLocalRandom.current().nextInt(objectsRange);

                }

            }
        }
    }
    public static int getTid() {
        return tid;
    }
    public static int getCurrentDomain() {
        return CurrentDomain;
    }

}
//End Code Changes by Austin Mestayer

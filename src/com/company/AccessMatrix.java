package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Construct an access matrix with permission for each object based on the domain
 */
public class AccessMatrix extends Thread {

    static int domainRange;
    static int objectsRange;
    static int domain;
    static int column;
    static String[][] matrix;
    static Lock lock = new ReentrantLock();
    static Random random = new Random();
    static String[] charArray;
    static final int threadNum = ThreadLocalRandom.current().nextInt(3,8);
    int yield = ThreadLocalRandom.current().nextInt(3,8);

    public static String randomDomain(){
        int r = random.nextInt(2);
        if (r==0){
            return "A";
        }
        else{
            return "N";
        }
    }
    public static String randomPermission(){
        int r = random.nextInt(3);
        if (r==0){
            return "R";
        }
        else if (r==1){
            return "W";
        }
        else {
            return "R/W";
        }

    }
    public static String threadName(){
        return Thread.currentThread().getName();
    }





    public void read(){
        //need to change to actual file number, but this is a temp solution
        int charPosition = random.nextInt(objectsRange);
        System.out.println(Thread.currentThread().getName() + " resource contains " + charArray[(int) Thread.currentThread().getId()]);
    }
    public String write(){
        int randomString = random.nextInt(6);
        if(randomString == 0){
            return "Green";
        }
        else if(randomString == 1){
            return "Red";
        }
        else if(randomString == 2){
            return "Yellow";
        }
        else if(randomString == 3){
            return "Blue";
        }
        else if(randomString == 4){
            return "Purple";
        }
        else {
            return "Rainbow";
        }
    }
    public String setMatrixPosition(int rows, int columns){
        return matrix[rows][columns];
    }



    //getters and setters, probably wont need most of these
    public static int getDomainRange() {
        return domainRange;
    }

    public static int getObjectsRange() {
        return objectsRange;
    }

    public static int getDomain() {
        return domain;
    }

    public static void setDomain(int domain) {
        AccessMatrix.domain = domain;
    }

    public static int getColumn() {
        return column;
    }

    public static void setColumn(int column) {
        AccessMatrix.column = column;
    }

    public static String[][] getMatrix() {
        return matrix;
    }

//    public static void setMatrix(String[][] matrix) {
//        AccessMatrix.matrix = matrix;
//    }

//    public static Lock getLock() {
//        return lock;
//    }
//
//    public static void setLock(Lock lock) {
//        AccessMatrix.lock = lock;
//    }
}


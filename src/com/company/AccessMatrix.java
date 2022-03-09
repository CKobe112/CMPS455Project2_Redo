//Begin code changes by Austin Mestayer
package com.company;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Construct an access matrix with permission for each object based on the domain
 */
public class AccessMatrix extends Thread {

    static int domainRange;
    static int objectsRange;
    static String[][] matrix;
    static Lock[] lock;
    static Random random = new Random();
    static String[] charArray;

    public static String randomDomain(){
        int r = random.nextInt(2);
        if (r==0){
            return "A  ";
        }
        else{
            return "N  ";
        }
    }
    public static String randomPermission(){
        int r = random.nextInt(3);
        if (r==0){
            return "R  ";
        }
        else if (r==1){
            return "W  ";
        }
        else {
            return "R/W";
        }

    }
    public String writeArray(){
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
}
//End Code Changes by Austin Mestayer

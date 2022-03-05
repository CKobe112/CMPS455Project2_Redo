package com.company;

import java.util.Random;
import java.util.concurrent.locks.Lock;

/**
 * Construct an access matrix with permission for each object based on the domain
 */
public class AccessMatrix extends Thread {
    static int domain;
    static int objects;
    static int permissions;
    static String[][] matrix;
    static Lock lock;
    public static String randomDomain(){
        int r = 1 + (int) (Math.random() * ((1) + 1));
        if (r==0){
            return "A";
        }
        else{
            return "N";
        }
    }
    public static String randomPermission(){
        int r = 1 + (int) (Math.random() * ((2 ) + 1));
        if (r==0){
            return "R";
        }
        else if (r==1){
            return "W";
        }
        else if (r==2){
            return "R/W";
        }
        else{
            return "";
        }
    }
//    public static void read(int row, int column){
//        matrix[row][column] = "R";
//    }
//
//    public static void write(int row, int column){
//        matrix[row][column] = "W";
//    }
//
//    public static void change(int row, int column){
//        matrix[row][column] = "allow";
//    }
}


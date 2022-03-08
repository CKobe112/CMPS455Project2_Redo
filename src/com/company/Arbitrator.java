package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Arbitrator extends MatrixThread {
    static int randomRow = ThreadLocalRandom.current().nextInt(domainRange);

    Arbitrator(int tid) {
        super(tid);
    }

    public static Boolean checkSwitch(){
        return matrix[randomRow][CurrentDomain].equals("A  ");
    }
    public static Boolean checkReadPerm(){
        if (matrix[randomRow][CurrentDomain].equals("R  ")){
            return true;
        }
        else return matrix[randomRow][CurrentDomain].equals("R/W");
    }
    public static Boolean checkWritePerm(){
        if(matrix[randomRow][CurrentDomain].equals("R/W")){
            return true;
        }
        else return matrix[randomRow][CurrentDomain].equals("W  ");
    }

}

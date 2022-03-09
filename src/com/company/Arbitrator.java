//Begin code changes by Austin Mestayer

package com.company;

public class Arbitrator extends MatrixThread {
    Arbitrator(int tid, int currentDomain) {
        super(tid,currentDomain);
    }
    
    public static Boolean checkSwitch(int row, int column){
        return matrix[row][column].equals("A  ");
    }

    public static Boolean checkReadPerm(int row, int column){
        if (matrix[row][column].equals("R  ")){
            return true;
        }
        else return matrix[row][column].equals("R/W");
    }

    public static Boolean checkWritePerm(int row, int column){
        if(matrix[row][column].equals("R/W")){
            return true;
        }
        else return matrix[row][column].equals("W  ");
    }

}
//End Code Changes by Austin Mestayer

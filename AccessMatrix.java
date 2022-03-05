package Interface;

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

class MatrixInit extends AccessMatrix{
    int yield;

    /**
     * initializes the matrix with a header and now i need to randomly assign permissions to each domain
     */
    MatrixInit(){
        domain = 1 + (int) (Math.random() * ((7 - 3) + 1));
        objects = 1 + (int) (Math.random() * ((7 - 3) + 1));
        permissions = 1 + (int) (Math.random() * ((4 - 1) + 1));
        this.yield = 1 + (int) (Math.random() * ((7 - 3) + 1));
        matrix = new String[domain][objects + domain];


        /**
         * The header for the 2D array
         */
        System.out.println("Domain count: " + domain);
        System.out.println("Object count: "+ objects);
        System.out.print("Domain/Objects ");
        for (int i = 0; i < objects; i++) {
            System.out.print("F"+ i + " ");
        }
        for (int i = 0; i < domain; i++) {
            System.out.print("D"+ i + " ");
        }
        System.out.println("    ");

        for (int i = 0; i < domain; i++) {
            for (int j = 0; j < domain + objects; j++) {
                matrix[i][j] = "";
            }
        }

        /**
         * sets the array to R or W or R/W or allow randomly
         */
        /*for (int i = 0; i < domain; i++) {
            for (int j = 0; j < domain + objects; j++) {
                switch (permissions) {
                    case 1:
                        for (int i = 0; i < domain; i++) {
                            for (int j = 0; j < domain + objects; j++) {
                                matrix[i][j] = "R";
                            }
                        }
                        break;
                    case 2:
                        for (int i = 0; i < domain; i++) {
                            for (int j = 0; j < domain + objects; j++) {
                                matrix[i][j] = "W";
                            }
                        }
                        break;
                    case 3:
                        for (int i = 0; i < domain; i++) {
                            for (int j = 0; j < domain + objects; j++) {
                                matrix[i][permissions] = "R";
                            }
                        }
                        break;
                    case 4:
                        for (int i = 0; i < domain; i++) {
                            for (int j = 0; j < domain + objects; j++) {
                                matrix[i][permissions] = "R";
                            }
                        }
                        break;
                    default:
                        for (int i = 0; i < domain; i++) {
                            for (int j = 0; j < domain + objects; j++) {
                                matrix[i][permissions] = "R";
                            }
                        }

                }
            }
        }*/
        /**
         * sets the first column equal to the domain header
         */
//        for (int i = 0; i < domain; i++) {
//            matrix[i][0] = "D" + i;
//        }
        for (int i = 0; i < domain; i++) {
            for (int j = 0; j < domain + objects; j++) {
                if(j < domain) {
                    matrix[i][j] = randomDomain();
                }
                else{
                    matrix[i][j] = randomPermission();
                }
            }
        }
        /**
         * prints the matrix
         */
        for (int i = 0; i < matrix.length; i++) {
            //System.out.print("            D"+ i + " "+"\n");
            for(int j = 0; j < matrix[i].length;j++){
                System.out.print("  " + matrix[i][j] + "");
            }
            System.out.println();
        }
        //System.out.println(permissions);

    }
    @Override
    public void run(){
//        int randomRow = 1 + (int) (Math.random() * ((7 - 3) + 1));
//        int randomColumn = 1 + (int) (Math.random() * ((7 - 3) + 1));


        //System.out.println();
        //System.out.println(randomRow);
        //System.out.println(randomColumn);
//        for (int i = 0; i < yield; i++) {
//            AccessMatrix.yield();
//        }
    }
}

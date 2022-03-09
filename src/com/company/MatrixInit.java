//Begin code changes by Austin Mestayer

package com.company;

import java.util.concurrent.locks.ReentrantLock;

class MatrixInit extends AccessMatrix {

    MatrixInit() {
        domainRange = random.nextInt((7-3)+1)+3;
        objectsRange = random.nextInt((7-3)+1)+3;
        charArray = new String[objectsRange];
        matrix = new String[domainRange][objectsRange + domainRange];
        lock = new ReentrantLock[objectsRange];
        for (int i = 0; i < objectsRange;i++) {
            lock[i] = new ReentrantLock();
        }

        /**
         * initialize char array
         */
        for (int i = 0; i < objectsRange; i++) {
            charArray[i] = writeArray();
        }


        /**
         * initialize 2D array
         */
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < domainRange + objectsRange; j++) {
                matrix[i][j] = "";
            }
        }

        /**
         * set random permissions and randomly allow for switching
         */
        for (int i = 0; i < domainRange; i++) {
            for (int j = 0; j < domainRange+objectsRange; j++) {
                if (j >= objectsRange) {
                    matrix[i][j] = randomDomain();
                } else {
                    matrix[i][j] = randomPermission();
                }
            }
        }
        /**
         * The header for the 2D array
         */
        System.out.println("Domain count: " + domainRange);
        System.out.println("Object count: " + objectsRange);
        System.out.print("Domain/Objects \t");
        for (int i = 0; i < objectsRange; i++) {
            System.out.print("F" + i + " | ");
        }
        for (int i = 0; i < domainRange; i++) {
            System.out.print("D" + i + " | ");
        }
        System.out.println("    ");
        /**
         * prints the matrix
         */
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("\t\tD"+ i + " | ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("  " + matrix[i][j] + "");
            }
            System.out.println();
        }
    }
    @Override
    public void run() {
        for (int i = 0; i < domainRange; i++) {
            MatrixThread threads = new MatrixThread(i);
            threads.setName("[Thread: " + MatrixThread.getTid() + "(D"+ (MatrixThread.getCurrentDomain() - objectsRange) + ")]");
            threads.start();
        }
    }
}
//End Code Changes by Austin Mestayer

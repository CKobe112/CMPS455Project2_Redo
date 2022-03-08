package com.company;

import java.util.concurrent.locks.ReentrantLock;

class MatrixInit extends AccessMatrix {

    MatrixInit() {
        domainRange = random.nextInt((7-3)+1)+3;
        objectsRange = random.nextInt((7-3)+1)+3;
        charArray = new String[objectsRange];
        matrix = new String[domainRange][objectsRange + domainRange];
        lock = new ReentrantLock[domainRange + objectsRange];
        //final int threadNum = ThreadLocalRandom.current().nextInt(3,8);
        for (int i = 0; i < domainRange + objectsRange;i++) {
            lock[i] = new ReentrantLock();
        }

        /**
         * initialize char array
         */
        for (int i = 0; i < objectsRange; i++) {
            charArray[i] = writeArray();
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
         * initialize 2D array
         */
        for (int i = 0; i < domainRange; i++) {
            for (int j = 0; j < domainRange + objectsRange; j++) {
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
         * prints the matrix
         */
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("\t\tD"+ i + " | ");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("  " + matrix[i][j] + "");
            }
            System.out.println();
        }
//        for (int i = 0; i < objects; i++) {
//            System.out.println(charArray[i]);
//        }
    }

    @Override
    public void run() {
        //int randomRow = 1 + (int) (Math.random() * ((7 - 3) + 1));
        //int randomColumn = 1 + (int) (Math.random() * ((7 - 3) + 1));
        for (int i = 0; i < domainRange; i++) {
            MatrixThread threads = new MatrixThread(i);
            threads.start();
        }

            //System.out.println();
            //System.out.println(randomRow);
            //System.out.println(randomColumn);
//        for (int i = 0; i < yield; i++) {
//            AccessMatrix.yield();
//        }




    }
}

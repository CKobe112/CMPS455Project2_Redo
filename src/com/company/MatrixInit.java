package com.company;

import java.util.concurrent.ThreadLocalRandom;

class MatrixInit extends AccessMatrix {

    MatrixInit(int domainRange) {
        AccessMatrix.domainRange = domainRange;
        domainRange = random.nextInt((7-3)+1)+3;
        objectsRange = random.nextInt((7-3)+1)+3;
        charArray = new String[objectsRange];
        matrix = new String[domainRange][objectsRange + domainRange];
        //final int threadNum = ThreadLocalRandom.current().nextInt(3,8);
        /**
         * set name of threads
         */
        for (int i = 0; i < domainRange; i++) {
            Thread.currentThread().setName("Thread: " + threadNum);
        }
        /**
         * initialize char array
         */
        for (int i = 0; i < objectsRange; i++) {
            charArray[i] = write();
        }
        /**
         * The header for the 2D array
         */
        System.out.println("Domain count: " + domainRange);
        System.out.println("Object count: " + objectsRange);
        System.out.print("Domain/Objects ");
        for (int i = 0; i < objectsRange; i++) {
            System.out.print("F" + i + " ");
        }
        for (int i = 0; i < domainRange; i++) {
            System.out.print("D" + i + " ");
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
            for (int j = 0; j < domainRange + objectsRange; j++) {
                if (j > domainRange) {
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
            //System.out.print("            D"+ i + " "+"\n");
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print("   " + matrix[i][j] + " ");
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
        int numRequests = random.nextInt((10-5)+1)+5;
        for (int i = 0; i < domainRange; i++) {
            MatrixInit threads = new MatrixInit(i);
            threads.start();
        }
        for (int runs = 0; runs < numRequests; runs++) {
            //randomNum is "X" from the project specs
            int randomNum = random.nextInt((domainRange + objectsRange)+1);
            if (randomNum <= objectsRange) {
                int operation = random.nextInt(2);
                if (operation == 0) {
                    System.out.println(threadName() + " attempting to read resource: ");
                    lock.lock();
                    read();
                    System.out.println(Thread.currentThread().getName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock.unlock();
                    System.out.println(threadName() + " Operation complete");
                } else {
                    System.out.println(threadName() + " attempting to write to resource");
                    lock.lock();
                    write();
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock.unlock();
                    System.out.println(threadName() + " Operation complete");
                }
            }
            //semaphore locks must be used to access objects properly
            if (objectsRange < randomNum && randomNum <= objectsRange + domainRange) {
                while (randomNum == domainRange) {
                    randomNum = random.nextInt(2);
                }
                //else
                //switch to domain (domain + objects) - randomNum
                lock.lock();
                System.out.println(threadName() + " is trying to switch from .. to ..");
                if (matrix[domainRange][objectsRange + domainRange].equals("A")) {
                    System.out.println("Operation successful, switching to ..");
                    //switch to the row needed
                    setMatrixPosition(domainRange,objectsRange + domainRange);
                    System.out.println(threadName() + " Yielding " + yield + " times");
                    for (int i = 0; i < yield; i++) {
                        Thread.yield();
                    }
                    lock.unlock();
                    System.out.println(" Operation complete");
                } else {
                    System.out.println("Operation failed, permission denied");
                }
            }
            //System.out.println();
            //System.out.println(randomRow);
            //System.out.println(randomColumn);
//        for (int i = 0; i < yield; i++) {
//            AccessMatrix.yield();
//        }


        }
        System.out.println(numRequests);

    }
}

package com.company;

class MatrixInit extends AccessMatrix {
    int yield;

    /**
     * initializes the matrix with a header and now i need to randomly assign permissions to each domain
     */
    MatrixInit() {
        domainRange = 1 + (int) (Math.random() * ((7 - 3) + 1));
        objectsRange = 1 + (int) (Math.random() * ((7 - 3) + 1));
        this.yield = 1 + (int) (Math.random() * ((7 - 3) + 1));
        charArray = new String[objectsRange];
        matrix = new String[domainRange][objectsRange + domainRange];

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
//        int randomRow = 1 + (int) (Math.random() * ((7 - 3) + 1));
//        int randomColumn = 1 + (int) (Math.random() * ((7 - 3) + 1);
        int numRequests = 1 + (int) (Math.random() * ((10 - 5) + 1));
        //while x = domain, reinitialize x
        int randomNum = random.nextInt(domainRange + objectsRange);
        if (randomNum <= objectsRange){
            int operation = random.nextInt(2);
            if ( operation ==0){
                read();
            }
            else{
                write();
            }
        }
        //semaphore locks must be used to access objects properly
        if (objectsRange < randomNum && randomNum <= objectsRange + domainRange){
            while(randomNum == domainRange){
                randomNum = random.nextInt(2);
            }
            //else
            //switch to domain (domain + objects) - randomNum
            if (matrix[getDomainRange()][getDomainRange() + getObjectsRange()].equals("A")){
                //setDomain();
            }


        }

        //System.out.println();
        //System.out.println(randomRow);
        //System.out.println(randomColumn);
//        for (int i = 0; i < yield; i++) {
//            AccessMatrix.yield();
//        }




    }
}

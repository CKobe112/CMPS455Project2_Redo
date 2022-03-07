package com.company;

import java.util.LinkedList;
import java.util.Random;

public class listHandler {

    Random rand = new Random();

    public void buildF(AccessList AL, int fileRange, int domainRange){

        int current;
        int perms;
        int prev;

        prev = 0;
        int j = 0;
        current = fileRange;

        while(current < fileRange + domainRange) {

            current = current + rand.nextInt(domainRange);
            while(current == prev)
                current = current + rand.nextInt(domainRange);
            if (current >= fileRange + domainRange)
                break;

            perms = rand.nextInt(3) + 1;
            prev = current;

            System.out.println("current j: "+j+" current position: "+current);

            AL.addList(current);
            AL.addList(perms);
            j++;
            for(int k = 0; k < AL.getSize();k++)
                System.out.print(AL.getList(k)+" ");
            System.out.println();
        }
        System.out.println("File Build Complete.");
    }

    public void buildD(AccessList AL, int fileRange, int domainRange){

        int current = fileRange;
        int prev = 0;
        int j = 0;

        while(current < fileRange + domainRange - 1){

            current = current + rand.nextInt(domainRange);
            while(current == prev)
                current = current + rand.nextInt(domainRange);
            if (current >= fileRange + domainRange)
                break;
            prev = current;

            System.out.println("current j: "+j+" current position: "+current);

            AL.addList(current);
            j++;
            for(int k = 0; k < AL.getSize();k++)
                System.out.print(AL.getList(k)+" ");
            System.out.println();
        }
        System.out.println("Domain Build Complete");
    }

    public void displayList(LinkedList<AccessList> mainList, int fileRange, int domainRange){

    }

}


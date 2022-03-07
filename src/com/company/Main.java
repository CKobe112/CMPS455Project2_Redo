package com.company;

import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.util.*;

public class Main {

    static int fileRange;
    static int domainRange;
    static int domainID;
    static Random rand = new Random();

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        else if (args.length > 1){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        String arg0 = args[0];
        arg0 = arg0.toLowerCase();

        if (arg0.length() > 1){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        else if (arg0.equals("m")){
            System.out.println("Execute command M");
            runAL();
        }

        else if (arg0.equals("l")){
            System.out.println("Execute command L");
        }

        else if (arg0.equals("c")){
            System.out.println("Execute command C");
        }

        else
            System.out.println("Please enter a valid command 4");

    }

    public static void runAL(){

        fileRange = TtoS();
        domainRange = TtoS();
        System.out.println("fileRange: "+fileRange+" domainRange: "+domainRange);
        LinkedList<AccessList> mainList = buildAL(fileRange, domainRange);

        for (int i = 0; i < 5; i++){
            RunList t1 = new RunList(mainList, fileRange, domainRange,i);
            t1.start();

        }
    }

    public static LinkedList<AccessList> buildAL(int fileRange, int domainRange) {

        LinkedList<AccessList> mainList = new LinkedList<>();

        for (int i = 0; i < fileRange; i++) {

            AccessList internalList = new AccessList();

            System.out.println("start loop");
            int current = fileRange;
            System.out.println("current i: " + i + " current position: " + current);
            int perms;
            int prev = 0;
            int j = 0;
            System.out.println("current i: " + i + " current position: " + current);

            while (current < fileRange + domainRange-1) {

                current = current + rand.nextInt(domainRange);
                if (current >= fileRange + domainRange)
                    break;
                if (current == prev)
                    current++;
                perms = rand.nextInt(3) + 1;
                prev = current;

                System.out.println("current j: " + j + " current position: " + current);

                //if (current >= fileRange + domainRange)
                //break;

                internalList.addList(current);
                internalList.addList(perms);
                j++;
                for (int k = 0; k < internalList.getSize(); k++)
                    System.out.print(internalList.getList(k));
                System.out.println();

            }
            mainList.add(internalList);
            System.out.println("complete");

        }
        System.out.println("mainList complete");
        return mainList;
    }

    public static int TtoS(){
        return rand.nextInt(5) + 3;
    }
}

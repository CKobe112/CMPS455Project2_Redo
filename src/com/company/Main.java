package com.company;

import java.util.*;

public class Main {

    //begin code by Chris Kobe
    static int fileRange;
    static int domainRange;
    static Random rand = new Random();

    public static void main(String[] args){

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
            MatrixInit matrix = new MatrixInit();
            matrix.run();
        }

        else if (arg0.equals("l")){
            System.out.println("Execute command L");
            runAL();
        }

        else if (arg0.equals("c")){
            System.out.println("Execute command C");
            runCL();
        }

        else
            System.out.println("Please enter a valid command 4");

    }

    //end code by Chris Kobe

    //begin code by Chris Kobe

    //sets ranges then passes information and list to threads
    //number of threads is based off of domain range
    public static void runAL(){

        fileRange = TtoS();
        domainRange = TtoS();
        System.out.println("File Range: "+fileRange+" Domain Range: "+domainRange);
        System.out.println();
        LinkedList<AccessList> mainList = buildAL(fileRange, domainRange);
        listHandler LB = new listHandler();

        LB.displayList(mainList, fileRange, domainRange);
        System.out.println();

        for (int i = 0; i < domainRange; i++){
            RunList t1 = new RunList(mainList, fileRange, domainRange, i, i);
            t1.setName(String.valueOf(i));
            t1.start();

        }
    }

    //builds the list using the listHandler class
    public static LinkedList<AccessList> buildAL(int fileRange, int domainRange) {

        LinkedList<AccessList> mainList = new LinkedList<>();
        listHandler LB = new listHandler();

        for (int i = 0; i < fileRange + domainRange; i++) {

            AccessList internalList = new AccessList();
            LB.buildF(internalList, fileRange, domainRange);
            LB.buildD(internalList, fileRange, domainRange, mainList.size());
            mainList.add(internalList);

        }
        return mainList;
    }

    public static int TtoS(){
        return rand.nextInt(5) + 3;
    }
    //end code by Chris Kobe

    //Beginning of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List

    public static void runCL(){

        domainRange = TtoS();
        fileRange = TtoS();
        System.out.println("Domain Count: " + domainRange);
        System.out.println("Object Count: " + fileRange);
        LinkedList<CapabilityList> mainCapList = buildCL(domainRange, fileRange);
        CapListHndlr LB = new CapListHndlr();

        LB.displayList(mainCapList, fileRange, domainRange);

        for (int i = 0; i < domainRange; i++){
            DisplayCapList t1 = new DisplayCapList(mainCapList, domainRange, fileRange,i, i);
            t1.setName(String.valueOf(i));
            t1.start();

        }
    }

    public static LinkedList<CapabilityList> buildCL(int domainRange, int fileRange) {

        LinkedList<CapabilityList> mainCapList = new LinkedList<>();
        CapListHndlr LB = new CapListHndlr();

        for (int i = 0; i < domainRange + fileRange; i++) {

            CapabilityList internalList = new CapabilityList();
            LB.buildFile(internalList, fileRange, domainRange);
            mainCapList.add(internalList);
        }
        return mainCapList;
    }
}
//End of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List

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
            System.out.println();
            runAL();
        }

        else if (arg0.equals("l")){
            System.out.println("Execute command L");
        }

        else if (arg0.equals("c")){
            System.out.println("Execute command C");
        }

        else
            System.out.println("Please enter a valid command.");

    }
    //end code by Chris Kobe

    //begin code by Chris Kobe

    //sets ranges then passes information and list to threads
    //number of threads is based off of domain range
    public static void runAL(){

        fileRange = TtoS();
        domainRange = TtoS();
        System.out.println("fileRange: "+fileRange+" domainRange: "+domainRange);
        System.out.println();
        LinkedList<AccessList> mainList = buildAL(fileRange, domainRange);
        listHandler LB = new listHandler();

        LB.displayList(mainList, fileRange, domainRange);
        System.out.println();

        for (int i = 0; i < domainRange; i++){
            RunList t1 = new RunList(mainList, fileRange, domainRange,i, i);
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
}

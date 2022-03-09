package com.company;

import sun.awt.image.ImageWatched;

import javax.swing.*;
import java.util.*;

public class Main {

    static int fileRange;
    static int domainRange;
    static int domainID;
    static Random rand = new Random();

    public static void main(String[] args) throws InterruptedException {

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

    public static void runAL() throws InterruptedException {

        fileRange = TtoS();
        domainRange = TtoS();
        System.out.println("fileRange: "+fileRange+" domainRange: "+domainRange);
        LinkedList<AccessList> mainList = buildAL(fileRange, domainRange);
        listHandler LB = new listHandler();

        System.out.println("Dsize: "+mainList.get(0).getDSize());

        LB.displayList(mainList, fileRange, domainRange);

        for (int i = 0; i < domainRange; i++){
            RunList t1 = new RunList(mainList, fileRange, domainRange,i, i);
            t1.setName(String.valueOf(i));
            t1.start();

        }
    }

    public static LinkedList<AccessList> buildAL(int fileRange, int domainRange) {

        LinkedList<AccessList> mainList = new LinkedList<>();
        listHandler LB = new listHandler();

        for (int i = 0; i < fileRange + domainRange; i++) {

            AccessList internalList = new AccessList();
            //System.out.println("Start file builder iteration: " + i);
            LB.buildF(internalList, fileRange, domainRange);
            LB.buildD(internalList, fileRange, domainRange, mainList.size());
            mainList.add(internalList);

        }
        return mainList;
    }

    public static int TtoS(){
        return rand.nextInt(5) + 3;
    }

    //Beginning of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List

    public static void runCL() throws InterruptedException {

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

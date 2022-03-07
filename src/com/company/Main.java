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
        LinkedList<AccessList> mainList = buildAL(fileRange, domainRange);

        for (int i = 0; i < 5; i++){
            RunList t1 = new RunList(mainList, fileRange, domainRange,i);
            t1.start();

        }
    }

    public static LinkedList<AccessList> buildAL(int fileRange, int domainRange){

        LinkedList<AccessList> mainList = new LinkedList<>();

        return mainList;

    }

    public static int TtoS(){
        return rand.nextInt(5) + 3;
    }
}

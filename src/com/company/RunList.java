package com.company;

import sun.awt.image.ImageWatched;

import java.util.*;

public class RunList extends AccessList {

    public int fileRange;
    public int domainRange;
    public int domainID;
    public LinkedList<AccessList> mainList;
    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas", "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"};

    public RunList(LinkedList<AccessList> mainList, int fileRange, int domainRange, int domainID){
        this.mainList = mainList;
        this.fileRange = fileRange;
        this.domainRange = domainRange;
        this.domainID = domainID;
    }

    public void run(){

        for(int i = 0; i < 5; i++)
            System.out.println("This is a test run "+domainID);
        for(int i = 0; i < 5; i++)
            Thread.yield();

    }

    public void arbitratorF(LinkedList<AccessList> mainList, int fileRange, int domainRange, int domainID) throws InterruptedException {
        mainList.get(1).getSem();
    }
}





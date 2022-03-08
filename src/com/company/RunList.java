package com.company;

import sun.awt.image.ImageWatched;

import java.util.*;

public class RunList extends AccessList {

    public  int fileRange;
    public int domainRange;
    public int domainID;
    public int threadID;
    public LinkedList<AccessList> mainList;
    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas",
                            "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"}; //11 entries

    public RunList(LinkedList<AccessList> mainList, int fileRange, int domainRange, int domainID, int threadID){
        this.mainList = mainList;
        this.fileRange = fileRange;
        this.domainRange = domainRange;
        this.domainID = domainID;
        this.threadID = threadID;
    }

    public void run() {

        Thread.yield();

        for(int i = 0; i < 10; i++){

            int op = rand.nextInt(fileRange+domainRange);

            //Read-Write
            if (op < fileRange) {
                try {
                    readWrite(mainList.get(domainID), op, domainID, threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void readWrite(AccessList AL, int op, int domainID, int threadID) throws InterruptedException {

        boolean decision = false;
        int location = 0;
        int RW = rand.nextInt(2);

        if (RW == 0){
            output(threadID, domainID);
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Attempting to read: F"+(op + 1));
        }
        if (RW == 1){
            output(threadID, domainID);
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Attempting to write: F"+(op + 1));
        }

        for(int i = 0; i < AL.getFSize(); i+=2){
            if(domainID == AL.getFList(i)) {
                location = i;
            }
        }

        if(AL.getFList(location + 1) == 1 && RW == 0)
            decision = true;
        else if(AL.getFList(location + 1) == 2 && RW == 1)
            decision = true;
        else if(AL.getFList(location + 1) == 3)
            decision = true;

        if(decision && RW == 0){

            AL.getSem();
            Thread.yield();
            output(threadID, domainID);
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): File F"+(op+1)+" contains the data:"+AL.getData());
            AL.releaseSem();
            Thread.yield();

        }

        if(decision && RW == 1){

            AL.getSem();
            Thread.yield();
            String newData = randomData[rand.nextInt(11)];
            AL.setData(newData);
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): File F"+(op+1)+" written to with the data: "+newData);
            AL.releaseSem();
            Thread.yield();

        }

        else{
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Access denied to file F"+(op+1)+".");
            Thread.yield();
        }

    }

    public void output(int threadID, int domainID){
        System.out.print("Thread #"+threadID+"(D"+(domainID+1)+"):");
    }
}





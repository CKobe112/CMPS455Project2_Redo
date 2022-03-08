//Beginning of code written by Chris Kobe, and edited by Spencer Vosloh

package com.company;

import sun.awt.image.ImageWatched;

import java.util.*;

public class DisplayCapList extends CapabilityList {

    public  int fileRange;
    public int domainRange;
    public int domainID;
    public int threadID;
    public LinkedList<CapabilityList> mainCapList;
    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas", "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"}; //11 entries

    public DisplayCapList(LinkedList<CapabilityList> mainCapList, int fileRange, int domainRange, int domainID, int threadID){
        this.mainCapList = mainCapList;
        this.fileRange = fileRange;
        this.domainRange = domainRange;
        this.domainID = domainID;
        this.threadID = threadID;
    }

    public void run() {

        Thread.yield();

        for(int i = 0; i < 10; i++){

            int output = rand.nextInt(domainRange);

            //Read-Write
            if (output < fileRange) {
                try {
                    readWrite(mainCapList.get(i), output, domainID, threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.yield();

            //Switch Domain
            if (output >= fileRange && output < (fileRange + domainRange)) {
                while ((fileRange + domainRange - output) == domainID && output < domainRange) {
                    output = rand.nextInt(fileRange + domainRange);
                }
                output = (fileRange + domainRange) - output;
                try {
                    switchDomain(mainCapList.get(i), output, domainID, threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Thread.yield();
        }

        System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Operation complete.");

    }

    public void readWrite(CapabilityList CL, int output, int domainID, int threadID) throws InterruptedException {

        boolean decision = false;
        int location = 99;
        int RW = rand.nextInt(2);

        System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): received size " + CL.getFileSize());
        if (RW == 0){
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Attempting to read: F" + (output + 1));
        }
        if (RW == 1){
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Attempting to write: F" + (output + 1));
        }

        for(int i = 0; i < CL.getFileSize(); i+=2){
            if(domainID == CL.getFileList(i)) {
                location = i;
            }

        }

        if (location == 99){
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Access denied to file F" + (output + 1) +".");
            Thread.yield();
            return;
        }

        System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): received perm " + (CL.getFileList(location + 1) + " @ location " + location));

        if(CL.getFileList(location + 1) == 1 && RW == 0)
            decision = true;
        else if(CL.getFileList(location + 1) == 2 && RW == 1)
            decision = true;
        else if(CL.getFileList(location + 1) == 3)
            decision = true;

        if(decision && RW == 0){

            CL.getCap();
            Thread.yield();
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): File F" + (output + 1) + " contains the data:"+ CL.getData());
            CL.releaseCap();
            Thread.yield();

        }

        if(decision && RW == 1){

            CL.getCap();
            Thread.yield();
            String newData = randomData[rand.nextInt(11)];
            CL.setData(newData);
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): File F" + (output + 1) + " written to with the data: " + newData);
            CL.releaseCap();
            Thread.yield();

        }

        if(!decision){
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Access denied to file F" + (output + 1) + ".");
            Thread.yield();
        }

    }

    public void switchDomain(CapabilityList CL, int target, int domainID, int threadID) throws InterruptedException {

        System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Attempting to switch to Domain " + (target) + ".");

        boolean decision = false;

        for (int i = 0; i < CL.getDomainSize(); i++){
            if(domainID == CL.getDomainList(i)){
                decision = true;
            }
        }

        if(decision){
            CL.getCap();
            Thread.yield();
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Operation successful, switching to Domain " + target + ".");
            this.domainID = target - 1;
            Thread.yield();
            CL.releaseCap();
        }

        if(!decision){
            System.out.println("Thread #" + threadID + "(D" + (domainID + 1) + "): Operation failed, Domain access denied.");
        }
    }
}

//End of code written by Chris Kobe, and edited by Spencer Vosloh
package com.company;

import java.util.*;

//begin code by Chris Kobe

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

    //runnable thread that does all the work
    public void run() {

        Thread.yield();

        for(int i = 0; i < rand.nextInt(15)+5; i++){

            int op = rand.nextInt(fileRange+domainRange);

            //Read-Write
            if (op < fileRange) {
                try {
                    readWrite(mainList.get(op), op, domainID, threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //nightmare logic to keep domain switching from trying to read OOB indexes
            if (op >= fileRange && op < (fileRange + domainRange)) {
                while ((fileRange + domainRange - op) == domainID && op > domainRange) {
                    op = rand.nextInt(fileRange + domainRange);
                }
                op = (fileRange + domainRange) - op;
                if(op >= domainRange)
                    op = rand.nextInt(domainRange);

                try {
                    domainSwitch(mainList.get(op), op, domainID, threadID);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Operation complete.");

    }

    //arbitrator function for file read write
    public void readWrite(AccessList AL, int op, int domainID, int threadID) throws InterruptedException {

        boolean decision = false;
        int location = 99;
        int RW = rand.nextInt(2);
        int halt;

        if (RW == 0){
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Attempting to read: F"+(op + 1));
        }
        if (RW == 1){
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Attempting to write: F"+(op + 1));
        }

        for(int i = 0; i < AL.getFSize(); i+=2){
            if(domainID == AL.getFList(i)) {
                location = i;
            }

        }

        if (location == 99){
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Access denied to file F"+(op+1)+".");
            Thread.yield();
            return;
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
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): File F"+(op+1)+" contains the data: "+AL.getData());

            halt = TtoS();
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): yielding for "+halt+" cycles.");
            for(int i = 0; i < halt; i++){
                Thread.yield();
            }

            AL.releaseSem();
            Thread.yield();

        }

        if(decision && RW == 1){

            AL.getSem();
            Thread.yield();
            String newData = randomData[rand.nextInt(11)];
            AL.setData(newData);
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): File F"+(op+1)+" written to with the data: "+newData);

            halt = TtoS();
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): yielding for "+halt+" cycles.");
            for(int i = 0; i < halt; i++){
                Thread.yield();
            }

            AL.releaseSem();
            Thread.yield();

        }

        if(!decision){
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Access denied to file F"+(op+1)+".");
            Thread.yield();
        }

    }

    //arbitrator function for domain switching
    public void domainSwitch(AccessList AL, int target, int domainID, int threadID) throws InterruptedException {

        System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Attempting to switch to Domain "+(target + 1)+".");

        boolean decision = false;
        int halt;

        for (int i = 0; i < AL.getDSize(); i++){
            if(domainID == AL.getDList(i)){
                decision = true;
            }
        }

        if(decision){
            AL.getSem();
            Thread.yield();
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Operation successful, switching to Domain "+(target + 1)+".");
            this.domainID = target;

            halt = TtoS();
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): yielding for "+halt+" cycles.");
            for(int i = 0; i < halt; i++){
                Thread.yield();
            }

            AL.releaseSem();
            Thread.yield();
        }

        if(!decision){
            System.out.println("Thread #"+threadID+"(D"+(domainID+1)+"): Operation failed, Domain access denied.");
            Thread.yield();
        }

    }

    public int TtoS(){
        return rand.nextInt(5) + 3;
    }
}
//end code by Chris Kobe




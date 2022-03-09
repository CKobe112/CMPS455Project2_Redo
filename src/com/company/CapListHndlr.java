//Beginning of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List

package com.company;

import java.util.LinkedList;
import java.util.Random;

public class CapListHndlr {

    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas", "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"}; //11 entries

    public void buildFile(CapabilityList CL, int fileRange, int domainRange){

        int crntThrd;
        int permissions;
        int prevThrd;

        prevThrd = -1;
        int j = 0;
        crntThrd = 0;

        while(crntThrd < fileRange) {

            crntThrd = crntThrd + rand.nextInt(domainRange);
            while(crntThrd == prevThrd)
                crntThrd = crntThrd + rand.nextInt(domainRange);
            if (crntThrd >= domainRange)
                break;

            permissions = rand.nextInt(3) + 1;
            prevThrd = crntThrd;

            CL.addFileList(crntThrd);
            CL.addFileList(permissions);
            j++;
            for(int k = 0; k < CL.getFileSize();k++)
                System.out.print(CL.getFileList(k)+" ");
            System.out.println();
        }
        CL.setData(randomData[rand.nextInt(11)]);
        System.out.println();
    }

    public void displayList(LinkedList<CapabilityList> mainCapList, int fileRange, int domainRange){

        for (int i = 0; i < domainRange; i++){

            System.out.print("D" + (i + 1) + " --> ");

            int k = 0;
            while(k < mainCapList.get(k).getFileSize()) {
                System.out.print("F" + (mainCapList.get(i).getFileList(k) + 1) + ":" + permissions(mainCapList.get(i).getFileList(k + 1)) + ", ");
                k += 2;
            }

            for (int l = 0; l < fileRange; l++) {
                int j = 0;
                while(j < mainCapList.get(l).getDomainSize()){
                    System.out.print("D" + (mainCapList.get(l).getDomainList(j) + 1) + ":access, ");
                    j++;
                }
            }
            System.out.println();
        }
    }

    public String permissions(int i){
        switch (i){
            case 1: return "R";
            case 2: return "W";
            case 3: return "R/W";
        }
        return "ERROR";
    }

}

//End of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List
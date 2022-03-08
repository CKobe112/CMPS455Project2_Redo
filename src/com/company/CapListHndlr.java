//Beginning of code written by Chris Kobe, and edited by Spencer Vosloh

package com.company;

import java.util.LinkedList;
import java.util.Random;

public class CapListHndlr {

    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas", "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"}; //11 entries

    public void buildFile(CapabilityList CL, int fileRange, int domainRange){

        int current;
        int perms;
        int prev;

        prev = -1;
        int j = 0;
        current = 0;

        while(current < fileRange) {

            current = current + rand.nextInt(domainRange);
            while(current == prev)
                current = current + rand.nextInt(domainRange);
            if (current >= domainRange)
                break;

            perms = rand.nextInt(3) + 1;
            prev = current;

            //System.out.println("current j: "+j+" current position: "+current);

            CL.addFileList(current);
            CL.addFileList(perms);
            j++;
            for(int k = 0; k < CL.getFileSize();k++)
                System.out.print(CL.getFileList(k)+" ");
            System.out.println();
        }
        CL.setData(randomData[rand.nextInt(11)]);
        //System.out.println("File Build Complete.");
        System.out.println();
    }

    public void displayList(LinkedList<CapabilityList> mainCapList, int fileRange, int domainRange){

        for (int i = 0; i < domainRange; i++){

            System.out.print("D" + (i + 1) + " --> ");

            int j = 0;
            while(j < mainCapList.get(i).getFileSize()){
                System.out.print("F" + (mainCapList.get(i).getFileList(j) + 1) + ":" + perms(mainCapList.get(i).getFileList(j + 1)) + ", ");
                j += 2;

                for (int l = 0; l < fileRange; l++) {
                    int k = 0;
                    while(k < mainCapList.get(k).getDomainSize()) {
                        System.out.print("D" + (mainCapList.get(l).getDomainList(k) + 1) + ":access, ");
                        k++;
                    }
                }
            }
            System.out.println();
        }
    }

    public String perms(int i){
        switch (i){
            case 1: return "R";
            case 2: return "W";
            case 3: return "R/W";
        }
        return "ERROR";
    }

}

//End of code written by Chris Kobe, and edited by Spencer Vosloh
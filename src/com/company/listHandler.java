package com.company;

import java.util.LinkedList;
import java.util.Random;

public class listHandler {

    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas",
                            "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"}; //11 entries

    public void buildF(AccessList AL, int fileRange, int domainRange){

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

            AL.addFList(current);
            AL.addFList(perms);
            j++;
            for(int k = 0; k < AL.getFSize();k++)
                System.out.print(AL.getFList(k)+" ");
            System.out.println();
        }
        AL.setData(randomData[rand.nextInt(11)]);
        //System.out.println("File Build Complete.");
        System.out.println();
    }

    public void buildD(AccessList AL, int fileRange, int domainRange, int iteration){

        int current = 0;
        int prev = iteration;
        int j = 0;

        while(current < domainRange){

            current = current + rand.nextInt(domainRange);
            while(current == prev)
                current = current + rand.nextInt(domainRange);

            if (current >= domainRange)
                break;
            prev = current;

            System.out.println("current j: "+j+" current position: "+current);

            AL.addDList(current);
            j++;
            for(int k = 0; k < AL.getDSize();k++)
                System.out.print(AL.getDList(k)+" ");
            System.out.println();
        }
        System.out.println("Domain Build Complete");
    }

    public void displayList(LinkedList<AccessList> mainList, int fileRange, int domainRange){

        for (int i = 0; i < fileRange; i++){

            int j=0;
            System.out.print("F"+(i+1)+"--> ");

            while(j < mainList.get(i).getFSize()){

                System.out.print("D"+(mainList.get(i).getFList(j)+1)+":"+perms(mainList.get(i).getFList(j+1))+" ");
                j += 2;

            }
            System.out.println();
        }

        for (int i = 0; i < domainRange; i++){

            System.out.print("D"+(i+1)+"--> ");
            int j = 0;
            while(j < mainList.get(i).getDSize()) {
                System.out.print("D" + (mainList.get(i).getDList(j) + 1) + ":access ");
                j++;
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


package com.company;

import java.util.*;

public class RunList extends AccessList {

    public int fileRange;
    public int domainRange;
    Random rand = new Random();
    String[] randomData = {"Peter", "John", "James", "Andrew", "Philip", "Thomas", "Bartholomew", "Matthew", "Simon", "Judas", "Matthias"};

    public void run() {

        int fileRange = TtoS();
        int domainRange = TtoS();
        System.out.println("test " + fileRange + " " + domainRange);

        LinkedList<AccessList> mainList = buildList(fileRange, domainRange);
        System.out.println("test 2 " + fileRange + " " + domainRange);

        for (int i = 0; i < mainList.size(); i++) {

            System.out.print("F" +i+": ");

            for (int j = 0; j < mainList.get(i).alSize(); j++) {
                System.out.print("D"+mainList.get(i).getList(i)+": "+mainList.get(i+1).getList(i+1)+" ");

            }
            System.out.println();

        }
    }

    public void mainThread(LinkedList<AccessList> mainList, int domainID){

    }

    public LinkedList<AccessList> buildList(int fileRange, int domainRange){

        LinkedList<AccessList> mainList = new LinkedList<>();
        AccessList internalList = new AccessList();

        for (int i = 0; i < fileRange; i++) {

            System.out.println("start loop");
            int current = fileRange;
            System.out.println("current i: "+i+" current position: "+current);
            int perms;
            int prev = 0;
            int j = 0;
            System.out.println("current i: "+i+" current position: "+current);

            while(current < fileRange + domainRange) {

                current = current + rand.nextInt(domainRange);
                if(current == prev)
                    current++;
                perms = rand.nextInt(3) + 1;
                prev = current;

                System.out.println("current j: "+j+" current position: "+current);

                //if (current >= fileRange + domainRange)
                    //break;

                //internalList.addList(current);
                //internalList.addList(perms);
                j++;
                //System.out.println("list: " + internalList.getList(0));

            }
            //mainList.add(internalList);
            //internalList.clearList();
            System.out.println("complete");
        }

        return mainList;
    }

    public void buildInner(){

    }

    public int TtoS(){
        int i = rand.nextInt(5)+3;
        return i;
    }
}

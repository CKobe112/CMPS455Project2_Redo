package com.company;

import java.util.*;
import java.util.concurrent.Semaphore;

public class AccessList extends Thread {

    public int fileRange;
    public int domainRange;

    //constructor for the objects inside the linked list
    public class internalList{

        final private Semaphore sem = new Semaphore(1);
        LinkedList<Integer> intLL = new LinkedList<>();
        private String data = "";

        public void getSem() throws InterruptedException {
            this.sem.acquire();
        }

        public void releaseSem() {
            this.sem.release();
        }

        public void readData(){
            System.out.print(this.data);
        }

        public void writeData(String in){
            this.data = in;
        }

        public int getDR(){
            return domainRange;
        }

        public int getFR(){
            return fileRange;
        }

        //random int from 3 to 7
        public int TtoS(){
            Random random = new Random();
            return (random.nextInt(7 - 3 + 1) + 3);
        }

        public LinkedList<AccessList> buildList(int fileRange, int domainRange){

            LinkedList<AccessList> mainList = new LinkedList<>();

            for (int i = 0; i < fileRange; i++){

                int prev;
                int current;
                AccessList internal = new AccessList();

                for (int j = 0; j < domainRange; j++){

                }

            }

            return mainList;

        }

    }


}

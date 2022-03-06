package com.company;

import java.util.*;
import java.util.concurrent.Semaphore;

public class AccessList {

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
            return random.nextInt();
        }

    }


}

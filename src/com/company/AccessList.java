package com.company;

import java.util.*;
import java.util.concurrent.Semaphore;

//begin code by Chris Kobe
public class AccessList extends Thread{

    public Semaphore sem = new Semaphore(1);
    public LinkedList<Integer> FL;
    public LinkedList<Integer> DL;
    String data;

    //class that manages all access requests, stores data, and stores a semaphore per object
    //two LL one for files one for domains, indexes point to the source file/domain while the lists contain permissions
    public AccessList(){
        Semaphore sem1 = sem;
        FL = new LinkedList<>();
        DL = new LinkedList<>();
        this.data = "";
    }

    //getters and setters for the object
    public String getData(){
        return this.data;
    }

    public void setData(String in){
        this.data = in;
    }

    public void getSem() throws InterruptedException {
        this.sem.acquire();
    }

    public void releaseSem(){
        this.sem.release();
    }

    public void addFList(int i){
        this.FL.add(i);
    }

    public int getFList(int i){
        return this.FL.get(i);
    }

    public int getFSize(){
        return this.FL.size();
    }

    public void addDList(int i){
        this.DL.add(i);
    }

    public int getDList(int i){
        return this.DL.get(i);
    }

    public int getDSize(){
        return this.DL.size();
    }
}
//end code by Chris Kobe
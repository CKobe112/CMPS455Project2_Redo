package com.company;

import java.util.*;
import java.util.concurrent.Semaphore;

public class AccessList extends Thread{

    public Semaphore sem = new Semaphore(1);
    public LinkedList<Integer> LL;
    String data = "";

    public AccessList(){
        Semaphore sem1 = sem;
        LL = new LinkedList<Integer>();
        this.data = "";
    }

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

    public void addList(int i){
        this.LL.add(i);
    }

    public int getList(int i){
        return this.LL.get(i);
    }

    public int alSize(){
        return this.LL.size();
    }

    public void clearList() {
        this.LL.clear();
    }

}

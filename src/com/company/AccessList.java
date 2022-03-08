package com.company;

import sun.awt.image.ImageWatched;

import java.util.*;
import java.util.concurrent.Semaphore;

public class AccessList extends Thread{

    public Semaphore sem = new Semaphore(1);
    public LinkedList<Integer> FL;
    public LinkedList<Integer> DL;
    String data = "";

    public AccessList(){
        Semaphore sem1 = sem;
        FL = new LinkedList<>();
        DL = new LinkedList<>();
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

    public void addFList(int i){
        this.FL.add(i);
    }

    public int getFList(int i){
        return this.FL.get(i);
    }

    public int getFSize(){
        return this.FL.size();
    }

    public void clearFList() {
        this.DL.clear();
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

    public void clearDList() {
        this.DL.clear();
    }

}

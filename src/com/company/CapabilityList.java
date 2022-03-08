//Beginning of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List

package com.company;

import java.util.*;
import java.util.concurrent.Semaphore;

public class CapabilityList extends Thread{

    public Semaphore cap = new Semaphore(1);
    public LinkedList<Integer> FileList;
    public LinkedList<Integer> DomainList;
    String data = "";

    public CapabilityList(){
        FileList = new LinkedList<>();
        DomainList = new LinkedList<>();
        this.data = "";
    }

    public String getData(){
        return this.data;
    }

    public void setData(String in){
        this.data = in;
    }

    public void getCap() throws InterruptedException {
        this.cap.acquire();
    }

    public void releaseCap(){
        this.cap.release();
    }

    public void addFileList(int i){
        this.FileList.add(i);
    }

    public int getFileList(int i){
        return this.FileList.get(i);
    }

    public int getFileSize(){
        return this.FileList.size();
    }

    public int getDomainList(int i){
        return this.DomainList.get(i);
    }

    public int getDomainSize(){
        return this.DomainList.size();
    }
}

//End of code written by Chris Kobe, and edited by Spencer Vosloh; edited to match for Task 3 Capability List
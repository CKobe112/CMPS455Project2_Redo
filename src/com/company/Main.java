package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        else if (args.length > 1){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        String arg0 = args[0];
        arg0 = arg0.toLowerCase();

        if (arg0.length() > 1){
            System.out.println("Please enter a valid command.");
            System.exit(0);
        }

        else if (arg0.equals("m")){
            System.out.println("Execute command M");
        }

        else if (arg0.equals("l")){
            System.out.println("Execute command L");
        }

        else if (arg0.equals("c")){
            System.out.println("Execute command C");
        }

        else
            System.out.println("Please enter a valid command 4");

    }
}

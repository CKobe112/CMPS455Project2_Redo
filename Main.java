package Interface;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    System.out.println("What layout would you like to use?: ");
	    String layout = sc.nextLine();

	    if(layout.equals("M") || layout.equals("m")){
	        MatrixInit matrix = new MatrixInit();
	        matrix.run();
        }


	    else if (layout.equals("L") || layout.equals("l")){

        }


	    else if (layout.equals("C") || layout.equals("c")){

        }


	    else{
	        System.out.println("Improper input. Please try again.");
        }
    }
}

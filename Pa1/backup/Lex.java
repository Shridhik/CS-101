///////////////////////////////////////////////
//  Lex.java
//  Shridhik John
//  shjohn@ucsc.edu
//  CMPS-101
//  Pa-1
//  main program that asks for two command line arguments and sorts 
///////////////////////////////////////////////

import java.io.*;
import java.util.Scanner;

class Lex {
	public static void main(String[] args)  throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      int i, lineNumber = 0;

		//Check that there are two command line arguments
      if(args.length != 2){
         System.err.println("Usage: Lex inputfile1 outputfile2");
         System.exit(1);
      }
      
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

// counts line numbers to initialize string fileLines array
      while( in.hasNextLine() ){
         lineNumber++;
         in.nextLine();
     }
// creates a string array with each line in the file stored inside
     String[] fileLines = new String[lineNumber];
       in = new Scanner(new File(args[0]));
         for(i=0; i<lineNumber; i++){
            fileLines[i] = in.nextLine();
            //out.println(fileLines[i]);
   			}


if (lineNumber==0){ // Checks to make sure we are not manipulating an empty string array 
	    System.err.println("Cannot sort empty array");

}
      List L = new List(); // creates the List ADT
	  L.append(0); // initializes the list's front and back


		//Sort the array
		for (i=1; i<lineNumber; i++) // goes through every line 
		{
			L.moveBack(); 
			
			String temp = fileLines[L.get()]; // Each string is stored into a temp variable for comparison

			if(fileLines[i].compareTo(temp) < 0 ){ // if the element compared is less than the stored element

				L.moveFront(); // move the cursor to the front
				temp = fileLines[L.get()]; // the temp is now the first element
				
				if (fileLines[i].compareTo(temp) < 0 ) { // if the element is still less than the first element, the new element will be prepended
					L.prepend(i);
				}
				
				else{ 
					temp = fileLines[L.get()]; 
					while(fileLines[i].compareTo(temp) > 0 ) { // if the compared string is greater than the compared file
						L.moveNext(); // we will move to the next cursor
						temp = fileLines[L.get()]; // the new compared temporary variable will be checked to see if it is greater than that number too 
					}
					L.insertBefore(i); // once we fail to find a nuber bigger, we will insert before the last biggest number
				}
			
			}

			else {
				L.append(i); // it 
			}

		}


      L.moveFront(); // moves List cursor to front and then prints out the sorted array to output file
      for ( i = 0; i < lineNumber-1; i++) { 
    	  out.println(fileLines[L.get()]);
    	  L.moveNext();
      }
      out.println(fileLines[L.back()]);     
     



      in.close();
      out.close();
   }
}

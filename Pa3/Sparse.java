//-----------------------------------------------------------------------------
//  Matrix.java
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-3
//  The top level client module that take two command line arguments giving the names of the input and output files
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;
   
class Sparse{
   public static void main(String[] args) throws IOException{
      Scanner in = null;
      PrintWriter out = null;
      String line = null;
      String[] token = null;
      int i,lineNumber = 0;
      int r,c;
      double v;


      if(args.length != 2){
         System.err.println("Usage: Sparse infile outfile");
         System.exit(1);
      }
      
      in = new Scanner(new File(args[0]));
      out = new PrintWriter(new FileWriter(args[1]));

      int n = in.nextInt(); 
	  int a = in.nextInt(); 
	  int b = in.nextInt();



	Matrix A = new Matrix(n);
	Matrix B = new Matrix(n);
      while( in.hasNextLine()){

      	for(i=0; i<= a;i++){
         	line = in.nextLine()+" ";    // add extra space so split works right
  			if ( line.trim().length() == 0 ) {
    			i=0;
    			continue;  // Skip blank lines
  			} // otherwise:
         	token = line.split("\\s+");  // split line around white space
         	r=(int)Integer.parseInt(token[0]);
         	c=(int)Integer.parseInt(token[1]);
         	v=(double)Double.parseDouble(token[2]);
         	A.changeEntry(r,c,v);
     	}

      	for(i=0; i<= b;i++){
         	line = in.nextLine()+" ";    // add extra space so split works right
  			if ( line.trim().length() == 0 ) {
  				i=0;
    			continue;  // Skip blank lines
  			} // otherwise:
         	token = line.split("\\s+");  // split line around white space
         	r=(int)Integer.parseInt(token[0]);
         	c=(int)Integer.parseInt(token[1]);
         	v=(double)Double.parseDouble(token[2]);
         	B.changeEntry(r,c,v);
     	}
     	out.println("A has "+a+" non-zero entries:");
     	out.println(A);
     	out.println("B has "+b+" non-zero entries:");
     	out.println(B);
     	out.println("(1.5)*A =");
     	out.println(A.scalarMult(1.5));
     	out.println("A+B =");
     	out.println(A.add(B));    	
     	out.println("A+A =");
     	out.println(A.add(A));
     	out.println("B-A =");
     	out.println(B.sub(A));
     	out.println("A-A =");
		out.println(A.sub(A));
		out.println("Transpose(A) =");
		out.println(A.transpose());
		out.println("A*B =");
		out.println(A.mult(B));
		out.println("B*B =");
		out.println(B.mult(B));


}



      in.close();
      out.close();
   }
}
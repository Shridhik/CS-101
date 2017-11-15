//-----------------------------------------------------------------------------
//  MatrixTest.java
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-3
//  A test file for Matrix to make sure all functions work properly
//-----------------------------------------------------------------------------
class MatrixTest {
  	public static void main(String args[]) {
  	Matrix A;
    Matrix B;
    Matrix C; /*
        A = new Matrix(10);
        B = new Matrix(10); 
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        C = A.add(A);
        System.out.println(C);
        if (C.getNNZ() != 4 || A.getNNZ() != 4){
        System.out.println("1");
    }
  */
     /* 
        B.changeEntry(1, 1, -4);
        B.changeEntry(1, 2, 0);
        B.changeEntry(2, 1, 0);
        B.changeEntry(2, 2, -2);
        B.changeEntry(2, 4, 2);
        B.changeEntry(3, 1, 0);
        B.changeEntry(3, 2, 2);
        B.changeEntry(7, 8, 5);
        C = A.add(B);
        System.out.println(A);
        System.out.println(B);
        System.out.println(C);

        if (C.getNNZ() != 5){
        System.out.println("2");
    }

*/

/*
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, 4);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 2);
        A.changeEntry(3, 1, 0);
        A.changeEntry(2, 2, 2);
        A.changeEntry(3, 3, 0);
        C = A.add(A);
        System.out.println(C);
        if (C.getNNZ() != 4 || A.getNNZ() != 4){
        	System.out.println("1");
    }
    */
    /*
        A = new Matrix(10);
        B = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 0);
        A.changeEntry(2, 1, 4);
        A.changeEntry(2, 2, 0);
        A.changeEntry(2, 3, 6);
        A.changeEntry(3, 1, 0);
        A.changeEntry(3, 2, 8);
        A.changeEntry(3, 3, 9);
        B.changeEntry(1, 1, 4);
        B.changeEntry(1, 2, 3);
        B.changeEntry(1, 3, 1);
        B.changeEntry(2, 1, 2);
        B.changeEntry(3, 1, 2);
        B.changeEntry(2, 2, 5);
        B.changeEntry(3, 3, 4);
        C = A.add(B);
         System.out.println(C);
*
    
        A = new Matrix(10);
        B = new Matrix(10);
        C = new Matrix(10);
        A.changeEntry(1, 1, 1);
        A.changeEntry(2, 2, 1);
        A.changeEntry(3, 3, 1);
        C = A.mult(A);
        
        A.changeEntry(1, 1, 1);
        A.changeEntry(1, 2, 2);
        A.changeEntry(1, 3, 3);
        A.changeEntry(2, 1, 4);
        A.changeEntry(2, 2, 5);
        A.changeEntry(2, 3, 6);
        A.changeEntry(3, 1, 7);
        A.changeEntry(3, 2, 8);
        A.changeEntry(3, 3, 9);
        B.changeEntry(1, 1, 1);
        B.changeEntry(2, 2, 1);
        C = A.mult(B);
                System.out.println(C);
        if (C.getNNZ() != 6){
        	System.out.println("2");
        	*/

        	/*
        int i, j, n=100000;
      Matrix A = new Matrix(n);
      Matrix B = new Matrix(n);

      A.changeEntry(1,1,1); B.changeEntry(1,1,1);
      A.changeEntry(1,2,2); B.changeEntry(1,2,0);
      A.changeEntry(1,3,3); B.changeEntry(1,3,1);
      A.changeEntry(2,1,4); B.changeEntry(2,1,0);
      A.changeEntry(2,2,5); B.changeEntry(2,2,1);
      A.changeEntry(2,3,6); B.changeEntry(2,3,0);
      A.changeEntry(3,1,7); B.changeEntry(3,1,1);
      A.changeEntry(3,2,8); B.changeEntry(3,2,1);
      A.changeEntry(3,3,9); B.changeEntry(3,3,1);

      System.out.println(A.getNNZ());
      System.out.println(A);

      System.out.println(B.getNNZ());
      System.out.println(B);

      Matrix C = A.scalarMult(1.5);
      System.out.println(C.getNNZ());
      System.out.println(C);

      Matrix D = A.add(A);
      System.out.println(D.getNNZ());
      System.out.println(D);

      Matrix E = A.sub(A);
      System.out.println(E.getNNZ());
      System.out.println(E);

      Matrix F = B.transpose();
      System.out.println(F.getNNZ());
      System.out.println(F);

      Matrix G = B.mult(B);
      System.out.println(G.getNNZ());
      System.out.println(G);

      Matrix H = A.copy();
      System.out.println(H.getNNZ());
      System.out.println(H);
      System.out.println(A.equals(H));
      System.out.println(A.equals(B));
      System.out.println(A.equals(A));

      A.makeZero();
      System.out.println(A.getNNZ());
      System.out.println(A);
      */
 
}
}

      


  
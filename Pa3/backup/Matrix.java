//-----------------------------------------------------------------------------
//  Matrix.java
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-3
//  A calculator for performing matrix operations using List ADT
//-----------------------------------------------------------------------------
class Matrix{

	private class Entry{
// Fields
      int column;
      double value;

// Constructor
      Entry(int column, double value) { 
         this.column = column;
      	this.value = value; 
      	 }
      
 // toString():  overrides Object's toString() method
      public String toString() { 
	     return ("("+column+", "+value+")"); //prints out column, and value
      }

      public boolean equals(Object x){
         boolean eq = false; // sets flag to false
         Entry that;
         if(x instanceof Entry){ // makes sure passed in x is an Entry with vaue and column to compare
            that = (Entry) x; // x is turned into an entry that is stored in variable "that"
            eq = (this.value==that.value && this.column == that.column); // compares value and column to check if equal
         }
         return eq; // returns true or false
      }
   }

	private List[] Array;
	private int size;
   //private int entries;


//Constructor
// Makes a new n x n zero Matrix. pre: n>=1
Matrix(int n){ 
      size = n; // size is the size of the n = n matrix
      //entries = 0; // no entries in the empty matrix that is created 
	if (n>=1){ // condition 
      Array = new List[n]; // an array of type list is created of size n
      for(int i=0; i<size; i++) { // each array has a linked list 
         Array[i] = new List();
      }
	}
	else{
		throw new RuntimeException("Matrix Error: cannot create a matrix of size less than 1x1");
	}
}

// Access functions
// Returns n, the number of rows and columns of this Matrix
int getSize(){  // returns size
      return size;
}


// Returns the number of non-zero entries in this Matrix 
int getNNZ() {
int entries = 0;
  for(int i=0; i<size; i++) { // goes through each linked list in the array and counts the entries
      if (Array[i].length() > 0)   {
         entries += Array[i].length();
      }
}
   return entries;
}

// overrides Object's equals() method
public boolean equals(Object x){
      boolean eq  = false; 
      Matrix that;
      if(x instanceof Matrix) {
      that = (Matrix) x; // x is turned into a Matrix that is stored in variable that
         if(getNNZ() == that.getNNZ() && size == that.getSize()){ //if entries are equal
            for(int i=0; i<size; i++){ // for each row check that each array has equal rows
               if(Array[i].equals(that.Array[i])){
                  eq = true;
               }
            }
         }
      }
      else{
         throw new RuntimeException("Matrix Error: equals() called with non-matrix parameter");    
      }
      return eq;
}


// Manipulation procedures


// sets this Matrix to the zero state
void makeZero(){
     //entries= 0; // set entire to 0
   for (int i=0; i<size; ++i) { // create a new Matrix
         Array[i] = new List();
   }
}






// returns a new Matrix having the same entries as this Matrix
Matrix copy(){
Matrix copy = new Matrix(size); // matrix we've created to copy into
   for(int i=0; i<copy.getSize(); i++) { // goes through each row's list
      Array[i].moveFront();  // goes to the new row in the array, and takes us to the front of the list
      while( Array[i].index() != -1 ) { // checks to make sure we go through each element in the list
         Entry pair = (Entry)Array[i].get(); // turn the Array's element into an entry 
         copy.Array[i].append(pair);  // add the pair to copy
         Array[i].moveNext(); // do it for the while loop :) // move next
      }
   }
      return copy;
}






 // changes ith row, jth column of this Matrix to x
 // pre: 1<=i<=getSize(), 1<=j<=getSize()
void changeEntry(int i, int j, double x){ //(2, 2, -2)

   if (i < 1 || i > getSize() || j < 1 || j > getSize()){
      throw new RuntimeException ("Matrix Error: changeEntry() called with indicies that are out of range");     
   }
   
   else{
      if (Array[i-1].length() == 0){ // if the Array has no length
         if(x == 0) { // edge case to not add 0
            return;
         }
         else{
         Array[i-1].append(new Entry(j,x)); // then we just add the entry wherever we are
         }

         return;
      }
      else{
         Entry pair =  new Entry (j,x); // entry pair 2 is the pair value of the cursor
         // two lines of code to get the column of the last entry
         Entry backPair = (Entry)Array[i-1].back();
         int backPairC = backPair.column;
         // two lines of code to get the column of the first entry
         Entry frontPair = (Entry)Array[i-1].front();
         int frontPairC = frontPair.column;
         Array[i-1].moveFront();  // goes to the new row in the array, and takes us to the front of the list
            // 1) no column changing or replacing necessary for back, front, and if none inside
            if (frontPairC > j){ // if we're putting into a column in which the front's index is greater than j
               if(x == 0) { // edge case to not add a new element if new value at front is a 0
                  return;
               }
               else{
                  Array[i-1].prepend(new Entry(j,x)); // add to the front of the row
               }
            }

            else if (frontPairC == j){ // if we're putting into a column in which the front's index is equal to j
               if(x == 0) {
                  Array[i-1].deleteFront(); // since front value is replaced with 0, we remove front                
                  return;
               }
               else{
                  Array[i-1].deleteFront(); // since front value is replaced with 0, we remove front                
                  Array[i-1].prepend(new Entry(j,x)); // add to the front of the row
                  return;

               }
            }

            else if (backPairC < j){ // goes to the back of the array, and if the back is less than the column in front
               if(x == 0) { // edge case to not add a new element if new value at back is a 0
                  return;
               }
               else{
                  Array[i-1].append(new Entry(j,x)); // insert at the very end of the row
               }
            }
            else if (backPairC == j){ // goes to the back of the array, and if the back is less than the column in front
               if(x == 0) {
                  Array[i-1].deleteBack(); // since back value is replaced with 0, we remove back               
                  return;
               }
               else{
                  Array[i-1].deleteBack(); // since front value is replaced with 0, we remove front                
                  Array[i-1].append(new Entry(j,x)); // add to the front of the row                 
                  return;
                  //Array[i-1].append(new Entry(j,x)); // insert at the very end of the row
               }
            }

               //2) if there is something in a row      
            //else if(Array[i].length() != 0){
            else{
               Array[i-1].moveFront();
               //System.out.println(Array[i-1].get());
               Entry loop = (Entry)Array[i-1].get(); 
               int k = loop.column; 
//System.out.println("first k ="+k);         // EDITED          
               while(Array[i-1].index() != -1){ // while the column required is less than that of the column we're searching for
//System.out.println("k ="+k);         // EDITED
               if (k <= j){
               Entry pair2 = (Entry)Array[i-1].get(); // entry pair 2 is the pair value of the current cursor state
               Array[i-1].moveNext();
               //System.out.println(Array[i-1].get());
               Entry afterPair = (Entry)Array[i-1].get();
               Array[i-1].movePrev();
               //System.out.println(Array[i-1].get());
                  if (pair2.column == j){ // if the existingcolumn we are in is equal to the colum we're looking for 
                     if(x == 0) {
                        Array[i-1].delete(); //delete value for edge case if 0 
                        return;
                     }
                     else{
                        pair2.value = pair.value; // make the new value substitute the old value
                     }    
                  }
                  else if(pair2.column < j && j < ((afterPair.column))) { // if our wanted column is between two columns
                     if(x == 0) {
                        return;
                     }
                     else{ 
                     Array[i-1].insertAfter(pair); // we will append the pair
                     //System.out.println("you are here");
                     return;
                     }
                  }
                  else{
                     //System.out.println(Array[i-1].get());
                     Array[i-1].moveNext(); // go through the while loop
                     loop = (Entry)Array[i-1].get();
                     k = loop.column;
                  }
               }
            }
         }
      }

   }
//return;

}


// returns a new Matrix that is the scalar product of this Matrix with x
Matrix scalarMult(double x){
   Matrix prod = new Matrix(this.size); // new Matrix for product   
   int i = 0;
   int productsize = prod.getSize(); // product size is stored for loop
   while(i< productsize){  
      Array[i].moveFront(); // moves to front of every row
      while(Array[i].index() != -1) {
         Entry Pair = (Entry)Array[i].get(); // we will get every Entry in row
         int column = (Pair.column); // store column
         double value = (Pair.value); // store value
         prod.changeEntry(i+1, Pair.column, Pair.value*x); //switches columns and rows as definition of trapose states
         Array[i].moveNext(); // move to next entry
      }
      i++; // move to next row
   }
   return prod;
}


 // returns a new Matrix that is the sum of this Matrix with M
 // pre: getSize()==M.getSize()
Matrix add(Matrix M){
   if(this.getSize() != M.getSize()){
      throw new RuntimeException ("Matrix Error: add() called on matrices with different sizes"); 
   }
   else{
      if (this.getSize() > M.getSize()){ 
         int size = this.getSize();
      }
      else{
         size = M.getSize();
      }
      double sol = 0;
      Matrix Add = new Matrix(size); // declares a transpose matrix
      for(int i=1; i<=size; i++) {
         this.Array[i-1].moveFront(); // moves to front of every row
         M.Array[i-1].moveFront();

         while((this.Array[i-1].index() >= 0) || (M.Array[i-1].index() >= 0)){
            int col1;
            double val1;
            int col2;
            double val2;

                  //System.out.println("Array index 1= "+Array[i-1].index());
                  //System.out.println("M.Array index 1= "+M.Array[i-1].index());
                  
                  if(this.Array[i-1].index() >=0){
                     Entry pair = (Entry)this.Array[i-1].get();
                     val1 = pair.value;
                     col1 = pair.column;
                  }
                  else{
                     val1=0;
                     Entry prePair = (Entry)M.Array[i-1].get();
                     col1 = prePair.column;
                  }

                  if(M.Array[i-1].index() >= 0){
                     Entry pair2 = (Entry)M.Array[i-1].get();
                     val2 = pair2.value;  
                     col2 = pair2.column;
                  }
                  
                  else{
                     val2=0;
                     Entry prePair2 = (Entry)this.Array[i-1].get();
                     col2 = prePair2.column;
                  } 
                  
                  if (col1 < col2){
                     Entry frontPair = (Entry)this.Array[i-1].get();
                     Add.Array[i-1].append(frontPair);
                     //this.Array[i-1].moveNext();
                     this.Array[i-1].moveNext();
                     val2=0;
                     }
                  else if (col2 < col1){
                     Entry frontPair2 = (Entry)M.Array[i-1].get();
                     Add.Array[i-1].prepend(frontPair2);
                     //M.Array[i-1].moveNext();
                     M.Array[i-1].moveNext();
                     val1=0;                     
                     }
                  else {
                     sol = (val1 + val2);
                     Add.changeEntry(i, col1, sol);
                  
                     if(this.equals(M)){
                        if(M.Array[i-1].index() != -1){
                           M.Array[i-1].moveNext();
                        }
                     }
                     else{
                        if(this.Array[i-1].index() != -1){
                           this.Array[i-1].moveNext();
                        }
                        if(M.Array[i-1].index() != -1){
                           M.Array[i-1].moveNext();
                        }
                     }
                  }
//            System.out.println("val1 = "+val1);
//            System.out.println("val2 = "+val2);
//            System.out.println("col1 = "+col1);
//            System.out.println("col2 = "+col2);
//            System.out.println("solution = "+sol+"  i =  "+i);         
         }
      }
      return Add;
   }
}
   

// returns a new Matrix that is the difference of this Matrix with M
// pre: getSize()==M.getSize()
Matrix sub(Matrix M){
//   Matrix copy = new Matrix(this.size); // declares a transpose matrix
//   return copy;

   if(this.getSize() != M.getSize()){
      throw new RuntimeException ("Matrix Error: add() called on matrices with different sizes"); 
   }
   else{
      if (this.getSize() > M.getSize()){ 
         int size = this.getSize();
      }
      else{
         size = M.getSize();
      }
      double sol = 0;
      Matrix Sub = new Matrix(size); // declares a transpose matrix
      for(int i=1; i<=size; i++) {
         this.Array[i-1].moveFront(); // moves to front of every row
         M.Array[i-1].moveFront();

         while((this.Array[i-1].index() >= 0) || (M.Array[i-1].index() >= 0)){
            int col1;
            double val1;
            int col2;
            double val2;

                  //System.out.println("Array index 1= "+Array[i-1].index());
                  //System.out.println("M.Array index 1= "+M.Array[i-1].index());
                  
                  if(this.Array[i-1].index() >=0){
                     Entry pair = (Entry)this.Array[i-1].get();
                     val1 = pair.value;
                     col1 = pair.column;
                  }
                  else{
                     val1=0;
                     Entry prePair = (Entry)M.Array[i-1].get();
                     col1 = prePair.column;
                  }

                  if(M.Array[i-1].index() >= 0){
                     Entry pair2 = (Entry)M.Array[i-1].get();
                     val2 = pair2.value;  
                     col2 = pair2.column;
                  }
                  
                  else{
                     val2=0;
                     Entry prePair2 = (Entry)this.Array[i-1].get();
                     col2 = prePair2.column;
                  } 
                  
                  if (col1 < col2){
                     Entry frontPair = (Entry)this.Array[i-1].get();
                     val1=-1*(frontPair.value);
                     Sub.changeEntry(i,col1,val1);
                     //Sub.Array[i-1].append(frontPair);
                     //this.Array[i-1].moveNext();
                     this.Array[i-1].moveNext();
                     val2=0;
                     }
                  else if (col2 < col1){
                     Entry frontPair2 = (Entry)M.Array[i-1].get();
                     val2=-1*(frontPair2.value);
                     //Sub.Array[i-1].prepend(frontPair2);
                     Sub.changeEntry(i,col2,val2);
                     //M.Array[i-1].moveNext();
                     M.Array[i-1].moveNext();
                     val1=0;                     
                     }
                  else {
                     sol = (val1 - val2);
                     Sub.changeEntry(i, col1, sol);
                  
                     if(this.equals(M)){
                        if(M.Array[i-1].index() != -1){
                           M.Array[i-1].moveNext();
                           Sub.changeEntry(i,col1,0.0);
                        }
                     }
                     else{
                        if(this.Array[i-1].index() != -1){
                           this.Array[i-1].moveNext();
                        }
                        if(M.Array[i-1].index() != -1){
                           M.Array[i-1].moveNext();
                        }
                     }
                  }
            //System.out.println("val1 = "+val1);
            //System.out.println("val2 = "+val2);
            //System.out.println("col1 = "+col1);
            //System.out.println("col2 = "+col2);
            //System.out.println("solution = "+sol+"  i =  "+i);         
         }
      }
      return Sub;
   }
}
 // returns a new Matrix that is the transpose of this Matrix
Matrix transpose(){
   Matrix trans = new Matrix(this.size); // declares a transpose matrix
   int i = 0;
   while( i < trans.getSize()) { // while loop that will continue while we are in the size of matrix
         Array[i].moveFront(); // moves to front of every row starting from first
         while(Array[i].index() != -1) { // while we are still in the row
            Entry Pair = (Entry)Array[i].get(); // we will get every Entry in row
            int column = (Pair.column); // store column
            double value = (Pair.value); // store value
            trans.changeEntry(column, i+1, value); //switches columns and rows as definition of transpose states
            Array[i].moveNext(); // move to next entry
         }
   i++; // move to next row
   }
      return trans;
   }

//helper function that returns dotproduct
public double dotprod(List A, List B){
   double dot=0;
   A.moveFront();
   B.moveFront();
   while(A.index() != -1 && B.index() != -1){ // make sure indeces are within rage, wil exit once we go through entire list and return -1
      Entry a = (Entry)(A.get()); // gets Entry data for both lists A and B 
      Entry b = (Entry)(B.get());
      double Aval = a.value;
      double Bval = b.value;
      int Acol = a.column;
      int Bcol = b.column; 
         if(Acol == Bcol){ // if we are in the same column
            dot += Aval * Bval; // the dot product is increased with the multiple of the values
            A.moveNext(); // both A and B will move up an entry to be compared
            B.moveNext();
         }
         else if(Acol<Bcol){ // if the A column number is less than B's column, we increase A's column number to catch up and compare to B's columns
            A.moveNext();
         }
         else{
            B.moveNext(); // if B's column number is less than A's, we increase B's column number to catch up and compare columns
         }
   }
   return dot; // total dot product will be returned
}


 // returns a new Matrix that is the product of this Matrix with M
 // pre: getSize()==M.getSize()
Matrix mult(Matrix M){ 
   if(this.getSize() != M.getSize()) {
      throw new RuntimeException("Matrix Error: mult() called on matrices with different sizes");
   }
      double sol; // solution to multiplication of matrices
      Matrix prod = new Matrix(this.size); // creates a new product matrix 
      Matrix transpose = M.transpose();
      for(int i=1; i<=size; i++) {
         if(Array[i-1].length() > 0) {
            for(int j=1; j<=size; j++) {
               if(transpose.Array[j-1].length() > 0) {
                  //System.out.println("Array[i] = "+Array[i-1]);
                  //System.out.println("Transpose[j] = "+ transpose.Array[j-1]);
                  sol = dotprod(Array[i-1], transpose.Array[j-1]);
                  //System.out.println("solution = "+sol+"  i =  "+i+"    j =  "+j);
                  prod.changeEntry(i, j, sol);
               }
            }
         }
      }
      return prod;
    
   }


// Other functions
// overrides Object's toString() method
public String toString(){
    String Mat = "";
    
    for (int i=1; i<=size; i++) {  
            if (Array[i-1].length() > 0){
               Mat += i + ": " + Array[i-1] + "\n";
            }
      }
    return Mat;
  }
}


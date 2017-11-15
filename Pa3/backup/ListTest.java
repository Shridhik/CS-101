//-----------------------------------------------------------------------------
//  ListTest.java
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-3
//  A test file for List to make sure all functions work properly
//-----------------------------------------------------------------------------

class ListTest{
  int i;
  double d;
  String str;

  public static void main(String args[]) {
  	List A;
  	A = new List();
  	A.append(1);
/*
    List A;
    List B;
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        //if (A.length() != 4) return 1;
      } else if (test == Prepend_length) {
        A = new List();
        A.prepend(6);
        A.prepend(4);
        A.prepend(2);
        A.prepend(1);
        if (A.length() != 4) return 1;
      } else if (test == InsertAfter_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        if (A.length() != 5) return 1;
      } else if (test == InsertBefore_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        if (A.length() != 5) return 1;
      } else if (test == DeleteFront_length) {
        A = new List();
        A.prepend(76);
        A.prepend(4);
        A.deleteFront();
        A.prepend(3);
        A.prepend(1);
        A.moveFront();
        A.insertBefore(115);
        A.deleteFront();
        if (A.length() != 3) return 1;
      } else if (test == DeleteBack_length) {
        A = new List();
        A.append(1);
        A.deleteBack();
        A.append(2);
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        A.deleteBack();
        if (A.length() != 3) return 1;
      } else if (test == Delete_length) {
        A = new List();
        A.append(1);
        A.append(2);
        A.moveFront();
        A.delete();
        A.append(3);
        A.append(5);
        A.moveFront();
        A.insertAfter(12);
        A.delete();
        if (A.length() != 3) return 1;
      } else if (test == EmptyList_index) {
        A = new List();
        if (A.index() != -1) return 1;
      } else if (test == MoveFront_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveFront();
        if (A.index() != 0) return 1;
      } else if (test == MoveBack_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveBack();
        if (A.index() != 4) return 1;
      } else if (test == MoveNext_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(16);
        A.append(176);
        A.append(3214);
        A.moveFront();
        A.moveNext();
        A.moveNext();
        if (A.index() != 2) return 1;
        A.moveNext();
        A.moveNext();
        A.moveNext();
        if (A.index() != -1) return 2;
      } else if (test == MovePrev_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(3214);
        A.moveBack();
        A.movePrev();
        if (A.index() != 1) return 1;
        A.movePrev();
        A.movePrev();
        if (A.index() != -1) return 2;
      } else if (test == Append_index) {
        A = new List();
        A.append(1);
        A.append(5);
        A.append(7);
        A.moveBack();
        A.append(45);
        A.append(51);
        A.append(3214);
        if (A.index() != 2) return 1;
        A.moveBack();
        A.movePrev();
        A.movePrev();
        if (A.index() != 3) return 2;
        A.moveFront();
        A.movePrev();
        if (A.index() != -1) return 3;
      } else if (test == Prepend_index) {
        A = new List();
        A.prepend(1);
        A.prepend(5);
        A.prepend(7);
        A.moveFront();
        A.prepend(45);
        A.prepend(51);
        A.prepend(3214);
        A.prepend(314);
        A.prepend(324);
        if (A.index() != 5) return 1;
        A.moveBack();
        A.movePrev();
        A.prepend(234);
        A.movePrev();
        if (A.index() != 6) return 2;
        A.moveFront();
        A.movePrev();
        if (A.index() != -1) return 3;
      } else if (test == InsertAfter_index) {
        A = new List();
        A.append(5);
        A.append(6);
        A.append(4);
        A.append(33);
        A.append(2);
        A.append(1);
        A.moveBack();
        A.insertAfter(75);
        A.moveNext();
        if (A.index() != 6) return 1;
        A.insertAfter(345);
        A.moveBack();
        if (A.index() != 7) return 2;
      } else if (test == InsertBefore_index) {
        A = new List();
        A.prepend(34);
        A.prepend(4);
        A.prepend(354);
        A.prepend(3674);
        A.moveBack();
        A.insertBefore(435);
        if (A.index() != 4) return 1;
        A.prepend(324);
        A.prepend(33464);
        A.prepend(3498);
        A.moveFront();
        A.insertBefore(67);
        if (A.index() != 1) return 2;
      } else if (test == DeleteFront_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveFront();
        A.deleteFront();
        if (A.index() != -1) return 1;
        A.moveBack();
        A.deleteFront();
        if (A.index() != 3) return 2;
      } else if (test == DeleteBack_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveBack();
        A.deleteBack();
        if (A.index() != -1) return 1;
        A.moveFront();
        A.deleteBack();
        A.moveNext();
        if (A.index() != 1) return 2;
      } else if (test == Delete_index) {
        A = new List();
        A.prepend(5);
        A.prepend(65);
        A.prepend(43);
        A.moveBack();
        A.delete();
        if (A.index() != -1) return 1;
        A.prepend(2);
        A.prepend(8);
        A.prepend(1);
        A.moveBack();
        if (A.index() != 4) return 2;
        A.delete();
        A.moveBack();
        if (A.index() != 3) return 3;
        A.moveFront();
        A.delete();
        A.moveFront();
        if (A.index() != 0) return 4;
        A.delete();
        if (A.index() != -1) return 5;
      } else if (test == Append_equals) {
        A = new List();
        B = new List();
        A.append(1);
        B.append(1);
        A.append(2);
        if (A.equals(B)) return 1;
        B.append(2);
        */
        //System.out.println(A);
        //System.outprintln(B);
        System.out.println(A);
  }
}
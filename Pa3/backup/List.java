//-----------------------------------------------------------------------------
//  List.java
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-3
//  A bi-directional ADT List that includes a “cursor” to be used for iteration.
//-----------------------------------------------------------------------------

class List{

   private class Node{
// Fields
      Object data;
      Node next;
      Node prev;

// Constructor
      Node(Object data) { 
      	this.data = data; next = null; prev =null;
      	 }
      
 // toString():  overrides Object's toString() method
      public String toString() { 
         return String.valueOf(data); 
      }

      public boolean equals(Object x){
         boolean eq = false;
         Node that;
         if(x instanceof Node){
            that = (Node) x;
            eq = (this.data==that.data);
         }
         return eq;
      }
   }

     


 // Fields
   private Node front;
   private Node back;
   private Node cursor;
   private int length;
   private int indexCursor;

 // Constructor
   public List() { // Creates a new empty list
      front = back = null; 
      length = 0; 
      cursor = null;
      indexCursor = -1;
   }

 //access functions
   int length () { // Returns the number of elements in this List.
      return length;
  }


   int index (){ // If cursor is defined, returns the index of the cursor element, otherwise returns -1.
   	  if (cursor != null){
   	  	return indexCursor;
   	  }
   	  else{
   	  	return -1;
   	  }
   }


   Object front(){ // Returns front element. Pre: length()>0
    if (length() <= 0 ){
    	throw new RuntimeException(
            "List Error: front() called on empty List");
      }
    else{
    	return front.data; 
    }
    }


    Object back(){ // Returns back element. Pre: length()>0
    if (length() <= 0 ){
    	throw new RuntimeException(
            "List Error: back() called on empty List");
      }
    else{
    	return back.data;
    }
    }


    Object get() { // Returns cursor element. Pre: length()>0, index()>=0
		if (length() < 0 && index() < 0) {
			throw new RuntimeException(
            "List Error: get() needs an index and length");
		} else {
			return cursor.data;
		}
}


 // Returns true if and only if this List and L are the same
 // integer sequence. The states of the cursors in the two Lists
 // are not used in determining equality.
   public boolean equals(Object x){
      boolean eq  = false;
      List Q;
      Node N, M;

      if(x instanceof List){
         Q = (List)x;
         N = this.front;
         M = Q.front;
         eq = (this.length==Q.length);
         while( eq && N!=null ){
            eq = N.equals(M);
            N = N.next;
            M = M.next;
         }
      }
      return eq;
   }

 // Manipulation procedures 
	void clear(){ // Resets this List to its original empty state.
		front = null;
		back = null;
		cursor = null;
		length = 0;
		indexCursor = -1;
	}

 // If List is non-empty, places the cursor under the front element,
 // otherwise does nothing.
    void moveFront(){
    	if (length() != 0){
    		indexCursor = 0;
    		cursor = front;
    	}
     }

 // If List is non-empty, places the cursor under the back element,
 // otherwise does nothing.
     void moveBack(){
     	if (length()!= 0){
     		indexCursor = (length() -1);
     		cursor = back;
     	}
     	     }


 // If cursor is defined and not at front, moves cursor one step toward
 // front of this List, if cursor is defined and at front, cursor becomes
 // undefined, if cursor is undefined does nothing.
     void movePrev(){
     	if (cursor != null && cursor != front){
     		cursor = cursor.prev;
     		indexCursor --;
     	}
     	else if (cursor != null && indexCursor == 0){
     		indexCursor = -1;
     		cursor = null;
     	}
     	else {
     		return;
     	}
     }

 // If cursor is defined and not at back, moves cursor one step toward
 // back of this List, if cursor is defined and at back, cursor becomes
 // undefined, if cursor is undefined does nothing.
     void moveNext(){
     	if (cursor != null && cursor != back){
     		cursor = cursor.next;
     		indexCursor ++;
     	}
     	else if (cursor != null && indexCursor == length() - 1){
     		indexCursor = -1;
     		cursor = null;
     	}
     	else {
     		return;
     	}
     }

 // Insert new element into this List. If List is non-empty,
 // insertion takes place before front element.
     void prepend(Object data){
     	Node temp = new Node(data);
     	if (length()!=0){
     		front.prev = temp;
     		temp.next = front;
     		front = temp;
     		length ++;
		indexCursor++;
     	}
     	else{
     		front = temp;
     		back = front;
     		length = 1;
		indexCursor++;
     	}
     	}

 // Insert new element into this List. If List is non-empty,
 // insertion takes place after back element.
     void append(Object data){
     	Node temp = new Node(data);
     	if (length!=0){
     		back.next = temp;
     		temp.prev = back;
     		back = temp;
     		length ++;
     	}
     	else{
     		back = temp;
     		front = temp;
     		length = 1;
     	}
     	}   

 // Insert new element before cursor.
 // Pre: length()>0, index()>=0
     void insertBefore(Object data){
     	Node temp = new Node(data);
     	if (length() > 0 && index() >= 0){
     		if (cursor == front){
     			temp.prev = null;
     			temp.next = cursor;
     			cursor.prev = temp;
     			length++;
     			front = temp;
			indexCursor++;
     		}
     		else{
     			temp.next = cursor;
     			temp.prev = cursor.prev;
     			cursor.prev.next = temp;
     			cursor.prev = temp;
     			length++;
			indexCursor++;
     		}

     	}
     	else{
     			throw new RuntimeException(
         "List Error: insertBefore() called on empty list");

     	}
     }  

 // Inserts new element after cursor.
 // Pre: length()>0, index()>=0
     void insertAfter(Object data){
     	Node temp = new Node(data);
     	if (length > 0 || indexCursor >= 0) {
     		if (cursor == back){
     			temp.next = null;
     			temp.prev = cursor;
     			cursor.next = temp;
     			back = temp;
     			length++;
     		}
     		else{
     		temp.prev = cursor;
     		temp.next = cursor.next;
     		cursor.next.prev = temp;
     		cursor.next = temp;
     		length++;
     		}
     	}
     	else{
     			throw new RuntimeException(
         "List Error: insertAfter() called on empty list");

     	}
     }

// Deletes the front element. Pre: length()>0
	void deleteFront(){
		if (length()>0){
			if(length()==1){ //edge case in which there is only one element in the list
				front = null;
				back = null;
				indexCursor = -1;
				length --;
			}
			else{
			front = front.next;
			front.prev = null;
			length --;
			indexCursor  --;
			}
		}
		else{
			    throw new RuntimeException(
         "List Error: deleteFront() called on empty list");
		}
     
    }


 // Deletes the back element. Pre: length()>0
	void deleteBack(){ 
		if (length()>0){
			if(length()==1){ // how to remov
				front = null;
				back = null;
				indexCursor = -1;
				length--;
			}
			else{
				if(cursor == back){
					cursor = null;
					indexCursor = -1;
				}
			back = back.prev;
			back.next = null;
			length--;
			}
		}
		else{
			    throw new RuntimeException(
         "List Error: deleteBack() called on empty list");
		}
	}

 // Deletes cursor element, making cursor undefined.
 // Pre: length()>0, index()>=0
	void delete(){
		if (length()>0 && index()>=0){
			if (length()==1){ // if there is only one item in the list set everything to null by calling deleteBack()
				deleteBack();
			}
			else if (cursor == front){ 
			front = front.next;
			front.prev = null;
			cursor = null;
			length--;
			}
			else if ( cursor == back){
                        back  = back.prev;
                        back.next = null;
			cursor = null;
			length --;
			}
			else{
				cursor.prev.next = cursor.next;
				cursor.next.prev = cursor.next;
				cursor = null;
				length --;
				indexCursor--;
			}
		}
		else{
			    throw new RuntimeException(
         "List Error: delete() called on empty list");
		}			
		}


 // Overrides Object's toString method. Returns a String
 // representation of this List consisting of a space
 // separated sequence of integers, with front on left.
		public String toString(){
      	StringBuffer sb = new StringBuffer();
      Node N = front;
      while(N!=null){
         sb.append(N.toString());
	if (N.next != null){
         sb.append(" ");
	}
         N = N.next;
      }
      	return new String(sb);
   		}

   

 }		




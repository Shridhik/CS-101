//-----------------------------------------------------------------------------
//  List.c
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-4
//  A bi-directional C ADT List that includes a “cursor” to be used for iteration.
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// structs --------------------------------------------------------------------

// private NodeObj type

typedef struct NodeObj{
   int data;
   struct NodeObj* next;
   struct NodeObj* prev;   
} NodeObj;

// private Node type
typedef NodeObj* Node;


// private ListObj type
typedef struct ListObj{
   Node front;
   Node back;
   Node cursor;
   int indexCursor;
   int length;
} ListObj;


// Constructors-Destructors ---------------------------------------------------

// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->prev = NULL;
   return(N);
}

// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}

// Returns reference to new empty List object.
List newList(void){
   List L;
   L = malloc(sizeof(ListObj));
   L->front = L->back = L->cursor=NULL;
   L->length = 0;
   L->indexCursor = -1;
   return(L);
}


// freeList()
// Frees all heap memory associated with List *pL, and sets *pL to NULL.S
void freeList(List* pL){
   if(pL!=NULL && *pL!=NULL) { 
      while( length(*pL) > 0 ) { 
         deleteBack(*pL); 
      }
      free(*pL);
      *pL = NULL;
   }
}

// isEmpty()
// Returns true (1) if Q is empty, otherwise returns false (0)
int isEmpty(List L){
   if( L==NULL ){
      printf("Queue Error: calling isEmpty() on NULL Queue reference\n");
      exit(1);
   }
   return(L->length==0);
}


 //access functions
   int length (List L) { // Returns the number of elements in this List.
   if( L==NULL ){
      printf("List Error: calling length() on NULL List reference\n");
      exit(1);
   }
   return(L->length);
  }


   int index (List L){ // If cursor is defined, returns the index of the cursor element, otherwise returns -1.
   	  if (L->cursor != NULL){
   	  	return (L->indexCursor);
   	  }
   	  else{
   	  	return -1;
   	  }
   }

   int front(List L){ // Returns front element. Pre: length()>0
    if( L==NULL ){
      printf("List Error: calling front() on NULL List reference\n");
      exit(1);
   }
    if( isEmpty(L) ){
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
    return(L->front->data);
}

  int back(List L){ // Returns back element. Pre: length()>0
    if( L==NULL ){
      printf("List Error: calling front() on NULL List reference\n");
      exit(1);
   }
    if( isEmpty(L) ){
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
    return(L->back->data);
}

int get(List L){ // Returns cursor element. Pre: length()>0, index()>=0
    if( L==NULL ){
      printf("List Error: calling front() on NULL List reference\n");
      exit(1);
   }
    if( isEmpty(L) ){
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
   if (length(L) < 0 && index(L) < 0) {
      printf("List Error: calling front() on an empty List\n");
      exit(1);
   }
    return(L->cursor->data);

}


// equals()
// returns true (1) if A is identical to B, false (0) otherwise
int equals(List A, List B){
   int eq = 0;
   Node N = NULL;
   Node M = NULL;

   if( A==NULL || B==NULL ){
      printf("Queue Error: calling equals() on NULL Queue reference\n");
      exit(1);
   }

   eq = ( A->length == B->length );
   N = A->front;
   M = B->front;
   while( eq && N!=NULL){
      eq = (N->data==M->data);
      N = N->next;
      M = M->next;
   }
   return eq;
}

 // Manipulation procedures 
	void clear(List L){ // Resets this List to its original empty state.
    while(L->back != NULL){
      deleteFront(L);
    }
    L->length = 0;
    L->indexCursor = -1;
    L->front = L->back = L->cursor=NULL;
	}

 // If List is non-empty, places the cursor under the front element,
 // otherwise does nothing.
    void moveFront(List L){
    	if (length(L) != 0){
    		L->indexCursor = 0;
    		L->cursor = L->front;
        L->indexCursor = 0;
    	}
     }

 // If List is non-empty, places the cursor under the back element,
 // otherwise does nothing.
     void moveBack(List L){
     	if (length(L)!= 0){
     		L->indexCursor = (length(L) -1);
     		L->cursor = L->back;
     	}
     	     }

 // If cursor is defined and not at front, moves cursor one step toward
 // front of this List, if cursor is defined and at front, cursor becomes
 // undefined, if cursor is undefined does nothing.
     void movePrev(List L){
     	if (L->cursor != NULL && L->cursor != L->front){
     		L->cursor = L->cursor->prev;
     		L->indexCursor --;
     	}
     	else if (L->cursor != NULL && L->indexCursor == 0){
     		L->indexCursor = -1;
     		L->cursor = NULL;
        //freeNode(&L->cursor);
     	}
     	else {
     		return;
     	}
     }

 // If cursor is defined and not at back, moves cursor one step toward
 // back of this List, if cursor is defined and at back, cursor becomes
 // undefined, if cursor is undefined does nothing.
     void moveNext(List L){
     	if (L->cursor != NULL && L->cursor != L->back){
     		L->cursor = L->cursor->next;
     		L->indexCursor ++;
     	}
     	else if (L->cursor != NULL && L->indexCursor == length(L) - 1){
     		L->indexCursor = -1;
     		L->cursor = NULL;
        //freeNode(&L->cursor);

     	}
     	else {
     		return;
     	}
     }

 // Insert new element into this List. If List is non-empty,
 // insertion takes place before front element.
     void prepend(List L, int data){
     	Node temp = newNode(data);
     	if (length(L)!=0){
     		L->front->prev = temp;
     		temp->next = L->front;
     		L->front = temp;
     		L->length ++;
		    L->indexCursor++;
     	}
     	else{
     		L->front = temp;
     		L->back = L->front;
     		L->length = 1;
		    L->indexCursor++;
     	}
      //freeNode(&temp);
      //temp = NULL;
     	}

 // Insert new element into this List. If List is non-empty,
 // insertion takes place after back element.
     void append(List L, int data){
     	Node temp = newNode(data);
     	if (length(L)!=0){
     		L->back->next = temp;
     		temp->prev = L->back;
     		L->back = temp;
     		L->length ++;
     	}
     	else{
     		L->back = temp;
     		L->front = temp;
     		L->length = 1;
     	}
      //freeNode(&temp);
      //temp = NULL;      
     	}   

 // Insert new element before cursor.
 // Pre: length()>0, index()>=0
     void insertBefore(List L, int data){
     	Node temp = newNode(data);
     	if (length(L) > 0 && index(L) >= 0){
     		if (L->cursor == L->front){
     			temp->prev = NULL;
     			temp->next = L->cursor;
     			L->cursor->prev = temp;
     			L->length++;
     			L->front = temp;
			    L->indexCursor++;
     		}
     		else{
     			temp->next = L->cursor;
     			temp->prev = L->cursor->prev;
     			L->cursor->prev->next = temp;
     			L->cursor->prev = temp;
     			L->length++;
			    L->indexCursor++;
     		}

     	}
     	else{
          printf("List Error: insertBefore() called on empty list\n");
          exit(1);
     	}
      //freeNode(&temp);
      //temp = NULL;
     }  

 // Inserts new element after cursor.
 // Pre: length()>0, index()>=0
     void insertAfter(List L, int data){
     	Node temp = newNode(data);
     	if (length(L) > 0 || L->indexCursor >= 0) {
     		if (L->cursor == L->back){
     			temp->next = NULL;
     			temp->prev = L->cursor;
     			L->cursor->next = temp;
     			L->back = temp;
     			L->length++;
     		}
     		else{
     		temp->prev = L->cursor;
     		temp->next = L->cursor->next;
     		L->cursor->next->prev = temp;
     		L->cursor->next = temp;
     		L->length++;
     		}
     	}
     	else{
         printf("List Error: insertAfter() called on empty list\n");
         exit(1);

     	}
      //freeNode(&temp);
      //temp = NULL;
     }

// Deletes the front element. Pre: length()>0
	void deleteFront(List L){
		if (length(L)>0){
			if(length(L)==1){ //edge case in which there is only one element in the list
        freeNode(&L->front);
				L->front = NULL;
				L->back = NULL;
				L->indexCursor = -1;
				L->length --;
        L-> cursor= NULL;
			}
			else{
        L->front = L->front->next;
      if (L->cursor== L->front->prev){
          L->cursor = NULL;
      }
      freeNode(&L->front->prev); //Would free the rest of the list
			L->front->prev = NULL;
			L->length --;
			L->indexCursor  --;
			}
		}
		else{
         printf("List Error: deleteFront() called on empty list\n");
          exit(1);
		}    
    }


 // Deletes the back element. Pre: length()>0
	void deleteBack(List L){ 
		if (length(L)>0){
			if(length(L)==1){ 
        freeNode(&L->front);
				L->front = NULL;
				L->back = NULL;
				L->indexCursor = -1;
				L->length--;
        L->cursor = NULL;
			 }
			else{
        L->back = L->back->prev;
        if (L->cursor == L->back->next){
          L->indexCursor--; 
          L->cursor = NULL;
        }
           freeNode(&L->back->next);
  		     L->back->next = NULL;
			     L->length--;
			 
		  }
  }

		else{
         printf("List Error: deleteBack() called on empty list\n");
         exit(1);
		}
}

 // Deletes cursor element, making cursor undefined.
 // Pre: length()>0, index()>=0
	void delete(List L){
		if (length(L)>0 && index(L)>=0){
			if (length(L)==1){ // if there is only one item in the list set everything to null by calling deleteBack()
				deleteBack(L);
			}
			else if (L->cursor == L->front){ 
        deleteFront(L);
			}
			else if ( L->cursor == L->back){
              L->back  = L->back->prev;
              freeNode(&L->cursor);             
              L->back->next = NULL;
			        L->length --;
			}
			else{
        L->cursor->next->prev = L->cursor->next;
				L->cursor->prev->next = L->cursor->next;
        freeNode(&L->cursor);             
				L->length --;
				L->indexCursor--;
			}
		}
		else{
         printf("List Error: delete() called on empty list\n");
         exit(1);
		}			
		}


// printQueue()
// Prints data elements in Q on a single line to stdout.
void printList(FILE* out, List L){
   Node N = NULL;

   if( L==NULL ){
      printf("List Error: calling printList() on NULL List reference\n");
      exit(1);
   }

   for(N = L->front; N != NULL; N = N->next){
      fprintf(out, "%d ", N->data);
   }
}

 // Returns a new List representing the same integer sequence as this
 // List. The cursor in the new list is undefined, regardless of the
 // state of the cursor in this List. This List is unchanged.   	
   	List copyList(List L){
  if(L == NULL) {
    printf("List Error: calling copyList() on NULL List reference\n");
    exit(1);
  }
  List copy = newList();
  Node temp = L->front;
  while(temp != NULL) {
    append(copy, temp->data);
    temp = temp->next;
  }
  copy->length = length(L);
  return copy;
}




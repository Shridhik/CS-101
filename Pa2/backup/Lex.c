///////////////////////////////////////////////
//  Lex.c
//  Shridhik John
//  shjohn@ucsc.edu
//  CMPS-101
//  Pa-1
//  main program that asks for two command line arguments and sorts 
///////////////////////////////////////////////


#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"List.h"
#define MAX_LEN 160


int main(int argc, char* argv[]){
      int i = 0;
      int lineNumber = 0;
   	  FILE *in = NULL;
   	  FILE *out = NULL;
      char line[MAX_LEN];

	//Check that there are two command line arguments
	if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
  	}
      
   in = fopen(argv[1], "r");
   out = fopen(argv[2], "w");

   if( in==NULL ){
      printf("Unable to open file %s for reading\n", argv[1]);
      exit(1);
   }

   if( out==NULL ){
      printf("Unable to open file %s for writing\n", argv[2]);
      exit(1);
   }

// counts line numbers to initialize string fileLines array
    while( fgets(line, MAX_LEN, in) != NULL){
        lineNumber++;
    }
//Starts reading input file from start
   fclose(in);
   in = fopen(argv[1], "r");


// creates a string array with each line in the file stored inside
   	char *fileLines[lineNumber]; 
    while( fgets(line, MAX_LEN, in) != NULL){
      fileLines[i] = malloc(strlen(line)+1);
      strcpy(fileLines[i],line);
      i++;
   	}



	if (lineNumber==0){ // Checks to make sure we are not manipulating an empty string array 
	    printf("Cannot sort empty array");
	    exit(1);
		}

    List L = newList(); // creates the List ADT
	  append(L,0); // initializes the list's front and back
	  // Sort executuon

	  	for (i=1; i<lineNumber; i++) // goes through every line 
		{
			moveBack(L); 
			
			char * temp = fileLines[get(L)]; // Each string is stored into a temp variable for comparison

			if(strcmp(fileLines[i],(temp))<0 ){ // if the element compared is less than the stored element

				moveFront(L); // move the cursor to the front
				temp = fileLines[get(L)]; // the temp is now the first element
				
				if(strcmp(fileLines[i],(temp))<0 ){ // if the element is still less than the first element, the new element will be prepended
					prepend(L,i);
				}
				
				else{ 
					temp = fileLines[get(L)]; 
					while(strcmp(fileLines[i],(temp))>0 ){ // if the compared string is greater than the compared file
						moveNext(L); // we will move to the next cursor
						temp = fileLines[get(L)]; // the new compared temporary variable will be checked to see if it is greater than that number too 
					}
					insertBefore(L,i); // once we fail to find a nuber bigger, we will insert before the last biggest number
				}
			
			}

			else {
				append(L,i); // it 
			}

		}
		
	//prints to output
   	moveFront(L);
   	while(index(L) >= 0){
   		fprintf(out,fileLines[get(L)]);
   		moveNext(L);
   	}

   	// frees every line in fileLines
  	for(i=0; i<lineNumber; i++){
  		free(fileLines[i]);
  	}

   clear(L);
   freeList(&L);

   fclose(in);
   fclose(out);

   return(0);
}


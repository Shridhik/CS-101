//-----------------------------------------------------------------------------
//  FindPath.c
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-4
//  Reads file input and prints out to file output
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Graph.h"
#include"List.h"
#define MAX_LEN 160

int main(int argc, char * argv[]){

   //int n, count=0;
   FILE *in, *out;
   char line[MAX_LEN];
   char* token;
   int numvert,u,v;
   Graph G;
   List L = newList();

   // check command line for correct number of arguments
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(1);
   }

   // open files for reading and writing 
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
   fgets(line,MAX_LEN,in);
   token = strtok(line," \n");
   numvert=atoi(token);
   G = newGraph(numvert);

   
   /* read each line of input file, then count and print tokens */
   fgets(line,MAX_LEN,in);
   while(strcmp(line,"0 0\n") != 0){
 		token = strtok(line," \n");
		u = atoi(token);
		token = strtok(NULL," \n");
		v = atoi(token);
		token = strtok(NULL," \n");
      //printf("\n %d %d", u, v);
		addEdge(G,u,v);
		fgets(line,MAX_LEN, in);
   }
   printGraph(out,G);

 fgets(line,MAX_LEN,in);
   while(strcmp(line,"0 0\n") != 0){
      token = strtok(line," \n");
      u = atoi(token);
      token = strtok(NULL," \n");
      v = atoi(token);
      token = strtok(NULL," \n");
      printf("\n %d %d", u, v);
      BFS(G,u);
      getPath(L,G,v);
      fprintf(out,"\n");

      if(length(L) == 0){
         fprintf(out,"The distance from %d to %d is infinity\n", u,v);
         fprintf(out,"No %d-%d path exist\n",u,v);
      }
      else{
         int a = getDist(G,v);
         fprintf(out,"The distance from %d to %d is %d\n",u,v,a);
         fprintf(out,"A shortest %d-%d path is: ",u,v);
         printList(out,L);
         fprintf(out,"\n");
      }
      clear(L);
      fgets(line,MAX_LEN, in);
   }
   //printGraph(out,G);

freeList(&L);
freeGraph(&G);




   /* close files */

   fclose(in);
   fclose(out);

   return(0);
}













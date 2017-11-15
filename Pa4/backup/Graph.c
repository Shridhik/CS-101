//-----------------------------------------------------------------------------
//  Graph.c
//  Shridhik John
//  CMPS-101
//  shjohn@ucsc.edu
//  PA-4
//  A Graph ADT built in C
//-----------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include "Graph.h"
#define WHITE 1
#define GRAY 2
#define BLACK 3
#define NIL -1
#define INF -2

/*** Constructors-Destructors ***/
typedef struct GraphObj{
	int order; // number of vertices
	int size; // number of edges
	int source;
	int* color;
	int* distance;
	int* parent; 
	List* Adjacent;
}GraphObj;

//returns a Graph pointing to a newly created GraphObj representing a graph having n vertices and no edges
Graph newGraph(int n){
	Graph G=malloc(sizeof(GraphObj)); // allocation of memory for Graph
	G->color = calloc(n+1,sizeof(int)); // allocation of memory for color
	G->distance = calloc(n+1,sizeof(int)); // allocation of memory for distance
	G->parent = calloc(n + 1, sizeof(int)); // allocation of memory for parent
    G->Adjacent = calloc(n + 1, sizeof(List)); // allocation of memory for Adjacent
	G->order = n; // number of vertices thats passed in 
	G->size = 0; 
	G->source = NIL;
	for(int i=1;i<n+1;i++){
		G->Adjacent[i] = newList(); // creates a new list for n+1
		G->color[i] = WHITE; // sets all colors to white (untouched)
		G->parent[i] = NIL; // sets all parents to NIL
		G->distance[i] = INF; // sets all distances to infinity
	}
return G;
}

//frees all dynamic memory associated with the Graph *pG, then sets the handle *pG to NULL
void freeGraph(Graph* pG){
	int i;
	if (pG == NULL || (*pG) == NULL ){
		return;
	}
	else{
		for ( i = 1; i <= getOrder(*pG); i++ ) {
			freeList(&((*pG)->Adjacent[i]));
		}
		free((*pG)->Adjacent); 
    	free((*pG)->color);
    	free((*pG)->distance);
    	free((*pG)->parent);
    	free(*pG);
    	*pG = NULL;
	}
}


/*** Access functions ***/
// returns the order (number of vertices)
int getOrder(Graph G){
	if ( G == NULL ) {
		printf("Graph Error: calling getOrder() on NULL Graph Reference\n");
		exit(1);
	}	
	return G->order; // return number of vertices 
}

// returns the size (number of edges)
int getSize(Graph G){ 
	if ( G == NULL ) {
		printf("Graph Error: calling getSize() on NULL Graph Reference\n");
		exit(1);
	}	
	return G->size; // return number of edges
}

// returns the source vertex most recently used in function BFS()
int getSource(Graph G){
	if ( G == NULL ) {
		printf("Graph Error: calling getSource() on NULL Graph Reference\n");
		exit(1);
	}	
	return G->source;	// returns source
}

// returns the parent of vertex u in the BreadthFirst tree created by BFS()
// precondition: 1 <= u <= getOrder(G)
int getParent(Graph G, int u){
	if ( G == NULL ) {
		printf("Graph Error: calling getParent() on NULL Graph Reference\n");
		exit(1);
	}	
	if ( u > getOrder(G) || u < 1){
		printf("Graph Error: calling getParent() out of bounds\n");
		exit(1);
	}
	return G->parent[u]; // returns parent of vertex u
}

// returns the distance from the most recent BFS source to vertex u, or INF if BFS() has not yet been called
// precondition: 1 <= u <= getOrder(G)
int getDist(Graph G, int u){
	if ( G == NULL ) {
		printf("Graph Error: calling getDist() on NULL Graph Reference\n");
		exit(1);
	}	
	if ( u > getOrder(G) || u < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	if( getSource(G) == NIL ){ // if the source is nill, distance is infinity
		return INF;
	}
	return G->distance[u];	
}

// appends to the List L the vertices of a shortest path in G from source to u, or appends to L the value NIL if no such path exists.
// precondition: 1 <= u <= getOrder(G)
// precondition: getSource(G)!=NIL, so BFS() must be called before getPath()
void getPath(List L, Graph G, int u){
	if ( G == NULL ) {
		printf("Graph Error: calling getPath() on NULL Graph Reference\n");
		exit(1);
	}	
	if ( L == NULL ) {
		printf("Graph Error: calling getPath() on NULL List Reference\n");
		exit(1);
	}	
	if ( u > getOrder(G) || u < 1){
		printf("Graph Error: calling getPath() out of bounds\n");
		exit(1);
	}
	if ( getSource(G) == NIL ){
		printf("Graph Error: calling getSource() on NIL Reference\n");
		exit(1);
	}
    if( u == getSource(G) ){
        append(L, getSource(G)); // add to the back 
    }
    else if( getParent(G,u) == NIL ){
        append(L, NIL);
    }
    else {
        getPath(L, G, getParent(G,u) );
        append(L, u);
    }
}

/*** Manipulation procedures ***/

// deletes all edges of G, restoring it to its original (no edge) state
void makeNull(Graph G){
	if ( G == NULL ) {
		printf("Graph Error: calling getPath() on NULL Graph Reference\n");
		exit(1);
	}	
	for(int i=1; i<=getOrder(G); i++){
		clear(G->Adjacent[i]);
	}
}

// inserts a new edge joining u to v, i.e. u is added to the adjacency List of v, and v to the adjacency List of u
// precondition: 1 <= u <= getOrder(G) for both vectors
void addEdge(Graph G, int u, int v){
	if ( G == NULL ) {
		printf("Graph Error: calling getPath() on NULL Graph Reference\n");
		exit(1);
	}
	if ( u > getOrder(G) || u < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	if ( v > getOrder(G) || v < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	append(G->Adjacent[v],u); //connects two vectors
	append(G->Adjacent[u],v);
	G->size++; // increments one edge
}

// inserts a new directed edge from u to v, i.e. v is added to the adjacency List of u (but not u to the adjacency List of v). 
// precondition: 1 <= u <= getOrder(G) for both vectors
void addArc(Graph G, int u, int v){
	if ( G == NULL ) {
		printf("Graph Error: calling getPath() on NULL Graph Reference\n");
		exit(1);
	}
	if ( u > getOrder(G) || u < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	if ( v > getOrder(G) || v < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	append(G->Adjacent[u],v);
	G->size++;
}

// runs the BFS algorithm on the Graph G with source s, setting the color, distance, parent, and source fields of G accordingly. 
void BFS(Graph G, int s){
	if ( G == NULL ) {
		printf("Graph Error: calling getPath() on NULL Graph Reference\n");
		exit(1);
	}
	if ( s > getOrder(G) || s < 1){
		printf("Graph Error: calling getDist() out of bounds\n");
		exit(1);
	}
	// for each vertex that in an element of G, initializes all to white 
	for(int i=1; i <= getOrder(G); i++){
		G->color[i] = WHITE;
		G->distance[i] = INF;
		G->parent[i]=NIL;
	}
	// Algorithm from figure 22.2 of psuedocode 
	G->source = s;
	G->color[s] = GRAY;
	G->distance[s] = 0;
	G->parent[s] = NIL;
	List Q = newList();
	append(Q,s);
	// While Q is not equal to null
	while(!isEmpty(Q)){
		moveFront(Q);
		int x = get(Q);
		deleteFront(Q);
		List list = G->Adjacent[x];
		//for( moveFront(list); index(list)!= -1; moveNext(list)){ // starting from the first element of the list
		moveFront(list);
		while( index(list) != -1){
            int v = get(list); // we get the element of the list which is the vertex 
            if(G->color[v] == WHITE) { // if the elemebt is untouched 
                G->color[v] = GRAY; // we mark as touched
                G->distance[v] = G->distance[x] + 1; // for each  vertex we count we increment the distance 
                G->parent[v] = x; // the parent of ever single element we go through n the list will be tge x value we stored from the front
                append(Q, v); // we will append the vector to the List L // enqueue (U,v)
			}
			moveNext(list);
		}
    	G->color[x] = BLACK; //u.color = Black
 	}
 freeList(&Q);
}
/*** Other operations ***/
void printGraph(FILE* out, Graph G){
	for (int i =1; i <= getOrder(G); i++){
		fprintf(out,"%d:",i); //prints each line number 
		printList(out,G->Adjacent[i]); // prits each list
		fprintf(out,"\n");
	}
}














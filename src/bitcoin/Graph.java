package bitcoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Graph {
	
	HashMap <Integer,Vertice> vMap = new HashMap <Integer,Vertice>();

	public void createGraph(int N, int NoOfEdgesinGraph)
	{
		/*
		 For each node you need at least one edge. Start with one node. In each iteration, create a new node and a new edge. 
		 The edge is to connect the new node with a random node from the previous node set.
		 After all nodes are created, create random edges until S is fulfilled. Make sure not to create double edges (for this you can use an adjacency matrix).
		 Random graph is done in O(S).
		 */
		for(int i=1;i<=N;i++)
		{
			createNode(i);
		}
		
		System.out.println("--------Connected GRAPH Created------ with nodes "+N);
		
		System.out.println("Noofedges"+NoOfEdgesinGraph);
		int count=0;
		for(int i=1; i<NoOfEdgesinGraph-N+1;i++)
		{
			int v1= randInt(1, N);
			int v2= randInt(1, N);
			Vertice V1 = vMap.get(v1);
			Vertice V2 = vMap.get(v2);
			System.out.println("Trying to connect "+v1+" to "+v2);
			if(v1!=v2 && !V1.connectedVertices.contains(V2) && !V2.connectedVertices.contains(V1))
			{
				connectVertices(V1,V2);
				count++;
			}
				
		}
		System.out.println("Graph created with "+N+" vertices and "+NoOfEdgesinGraph+" edges");
	}
	
	
	void createNode(int i)
	{
		System.out.println("Creating Vertice "+i);
		if(i==1)
		{
			Vertice newVertice=new Vertice(i); //create a new vertice
			vMap.put(i,newVertice); //adding vertice to hasmap of created vertices
		}
		 
		else{
			Vertice newVertice =new Vertice(i);
			vMap.put(i,newVertice);
			
			int randomVerticeToConnecTo= randInt(1, i-1);
			System.out.println("Random Vertice Selected is "+randomVerticeToConnecTo);
			Vertice randomVertice= vMap.get(randomVerticeToConnecTo);
			//connect NewVertice created to RandomVertice
			connectVertices(randomVertice, newVertice);
		}
		
	}
	
	
	public void connectVertices(Vertice v1, Vertice v2)
	{
		v1.connectedVertices.add(v2);
		v2.connectedVertices.add(v1);
		System.out.println("Connecting "+ v1.data +" to "+ v2.data);
	}
	
	
	public static int randInt(int min, int max) {
	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand = new Random();
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	
	public static void main(String[] args) {
		int N=5000; //no of nodes in the graph
		int NoOfEdgesinGraph= (N*(N-1))/20; // the maximum no of edges in the graph is n(n-1)/2
		
		Graph graph= new Graph();
		graph.createGraph(N,NoOfEdgesinGraph);
	}

}

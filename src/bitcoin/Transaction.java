package bitcoin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Transaction {
	
	int from;
	int to;
	int milliBitCoins;
	long birthTime;
	long lastUpdated;
		
	public Vertice createTransaction(int N, Graph graph, Vertice vertice, long birthTime)
	{
		int v2= randInt(1, N);
		int coins= randInt(1,N);
		this.from=vertice.data;
		this.to=v2;
		this.milliBitCoins=coins;
		this.birthTime=birthTime;
		
		System.out.println("Random Transaction created from Node "+ from+ " No of milliBitcoins to be sent "+milliBitCoins);
		
		Vertice V1 = vertice;
		Vertice V2 = graph.vMap.get(v2);
		System.out.println(" inserting in to hashmap "+ this.hashCode()+ " to "+ V1.data);
		V1.hashCodesRecieved.put(this.hashCode(), true);
		System.out.println(" Transaction created");
		return V1;
		//PropogateTransaction(V1);
		//System.out.println(" --------- PROPOGATION COMPLETE FOR TRANSACTION -----------");
	}
	

	static int count=0;
	public Vertice PropogateTransaction(Vertice V1)
	{	
		for (Vertice temp : V1.connectedVertices) {
			if(!temp.hashCodesRecieved.containsKey(this.hashCode()))// hash has not been recieved  before
			{
				System.out.println("Propogating tx with txHash "+this.hashCode()+ " from "+ V1.data +" to "+ temp.data);
				temp.hashCodesRecieved.put(this.hashCode(), true);
				//System.out.println(" inserting in to hashmap "+ this.hashCode()+ " for node "+ temp.data);
				count++;
				System.out.println("---------"+ count);
				return temp;
				//PropogateTransaction(temp);
			}
		}
		return null;
		
	}
	
	
	int randInt(int min, int max) {
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
	
	

}

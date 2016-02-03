package bitcoin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {

	ArrayList <Transaction> transactionList = new ArrayList <Transaction>();
	// the priority queue - the comparator is used to look at the event times and put them in order
	// the priority queue - the comparator is used to look at the event times and put them in order
	PriorityQueue<Event> queue = new PriorityQueue<>(new Comparator<Event>() {
		@Override
		public int compare(Event o1, Event o2) {
			// TODO Auto-generated method stub
			if (o1.time < o2.time)
				return -1;
			else if(o1.time == o2.time)
				return 0;
			else
				return +1;
		}
	});
	

	public static String NodeLocation()
	{
		
			List <String> countries=new ArrayList<String>();
			countries.add("Uk");
			countries.add("USA");
			//m.add("France");
			//m.add("do nothing");
			Simulator obj=new Simulator();
			return (obj.getRandomList(countries));
	}
	
	public static String getRandomEventType()
	{
		
			List <String> differentEvents=new ArrayList<String>();
			differentEvents.add("CreateTx");
			differentEvents.add("X");
			differentEvents.add("Y");
			differentEvents.add("Z");
			//m.add("France");
			//m.add("do nothing");
			Simulator obj=new Simulator();
			return (obj.getRandomList(differentEvents));
	}
	
			
	public String getRandomList(List<String> m) {
				Random random=new Random();
				int index = random.nextInt(m.size());
				return m.get(index);
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
		
	
	void createInitialEvents(int NoOfRandomEvents, int N, Graph graph) throws InterruptedException {
		Random r=new Random();
		int randNode;
	
		for(int i=0;i<NoOfRandomEvents;i++)
		{
			//Message m=creatmessage();
			long  timestamp= java.lang.System.currentTimeMillis();
			String eventType= getRandomEventType();
			String location=NodeLocation();
			randNode= randInt(1,N);
			Thread.sleep(1);
			queue.add(new Event(timestamp ,eventType ,graph.vMap.get(randNode),location,null));
			System.out.println("event added for NODE= "+ randNode + " in region " +location + ", Event Type= "+ eventType + " ,Time Stamp="+ timestamp);		
			Thread.sleep(200);
		}
	}
	
	public void runSimulation(int N, Graph graph) throws InterruptedException
	{
		  while (!queue.isEmpty())
			{
			  	Event event = queue.poll();
			  	//Thread.sleep(200);
			  	System.out.println("***** am reading priority queue *******");
			  	switch(event.eventType)
			  	{
				  	case "CreateTx":
				  	{	
				  		Transaction t= new Transaction();
				  		transactionList.add(t); //adding to the list of transactions
				  		long  timestamp= java.lang.System.currentTimeMillis();
						Vertice V1= t.createTransaction(N,graph,event.vertice,timestamp);
						// Added to the queue Forward Transaction
						System.out.println(" Forward Tx added to the queue to vertice "+V1.data);
						queue.add(new Event(timestamp,"ForwardTx1",V1, event.location, t));
						//System.out.println(t);
				  	}
				  	break;
			  	
				  	case "ForwardTx1":
				  	{
				  		System.out.println("Inside Switch Case Forwarding Transacton");
				  		Transaction t=event.transaction;
				  		Vertice V1= t.PropogateTransaction(event.vertice);
				  		t.lastUpdated=java.lang.System.currentTimeMillis();
				  		if(V1!=null)
				  		{
				  		 long  timestamp= java.lang.System.currentTimeMillis();
				  		 System.out.println(" Forward Tx added to the queue for "+V1.data);
				  		 queue.add(new Event(timestamp,"ForwardTx1",V1,event.location,event.transaction));
				  		}
				  	}
				  	break;
			  	}
			}
		  
		  System.out.println("Priority Queue got empty");
		  
		  System.out.println("Propogation Delay for");
		  int i=0;
		  for (Transaction t : transactionList) {
			  System.out.println(" Transaction "+i);
			  System.out.println(" The birthtime for Transaction "+t.birthTime );
			  System.out.println(" The lastUpdated Time for Transaction "+t.lastUpdated );
			  System.out.println("The propogation delay is >>>"+ (t.lastUpdated-t.birthTime));
			  i++;
		  }
	}

	

	public static void main(String[] args) throws InterruptedException {
		int N=4000; //no of nodes in the graph
		int NoOfEdgesinGraph= (N*(N-1))/200; // the maximum no of edges in the graph is n(n-1)/2
		int NoOfRandomEvents =N/100;
		
		Graph graph= new Graph();
		graph.createGraph(N,NoOfEdgesinGraph);
		System.out.println(" ------- Graph Created ------------");
		
		Simulator s= new Simulator();
		s.createInitialEvents(NoOfRandomEvents, N, graph);
		System.out.println(" ----Random Events Created and added to the priority queue---");
		
		s.runSimulation(N,graph);
		
	
		
	}

}

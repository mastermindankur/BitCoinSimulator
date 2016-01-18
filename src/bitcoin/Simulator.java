package bitcoin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class Simulator {

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
			differentEvents.add("Create Transaction");
			differentEvents.add("Forward Transaction");
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
		
	

	void createInitialEvents(int NoOfRandomEvents, int N) throws InterruptedException {
		Random r=new Random();
		int randnNode;
	
		for(int i=0;i<NoOfRandomEvents;i++)
		{
			//Message m=creatmessage();
			long  timestamp= java.lang.System.currentTimeMillis();
			String eventType= getRandomEventType();
			String location=NodeLocation();
			randnNode= randInt(1,N);
			Thread.sleep(1);
			queue.add(new Event(timestamp,eventType,randnNode,location));
			System.out.println("event added for NODE= "+ randnNode + " in region " +location + " Event Type= "+ eventType + " Time Stamp="+ timestamp);		
		}
	}

	
	
	public static void main(String[] args) throws InterruptedException {
		int N=5000; //no of nodes in the graph
		int NoOfEdgesinGraph= (N*(N-1))/200; // the maximum no of edges in the graph is n(n-1)/2
		int NoOfRandomEvents =N/100;
		
		Graph graph= new Graph();
		graph.createGraph(N,NoOfEdgesinGraph);
		
		System.out.println(" ------- Graph Created ------------");
	
		Simulator s= new Simulator();
		s.createInitialEvents(NoOfRandomEvents, N);
		System.out.println(" ----Random Events Created and added the priority queue---");
	}

}

package bitcoin;

import java.sql.Timestamp;

public class Event {
	  long time;
	  String eventType;
	  Vertice vertice;
	  String location;
	  Transaction transaction;

	
	 Event(long d, String eventType, Vertice vertice, String location, Transaction transaction) {
		super();
		this.time = d;
		this.eventType = eventType;
		this.vertice = vertice;
		this.location=location;
		this.transaction=transaction;
}
	


	
}

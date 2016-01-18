package bitcoin;

import java.sql.Timestamp;

public class Event {
	  long time;
	  String eventType;
	  int node;
	  String location;

	
	 Event(long d, String eventType, int node, String location) {
		super();
		this.time = d;
		this.eventType = eventType;
		this.node = node;
		this.location=location;

}
	


	
}

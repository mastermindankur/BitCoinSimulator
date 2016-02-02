package bitcoin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vertice {
	
	int  data;
	ArrayList <Vertice> connectedVertices = new ArrayList <Vertice>();
	
	HashMap <Integer,Boolean> hashCodesRecieved = new HashMap <Integer,Boolean>();
	
	Vertice(int n)
	{
		this.data=n;
	}
	
		
}

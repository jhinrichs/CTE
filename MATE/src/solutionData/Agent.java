package solutionData;

import java.util.ArrayList;
import java.util.Collection;

import tree.Node;

public class Agent {
	
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> nodesToVist;
	
	
	public Agent (){
		id=idCounter;
		idCounter++;
		nodesToVist= new ArrayList<Node>();
	}


	public Collection<?> getNodesToVisit() {
		
		return nodesToVist;
	}

}

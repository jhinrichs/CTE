package solutionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tree.Node;

public class Agent {
	
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> nodesToVisit;
	
	
	public Agent (){
		id=idCounter;
		idCounter++;
		nodesToVisit= new ArrayList<Node>();
	}

	public void addNode(Node nodeToVisit){
		if(!nodesToVisit.contains(nodeToVisit)){
			nodesToVisit.add(nodeToVisit);
		}
	}
	public void removeNode(Node nodeToRemove){
		nodesToVisit.remove(nodeToRemove);
	}

	public Collection<?> getNodesToVisit() {
		
		return nodesToVisit;
	}

	public void addNodes(List<Node> nodes) {
		nodesToVisit.addAll(nodes);
	}

}

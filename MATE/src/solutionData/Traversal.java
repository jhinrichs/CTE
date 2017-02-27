package solutionData;

import java.util.ArrayList;
import java.util.List;

import tree.Node;

public class Traversal {

	private int id;
	private static int idCounter=0; 
	private int numberOfRobots;
	private Agent[] agents;
	private Node root;

	/**
	 * A Traversal is a part of a solution for the given tree and the Number of robots; 
	 * @param root
	 * @param numberOfRobots
	 */
	public Traversal(Node tree, int numberOfRobots) {
		id= idCounter;
		idCounter++;
		this.root = tree;
		this.numberOfRobots = numberOfRobots;
		agents = new Agent[numberOfRobots];
	}
	
	/**
	 * @returns true if all Nodes of the tree are covered by at least one Agent
	 */
	public boolean isValidSolution(){
		
		List<Node> listOfNodes = new ArrayList<Node>();
		listOfNodes= root.getNodeList(listOfNodes);
		
		int i=0;
		while(!listOfNodes.isEmpty() && i<numberOfRobots){
			listOfNodes.removeAll(agents[i].getNodesToVisit());
		i++;
		}
		
		if(listOfNodes.isEmpty()){
			return true;
		}
		else{
			return false;
		}

	}
	
	
	
	


}
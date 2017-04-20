package solutionData;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.stream.events.NotationDeclaration;

import tree.Node;
import tree.TreeFactory;

public class Agent {

	private Node root;
	public int energy = 0;
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> nodesToVisit;
	
	public Node getRoot() {
		return root;
	}
	
	public Node getTree(){
		return TreeFactory.createTree(root, nodesToVisit);
	}



	public int getId() {
		return id;
	}

	public boolean enoughEnergy() {
		if (energy > 0) {
			return true;
		} else {
			return false;
		}
	}



	public Agent() {
		id = idCounter;
		idCounter++;
		nodesToVisit = new ArrayList<Node>();
	}

	public Agent(Node root, int energy) {
		this.root = root;
		this.energy = energy;
		id = idCounter;
		idCounter++;
		nodesToVisit = new ArrayList<Node>();
	}

	public void addNode(Node nodeToVisit) {
		if (!nodesToVisit.contains(nodeToVisit)) {
			nodesToVisit.add(nodeToVisit);
		}
	}

	public void removeNode(Node nodeToRemove) {
		nodesToVisit.remove(nodeToRemove);
	}

	public ArrayList<Node> getNodesToVisit() {

		return nodesToVisit;
	}

	public void addNodes(List<Node> nodes) {
		nodesToVisit.addAll(nodes);
	}

	public int getStepsCount() {
		if (nodesToVisit.size() > 1) {
			return (nodesToVisit.size() - 1) * 2;
		}
		return 0;
	}

}

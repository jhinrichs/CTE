package solutionData;

import java.util.ArrayList;
import java.util.List;

import tree.INode;
import tree.Node;

public class Traversal {

	private int id;
	private static int idCounter = 0;
	private int numberOfRobots;
	private List<Agent> agents;
	
	public List<Agent> getAgents() {
		return agents;
	}

	private Node root;

	public Node getRoot() {
		return root;
	}

	/**
	 * A Traversal is a part of a solution for the given tree and the Number of
	 * robots;
	 * 
	 * @param root
	 * @param numberOfRobots
	 */
	public Traversal(Node tree, int numberOfRobots) {
		id = idCounter;
		idCounter++;
		this.root = tree;
		this.numberOfRobots = numberOfRobots;
		agents = new ArrayList<Agent>();
	}

	public void addAgent(Agent a, int index) {
		agents.add(index, a);
	}
	public void addAgent(Agent a) {
		agents.add(a);
	}
	

	public int getSteps() {
		int steps = 0;
		for (Agent agent : agents) {
			if (agent != null) {
				steps = Integer.max(steps, agent.getStepsCount());
			}
		}
		return steps;
	}

	/**
	 * @returns true if all Nodes of the tree are covered by at least one Agent
	 */
	public boolean isValidSolution() {

		List<Node> listOfNodes = new ArrayList<Node>();
		listOfNodes = root.getLeafList(listOfNodes);

		int i = 0;
		while (!listOfNodes.isEmpty() && i < numberOfRobots) {
			listOfNodes.removeAll(agents.get(i).getNodesToVisit());
			i++;
		}

		if (listOfNodes.isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

	public Traversal getStep(int i) {
		Traversal temp = new Traversal(root, numberOfRobots);
		for(Agent a : agents) {
			Agent b = new Agent(root);
			b.addNode(a.getNodesToVisit().get(i));
			temp.addAgent(b);
		}
		return temp;
	}
	public Traversal getStepsUpToNumber(int i) {
		Traversal temp = new Traversal(root, numberOfRobots);
		for(Agent a : agents) {
			Agent b = new Agent(root);
			b.addNodes(a.getNodesToVisit().subList(0, i));
			temp.addAgent(b);
		}
		return temp;
	}

}

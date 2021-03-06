package solutionData;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.net.ssl.HandshakeCompletedListener;

import optimalExploration.MovingPlanList;
import tree.Node;

public class Traversal {

	private int id;
	private static int idCounter = 0;
	private List<IAgent> agents;
	private MovingPlanList plan = new MovingPlanList();

	public Integer getNumberOfNodes() {
		if(root!= null) {
		return root.getTreeNodeCount();}
		else {
			return 0;
		}
	}
	
	public List<IAgent> getAgents() {
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
		agents = new ArrayList<IAgent>();
		for (int i = 0; i < numberOfRobots; i++) {
			IAgent a = new Agent(root);
			addAgent(a, i);
		}
	}

	public Traversal(Node tree) {
		id = idCounter;
		idCounter++;
		this.root = tree;
		agents = new ArrayList<IAgent>();
	}

	public void addAgent(IAgent a, int index) {
		agents.add(index, a);
	}

	public void addAgent(IAgent a) {
		agents.add(a);
	}

	/**
	 * returns the number of steps necessary for the agents to complete the
	 * exploration of the tree
	 * 
	 * @return
	 */
	public int getNumberOfSteps() {
		int steps = 0;
		for (IAgent agent : agents) {
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
		
//		System.out.print("test if isvalidsolution ");
		List<Node> listOfNodes = new ArrayList<Node>();
		listOfNodes = root.getLeafList(listOfNodes);
		
		Hashtable<Integer, Node> leafs = new Hashtable<>(listOfNodes.size());
		for(Node n : listOfNodes) {
			leafs.put(n.getId(), n);
		}
		
		for(IAgent a : agents) {
			List<Node> nodesToRemove = a.getLeafs();
			if(nodesToRemove.isEmpty()) {
					nodesToRemove =a.getNodesToVisit();
			}
				
			for (Node n : nodesToRemove) {
				leafs.remove(n.getId());
			}
		}

		if (leafs.isEmpty()) {
			return true;
		} else {

			return false;
		}

	}

	public boolean allRobotsAtRoot() {
		for (IAgent a : agents) {
			if (a.getPosition() != root) {
				return false;
			}
		}
		return true;
	}

	public Traversal getStep(int i) {
		Traversal temp = new Traversal(root);
		for (IAgent a : agents) {
			IAgent b = new Agent(root);
			if (!a.getNodesToVisit().isEmpty()) {

				if (i >= a.getNodesToVisit().size()) {
					b.addNode(a.getNodesToVisit().get(0));
				} else {
					b.addNode(a.getNodesToVisit().get(i));
				}
				temp.addAgent(b);
			}
		}
		return temp;
	}

	public Traversal getStepsUpToNumber(int i) {
		Traversal temp = new Traversal(root);
		for (IAgent a : agents) {
			if (!a.getNodesToVisit().isEmpty()) {
				IAgent b = new Agent(root);
				if (a.getNodesToVisit().size() < i) {
					b.addNodes(a.getNodesToVisit());
				} else {
					b.addNodes(a.getNodesToVisit().subList(0, i));
				}
				temp.addAgent(b);
			}
		}
		return temp;
	}
	
	public MovingPlanList getPlan() {
		return plan;
	}

	public String getTreeCode() {

		return root.getTreeCode();
	}

}

package solutionData;

import java.util.ArrayList;
import java.util.List;

import mate.Brain;
import mate.Brain.BrainModuleType;
import optimalExploration.MovingPlan;
import tree.INode;
import tree.Node;
import tree.TreeFactory;

public class Agent implements IAgent {

	public Node root;
	public int energy = 0;
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> path;
	public Traversal traversal;
	
	public Brain brainModule;
	
	
	public Agent(Node root, BrainModuleType brainType) {
		this(root);
		this.brainModule = new Brain(this, brainType);
		
	}
	public Agent() {
		setId();
		path = new ArrayList<Node>();
	}

	private void setId() {
		id = idCounter;
		idCounter++;
	}

	public Agent(Node root) {
		this();
		this.root = root;
		path.add(root);
		root.setVisited(true);
		root.getRobPos().addAgentToNode(this);
	}

	public Agent(Node root, int energy) {
		this(root);
		this.energy = energy;
		
	}

	public Agent(Node root, BrainModuleType brainType, Traversal traversal) {
		this(root, brainType);
		this.traversal = traversal;
	}
	
	public Brain getBrainModule() {
		return brainModule;
	}
	public void setBrainModule(Brain brainModule) {
		this.brainModule = brainModule;
	}
	
	public INode getRoot() {
		return root;
	}

	public Node getTree() {
		return TreeFactory.createTree(root, path);
	}

	public int getId() {
		return id;
	}
	
	public Node activeNode() {
		return path.get(path.size()-1);
	}

	public boolean enoughEnergy() {
		if (energy > 0) {
			return true;
		} else {
			return false;
		}
	}

	

	public void addNode(Node nodeToVisit) {
		if (!path.contains(nodeToVisit)) {
			path.add(nodeToVisit);
		}
	}

	public void removeNode(INode nodeToRemove) {
		path.remove(nodeToRemove);
	}

	public ArrayList<Node> getNodesToVisit() {
		return path;
	}

	public void addNodes(List<Node> nodes) {
		path.addAll(nodes);
	}

	
	/* 
	 */
	public int getStepsCount() {
		
		return getNodesToVisit().size();
	}

	public Node getPosition() {
		if (path.size() == 0) {
			return null;
		} else {
			
			return path.get(path.size()-1);
		}
	}
	
	public MovingPlan calculateMove() {
		Node nextNode = brainModule.getNextNode();
		MovingPlan nextStep = new MovingPlan(nextNode ,this);
		return nextStep;
	}

	public void moveAgent(Node newNode){
		activeNode().getRobPos().moveAgentTo(this, newNode);
		path.add(newNode);
		newNode.setVisited(true);
		
	}

}

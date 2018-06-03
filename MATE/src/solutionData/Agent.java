package solutionData;

import java.util.ArrayList;
import java.util.List;

import sun.management.resources.agent;
import tree.INode;
import tree.Node;
import tree.TreeFactory;

public class Agent implements IAgent {

	public Node root;
	public int energy = 0;
	private int id;
	private static int idCounter = 0;
	private ArrayList<Node> path;
	private ArrayList<Node> leafs;
	public Traversal traversal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getLeafs()
	 */
	@Override
	public ArrayList<Node> getLeafs() {
		return leafs;
	}

	public Agent() {
		setId();
		path = new ArrayList<Node>();
		leafs = new ArrayList<Node>();
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
		path.add(root);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getRoot()
	 */
	@Override
	public INode getRoot() {
		return root;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getTree()
	 */
	@Override
	public Node getTree() {
		return TreeFactory.createTree(root, path);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#activeNode()
	 */
	@Override
	public Node activeNode() {
		return path.get(path.size() - 1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#enoughEnergy()
	 */
	@Override
	public boolean enoughEnergy() {
		if (energy > 0) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#addNode(tree.Node)
	 */
	@Override
	public void addNode(Node nodeToVisit) {
		if (!path.contains(nodeToVisit)) {
			path.add(nodeToVisit);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#removeNode(tree.INode)
	 */
	@Override
	public void removeNode(INode nodeToRemove) {
		path.remove(nodeToRemove);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getNodesToVisit()
	 */
	@Override
	public ArrayList<Node> getNodesToVisit() {
		return path;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#addNodes(java.util.List)
	 */
	@Override
	public void addNodes(List<Node> nodes) {
		path.addAll(nodes);
	}

	/* 
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getStepsCount()
	 */
	@Override
	public int getStepsCount() {

		return getNodesToVisit().size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#getPosition()
	 */
	@Override
	public Node getPosition() {
		if (path.size() == 0) {
			return null;
		} else {

			return path.get(path.size() - 1);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see solutionData.IAgnet#moveAgent(tree.Node)
	 */
	@Override
	public void moveAgent(Node newNode) {
		if (newNode == null) {
			System.out.println("Node is Null 2");
		}
		activeNode().getRobPos().moveAgentTo(this, newNode);
		path.add(newNode);
		newNode.setVisited(true);
		if (newNode.isLeaf()) {
			leafs.add(newNode);
		}
	}

	public void moveAgentLeftWalker(Node n) {
		if (n == null) {
			System.out.println("Node is Null");
		} else {
			path.add(n);
			n.setVisited(true);
			if (n.isLeaf()) {
				leafs.add(n);
			}
			energy--;
		}
	}

	public void moveToNode(Node lastFound) {
		if (!lastFound.isRoot()) {
			moveToNode(lastFound.getParent());
			this.moveAgent(lastFound);
		}
	}

}

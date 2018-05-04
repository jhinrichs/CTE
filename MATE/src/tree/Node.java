package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.swing.tree.TreeNode;

import optimalExploration.CollectiveExploration;
import solutionData.IAgent;
import solutionData.RobPosTree;

public class Node implements INode {

	private static int idCount = 0;
	private int id;
	private UUID uid;
	private Node parent;
	private TreeDataCalculator treeData;
	private RobPosTree robPos;
	private String treeCode;

	public static int getIdCount() {
		return idCount;
	}

	private Boolean finished = false;
	private boolean visited = false;
	private ArrayList<Node> children;

	public Node(Node parent) {
		uid = java.util.UUID.randomUUID();

		id = idCount;
		idCount++;

		this.parent = parent;
		children = new ArrayList<Node>();
		if (parent != null) {
			this.parent.children.add(this);
		}

	}

	public Node(Node parent, int id) {
		uid = java.util.UUID.randomUUID();
		this.id = id;
		this.parent = parent;
		children = new ArrayList<Node>();
		if (parent != null) {
			this.parent.children.add(this);
		}

	}

	/**
	 * sets the visited flag from a node and updates its finished state and all changing finished states for parents and parents ......
	 * @param b
	 */
	public void setVisited(boolean b) {
		visited = b;
		if (isLeaf() && visited) {
			finished=true;
			Node nextNode = parent;
		while(nextNode!= null && !nextNode.isFinished()) {
			nextNode = nextNode.getParent();
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getTreeNodeCount()
	 */
	@Override
	public int getTreeNodeCount() {
		if (treeData == null) {
			treeData = new TreeDataCalculator(this);
		}
		return treeData.getNumberOfNodes();
	}
	
	public List<Node> getAllNodes(){
		
		List<Node> nodes = new ArrayList<Node>();
		for(Node child : children) {
			child.getNodesInternal(nodes);
		}
		nodes.add(this);
		System.out.println("number of Nodes in tree = " + nodes.size());
		
		return nodes;
	}
	
	private List<Node> getNodesInternal(List<Node> nodes){
		for(Node child : children) {
			child.getNodesInternal(nodes);
		}
		nodes.add(this);
		return nodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#addNode()
	 */
	@Override
	public void addNode() {
		new Node(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getParent()
	 */
	@Override
	public Node getParent() {
		return parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#setParent(tree.Node)
	 */
	@Override
	public void setParent(Node parent) {
		this.parent = parent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getChildren()
	 */
	@Override
	public ArrayList<Node> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#setChildren(java.util.ArrayList)
	 */
	@Override
	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#isFinished()
	 */
	@Override
	public Boolean isFinished() {
		if(isLeaf() && isVisited()) {
			finished = true;
			return true;
		}
		if (!finished && !isLeaf()) {
			for (Node child : children) {
				if (!child.finished) {
					return false;
				}
			}
			finished = true;
		}
		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#children()
	 */
	@Override
	public Enumeration<Node> children() {

		return Collections.enumeration(children);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getAllowsChildren()
	 */
	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getChildAt(int)
	 */
	@Override
	public Node getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return children.get(childIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getChildCount()
	 */
	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return children.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getIndex(javax.swing.tree.TreeNode)
	 */
	@Override
	public int getIndex(TreeNode node) {

		return children.indexOf(node);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return children.isEmpty();
	}

	public static void setIdCount(int i) {
		idCount = i;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/*
	 * for the given Node it checks if it is a Leaf. If so it adds the node to the List. 
	 * Otherwise it deligates the task to his children. 
	 * 
	 * Basically the procedure goes through the whole tree and every Node that is a Leaf checks himself into the List
	 * 
	 * @see tree.INode#getNodeList(java.util.List)
	 */
	@Override
	public List<Node> getLeafList(List<Node> listOfNodes) {

		if (isLeaf()) {
			if (listOfNodes == null) {
				System.out.println("ListOfNoes = null and roo is " + this.id);

				listOfNodes = new ArrayList<Node>();
			}
			listOfNodes.add(this);
			return listOfNodes;
		} else {
			for (INode child : getChildren()) {
				listOfNodes = child.getLeafList(listOfNodes);
			}

			return listOfNodes;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#addChild(tree.Node)
	 */
	@Override
	public void addChild(Node newChild) {

		if (newChild != null) {
			children.add(newChild);
			newChild.setParent(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#setFinished(boolean)
	 */
	@Override
	public void setFinished(boolean b) {
		finished = b;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tree.INode#isRoot()
	 */
	@Override
	public boolean isRoot() {
		if (parent == null) {
			return true;
		} else {
			return false;
		}
	}

	public RobPosTree getRobPos() {
		if(robPos == null) {
			robPos = new RobPosTree(this);
		}
		return robPos;
	}

	public void setRobPos(RobPosTree robPos) {
		this.robPos = robPos;
	}

	
	/**
	 * @param n
	 * @returns if the given Node n is contained in a Subtree of this Node
	 */
	public boolean isRootOf(Node n){
		if(maxIdOfSubTree() > n.getId() && n.getId() > id){
			return true;
		}
		else return false;
	}
	
	/**
	 * @return id of the next Node that is not within the Subtree of this Node
	 */
	public int maxIdOfSubTree() {
		
		int index = parent.getChildren().indexOf(this);
		//if parent has another child after this one
		if (parent.getChildCount() > index + 1) {
			return parent.getChildAt(index).getId();
		}
		else {
			return parent.maxIdOfSubTree();
		}
		
	}

	public boolean isVisited() {
		return visited;
	}

	public String getTreeCode() {
		
		return treeCode;
	}

	public void setTreeCode(String createedTreeCode) {
		treeCode = createedTreeCode;		
	}

}
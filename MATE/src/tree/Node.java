package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.UUID;

import javax.swing.tree.TreeNode;

public class Node implements TreeNode {

	private static int idCount=0;
	private int id;
	private UUID uid;
	private Node parent;
	public static int getIdCount() {
		return idCount;
	}

	private Boolean finished = false;
	private ArrayList<Node> children;
	
	public Node(Node parent) {
		uid = java.util.UUID.randomUUID();
		
		id = idCount;
		idCount++;
		
		this.parent = parent;
		children = new ArrayList<Node>();
		if (parent!=null) {
			this.parent.children.add(this);
		}

	}

	public void addNode() {
		new Node(this);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

	@Override
	public Enumeration<Node> children() {

		return Collections.enumeration(children);
	}

	@Override
	public boolean getAllowsChildren() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		// TODO Auto-generated method stub
		return children.get(childIndex);
	}

	@Override
	public int getChildCount() {
		// TODO Auto-generated method stub
		return children.size();
	}

	@Override
	public int getIndex(TreeNode node) {

		return children.indexOf(node);
	}

	@Override
	public boolean isLeaf() {
		// TODO Auto-generated method stub
		return children.isEmpty();
	}

}
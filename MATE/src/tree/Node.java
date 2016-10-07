package tree;


import java.util.ArrayList;
import java.util.UUID;

public class Node {

	private UUID id;
	private Node parent;
	private Boolean finished = false;
	private ArrayList<Node> children;
	
	public Node(Node parent){
		id = java.util.UUID.randomUUID();  
		this.parent=parent;
		if(!parent.equals(null)){
			this.parent.children.add(this);
		}
		
	}
	
	public void addNode(){
		new Node(this);
	}
	public void addNode(Node newNode){
		new Node(newNode);
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

	
}
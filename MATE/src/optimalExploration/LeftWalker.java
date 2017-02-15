package optimalExploration;

import solutionData.Traversal;
import tree.Node;

public class LeftWalker {

	private Node tree;
	private int numberOfRobots;
	private int depth=0;
	private int nodes;
	
	
	public int getDepth() {
		if(depth==0){
			calculateTreeData(tree);
		}
		return depth;
	}

	private void calculateTreeData(Node node) {
		nodes++;
		if(node.isLeaf()){
			calculateDepth(node);
		}
		for(Node child : node.getChildren()){
			calculateTreeData(child);
		}
		
		
	}

	private void calculateDepth(Node node) {
		int length=0;
		Node parent = node.getParent();
		while(parent != null){
			length++;
			parent = parent.getParent();
		}
		
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getNodes() {
		return nodes;
	}

	public void setNodes(int nodes) {
		this.nodes = nodes;
	}

	
	private Traversal optimumSolution;
	
	public LeftWalker(Node tree, int numberOfRobots) {
		this.tree = tree;
		this.numberOfRobots = numberOfRobots;
	}
	
	public Traversal getOptimum(){
		if(optimumSolution == null){
			computeOpt();
		}

		return optimumSolution;
	}

	private void computeOpt() {
		
		
	}
	
	

}

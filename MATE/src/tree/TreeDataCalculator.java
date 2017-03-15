package tree;

public class TreeDataCalculator {
	private Node tree;
	private int depth=0;
	private int numberOfNodes=0;
	
	
	public TreeDataCalculator(Node root){
		tree = root;
	}
	/**
	 * @return count of all nodes in given Tree
	 */
	public int getNumberOfNodes() {
		if(numberOfNodes==0){
			numberOfNodes=calculateTreeData(tree);
		}
		return numberOfNodes;
	}
	
	public int getDepth() {
		if(depth==0){
			calculateTreeData(tree);
		}
		return depth;
	}

	private int calculateTreeData(Node node) {
		if (node == null){
			return 0;
		}else{
		int nodes =1;
		if(node.isLeaf()){
			depth = Integer.max(depth, calculateDepth(node)-1);
		}
		for(Node child : node.getChildren()){
			nodes += calculateTreeData(child);
		}
		return nodes;
		}
	}

	private int calculateDepth(Node node) {
		int length=1;
		if(node == null){
			length=0;
		}
		while(node.getParent() != null){
			length++;
			node = node.getParent();
		}
		return length;
		
	}
}

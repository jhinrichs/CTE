package mate;

import java.util.List;

import solutionData.Agent;
import tree.Node;

public class MateAgent extends Agent {

	public Node activeNode;

	public List<Node> path;

	public void addNode(Node n) {
		path.add(n);
	}

	/**
	 * Moves Agent to its first unfinished child.
	 */
	public void forward() {
		if (!activeNode.isLeaf()) {
			int i = 0;
			while (activeNode.getChildAt(i).isFinished() && i < activeNode.getChildCount()) {
				i++;
			}
			if (i < activeNode.getChildCount()) {
				activeNode = activeNode.getChildAt(i);
			}
			path.add(activeNode);
		}
	}

	public void waitAtNode() {
		path.add(activeNode);
	}

	/**
	 * moves agent to parent Node if existing, and adds it to path
	 */
	public void backward() {
		if (!activeNode.isRoot()) {
			activeNode = activeNode.getParent();

		}
		path.add(activeNode);
	}
	
	public void calculateMove(){
		
	}

}

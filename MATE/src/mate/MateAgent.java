package mate;

import java.util.List;

import solutionData.Agent;
import tree.Node;

public class MateAgent extends Agent implements IMateAgent {

	public Node activeNode;
	public IDecisionModule decisionModule = new DecisionModule();

	public List<Node> path;

	public MateAgent(Node root, DecisionModule decisionModule){
		activeNode=root;
		this.decisionModule = decisionModule;
	}
	
	public void addNode(Node n) {
		path.add(n);
	}
	

	/**
	 * Moves Agent to its first unfinished child.
	 */
	public void forwardToUnfinishedChild() {
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
		else {
			throw new NullPointerException("no child is unvisited therefore the Agent is unable to move forward");	
		}
	}
	
	public void forward(Node goTo){
		if(activeNode.getChildren().contains(goTo)){
			activeNode=goTo;
			path.add(activeNode);
		}
		else {
			throw new NullPointerException("node is not a child of the active Node and therefore cant be visited");	
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
		decisionModule.getDecision();
	}

}

package mate;

import java.util.List;

import mate.Brain.BrainModuleType;
import optimalExploration.MovingPlan;
import solutionData.Agent;
import solutionData.RobPosTree;
import tree.Node;

public class MateAgent extends Agent implements IMateAgent {

	private Node activeNode;

	public Brain brainModule;

	public List<Node> path;
	
	public Node getActiveNode() {
		return activeNode;
	}

	public void setActiveNode(Node activeNode) {
		this.activeNode = activeNode;
	}

	public Brain getBrainModule() {
		return brainModule;
	}

	public void setBrainModule(Brain brainModule) {
		this.brainModule = brainModule;
	}

	public List<Node> getPath() {
		return path;
	}

	public void setPath(List<Node> path) {
		this.path = path;
	}



	public MateAgent(Node root, BrainModuleType brainType) {
		activeNode = root;
		activeNode.setVisited(true);
		this.brainModule = new Brain(this, brainType);
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
		} else {
			throw new NullPointerException("no child is unvisited therefore the Agent is unable to move forward");
		}
	}

	public void forward(Node goTo) {
		if (activeNode.getChildren().contains(goTo)) {
			activeNode = goTo;
			path.add(activeNode);
		} else {
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

	public MovingPlan calculateMove() {
		Node nextNode = brainModule.getNextNode();
		MovingPlan nextStep = new MovingPlan(nextNode ,this);
		return nextStep;
	}

	public void moveAgent(Node newNode){
		activeNode.setVisited(true);
		activeNode = newNode;
		path.add(newNode);
	}
	
	
	/**@deprecated
	 * @param newPosition
	 * @return
	 * @throws Exception 
	 */
	private boolean moveAgentOld( Node newPosition) throws Exception {
		Node previousPosition = activeNode;
		activeNode = newPosition;
		
			RobPosTree oldRob = previousPosition.getRobPos();
			RobPosTree newRob = activeNode.getRobPos();
			
			// if forward move:
			if (previousPosition.getChildren().contains(activeNode)) {				
				
				
				oldRob.removeAgentFromNode(this);
				//falls vorheriger Knoten vom typ robpos
				if(oldRob.isRobPosNode()){
					oldRob.robPosChildren.add(newPosition);
					newRob.setParent(previousPosition);
					newRob.setDistanceToParent(1);
					newRob.addAgentToNode(this);
				}
				else{
					newRob.setParent(oldRob.getParent());
					newRob.getParent().getRobPos().robPosChildren.add(activeNode);
					newRob.getParent().getRobPos().robPosChildren.remove(previousPosition);
				}
				
			}

			return false;
		
	}

}

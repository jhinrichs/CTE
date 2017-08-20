package mate;

import java.util.ArrayList;

import optimalExploration.MovingPlan;
import solutionData.Agent;
import solutionData.IAgent;
import tree.Node;

public class CTEBrain implements IBrainModule {

	private Agent agent;

	public CTEBrain(Agent a) {
		this.agent = a;
	}

	@Override
	public Node getNextNode() {
		Node nextNode = agent.activeNode();

		if (nextNode.isLeaf() || nextNode.isFinished() && !nextNode.isRoot()) {

			return nextNode.getParent();

		} else {
			nextNode = agent.activeNode().getChildAt(0);
			int minAgents = nextNode.getRobPos().getAllAgentsInTree();

			for (Node child : nextNode.getChildren()) {
				if (!child.isFinished()) {
					int tempAgentsNumber = child.getRobPos().getAllAgentsInTree();
					//agent.traversal.plan 		--> Abfrage nach bereits vwerplanten knoten einfügen 
					if (minAgents > tempAgentsNumber) {
						minAgents = tempAgentsNumber;
						nextNode = child;
					}
				}
			}

			return nextNode;
		}
	}

	private int getAgentsInTree(Node n) {

		n.getRobPos().getAllAgentsInTree();
		return 0;
	}
}

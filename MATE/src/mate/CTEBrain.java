package mate;

import solutionData.Agent;
import tree.Node;

public class CTEBrain implements IBrainModule {

	private Agent agent;

	public CTEBrain(Agent a) {
		this.agent = a;
	}

	@Override
	public Node getNextNode() {
		Node nextNode = agent.activeNode();

		if (nextNode.isLeaf() || nextNode.checkIfFinished() && !nextNode.isRoot()) {

			System.out.println("Knoten " + nextNode.getId() +" ist fertig --> gehe zu parent");
			return nextNode.getParent();

		} else {
			
			//tricky --> maximum number of all agents in whole tree plus 1 ensures there is a child with less agents in tree
			int minAgents = nextNode.getRobPos().getAllAgentsInTree()+1;

			for (Node child : nextNode.getChildren()) {
				if (!child.checkIfFinished()) {
					int tempAgentsNumber = child.getRobPos().getAllAgentsInTree();
					tempAgentsNumber  += agent.traversal.getPlan().getAgentPlannedFromTo(agent.getPosition(),child);
					System.out.println(agent.traversal.getPlan().getAgentPlannedFromTo(agent.getPosition(),child) + " Agents in Knoten " + child.getId());
					if (minAgents > tempAgentsNumber) {
						minAgents = tempAgentsNumber;
						nextNode = child;
					}
				}
			}
			System.out.println("nächster Knoten für Agent " + agent.getId() +" ist "+ nextNode.getId());
			return nextNode;
		}
	}

}

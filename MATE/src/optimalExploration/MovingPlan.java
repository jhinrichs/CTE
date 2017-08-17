package optimalExploration;

import mate.MateAgent;
import solutionData.IAgent;
import tree.Node;

public class MovingPlan {
	public Node nodeToGo;
	public IAgent agent;

	public MovingPlan() {
	}
	public MovingPlan(Node n, IAgent a) {
		agent = a;
		nodeToGo = n;
	}
	public void execute() {
//		System.out.println("Moving Agent "+ agent.getId() + " to Node " + nodeToGo.getId() ) ;
		agent.moveAgent(nodeToGo);
	}
}
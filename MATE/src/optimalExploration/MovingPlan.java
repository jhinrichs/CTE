package optimalExploration;

import solutionData.Agent;
import tree.Node;

public class MovingPlan {
	public Node nodeToGo;
	public Agent agent;

	public MovingPlan() {
	}
	public MovingPlan(Node n, Agent a) {
		agent = a;
		nodeToGo = n;
	}
}
package optimalExploration;

import mate.MateAgent;
import tree.Node;

public class MovingPlan {
	public Node nodeToGo;
	public MateAgent agent;

	public MovingPlan() {
	}
	public MovingPlan(Node n, MateAgent a) {
		agent = a;
		nodeToGo = n;
	}
	public void activate() {
		agent.moveAgent(nodeToGo);
	}
}
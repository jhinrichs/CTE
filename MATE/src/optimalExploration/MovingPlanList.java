package optimalExploration;

import java.util.ArrayList;
import java.util.List;

import solutionData.IAgent;
import tree.Node;

public class MovingPlanList {
	public List<MovingPlan> plan = new ArrayList<MovingPlan>();

	public MovingPlanList() {
		// TODO Auto-generated constructor stub
	}

	public List<MovingPlan> getPlan() {
		return plan;
	}

	public void execute() {
		for (MovingPlan p : plan) {
			p.execute();
		}
		plan = new ArrayList<MovingPlan>();

	}

	/**
	 * calculates the number of agents that will go the specified way from actual position to n new planned position
	 * @param from
	 * @param to
	 * @return
	 */
	public int getAgentPlannedFromTo(Node from, Node to) {
		int numberOfAgent = 0;
		for (MovingPlan p : plan) {
			if (p.nodeToGo.equals(to)) {
				if (p.agent.getPosition().equals(from)) {
					numberOfAgent++;
				}
			}
		}
		return numberOfAgent;
	}
}
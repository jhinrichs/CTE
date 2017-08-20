package mate;

import java.util.ArrayList;
import java.util.List;

import mate.Brain.BrainModuleType;
import optimalExploration.MovingPlan;
import optimalExploration.MovingPlanList;
import solutionData.Agent;
import solutionData.IAgent;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;

public class AgentManager {

	private List<IAgent> agents = new ArrayList<>();

	private List<Node> exploredNodes = new ArrayList<>();

	private Node root;

	private Traversal solution;

	public int searchDepth = 100;

	public static TreeDataCalculator calci;
	
	

	public AgentManager(Node root, int numberOfRobots, BrainModuleType brainType) {
		this.root = root;
		calci = new TreeDataCalculator(root);
		solution = new Traversal(root);

		for (int i = 0; i < numberOfRobots; i++) {
			Agent a = new Agent(root,brainType, solution);
			solution.addAgent(a, i);
		}
		agents = solution.getAgents();


	}
	

	private boolean computeOpt() {
		System.out.println("Start calculating mate Solution");
		int steps = 0;
		while (!root.isFinished() || !solution.allRobotsAtRoot()) {
			System.out.println("calculating step " + steps);
			step();
			steps++;
		}
		
//		for (int i=0 ; i< 1000 ;i++) {
//			System.out.println("calculating step " + steps);
//			step();
//			steps++;
//		}

		return solution.isValidSolution();
	}

	private Traversal step() {

		// 1 calculate movement for each agent
		MovingPlanList plan = new MovingPlanList();
		
		plan.calculateMoves(agents);

		// 2#
		plan.execute();
		// ProgrammManager.paintStep(solution);

		return solution;
	}


	public void nextStep() {
		System.out.println("nextStep");
		// calculateRobPosTree();
		List<MovingPlan> plan = new ArrayList<>();

		// plan steps
		for (IAgent mate : agents) {
			plan.add(mate.calculateMove());
		}

		// do steps
		for (MovingPlan step : plan) {
			step.execute();
		}

	}

	public Traversal getOptimum() {
		computeOpt();
		return solution;
	}

}

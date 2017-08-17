package mate;

import java.util.ArrayList;
import java.util.List;

import mate.Brain.BrainModuleType;
import optimalExploration.MovingPlan;
import solutionData.Agent;
import solutionData.IAgent;
import solutionData.RobPosTree;
import solutionData.Traversal;
import tree.INode;
import tree.Node;
import tree.TreeDataCalculator;
import treeExploration.ProgrammManager;

public class MateAgentManager {

	private List<IAgent> mates = new ArrayList<>();

	private List<Node> exploredNodes = new ArrayList<>();

	private Node root;

	private Traversal solution;

	public int searchDepth = 100;

	private TreeDataCalculator calci;

	public MateAgentManager(Node root, int numberOfRobots, BrainModuleType brainType) {
		this.root = root;
		calci = new TreeDataCalculator(root);
		solution = new Traversal(root, numberOfRobots);

		for (int i = 0; i < numberOfRobots; i++) {
			MateAgent a = new MateAgent(root,brainType);
			solution.addAgent(a, i);
		}
		mates = solution.getAgents();


	}

	private boolean computeOpt() {
		System.out.println("Start calculating mate Solution");
		int steps = 0;
//		while (!root.isFinished() || !solution.allRobotsAtRoot()) {
//			System.out.println("calculating step " + steps);
//			step();
//			steps++;
//		}
		
		for (int i=0 ; i< 1000 ;i++) {
			System.out.println("calculating step " + steps);
			step();
			steps++;
		}

		return solution.isValidSolution();
	}

	private Traversal step() {

		// 1 calculate movement for each agent
		List<MovingPlan> plan = new ArrayList<MovingPlan>();
		calculateMove(plan);

		// 2#
		move(plan);
		// ProgrammManager.paintStep(solution);

		return solution;
	}

	private void move(List<MovingPlan> plan) {
		for (MovingPlan p : plan) {
			p.execute();
		}

	}

	private void calculateMove(List<MovingPlan> plan) {
		for (IAgent mate : mates) {
			plan.add(((MateAgent) mate).calculateMove());
		}
	}

	private boolean allRobotsAtRoot() {
		// TODO Auto-generated method stub
		return false;
	}

	public void nextStep() {
		System.out.println("nextStep");
		// calculateRobPosTree();
		List<MovingPlan> plan = new ArrayList<>();

		// plan steps
		for (IAgent mate : mates) {
			plan.add(((MateAgent) mate).calculateMove());
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

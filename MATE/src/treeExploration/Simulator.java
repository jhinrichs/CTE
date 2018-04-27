package treeExploration;

import tree.TreeFactory;

public class Simulator implements Runnable {
	TreeFactory fac ;
	int[] numberOfAgents;
	int numberOfRuns;

	public Simulator(TreeFactory treeFactory, int[] numberOfAgents, int i) {
		fac = treeFactory;
		this.numberOfAgents = numberOfAgents;
		this.numberOfRuns = i;
	}

	@Override
	public void run() {
		
	}

}

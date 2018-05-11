package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import reporting.ExportData;
import reporting.NKExport;
import tree.Node;
import tree.TreeFactory;

public class Simulator extends Thread {


	private static int threads =0;

	int threadnumber;
	Node tree;
	int numberOfAgents;
	ExportData leftWalkerData;
	ExportData CTEData;
	double factor;
	boolean finished = false;
	

	public Simulator(Node tree, int numberOfAgents) {
		this.tree = tree;
		this.numberOfAgents = numberOfAgents;
		threadnumber = threads;
		threads++;
	}
	

	@Override
	public void run() {
		if (!finished) {

			long startTime = System.currentTimeMillis();
			System.out.println(
					"starting thread number " + threadnumber + " with " + tree.numberOfNodesInTree + " nodes ");
			threadnumber++;
			getLeftie();
			getCTE();
			long stoptime = System.currentTimeMillis();
			long usedTime = stoptime - startTime;
			calculateFactor();

			// System.out.println("finished thread number " + threadnumber + " with " +
			// tree.getTreeNodeCount()
			// + " Nodes in " + usedTime + " Seconds ... active Threads = " + activeThreads
			// + " Remaining runs : " + (worker.size()-lastStarted));
			setFinished();
		}

	}

	private void setFinished() {
		finished = true;
		
	}

	private void calculateFactor() {
		factor = (double) CTEData.numberOfSteps / (double) leftWalkerData.numberOfSteps;

		System.out.println(CTEData.numberOfAgents + " Agents and " + CTEData.numberOfNodes + " Nodes: CTE = "
				+ CTEData.numberOfSteps + " Steps --- Leftie = " + leftWalkerData.numberOfSteps
				+ " Steps. ---: real Factor= " + factor + " | Theoretical Factor = "
				+ (double) numberOfAgents / Math.log((double) numberOfAgents));

	}

	private void getCTE() {
		// System.out.println("Start cte ");
		CollectiveExploration colEx = new CollectiveExploration(tree, numberOfAgents);
		CTEData = new ExportData(colEx.getOptimum());
		// System.out.println("finish cte");
	}

	private void getLeftie() {
		// System.out.println("start leftie");
		LeftWalker leftie = new LeftWalker(tree, numberOfAgents);
		leftWalkerData = new ExportData(leftie.getOptimum());
		// System.out.println("finish leftie");
	}

}

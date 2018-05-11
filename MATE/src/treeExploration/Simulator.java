package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import reporting.ExportData;
import tree.Node;

public class Simulator extends Thread {
	static int threads = 0;
	static ArrayList<Simulator> worker = new ArrayList();
	public static GuiBuilder mainWindow;
	static int activeThreads = 0;
	static int maximumThreads = 16;
	static int lastStarted = 0;

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
		worker.add(this);

	}
	
	

	public static void startSimulation() {
		while (worker.size() > lastStarted) {
			if (activeThreads < maximumThreads) {
				Simulator sim = worker.get(lastStarted);
				activeThreads++;
				sim.start();
				lastStarted++;
			} else {
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		if (!finished) {

			long startTime = System.currentTimeMillis();
//			System.out
//					.println("starting thread number " + threadnumber + " with " + tree.numberOfNodesInTree + " nodes "+ tree.getTreeNodeCount());
			threadnumber++;
			getLeftie();
			getCTE();
			long stoptime = System.currentTimeMillis();
			long usedTime = stoptime - startTime;
			calculateFactor();
			activeThreads--;
//			System.out.println("finished thread number " + threadnumber + " with " + tree.getTreeNodeCount()
//					+ " Nodes in " + usedTime + " Seconds ... active Threads = " + activeThreads + " Remaining runs : " + (worker.size()-lastStarted));
			finished = true;
		}

	}

	private void calculateFactor() {
		factor = (double) CTEData.numberOfSteps / (double) leftWalkerData.numberOfSteps;
		System.out.println("real Factor= " + factor + " | Theoretical Factor = "
				+ (double) numberOfAgents / Math.log((double) numberOfAgents));

	}

	private void getCTE() {
//		System.out.println("Start cte ");
		CollectiveExploration colEx = new CollectiveExploration(tree, numberOfAgents);
		CTEData = new ExportData(colEx.getOptimum());
//		System.out.println("finish cte");
	}

	private void getLeftie() {
//		System.out.println("start leftie");
		LeftWalker leftie = new LeftWalker(tree, numberOfAgents);
		leftWalkerData = new ExportData(leftie.getOptimum());
//		System.out.println("finish leftie");
	}

}

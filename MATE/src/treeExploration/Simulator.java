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
	static int threads = 0;
	static ArrayList<Simulator> allWorker = new ArrayList();
	static NKExport exporter;
	public static GuiBuilder mainWindow;
	public static int finishedThreads = 0;
	static int activeThreads = 0;
	static int maximumThreads = 16;
	static int lastStarted = 0;
	private static boolean isRunning = false;
	public static boolean exportWhenFinished;

	int threadnumber;
	Node tree;
	int numberOfAgents;
	ExportData leftWalkerData;
	ExportData CTEData;
	double factor;
	boolean finished = false;
	private static boolean simulationFinished;
	public static boolean finishedInitialization;

	public Simulator(Node tree, int numberOfAgents) {
		this.tree = tree;
		this.numberOfAgents = numberOfAgents;
		threadnumber = threads;
		threads++;
		allWorker.add(this);

	}

	public static void initSimulation(int numberOfRuns, TreeFactory treeFactory, int[] numberOfAgents2) {
		exporter = new NKExport(treeFactory, numberOfAgents2, numberOfRuns);
		exporter.initializeWriteSolutionSimulation();

		exportWhenFinished=true;
		
		for (int i = 0; i < numberOfRuns; i++) {
			Node tree = treeFactory.createTree();
			for (int j = 0; j < numberOfAgents2.length; j++) {
				new Simulator(tree, numberOfAgents2[j]);
				startSimulation();
			}
			startSimulation();
		}
		

	}

	public static void startSimulation() {
		if(!isRunning) {
			isRunning = true;
			while (allWorker.size() > lastStarted) {
				if (activeThreads < maximumThreads) {
					Simulator sim = allWorker.get(lastStarted);
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
			isRunning = false;
		}
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

			if (exportWhenFinished) {
				exporter.writeSolutionsSimulator(CTEData, leftWalkerData, factor);
			}

			activeThreads--;
			// System.out.println("finished thread number " + threadnumber + " with " +
			// tree.getTreeNodeCount()
			// + " Nodes in " + usedTime + " Seconds ... active Threads = " + activeThreads
			// + " Remaining runs : " + (worker.size()-lastStarted));
			setFinished();
		}

	}

	private void setFinished() {
		finished = true;
		finishedThreads++;
		if (finishedThreads == allWorker.size()) {
			simulationFinished = true;
			System.out.println("Simulation finished");
			exporter.save();
		}
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

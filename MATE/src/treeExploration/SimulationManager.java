package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import reporting.NKExport;
import tree.Node;
import tree.TreeFactory;

public class SimulationManager extends Thread {
	int maximumThreads = 16;
	int threads = 0;
	ArrayList<Simulator> activeWorker = new ArrayList();
	ArrayList<Simulator> allWorker = new ArrayList();
	NKExport exporter;
	public GuiBuilder mainWindow;
	public int finishedThreads = 0;

	int lastStarted = 0;

	int numberOfRuns;
	TreeFactory fac;
	int[] numberOfAgents;

	public boolean finishedInitialization;
	private boolean isFinished;

	public SimulationManager(int numberOfRuns, TreeFactory treeFactory, int[] numberOfAgents) {
		exporter = new NKExport(treeFactory, numberOfAgents, numberOfRuns);
		exporter.initializeWriteSolutionSimulation();
		this.numberOfAgents = numberOfAgents;
		this.numberOfRuns = numberOfRuns;
		this.fac = treeFactory;
	}

	@Override
	public void run() {

		for (int i = 0; i < numberOfRuns; i++) {
			Node tree = fac.createTree();
			for (int j = 0; j < numberOfAgents.length; j++) {
				Simulator sim = new Simulator(tree, numberOfAgents[j]);
				allWorker.add(sim);
			}
			startThreads();
		}
		while (!isFinished) {
			startThreads();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			checkIfFinished();
		}
	}

	private void checkIfFinished() { // evtl. Problem da veränderung der Liste während auf der liste geabrietet
										// wird....
		
			for (int i = activeWorker.size()-1;i>=0;i--) {
				if (activeWorker.get(i).finished) {
					Simulator sim =activeWorker.get(i);
					exporter.writeSolutionsSimulator(sim.CTEData, sim.leftWalkerData, sim.factor);
					activeWorker.remove(activeWorker.get(i));
					
					finishedThreads++;
				}
			}

			if (finishedThreads == allWorker.size()) {
				isFinished = true;
				System.out.println("Simulation finished");
				exporter.save();
			}
		}

	

	private void startThreads() {

		while (allWorker.size() > lastStarted && activeWorker.size() < maximumThreads) {

			Simulator sim = allWorker.get(lastStarted);

			activeWorker.add(sim);
			sim.start();
			lastStarted++;
		}

	}

}

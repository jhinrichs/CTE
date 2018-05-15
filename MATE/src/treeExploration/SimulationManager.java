package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import gui.SmallSimulator;
import reporting.NKExport;
import tree.Node;
import tree.TreeFactory;

public class SimulationManager extends Thread {
	int maximumThreads = 16;
	int threads = 0;
	private ArrayList<Simulator> activeWorker = new ArrayList();
	private ArrayList<Simulator> allWorker = new ArrayList();
	private NKExport exporter;
	public GuiBuilder mainWindow;
	public int finishedThreads = 0;

	int lastStarted = 0;

	int numberOfRuns;
	private TreeFactory fac;
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

//		SmallSimulator.getSmallWindow().writeLine("Start Simulation");
		for (int i = 0; i < numberOfRuns; i++) {
			Node tree = fac.createTree();
			
			for (int j = 0; j < numberOfAgents.length; j++) {
				Simulator sim = new Simulator(tree, numberOfAgents[j]);
				allWorker.add(sim);
			}
			startThreads();
			while(!activeWorker.isEmpty()) {
				try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				checkIfFinished();
			}
		}
		finishedInitialization =true;
		while (!isFinished) {
//			SmallSimulator.getSmallWindow().writeLine(Math.round((double)finishedThreads/(double)allWorker.size()*(double)100) +" Prozent");
			startThreads();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			checkIfFinished();
		}
		System.out.println("Simulation finished");		
		
		
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

			if (finishedThreads == allWorker.size()&& finishedInitialization) {
				isFinished = true;
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

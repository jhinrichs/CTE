package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import gui.SmallSimulator;
import reporting.NKExport;
import tree.Node;
import tree.TreeFactory;

public class SimulationManager extends Thread {
	int maximumThreads = 12;
	int threads = 0;
	private ArrayList<Simulator> activeWorker = new ArrayList();
	private ArrayList<Simulator> unfinishedWorker = new ArrayList();
	public NKExport exporter;
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
	public SimulationManager(int numberOfRuns, TreeFactory treeFactory, int[] numberOfAgents, NKExport exporter) {
		this.exporter = exporter;
		exporter.addRun(treeFactory, numberOfAgents, numberOfRuns);
		this.numberOfAgents = numberOfAgents;
		this.numberOfRuns = numberOfRuns;
		this.fac = treeFactory;
	}


	@Override
	public void run() {

//		SmallSimulator.getSmallWindow().writeLine("Start Simulation");
		System.out.println("Start Simulation");
		for (int i = 0; i < numberOfRuns; i++) {
			System.out.println("Start tree with " + fac.numberOfNodes);
			Node tree = fac.createTree();
			
			for (int j = 0; j < numberOfAgents.length; j++) {
				Simulator sim = new Simulator(tree, numberOfAgents[j]);
				unfinishedWorker.add(sim);
				startThreads();
				while (!unfinishedWorker.isEmpty()) {
					try {
						sleep(100);
						startThreads();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					checkIfFinished();
				}
				exporter.save();
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
		System.out.println("Simulation finished ############################################################");		
		
		exporter.save();
		

		SimulationQueue.startNextSimulation();
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

			if (unfinishedWorker.isEmpty() && activeWorker.isEmpty() && finishedInitialization) {
				isFinished = true;

			}
			
		}

	

	private void startThreads() {

		while (!unfinishedWorker.isEmpty() && activeWorker.size() < maximumThreads) {

			Simulator sim = unfinishedWorker.get(0);
			unfinishedWorker.remove(sim);
			activeWorker.add(sim);
			sim.start();

		}

	}

}

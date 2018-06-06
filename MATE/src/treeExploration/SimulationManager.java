package treeExploration;

import java.util.ArrayList;

import gui.GuiBuilder;
import gui.SmallSimulator;
import gui.simulationPopUp;
import reporting.NKExport;
import tree.Node;
import tree.TreeFactory;

public class SimulationManager extends Thread {
	int maximumThreads = 1;
	int activeNodes=0;
	int maximumNodes = 8100100; // 10 millionen knoten gesamtleistung... --> ca 5gb ram
//	int threads = 0;
	private ArrayList<Simulator> activeWorker = new ArrayList();
	private ArrayList<Simulator> unfinishedWorker = new ArrayList();
	public NKExport exporter;
	public GuiBuilder mainWindow;
	public static int finishedThreads = 0;
	public int progress =1;

	public Simulator lastStarted;

	int numberOfRuns;
	private TreeFactory fac;
	int[] numberOfAgents;

	public boolean finishedInitialization;
	private boolean isFinished;
	private NKExport exportAll;

	public SimulationManager(int numberOfRuns, TreeFactory treeFactory, int[] numberOfAgents) {
		exporter = new NKExport(treeFactory, numberOfAgents, numberOfRuns);
		exporter.initializeWriteSolutionSimulation();
		this.numberOfAgents = numberOfAgents;
		this.numberOfRuns = numberOfRuns;
		this.fac = treeFactory;
	}
	public SimulationManager(int numberOfRuns, TreeFactory treeFactory, int[] numberOfAgents, NKExport exporter, NKExport exAll) {
		this.exporter = exporter;
		exporter.addRun(treeFactory, numberOfAgents, numberOfRuns);
		exportAll= exAll;
		this.numberOfAgents = numberOfAgents;
		this.numberOfRuns = numberOfRuns;
		this.fac = treeFactory;
	}


	@Override
	public void run() {

//		SmallSimulator.getSmallWindow().writeLine("Start Simulation");
		System.out.println("Start Simulation");
		for (int i = 0; i < numberOfRuns; i++) {

			Node tree = fac.createTree();
			System.out.println("Start tree with " + tree.getNumberOfNodesInTree());
			for (int j = 0; j < numberOfAgents.length; j++) {
				Simulator sim = new Simulator(tree, numberOfAgents[j], fac);
				unfinishedWorker.add(sim);
				
					
				}
			startThreads();
			while (!unfinishedWorker.isEmpty() || !activeWorker.isEmpty()) {
				try {
					sleep(100);
					startThreads();
					checkIfFinished();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				exportAll.save();
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
		
		exportAll.save();
		exporter.save();
		

		SimulationQueue.startNextSimulation();
	}

	private void checkIfFinished() { // evtl. Problem da veränderung der Liste während auf der liste geabrietet
										// wird....
		
			for (int i = activeWorker.size()-1;i>=0;i--) {
				if (activeWorker.get(i).finished) {
					Simulator sim =activeWorker.get(i);
					exporter.writeSolutionsSimulator(sim.fac,sim.CTEData, sim.leftWalkerData, sim.factor);
					exportAll.writeSolutionsSimulator(sim.fac,sim.CTEData, sim.leftWalkerData, sim.factor);
					activeWorker.remove(activeWorker.get(i));
					activeNodes -=sim.tree.getNumberOfNodesInTree();
					finishedThreads++;
					progress++;
//					System.out.println("Active Nodes: - " + activeNodes);
					updateWindow();
				}
			}

			if (unfinishedWorker.isEmpty() && activeWorker.isEmpty() && finishedInitialization) {
				isFinished = true;

			}
			
		}

	

	private void startThreads() {

		
		while (!unfinishedWorker.isEmpty() && activeNodes+ unfinishedWorker.get(0).tree.getNumberOfNodesInTree() < maximumNodes && activeWorker.size()<maximumThreads) {

			Simulator sim = unfinishedWorker.get(0);
			unfinishedWorker.remove(sim);
			activeWorker.add(sim);
			sim.start();
			lastStarted=sim;
			activeNodes += sim.tree.getNumberOfNodesInTree();
			System.out.println("Active Nodes: " + activeNodes);
			updateWindow();
		}

	}
	private void updateWindow() {
		simulationPopUp.updateValues(activeWorker.size(),activeNodes,lastStarted, finishedThreads, getProgress());
	}
	private int getProgress() {
		int allSimulations = numberOfRuns*numberOfAgents.length;
		return 100*progress/allSimulations;
	}

}

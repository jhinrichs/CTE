package treeExploration;

import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import reporting.NKExport;
import tree.TreeFactory;



public class SimulationQueue {

	public static SimulationManager activeSimulation = null;
	public static ArrayList<SimulationManager> manager = new ArrayList<SimulationManager>();

	
	public static void queuSimulation(int numberOfRuns, TreeFactory fac, int[] numberOfAgents) {
			
		NKExport exporter = new NKExport(fac, numberOfAgents, numberOfRuns);
				 exporter.initializeWriteSolutionSimulation();			
			manager.add(new SimulationManager(numberOfRuns,fac , numberOfAgents,exporter));
			System.out.println("Simulation added queue");
			
			if(activeSimulation == null) {
				startNextSimulation();
			}
		}
	
	public static void startNextSimulation() {
		if(manager == null || !manager.isEmpty()) {
			
			activeSimulation = manager.get(0);
			activeSimulation.start();
			manager.remove(0);
			System.out.println("Start next simulation");
		}
		else {
			activeSimulation = null;
		}
		
	}
	
	public static void stopSimulations() {
		manager = null;
		activeSimulation.exporter.save();
		activeSimulation=null;
	}
		
	}



package treeExploration;

import java.util.ArrayList;

import com.sun.corba.se.spi.orbutil.fsm.Action;

import gui.GuiBuilder;
import reporting.NKExport;
import tree.TreeFactory;



public class SimulationQueue {

	public static SimulationManager activeSimulation = null;
	public static ArrayList<SimulationManager> manager = new ArrayList<SimulationManager>();
	public static GuiBuilder mainWindow;
	public static NKExport exportAll; 
	
	public static void queuSimulation(int numberOfRuns, TreeFactory fac, int[] numberOfAgents, GuiBuilder gui) {
			if(mainWindow == null) {
				mainWindow =gui;
			}
			if(exportAll == null) {
				exportAll= new NKExport(fac, numberOfAgents, numberOfRuns);
				exportAll.initializeWriteSolutionSimulation();
			}
			
		NKExport exporter = new NKExport(fac, numberOfAgents, numberOfRuns);
				 exporter.initializeWriteSolutionSimulation();		
				 
				 
			manager.add(new SimulationManager(numberOfRuns,fac , numberOfAgents,exporter,exportAll));
			System.out.println("Simulation added queue");
			
			if(activeSimulation == null) {
				startNextSimulation();
			}
		}
	
	public static void startNextSimulation() {
		if(manager != null && !manager.isEmpty()) {
			
			activeSimulation = manager.get(0);
			activeSimulation.start();
			manager.remove(0);
			System.out.println("Start next simulation");
		}
		else {
			
			activeSimulation = null;
			if(mainWindow.isInfiniteLoop()&&manager!=null) {
				mainWindow.setNewValues();
				mainWindow.simulation.runSimulationButton.doClick();
			}
		}
		
	}
	
	public static void stopSimulations() {
		manager = null;
		activeSimulation.exporter.save();
		activeSimulation=null;
	}
		
	}



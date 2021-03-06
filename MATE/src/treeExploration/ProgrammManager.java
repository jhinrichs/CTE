package treeExploration;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import drawTree.TreePainter;
import gui.GuiBuilder;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import reporting.ExportData;
import reporting.NKExport;
import solutionData.Agent;
import solutionData.IAgent;
import solutionData.IAgent;
import solutionData.SolutionManager;
import solutionData.Traversal;
import tree.INode;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;
import tree.Verteilungsfunktionen;

/**
 * @author jonas.hinrichs
 *
 */
public class ProgrammManager {
	private static Node tree = new Node(null);
	private static GuiBuilder mainWindow;

	private static CollectiveExploration colEx;
	private static LeftWalker leftie;
	private static TreeFactory treeFactory;

	public static Traversal recentTraversal;

	public static INode getRoot() {
		return tree;
	}

	public static void setRoot(Node root) {
		ProgrammManager.tree = root;
	}

	public static void main(String[] args) {

		mainWindow = new GuiBuilder();
		mainWindow.btnCreateTree.doClick();
	}

	/**
	 * gets a JPanel and paints the active tree within that window
	 * 
	 * @param panel
	 * @param bigNodes
	 */
	public static void paintTree(JPanel panel, boolean bigNodes) {

		TreePainter painter = new TreePainter();
		painter.drawTree(tree, panel, bigNodes);
	}

	/**
	 * @deprecated
	 * 
	 */
	public static void paintCTE() {
		calculateCTE();

		System.out.println("print collective paths");
		TreePainter painter = new TreePainter();

		mainWindow.treeInlayPanel.repaint();
		mainWindow.treeInlayPanel.removeAll();
		painter.drawTree(colEx.root, colEx.getOptimum(), mainWindow.treeInlayPanel,
				mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);

	}

	public static void paintOnlyAgentPaths(int agent) {

		TreePainter painter = new TreePainter();
		painter.drawTree(recentTraversal.getAgents().get(agent).getTree(), mainWindow.treeInlayPanel,
				mainWindow.bigNodesCheckBox.isSelected());
	}

	public static void paintAgentPaths(int agent) {

		TreePainter painter = new TreePainter();

		Traversal tempPaint = createTempPaint(agent);

		painter.drawTree(recentTraversal.getRoot(), tempPaint, mainWindow.treeInlayPanel,
				mainWindow.bigNodesCheckBox.isSelected());
	}

	private static Traversal createTempPaint(int agent) {
		Traversal tempPaint = new Traversal(recentTraversal.getRoot(), 2);
		IAgent a = new Agent(recentTraversal.getRoot());
		a.addNodes(recentTraversal.getRoot().getAllNodes());
		tempPaint.addAgent(a, 0);
		tempPaint.addAgent(recentTraversal.getAgents().get(agent), 1);
		return tempPaint;
	}

	public static void paintAllAgents() {
		// System.out.println("print paths for all agents");
		TreePainter painter = new TreePainter();
		mainWindow.treeInlayPanel.removeAll();
		// treeInlayPanel.repaint();
		painter.drawTree(recentTraversal.getRoot(), recentTraversal, mainWindow.treeInlayPanel,
				mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);
	}

	/**
	 * paint only positions of agent at given step
	 * 
	 * @param i
	 */
	public static void paintStep(int i) {
		System.out.println("print step number " + i + " for all agents");
		paintStep(recentTraversal.getStep(i));
	}

	/**
	 * paints all steps up to given step
	 * 
	 * @param i
	 */
	public static void paintSteps(int i) {

		System.out.println("print step up to number " + i + " for all agents");
		paintStep(recentTraversal.getStepsUpToNumber(i));

	}

	public static void paintStep(Traversal solution) {

		TreePainter painter = new TreePainter();
		mainWindow.treeInlayPanel.removeAll();
		mainWindow.treeInlayPanel.repaint();
		painter.drawTree(solution.getRoot(), solution, mainWindow.treeInlayPanel,
				mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);
	}

	public static void createTree(long seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double leafFactor, Verteilungsfunktionen verteilung, boolean randomized) {

		TreeFactory treeCreator = new TreeFactory(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes,
				minNodes, leafFactor, verteilung,randomized);
		tree = treeCreator.createTree();

	}

	public static TreeFactory createTreeFactory(int seed, int maxDepth, int minDepth, int maxBranches, int minBranches,
			int maxNodes, int minNodes, double leafFactor, Verteilungsfunktionen verteilung, Boolean randomized) {

		return new TreeFactory(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes, minNodes, leafFactor,
				verteilung, randomized);
	}

	private static void printTraversals(Traversal bestPath) {
		int maxNodes = 0;

		for (IAgent a : bestPath.getAgents()) {
			if (a.getNodesToVisit().size() > maxNodes) {
				maxNodes = a.getNodesToVisit().size();
			}

			System.out.println("Agent " + a.getId() + " besucht die Knoten :");

			for (INode n : a.getNodesToVisit()) {
				System.out.print(n.getId() + ",");

			}

			System.out.println();
		}
		System.out.println();
		System.out.println("Maximale Anzahl besuchter Knoten = " + maxNodes);
	}

	public static TreeDataCalculator getTreeDataImprovised() {
		TreeDataCalculator calcie = new TreeDataCalculator(tree);

		return calcie;

	}

	public static void calculateLeftWalker() {
		SolutionManager solutionManager = new SolutionManager(tree, mainWindow.getNumberOfAgents());

		leftie = solutionManager.getLeftWalker();

		recentTraversal = leftie.getOptimum();

		paintAndPrintPaths();
	}

	private static void paintAndPrintPaths() {
		paintAllAgents();
		// printTraversals(recentTraversal);
		System.out.println("number of Nodes in tree = " + tree.getTreeNodeCount());
		System.out.println("number of Steps needed = " + recentTraversal.getNumberOfSteps());
	}

	public static void calculateCTE() {
		SolutionManager solutionManager = new SolutionManager(tree, mainWindow.getNumberOfAgents());

		colEx = solutionManager.getCTE();

		recentTraversal = colEx.getOptimum();
		paintAndPrintPaths();
	}

	/**
	 * @deprecated
	 * @param treeFactory
	 * @param numberOfAgents
	 * @param numberOfRuns
	 */
	public static void runSimulation(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {
		int percent = 0;
		estimateTime(numberOfAgents, numberOfRuns);
		long startTime = System.currentTimeMillis();

		NKExport exporter = new NKExport(treeFactory, numberOfAgents, numberOfRuns);
		ExportData[][] allSolutions = new ExportData[numberOfRuns][];
		for (int i = 0; i < numberOfRuns; i++) {
			tree = treeFactory.createTree(); // eventuell muss seed neu gesetzt werden Wenn alle B�ume gleichen Seed
												// haben

			ExportData[] solutionPack = new ExportData[numberOfAgents.length];
			for (int agentNumber = 0; agentNumber < numberOfAgents.length; agentNumber++) {

				SolutionManager thisSolutionManager = new SolutionManager(tree, numberOfAgents[agentNumber]);
				Traversal solution = thisSolutionManager.getCTE().getOptimum();
				// System.out.println("number of Nodes in tree = "+ tree.getTreeNodeCount());
				// System.out.println("number of Steps needed = "+ solution.getNumberOfSteps());
				solutionPack[agentNumber] = new ExportData(solution);
			}
			allSolutions[i] = solutionPack;

			if (i % (numberOfRuns / 100) == 0) {
				System.out.println(percent + " Prozent erreicht");
				percent++;
			}
		}
		exporter.addSolutions(allSolutions);
		exporter.save();
		long stoptime = System.currentTimeMillis();
		long usedTime = stoptime - startTime;
		System.out.println("Simulation finished... time used: " + usedTime / 1000 + " seconds");
	}

	public static void runSimulationComparison(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {

		int percent = 0;

		long startTime = System.currentTimeMillis();

		NKExport exporter = new NKExport(treeFactory, numberOfAgents, numberOfRuns);
		exporter.initializeWriteSolutionSimulation();
		for (int i = 0; i < numberOfRuns; i++) {
			tree = treeFactory.createTree(); // eventuell muss seed neu gesetzt werden Wenn alle B�ume gleichen Seed
												// haben

			ExportData[][] solutionPack = new ExportData[2][numberOfAgents.length];

			for (int agentNumber = 0; agentNumber < numberOfAgents.length; agentNumber++) {

				SolutionManager thisSolutionManager = new SolutionManager(tree, numberOfAgents[agentNumber]);
				Traversal cteSolution = thisSolutionManager.getCTE().getOptimum();
				Traversal leftieSolution = thisSolutionManager.getLeftWalker().getOptimum();
				// System.out.println("number of Nodes in tree = "+ tree.getTreeNodeCount());
				// System.out.println("number of Steps needed = "+ solution.getNumberOfSteps());
				solutionPack[0][agentNumber] = new ExportData(cteSolution);
				solutionPack[1][agentNumber] = new ExportData(leftieSolution);

			}
			exporter.writeSolutionPack(solutionPack);

			if (i % (numberOfRuns / 100) == 0) {
				long stoptime = System.currentTimeMillis();
				long usedTime = stoptime - startTime;
				System.out.println(
						percent + " Prozent erreicht : --- Verbleibende Zeit = " + (100 - percent) * usedTime / 100000);
				percent++;

			}
		}

		exporter.save();
		long stoptime = System.currentTimeMillis();
		long usedTime = stoptime - startTime;
		System.out.println("Simulation finished... time used: " + usedTime / 1000 + " seconds");
	}


	public static void runSimulationThreaded(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {

		new SimulationManager(numberOfRuns, treeFactory, numberOfAgents).start();
	
	}


	private static void estimateTime(int[] numberOfAgents, int numberOfRuns) {
		long startTime = System.currentTimeMillis();
		for (int agentNumber = 0; agentNumber < numberOfAgents.length; agentNumber++) {

			SolutionManager solutionManager = new SolutionManager(tree, numberOfAgents[agentNumber]);
			Traversal solution = solutionManager.getCTE().getOptimum();
		}
		long stoptime = System.currentTimeMillis();
		long usedTime = stoptime - startTime;
		System.out.println(
				"Start Simulation.... estimated Time to finish is " + (usedTime * numberOfRuns) / 1000 + " seconds");
	}

	public static boolean isInitialized() {
		// TODO Auto-generated method stub
		return false;
	}

}

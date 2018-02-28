package treeExploration;


import javax.swing.JPanel;

import drawTree.TreePainter;
import gui.GuiBuilder;
import mate.AgentManager;
import mate.Brain.BrainModuleType;
import optimalExploration.CollectiveExploration;
import optimalExploration.LeftWalker;
import solutionData.Agent;
import solutionData.IAgent;
import solutionData.SolutionManager;
import solutionData.Traversal;
import tree.INode;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;

/**
 * @author jonas.hinrichs
 *
 */
public class ProgrammManager {
	private static Node tree = new Node(null);
	private static GuiBuilder mainWindow;

	private static CollectiveExploration colEx;

	
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


	/** gets a JPanel and paints the active tree within that window
	 * @param panel
	 * @param bigNodes
	 */
	public static void paintTree(JPanel panel, boolean bigNodes){

		TreePainter painter = new TreePainter();
		painter.drawTree(tree,panel, bigNodes);
	}
	
	/**@deprecated
	 * 
	 */
	public static void paintCTE() {
		calculateCTE();
		
		System.out.println("print collective paths");
		TreePainter painter = new TreePainter();

		mainWindow.treeInlayPanel.repaint();
		mainWindow.treeInlayPanel.removeAll();
		painter.drawTree(colEx.root, colEx.getOptimum(), mainWindow.treeInlayPanel, mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);
		

	}
	
	
	public static void paintOnlyAgentPaths(int agent){

		TreePainter painter = new TreePainter();
		painter.drawTree(recentTraversal.getAgents().get(agent).getTree(),mainWindow.treeInlayPanel, mainWindow.bigNodesCheckBox.isSelected());
	}
	
	public static void paintAgentPaths(int agent){

		TreePainter painter = new TreePainter();
		
		Traversal tempPaint = createTempPaint(agent);
		
		painter.drawTree(recentTraversal.getRoot(), tempPaint ,mainWindow.treeInlayPanel, mainWindow.bigNodesCheckBox.isSelected());
	}
	private static Traversal createTempPaint(int agent) {
		Traversal tempPaint = new Traversal(recentTraversal.getRoot(), 2);
		Agent a = new Agent(recentTraversal.getRoot());
		a.addNodes(recentTraversal.getRoot().getAllNodes());
		tempPaint.addAgent(a, 0);
		tempPaint.addAgent(recentTraversal.getAgents().get(agent), 1);
		return tempPaint;
	}
	
	
	public static void paintAllAgents(){
		System.out.println("print paths for all agents");
		TreePainter painter = new TreePainter();
		mainWindow.treeInlayPanel.removeAll();
		//treeInlayPanel.repaint();
		painter.drawTree(recentTraversal.getRoot(), recentTraversal, mainWindow.treeInlayPanel, mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);
	}
	
	/**paint only positions of agent at given step
	 * @param i
	 */
	public static void paintStep(int i) {	
		System.out.println("print step number " +i+ " for all agents");
		paintStep(recentTraversal.getStep(i));
	}
	
	/**paints all steps up to given step
	 * @param i
	 */
	public static void paintSteps(int i) {
		
		System.out.println("print step up to number " +i+ " for all agents");	
		paintStep(recentTraversal.getStepsUpToNumber(i));

	}
	
	public static void paintStep(Traversal solution) {
		
		TreePainter painter = new TreePainter();
		mainWindow.treeInlayPanel.removeAll();
		mainWindow.treeInlayPanel.repaint();
		painter.drawTree(solution.getRoot(), solution, mainWindow.treeInlayPanel, mainWindow.bigNodesCheckBox.isSelected());
		mainWindow.frame.setVisible(true);		
	}
	
	

	public static void createTree(int seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double leafFactor) {

		TreeFactory treeCreator = new TreeFactory(seed,maxDepth,minDepth,maxBranches,minBranches,maxNodes,minNodes,leafFactor);
		tree = treeCreator.createTree();
				
	}
	

	
	private static void printTraversals(Traversal bestPath) {
		int maxNodes=0;
		
		for( IAgent a : bestPath.getAgents()){
			if(a.getNodesToVisit().size()>maxNodes) {
				maxNodes = a.getNodesToVisit().size();
			}
			
			System.out.println("Agent " + a.getId() + " besucht die Knoten :");
			
			for( INode n : a.getNodesToVisit()){
				System.out.print(n.getId()+",");
				
			}

			System.out.println();
		}
		System.out.println();
		System.out.println("Maximale Anzahl besuchter Knoten = " + maxNodes);
	}
	public static TreeDataCalculator getTreeDataImprovised(){
		TreeDataCalculator calcie = new TreeDataCalculator(tree);
		
		return calcie;
		
	}
	public static void calculateCTE() {
		SolutionManager solutionManager = new SolutionManager(tree, mainWindow.getNumberOfAgents());
		
		colEx = solutionManager.getCTE();
		
		recentTraversal = colEx.getOptimum(); 
		paintAllAgents();
		printTraversals(colEx.getOptimum());
	}




	
}

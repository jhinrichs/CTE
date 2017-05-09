package treeExploration;


import javax.swing.JPanel;

import drawTree.TreePainter;
import gui.GuiBuilder;
import optimalExploration.LeftWalker;
import solutionData.Agent;
import solutionData.SolutionManager;
import solutionData.Traversal;
import tree.INode;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;
import tree.TreeSpecifier;

/**
 * @author jonas.hinrichs
 *
 */
public class ProgrammManager {
	private static Node tree = new Node(null);
	private static GuiBuilder mainWindow;
	private static LeftWalker leftWalker;
	
	

	public static INode getRoot() {
		return tree;
	}
	public static void setRoot(Node root) {
		ProgrammManager.tree = root;
	}
	public static void main(String[] args) {
		
		mainWindow = new GuiBuilder();
	}


	/** gets a JPanel and paints the active tree within that window
	 * @param panel
	 * @param bigNodes
	 */
	public static void paintTree(JPanel panel, boolean bigNodes){
		
		TreePainter painter = new TreePainter();
		painter.drawTree(tree,panel, bigNodes);
	}
	
	public static void paintAgentPaths(JPanel panel, boolean bigNodes, int agent){

		TreePainter painter = new TreePainter();
		painter.drawTree(leftWalker.getOptimum().getAgents()[agent].getTree(), panel, bigNodes);
	}
	
	public static void paintAllAgents(JPanel panel, boolean bigNodes){
		System.out.println("print paths");
		TreePainter painter = new TreePainter();
		painter.drawTree(leftWalker.tree, leftWalker.getOptimum(), panel, bigNodes);
	}

	public static void createTree(int seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double leafFactor, TreeSpecifier treeSpecifier) {

		TreeFactory treeCreator = new TreeFactory(seed,maxDepth,minDepth,maxBranches,minBranches,maxNodes,minNodes,leafFactor, treeSpecifier);
		tree = treeCreator.createTree();
				
	}
	
	public static void calculateLeftWalker(int numberOfRobots){
	
		SolutionManager solutionManager = new SolutionManager(tree, numberOfRobots);
		
		leftWalker = solutionManager.getLeftWalker();
		
		System.out.println(leftWalker.getOptimum().getSteps()+" schritte nötig");
		printTraversals(leftWalker.getOptimum());
	}
	
	private static void printTraversals(Traversal bestPath) {
		for( Agent a : bestPath.getAgents()){
			System.out.println("Agent " + a.getId() + " besucht die Knoten :");
			for( INode n : a.getNodesToVisit()){
				System.out.print(n.getId()+",");
			}
			System.out.println();
		}
	}
	public static TreeDataCalculator getTreeDataImprovised(){
		TreeDataCalculator calcie = new TreeDataCalculator(tree);
		
		return calcie;
		
	}
	
}

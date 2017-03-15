package treeExploration;


import javax.swing.JPanel;

import drawTree.TreePainter;
import gui.GuiBuilder;
import solutionData.Agent;
import solutionData.SolutionManager;
import solutionData.Traversal;
import tree.Node;
import tree.TreeDataCalculator;
import tree.TreeFactory;
import tree.TreeSpecifier;

public class ProgrammManager {
	private static Node tree = new Node(null);
	private static GuiBuilder mainWindow;
	

	public static Node getRoot() {
		return tree;
	}
	public static void setRoot(Node root) {
		ProgrammManager.tree = root;
	}
	public static void main(String[] args) {
		
		mainWindow = new GuiBuilder();
	}


	public static void paintTree(JPanel panel, boolean bigNodes){
		
		TreePainter painter = new TreePainter();
		painter.drawTree(tree,panel, bigNodes);
	}

	public static void createTree(int seed, int maxDepth, int minDepth, int maxBranches, int minBranches, int maxNodes,
			int minNodes, double leafFactor, TreeSpecifier treeSpecifier) {

		TreeFactory treeCreator = new TreeFactory(seed,maxDepth,minDepth,maxBranches,minBranches,maxNodes,minNodes,leafFactor, treeSpecifier);
		tree = treeCreator.createTree();
				
	}
	
	public static void calculateOptimum(int numberOfRobots){
	
		SolutionManager solutionManager = new SolutionManager(tree, numberOfRobots);
		
		Traversal bestPath = solutionManager.getOptimum();
		
		System.out.println(bestPath.getSteps()+" schritte nötig");
		printTraversals(bestPath);
	}
	
	private static void printTraversals(Traversal bestPath) {
		for( Agent a : bestPath.getAgents()){
			System.out.println("Agent " + a.getId() + " besucht die Knoten :");
			for( Node n : a.getNodesToVisit()){
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

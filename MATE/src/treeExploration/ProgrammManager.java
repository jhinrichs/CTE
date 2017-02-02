package treeExploration;


import javax.swing.JDialog;
import javax.swing.JPanel;

import drawTree.TreePainter;
import gui.GuiBuilder;
import solutionData.SolutionManager;
import solutionData.Traversal;
import tree.Node;
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
	
	public void calculateOptimum(int numberOfRobots){
	
		SolutionManager solutionManager = new SolutionManager(tree, numberOfRobots);
		
		Traversal bestPath = solutionManager.getOptimum();
		
		
		
	}
	
}

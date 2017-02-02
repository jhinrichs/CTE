package treeExploration;


import javax.swing.JDialog;
import javax.swing.JPanel;

import drawTree.TreePainter;
import tree.Node;
import tree.TreeCreator;

public class ProgrammManager {
	private static Node tree = new Node(null);

	public static Node getRoot() {
		return tree;
	}
	public static void setRoot(Node root) {
		ProgrammManager.tree = root;
	}
	public static void main() {
		// TODO Auto-generated method stub
//		
		

	}


	public static void paintTree(JPanel panel){
		
		TreePainter painter = new TreePainter();
		painter.drawTree(tree,panel);
		
	}
	
	public static void calculatePaths(){
		
	}
	
	public static void showSolutions(){
		
	}
	
}

package treeExploration;


import javax.swing.JDialog;
import javax.swing.JPanel;

import drawTree.TreePainter;
import tree.Node;
import tree.TreeCreator;

public class Main {
	private static Node root = new Node(null);

	public static Node getRoot() {
		return root;
	}
	public static void setRoot(Node root) {
		Main.root = root;
	}
	public static void main() {
		// TODO Auto-generated method stub
//		
		

	}
	public static void createNewTree(long seed){
		root = new Node(null);
		TreeCreator creator = new TreeCreator(seed);
		creator.addTree(root);
	}

	public static void paintTree(JPanel panel){
		
		TreePainter painter = new TreePainter();
		painter.drawTree(root,panel);
		
	}
}

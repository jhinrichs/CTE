package treeExploration;


import javax.swing.JDialog;
import javax.swing.JPanel;

import drawTree.TreePainter;
import tree.Node;
import tree.TreeCreator;

public class Main {

	public static void main(JPanel panel) {
		// TODO Auto-generated method stub
		Node root= new Node(null);
		TreeCreator creator = new TreeCreator();
		creator.addTree(root);
		TreePainter painter = new TreePainter();
		painter.drawTree(root,panel);
		

	}

}

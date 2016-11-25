package drawTree;

import java.awt.Color;
import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.tree.TreeNode;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

import gui.GuiBuilder;
import tree.Node;
import tree.TreeCreator;
import tree.TreeCreator.treeTypes;

public class TreePainter {

	private static void showInDialog(JComponent panel) {
		JDialog dialog = new JDialog();
		Container contentPane = dialog.getContentPane();
		((JComponent) contentPane).setBorder(BorderFactory.createEmptyBorder(
				100, 10, 100, 10));
		contentPane.add(panel);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);		
	}
	
	public void drawTree(Node root, JPanel panel2){

		
		TreeForTreeLayout treeForLayout = new TreeForTreeLayout(root);
			
		
		// setup the tree layout configuration
		double gapBetweenLevels = 10;
		double gapBetweenNodes = 2;
		DefaultConfiguration<Node> configuration = new DefaultConfiguration<Node>(
				gapBetweenLevels, gapBetweenNodes);
		
		// create the NodeExtentProvider for TextInBox nodes
		NodeExtentProvider<Node> nodeExtentProvider = new NodeExtentProvider<Node>();
		
		
		TreeLayout<Node> treeLayout = new TreeLayout<Node>(treeForLayout, nodeExtentProvider, configuration);
		
		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane panel = new TextInBoxTreePane(treeLayout);
		showInDialog(panel, panel2);
//		showInDialog(panel);
		
		

	}

	private void showInDialog(TextInBoxTreePane panel, JPanel panel2) {

		panel2.add(panel);

	}
	
}

package drawTree;

import java.awt.Container;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;

import solutionData.Traversal;
import tree.Node;

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
	
	public void drawTree(Node root, JPanel panel, boolean bigNodes){
		
		TreeForTreeLayout treeForLayout = new TreeForTreeLayout(root);
					
		// setup the tree layout configuration
		double gapBetweenLevels = 10;
		double gapBetweenNodes = 5;
		DefaultConfiguration<Node> configuration = new DefaultConfiguration<Node>(
				gapBetweenLevels, gapBetweenNodes);
		
		// create the NodeExtentProvider for TextInBox nodes
		if(!bigNodes){
			NodeExtentProvider.height = 5;
			NodeExtentProvider.width =5;
			}
		else{
			NodeExtentProvider.height = 20;
			NodeExtentProvider.width =20;
			}
		NodeExtentProvider<Node> nodeExtentProvider = new NodeExtentProvider<Node>();
		
		
		TreeLayout<Node> treeLayout = new TreeLayout<Node>(treeForLayout, nodeExtentProvider, configuration);
		
		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane pane = new TextInBoxTreePane(treeLayout,bigNodes);
		panel.add(pane);
		

	}

	public void drawTree(Node root, Traversal leftWalker, JPanel panel, boolean bigNodes) {

		TreeForTreeLayout treeForLayout = new TreeForTreeLayout(root);
		
		// setup the tree layout configuration
		double gapBetweenLevels = 10;
		double gapBetweenNodes = 5;
		DefaultConfiguration<Node> configuration = new DefaultConfiguration<Node>(
				gapBetweenLevels, gapBetweenNodes);
		
		// create the NodeExtentProvider for TextInBox nodes
		if(!bigNodes){
			NodeExtentProvider.height = 5;
			NodeExtentProvider.width =5;
			}
		else{
			NodeExtentProvider.height = 20;
			NodeExtentProvider.width =20;
			}
		NodeExtentProvider<Node> nodeExtentProvider = new NodeExtentProvider<Node>();
		
		
		TreeLayout<Node> treeLayout = new TreeLayout<Node>(treeForLayout, nodeExtentProvider, configuration);
		
		// Create a panel that draws the nodes and edges and show the panel
		TextInBoxTreePane pane = new TextInBoxTreePane(treeLayout,bigNodes, leftWalker);
		panel.add(pane);
		
	}
	
}

package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextPane;

import optimalExploration.LeftWalker;
import tree.TreeDataCalculator;
import treeExploration.ProgrammManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreeDataInlay extends JPanel{



	/**
	 * Create the application.
	 */
	public TreeDataInlay() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setSize(120, 100);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JTextPane NumberOfNodesText = new JTextPane();
		NumberOfNodesText.setText("Number of Nodes = x");
		add(NumberOfNodesText);
		
		JTextPane TreeDepthText = new JTextPane();
		TreeDepthText.setText("Tree depth = x");
		add(TreeDepthText);
		
		JButton btnNewButton = new JButton("calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TreeDataCalculator calcy = ProgrammManager.getTreeDataImprovised();
				TreeDepthText.setText("Tree Depth = " + calcy.getDepth());
				NumberOfNodesText.setText("Number of Nodes = " + calcy.getNumberOfNodes());
				ProgrammManager.calculateOptimum(5);
			}
		});
		add(btnNewButton);
		
	}
}

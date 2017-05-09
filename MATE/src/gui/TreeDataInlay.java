package gui;

import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import optimalExploration.LeftWalker;
import tree.TreeDataCalculator;
import treeExploration.ProgrammManager;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TreeDataInlay extends JPanel{



	private JTextField numberOfRobotsField =new JTextField();

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
		this.setSize(120, 150);
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				JTextPane numberOfRobots = new JTextPane();
				numberOfRobots.setToolTipText("the tree creation wont stop before the minimum number of Nodes is reached");
				numberOfRobots.setText("Number of Agents");
				numberOfRobots.setOpaque(false);
				numberOfRobots.setBounds(10, 5, 5,  20);
				add(numberOfRobots);
		
		
		
		numberOfRobotsField.setToolTipText("Enter an Integer value here");
		numberOfRobotsField.setText("5");
		numberOfRobotsField.setColumns(10);
		numberOfRobotsField.setBounds(10, 220, 120, 20);
		add(numberOfRobotsField);
		
		
		
		JTextPane NumberOfNodesText = new JTextPane();
		NumberOfNodesText.setText("Number of Nodes = x");
		//add(NumberOfNodesText);
		
		JTextPane TreeDepthText = new JTextPane();
		TreeDepthText.setText("Tree depth = x");
		add(TreeDepthText);
		
		JButton btnNewButton = new JButton("calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TreeDataCalculator calcy = ProgrammManager.getTreeDataImprovised();
				TreeDepthText.setText("Tree Depth = " + calcy.getDepth());
				NumberOfNodesText.setText("Number of Nodes = " + calcy.getNumberOfNodes());
				ProgrammManager.calculateLeftWalker(getNumberOfRobots());
			}
		});
		add(btnNewButton);
		
	}
	public int getNumberOfRobots(){
		return Integer.parseInt(numberOfRobotsField.getText());
		
	}
}

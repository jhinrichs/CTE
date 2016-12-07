package gui;

import javax.swing.JPanel;

import tree.TreeTypes;

public class TreeComponent {
	
	public JPanel panel;
	public TreeTypes type;

	public TreeComponent(TreeTypes type, JPanel panel) {
		this.panel = panel;
		this.type = type;
	}
	
	

}

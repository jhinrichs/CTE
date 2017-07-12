package gui;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JScrollBar;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.UIManager;

import treeExploration.ProgrammManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class MatePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField searchDept_textfield;
	public int getSearchDept_textfield() {
		return Integer.parseInt(searchDept_textfield.getText());
	}

	public int getNumberOfAgents_textfield() {
		return Integer.parseInt(numberOfAgents_textfield.getText());
	}

	public int getMovingTreshhold_textfield() {
		return Integer.parseInt(movingTreshhold_textfield.getText());
	}

	public int getDistanceInfluence_textfield() {
		return Integer.parseInt(distanceInfluence_textfield.getText());
	}

	private JTextField numberOfAgents_textfield;
	private JTextField movingTreshhold_textfield;
	private JTextField distanceInfluence_textfield;

	/**
	 * Create the panel.
	 */
	public MatePanel() {
		setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgrammManager.startMate();
			}
		});
		btnStart.setBounds(10, 266, 121, 23);
		add(btnStart);
		
		JTextPane txtpnExploration = new JTextPane();
		txtpnExploration.setBackground(UIManager.getColor("Button.background"));
		txtpnExploration.setText("Mate Algorithm");
		txtpnExploration.setBounds(10, 11, 121, 23);
		add(txtpnExploration);
		
		searchDept_textfield = new JTextField();
		searchDept_textfield.setText("20");
		searchDept_textfield.setBounds(10, 235, 121, 20);
		add(searchDept_textfield);
		searchDept_textfield.setColumns(10);
		
		JTextPane txtpnSearchDepth = new JTextPane();
		txtpnSearchDepth.setEditable(false);
		txtpnSearchDepth.setText("Searchdepth");
		txtpnSearchDepth.setBounds(10, 211, 121, 20);
		add(txtpnSearchDepth);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText("Number of Agents");
		textPane.setBounds(10, 44, 121, 20);
		add(textPane);
		
		numberOfAgents_textfield = new JTextField();
		numberOfAgents_textfield.setText("10");
		numberOfAgents_textfield.setColumns(10);
		numberOfAgents_textfield.setBounds(10, 68, 121, 20);
		add(numberOfAgents_textfield);
		
		JTextPane txtpnMovingThreshhold = new JTextPane();
		txtpnMovingThreshhold.setEditable(false);
		txtpnMovingThreshhold.setText("Moving Threshhold");
		txtpnMovingThreshhold.setBounds(10, 99, 121, 20);
		add(txtpnMovingThreshhold);
		
		movingTreshhold_textfield = new JTextField();
		movingTreshhold_textfield.setText("5");
		movingTreshhold_textfield.setColumns(10);
		movingTreshhold_textfield.setBounds(10, 123, 121, 20);
		add(movingTreshhold_textfield);
		
		JTextPane txtpnDistanceInfluence = new JTextPane();
		txtpnDistanceInfluence.setEditable(false);
		txtpnDistanceInfluence.setText("distance Influence");
		txtpnDistanceInfluence.setBounds(10, 154, 121, 20);
		add(txtpnDistanceInfluence);
		
		distanceInfluence_textfield = new JTextField();
		distanceInfluence_textfield.setText("0.8");
		distanceInfluence_textfield.setColumns(10);
		distanceInfluence_textfield.setBounds(10, 178, 121, 20);
		add(distanceInfluence_textfield);

	}
}

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

public class CTEInlay extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CTEInlay() {
		setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProgrammManager.startCTE();
			}
		});
		btnStart.setBounds(10, 83, 121, 23);
		add(btnStart);
		
		JTextPane txtpnExploration = new JTextPane();
		txtpnExploration.setBackground(UIManager.getColor("Button.background"));
		txtpnExploration.setText("Collective Exploration");
		txtpnExploration.setBounds(10, 11, 121, 23);
		add(txtpnExploration);
		
		textField = new JTextField();
		textField.setText("5");
		textField.setBounds(20, 52, 99, 20);
		add(textField);
		textField.setColumns(10);
		
		JTextPane txtpnNumberOfAgents = new JTextPane();
		txtpnNumberOfAgents.setText("Number of Agents");
		txtpnNumberOfAgents.setBounds(10, 29, 109, 20);
		add(txtpnNumberOfAgents);

	}
}

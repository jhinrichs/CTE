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

public class CTEInlay extends JPanel {

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
		btnStart.setBounds(38, 34, 57, 23);
		add(btnStart);
		
		JTextPane txtpnExploration = new JTextPane();
		txtpnExploration.setBackground(UIManager.getColor("Button.background"));
		txtpnExploration.setText("Collective Exploration");
		txtpnExploration.setBounds(10, 11, 121, 28);
		add(txtpnExploration);

	}
}

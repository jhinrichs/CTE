package gui;

import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;

import treeExploration.Main;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

public class GuiBuilder {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBuilder window = new GuiBuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiBuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(153, 11, 845, 659);
		frame.getContentPane().add(panel);

		JButton btnCreateTree = new JButton("create tree");
		btnCreateTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.removeAll();
				panel.repaint();
				Main.main(panel);
				frame.setVisible(true);

			}
		});
		btnCreateTree.setBounds(26, 346, 89, 23);
		frame.getContentPane().add(btnCreateTree);

		TextField textField = new TextField();

		TextField textField_1 = new TextField();
		TextField textField_2 = new TextField();

		JProgressBar progressBar = new JProgressBar();
		JProgressBar progressBar_1 = new JProgressBar();
		JProgressBar progressBar_2 = new JProgressBar();

		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				System.out.println("Text Changed");
				if (!textField.getText().isEmpty() && textField.getText().chars().allMatch(Character::isDigit)) {
					progressBar.setValue(Integer.parseInt(textField.getText()));
					frame.repaint();
				}
			}
		});

		textField.setText("50");
		textField.setBounds(101, 97, 31, 22);
		frame.getContentPane().add(textField);

		textField_1.setEditable(false);
		textField_1.setEnabled(false);
		textField_1.setText("30");
		textField_1.setBounds(101, 145, 31, 22);
		frame.getContentPane().add(textField_1);

		progressBar.setStringPainted(true);
		progressBar.setValue(50);
		progressBar.setBounds(6, 124, 126, 14);
		frame.getContentPane().add(progressBar);

		progressBar_1.setStringPainted(true);
		progressBar_1.setBounds(6, 172, 124, 14);
		frame.getContentPane().add(progressBar_1);

		progressBar_2.setStringPainted(true);
		progressBar_2.setBounds(6, 223, 126, 14);
		frame.getContentPane().add(progressBar_2);

		textField_2.setEnabled(false);
		textField_2.setEditable(false);
		textField_2.setBounds(101, 193, 31, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setText("20");

		JCheckBox chckbxStandartTree = new JCheckBox("Standart Tree");
		chckbxStandartTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = chckbxStandartTree.isSelected();
				textField.setEnabled(selected);
				textField.setEditable(selected);
			}
		});

		JCheckBox chckbxSnakeTrees = new JCheckBox("Snake Trees");
		chckbxSnakeTrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = chckbxSnakeTrees.isSelected();
				textField_1.setEnabled(selected);
				textField_1.setEditable(selected);
			}
		});
		chckbxSnakeTrees.setToolTipText("When enabled the tree will contain more parts with only one child per Node");

		chckbxSnakeTrees.setBounds(6, 145, 97, 23);
		frame.getContentPane().add(chckbxSnakeTrees);

		JCheckBox chckbxFanTree = new JCheckBox("Fan Trees");
		chckbxFanTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = chckbxFanTree.isSelected();
				textField_2.setEnabled(selected);
				textField_2.setEditable(selected);
			}
		});
		chckbxFanTree.setToolTipText("when enabled the tree will contain some Nodes that have a lot of leaves");
		chckbxFanTree.setBounds(6, 193, 89, 23);
		frame.getContentPane().add(chckbxFanTree);

		chckbxStandartTree.setSelected(true);
		chckbxStandartTree.setBounds(6, 97, 97, 23);
		frame.getContentPane().add(chckbxStandartTree);
		

		JPanel standartTree = createTypeSelectionPanel("Standard Tree", "Creates a standart Tree");
		
		
	}

	private JPanel createTypeSelectionPanel(String name, String tooltip) {
		JPanel testSelection = new JPanel();
		testSelection.setBorder(new MatteBorder(2, 2, 2, 2, (Color) null));
		
		testSelection.setBackground(SystemColor.controlHighlight);
		testSelection.setBounds(6, 271, 140, 53);
		frame.getContentPane().add(testSelection);
		testSelection.setLayout(null);
		
		JCheckBox chckbx = new JCheckBox(name);
		chckbx.setOpaque(false);
		
		chckbx.setToolTipText(tooltip);
		chckbx.setBounds(8, 3, 89, 23);
		testSelection.add(chckbx);
		
		TextField percentage = new TextField();
		percentage.setText("0");
		percentage.setEnabled(false);
		percentage.setEditable(false);
		percentage.setBounds(95, 5, 31, 22);
		testSelection.add(percentage);
		
		JProgressBar percentageBar = new JProgressBar();
		percentageBar.setValue(0);
		percentageBar.setStringPainted(true);
		percentageBar.setBounds(10, 30, 120, 14);
		testSelection.add(percentageBar);
		
		
		chckbx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean selected = chckbx.isSelected();
				percentage.setEnabled(selected);
				percentage.setEditable(selected);
			}
		});
		
		percentage.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if (!percentage.getText().isEmpty() && percentage.getText().chars().allMatch(Character::isDigit)) {
					percentageBar.setValue(Integer.parseInt(percentage.getText()));
					frame.repaint();
				}
			}
		});
		
		return testSelection;
	}
}

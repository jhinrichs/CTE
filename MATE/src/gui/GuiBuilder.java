package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import tree.TreeCreator;
import tree.TreeSpecifier;
import tree.TreeTypes;
import treeExploration.Main;

public class GuiBuilder {

	private JFrame frame;
	private ArrayList<TreeComponent> treeComponents = new ArrayList<>();
	private JTextField seedField;
	private JTextField maxNodesField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	Random rand = new Random();
	int seed = rand.nextInt();

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
		JButton btnCreateTree = new JButton("create tree");
		btnCreateTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTree(panel);
				paintTree(panel);
			}

			private void paintTree(JPanel panel) {
				panel.removeAll();
				panel.repaint();
				seedField.setText("" + rand.nextInt());
				Main.paintTree(panel);
				frame.setVisible(true);
			}

			private void createTree(JPanel panel) {
				TreeSpecifier treeSpecifier = new TreeSpecifier();
				for (TreeComponent p : treeComponents) {
					JSlider slider = (JSlider) p.panel.getComponent(2);
					treeSpecifier.addTreeType(p.type, slider.getValue());
				}
				TreeCreator treeCreator = new TreeCreator(seed,20,5,5,1, treeSpecifier);
				Main.setRoot(treeCreator.createTree());
				
			}
		});
		btnCreateTree.setBounds(29, 528, 101, 23);
		frame.getContentPane().add(btnCreateTree);

		JPanel standardTree = createTypeSelectionPanel("Standard Tree", "Creates a standart Tree", 6, 10);
		treeComponents.add(new TreeComponent(TreeTypes.standardTree, standardTree));

		JPanel fanTree = createTypeSelectionPanel("FanTree",
				"When enabled the tree will contain some Nodes that have a lot of leaves", 6, 60);
		treeComponents.add(new TreeComponent(TreeTypes.fanTree, fanTree));

		JPanel snakeTree = createTypeSelectionPanel("SnakeTree",
				"When enabled the tree will have a snakes with just one child per node", 6, 110);
		treeComponents.add(new TreeComponent(TreeTypes.snakeTree, snakeTree));

		// for design purpose only
		frame.getContentPane().add(snakeTree);

		for (TreeComponent p : treeComponents) {
			frame.getContentPane().add(p.panel);
		}

		JSlider standardTreeSlider = (JSlider) (standardTree.getComponent(2));
		standardTreeSlider.setValue(100);
		updatePercentage(standardTreeSlider);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 11, 842, 659);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(6, 164, 140, 171);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JTextPane txtpnSeed = new JTextPane();
		txtpnSeed.setOpaque(false);
		txtpnSeed.setText("Seed");
		txtpnSeed.setBounds(10, 31, 120, 20);
		panel_1.add(txtpnSeed);

		seedField = new JTextField();
		seedField.setText(seed + "");
		seedField.setBounds(10, 50, 120, 20);
		panel_1.add(seedField);
		seedField.setColumns(10);
		seedField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					seed = Integer.parseInt(seedField.getText());
				} catch (Exception e2) {
					seed = 0;
				}
				System.out.println("Seed updated");
			}
		});

		JTextPane txtpnTreeSpecifications = new JTextPane();
		txtpnTreeSpecifications.setBackground(SystemColor.info);
		txtpnTreeSpecifications.setText("Tree specifications");
		txtpnTreeSpecifications.setBounds(10, 0, 120, 20);
		panel_1.add(txtpnTreeSpecifications);

		JTextPane txtpnMaxNodes = new JTextPane();
		txtpnMaxNodes.setText("Max Nodes");
		txtpnMaxNodes.setOpaque(false);
		txtpnMaxNodes.setBounds(10, 78, 120, 20);
		panel_1.add(txtpnMaxNodes);

		maxNodesField = new JTextField();
		maxNodesField.setText("1000");
		maxNodesField.setColumns(10);
		maxNodesField.setBounds(10, 97, 120, 20);
		panel_1.add(maxNodesField);

		JTextPane txtpnChaosfactor = new JTextPane();
		txtpnChaosfactor.setText("Chaosfactor");
		txtpnChaosfactor.setOpaque(false);
		txtpnChaosfactor.setBounds(10, 121, 120, 20);
		panel_1.add(txtpnChaosfactor);

		textField_6 = new JTextField();
		textField_6.setText("1000");
		textField_6.setColumns(10);
		textField_6.setBounds(10, 140, 120, 20);
		panel_1.add(textField_6);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(6, 346, 140, 171);
		frame.getContentPane().add(panel_2);

		JTextPane txtpnMaxChildren = new JTextPane();
		txtpnMaxChildren.setText("max Children");
		txtpnMaxChildren.setOpaque(false);
		txtpnMaxChildren.setBounds(10, 31, 120, 20);
		panel_2.add(txtpnMaxChildren);

		textField_3 = new JTextField();
		textField_3.setText("5");
		textField_3.setColumns(10);
		textField_3.setBounds(10, 50, 120, 20);
		panel_2.add(textField_3);

		JTextPane txtpnNodeSpecification = new JTextPane();
		txtpnNodeSpecification.setText("Node specification");
		txtpnNodeSpecification.setBackground(SystemColor.info);
		txtpnNodeSpecification.setBounds(10, 0, 120, 20);
		panel_2.add(txtpnNodeSpecification);

		JTextPane txtpnPlaceholder = new JTextPane();
		txtpnPlaceholder.setText("Placeholder");
		txtpnPlaceholder.setOpaque(false);
		txtpnPlaceholder.setBounds(10, 78, 120, 20);
		panel_2.add(txtpnPlaceholder);

		textField_4 = new JTextField();
		textField_4.setText("1000");
		textField_4.setColumns(10);
		textField_4.setBounds(10, 97, 120, 20);
		panel_2.add(textField_4);

		JTextPane txtpnPlaceholder_1 = new JTextPane();
		txtpnPlaceholder_1.setText("Placeholder");
		txtpnPlaceholder_1.setOpaque(false);
		txtpnPlaceholder_1.setBounds(10, 121, 120, 20);
		panel_2.add(txtpnPlaceholder_1);

		textField_5 = new JTextField();
		textField_5.setText("0815");
		textField_5.setColumns(10);
		textField_5.setBounds(10, 140, 120, 20);
		panel_2.add(textField_5);

		JTextPane txtpnTreecode = new JTextPane();
		txtpnTreecode.setBounds(10, 631, 120, 20);
		frame.getContentPane().add(txtpnTreecode);
		txtpnTreecode.setText("TreeCode");
		txtpnTreecode.setOpaque(false);

		textField_2 = new JTextField();
		textField_2.setBounds(10, 650, 120, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setBackground(SystemColor.menu);
		textField_2.setText("0815");
		textField_2.setColumns(10);

	}

	private JPanel createTypeSelectionPanel(String name, String tooltip, int posX, int posY) {
		JPanel testSelection = new JPanel();
		testSelection.setBorder(new MatteBorder(2, 2, 2, 2, (Color) null));

		testSelection.setBackground(SystemColor.controlHighlight);
		testSelection.setBounds(posX, posY, 140, 50);
		testSelection.setLayout(null);

		JTextPane title = new JTextPane();
		title.setOpaque(false);
		title.setText(name);
		title.setToolTipText(tooltip);
		title.setBounds(8, 3, 89, 23);
		testSelection.add(title, 0);

		TextField percentage = new TextField();
		percentage.setText("0");
		percentage.setEditable(false);
		percentage.setBounds(95, 5, 38, 22);
		testSelection.add(percentage, 1);

		JSlider slider = new JSlider();
		slider.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				updatePercentage(slider);
			}
		});
		slider.setBounds(8, 32, 118, 7);
		slider.setOpaque(false);
		testSelection.add(slider, 2);
		slider.getValue();

		return testSelection;
	}

	/**
	 * calculates the percentage for all treeTypes and displayes it
	 * 
	 * @param s
	 */
	private void updatePercentage(JSlider s) {
		int sum = 0;

		int threshold = 0;
		for (TreeComponent p : treeComponents) {
			JSlider slide = (JSlider) p.panel.getComponent(2); // component 2 is
																// the slider
			threshold += slide.getValue();
		}
		if (threshold >= 102 || threshold <= 98) {
			for (TreeComponent p : treeComponents) {
				JSlider slide = (JSlider) p.panel.getComponent(2);
				if (!slide.equals(s)) {

					sum += slide.getValue();
				}
			}
			for (TreeComponent p : treeComponents) {
				TextField text = (TextField) p.panel.getComponent(1);
				JSlider slide = (JSlider) p.panel.getComponent(2);
				if (!slide.equals(s)) {

					if (sum == 0) {
						slide.setValue(Math.round(((float) 100 - (float) s.getValue()) / (float) 2));
					} else {
						slide.setValue(
								Math.round(((float) slide.getValue() / (float) sum * (float) (100 - s.getValue()))));
					}
				}
				text.setText(slide.getValue() + "%");

			}
		}

	}
}

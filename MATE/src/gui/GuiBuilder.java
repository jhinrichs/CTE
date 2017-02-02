package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import treeExploration.ProgrammManager;

public class GuiBuilder {

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

	private JFrame frame;
	private ArrayList<TreeComponent> treeComponents = new ArrayList<>();
	private JTextField seedField;
	private JTextField maxDepthField;
	private JTextField textField_2;
	private JTextField maxChildrenField;
	private JTextField leafFactorField;
	private JTextField minChildrenField;
	private JTextField minDepthField;

	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double branchingfactor;
	private int maxNodes;
	private int minNodes;

	Random rand = new Random();
	int seed = rand.nextInt();
	private JTextField maxNodesField;

	private JTextField minNodesField;

	/**
	 * Create the application.
	 */
	public GuiBuilder() {
		initialize();
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
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				updatePercentage(slider);
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		slider.setBounds(8, 32, 118, 7);
		slider.setOpaque(false);
		testSelection.add(slider, 2);
		slider.getValue();

		return testSelection;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1228, 871);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JPanel panel_3 = new JPanel();
		JButton btnCreateTree = new JButton("create tree");
		btnCreateTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					validateData();
					
					createTree(panel_3);
					paintTree(panel_3);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame, "Cannot create Tree due to one ore more values are incorrect. Please correct " + e2.getMessage().substring(16));
				}
					
				
			}

			private void validateData() {
				maxDepth = Integer.parseInt(maxDepthField.getText());
				minDepth= Integer.parseInt(minDepthField.getText());
				maxBranches = Integer.parseInt(maxChildrenField.getText());
				minBranches = Integer.parseInt(minChildrenField.getText());
				maxNodes = Integer.parseInt(maxNodesField.getText());
				minNodes = Integer.parseInt(minNodesField.getText());
				
				branchingfactor = Double.parseDouble(leafFactorField.getText().replace(",", "."));
			}

			private void createTree(JPanel panel) {
				TreeSpecifier treeSpecifier = new TreeSpecifier();
				for (TreeComponent p : treeComponents) {
					JSlider slider = (JSlider) p.panel.getComponent(2);
					treeSpecifier.addTreeType(p.type, slider.getValue());
				}
				TreeCreator treeCreator = new TreeCreator(seed,maxDepth,minDepth,maxBranches,minBranches,maxNodes,minNodes,branchingfactor, treeSpecifier);
				ProgrammManager.setRoot(treeCreator.createTree());

			}

			private void paintTree(JPanel panel) {
				panel.removeAll();
				panel.repaint();
				rand = new Random(Integer.parseInt(seedField.getText()));
				seedField.setText("" + rand.nextInt());
				ProgrammManager.paintTree(panel);
				frame.setVisible(true);
			}
		});
		btnCreateTree.setBounds(26, 643, 106, 23);
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
		scrollPane.setBounds(156, 11, 1046, 810);
		frame.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(panel_3);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.controlHighlight);
		panel_1.setBounds(6, 164, 140, 247);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);

		JTextPane txtpnSeed = new JTextPane();
		txtpnSeed.setOpaque(false);
		txtpnSeed.setText("Seed");
		txtpnSeed.setBounds(10, 28, 120, 20);
		panel_1.add(txtpnSeed);

		seedField = new JTextField();
		seedField.setToolTipText("Enter an Integer value here");
		seedField.setText(seed + "");
		seedField.setBounds(10, 47, 120, 20);
		panel_1.add(seedField);
		seedField.setColumns(10);
		seedField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				try {
					seed = Integer.parseInt(seedField.getText());
				} catch (Exception e2) {
					seed = 0;
				}
				System.out.println("Seed updated");
			}

			@Override
			public void insertUpdate(DocumentEvent e) {

				changedUpdate(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}
		});

		JTextPane txtpnTreeSpecifications = new JTextPane();
		txtpnTreeSpecifications.setBackground(SystemColor.info);
		txtpnTreeSpecifications.setText("Tree specifications");
		txtpnTreeSpecifications.setBounds(10, 0, 120, 20);
		panel_1.add(txtpnTreeSpecifications);

		JTextPane textfield124124 = new JTextPane();
		textfield124124.setToolTipText("maximum depth of the tree ");
		textfield124124.setText("maxDepth");
		textfield124124.setOpaque(false);
		textfield124124.setBounds(10, 71, 120, 20);
		panel_1.add(textfield124124);

		maxDepthField = new JTextField();
		maxDepthField.setToolTipText("Enter an Integer value here");
		maxDepthField.setText("20");
		maxDepthField.setColumns(10);
		maxDepthField.setBounds(10, 90, 120, 20);
		panel_1.add(maxDepthField);

		JTextPane txtpn124124 = new JTextPane();
		txtpn124124.setToolTipText("minimum depth of the tree");
		txtpn124124.setText("minDepth");
		txtpn124124.setOpaque(false);
		txtpn124124.setBounds(10, 114, 120, 20);
		panel_1.add(txtpn124124);

		minDepthField = new JTextField();
		minDepthField.setToolTipText("Enter an Integer value here");
		minDepthField.setText("5");
		minDepthField.setColumns(10);
		minDepthField.setBounds(10, 133, 120, 20);
		panel_1.add(minDepthField);

		JTextPane txtpnMaxnodes = new JTextPane();
		txtpnMaxnodes.setToolTipText("The tree creator will stop after this many nodes are created. ");
		txtpnMaxnodes.setText("maxNodes");
		txtpnMaxnodes.setOpaque(false);
		txtpnMaxnodes.setBounds(10, 158, 120, 20);
		panel_1.add(txtpnMaxnodes);

		maxNodesField = new JTextField();
		maxNodesField.setToolTipText("Enter an Integer value here");
		maxNodesField.setText("500");
		maxNodesField.setColumns(10);
		maxNodesField.setBounds(10, 177, 120, 20);
		panel_1.add(maxNodesField);

		minNodesField = new JTextField();
		minNodesField.setToolTipText("Enter an Integer value here");
		minNodesField.setText("10");
		minNodesField.setColumns(10);
		minNodesField.setBounds(10, 220, 120, 20);
		panel_1.add(minNodesField);

		JTextPane minNodes = new JTextPane();
		minNodes.setToolTipText("the tree creation wont stop before the minimum number of Nodes is reached");
		minNodes.setText("minNodes");
		minNodes.setOpaque(false);
		minNodes.setBounds(10, 201, 120, 20);
		panel_1.add(minNodes);

		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(SystemColor.controlHighlight);
		panel_2.setBounds(6, 415, 140, 171);
		frame.getContentPane().add(panel_2);

		JTextPane txtfield123 = new JTextPane();
		txtfield123.setText("max Children per Node");
		txtfield123.setOpaque(false);
		txtfield123.setBounds(10, 31, 120, 20);
		panel_2.add(txtfield123);

		maxChildrenField = new JTextField();
		maxChildrenField.setToolTipText("Enter an Integer value here");
		maxChildrenField.setText("5");
		maxChildrenField.setColumns(10);
		maxChildrenField.setBounds(10, 50, 120, 20);
		panel_2.add(maxChildrenField);

		JTextPane txtpnNodeSpecification = new JTextPane();
		txtpnNodeSpecification.setText("Node specification");
		txtpnNodeSpecification.setBackground(SystemColor.info);
		txtpnNodeSpecification.setBounds(10, 0, 120, 20);
		panel_2.add(txtpnNodeSpecification);

		JTextPane txtpnPlaceholder = new JTextPane();
		txtpnPlaceholder.setToolTipText(
				"gives a probability that the node is a leaf. And the tree ends. Even with minimum children but a leaffactor, the node can be a leaf. ");
		txtpnPlaceholder.setText("leafFactor");
		txtpnPlaceholder.setOpaque(false);
		txtpnPlaceholder.setBounds(10, 121, 120, 20);
		panel_2.add(txtpnPlaceholder);

		leafFactorField = new JTextField();
		leafFactorField.setToolTipText("Enter a double value here. Seperated with \".\" instead of \",\"");
		leafFactorField.setText("0,6");
		leafFactorField.setColumns(10);
		leafFactorField.setBounds(10, 140, 120, 20);
		panel_2.add(leafFactorField);

		JTextPane txtpnPlaceholder_1 = new JTextPane();
		txtpnPlaceholder_1.setBounds(10, 71, 120, 20);
		panel_2.add(txtpnPlaceholder_1);
		txtpnPlaceholder_1.setToolTipText("minimum number of children per Node");
		txtpnPlaceholder_1.setText("minChildren");
		txtpnPlaceholder_1.setOpaque(false);

		minChildrenField = new JTextField();
		minChildrenField.setToolTipText("Enter an Integer value here");
		minChildrenField.setBounds(10, 90, 120, 20);
		panel_2.add(minChildrenField);
		minChildrenField.setText("0");
		minChildrenField.setColumns(10);

		JTextPane txtpnTreecode = new JTextPane();
		txtpnTreecode.setToolTipText("Copy Paste this code to quickly regenerate Trees. ");
		txtpnTreecode.setBounds(15, 670, 120, 20);
		frame.getContentPane().add(txtpnTreecode);
		txtpnTreecode.setText("TreeCode");
		txtpnTreecode.setOpaque(false);

		textField_2 = new JTextField();
		textField_2.setBounds(15, 694, 120, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setBackground(SystemColor.menu);
		textField_2.setText("0815");
		textField_2.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Big numbered Nodes");
		chckbxNewCheckBox.setBounds(6, 597, 140, 23);
		frame.getContentPane().add(chckbxNewCheckBox);

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

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

import gui.GuiBuilder.algos;
import tree.TreeFactory;
import tree.TreeSpecifier;
import tree.TreeTypes;
import treeExploration.ProgrammManager;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLayeredPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JList;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;

public class GuiBuilder {

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// GuiBuilder window = new GuiBuilder();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	public JFrame frame;
	private ArrayList<TreeComponent> treeComponents = new ArrayList<>();
	private JTextField seedField;
	private JTextField maxDepthField;
	private JTextField textField_2;
	private JTextField maxChildrenField;
	private JTextField leafFactorField;
	private JTextField minChildrenField;
	private JTextField minDepthField;
	public JButton btnCreateTree;
	public JCheckBox bigNodesCheckBox;
	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double leafFactor;
	private int maxNodes;
	private int minNodes;
	public TreeDataInlay panel;
	public MatePanel matePanel;
	public CTEInlay ctePanel;
	public JPanel treeInlayPanel;
	public JSpinner stepSpinner;
	public JSpinner stepSpinner_2;
	public JComboBox<algos> comboBox;
	
	Random rand = new Random();
	int seed = rand.nextInt();
	private JTextField maxNodesField;

	private JTextField minNodesField;
	
	public enum algos {
		cte,
		mate,
		leftWalker;
	}
	

	/**
	 * Create the application.
	 */
	public GuiBuilder() {
		initialize();
		frame.setVisible(true);
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
		frame.setBounds(100, 100, 1484, 963);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		treeInlayPanel = new JPanel();
		btnCreateTree = new JButton("create tree");

		btnCreateTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					validateData();
					createTree();
					startAlgo();
					paintAllPaths();

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(frame,
							"Cannot create Tree due to one ore more values are incorrect. Please correct "
									+ e2.getMessage().substring(16));
				}

			}

			private void validateData() {
				maxDepth = Integer.parseInt(maxDepthField.getText());
				minDepth = Integer.parseInt(minDepthField.getText());
				maxBranches = Integer.parseInt(maxChildrenField.getText());
				minBranches = Integer.parseInt(minChildrenField.getText());
				maxNodes = Integer.parseInt(maxNodesField.getText());
				minNodes = Integer.parseInt(minNodesField.getText());

				leafFactor = Double.parseDouble(leafFactorField.getText().replace(",", "."));
			}


			private void createTree() {
				TreeSpecifier treeSpecifier = new TreeSpecifier();
				for (TreeComponent p : treeComponents) {
					JSlider slider = (JSlider) p.panel.getComponent(2);
					treeSpecifier.addTreeType(p.type, slider.getValue());
				}
				ProgrammManager.createTree(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes, minNodes,
						leafFactor, treeSpecifier);
				rand = new Random(Integer.parseInt(seedField.getText()));
				seedField.setText("" + rand.nextInt());
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

		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				paintSingleAgentsPath((Integer) spinner.getValue());
				
			}
		});
		spinner.setModel(new SpinnerNumberModel(new Integer(0), null, null, new Integer(1)));
		// for design purpose only
		frame.getContentPane().add(snakeTree);

		for (TreeComponent p : treeComponents) {
			frame.getContentPane().add(p.panel);
		}

		JSlider standardTreeSlider = (JSlider) (standardTree.getComponent(2));
		standardTreeSlider.setValue(100);
		updatePercentage(standardTreeSlider);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 11, 1046, 832);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(treeInlayPanel);

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
		maxNodesField.setText("20");
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
		maxChildrenField.setText("3");
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
		leafFactorField.setText("0,7");
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

		textField_2 = new JTextField();
		textField_2.setBounds(15, 694, 131, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setBackground(Color.WHITE);
		textField_2.setColumns(10);

		bigNodesCheckBox = new JCheckBox("Big numbered Nodes");
		bigNodesCheckBox.setSelected(true);
		bigNodesCheckBox.setBounds(6, 597, 140, 23);
		frame.getContentPane().add(bigNodesCheckBox);

		panel = new TreeDataInlay();
		panel.setBounds(6, 725, 139, 118);
		frame.getContentPane().add(panel);

		JButton btnShowAgent = new JButton("Show Agent");
		btnShowAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintSingleAgentsPath((Integer) spinner.getValue());
			}
		});
		btnShowAgent.setBounds(467, 854, 106, 23);
		frame.getContentPane().add(btnShowAgent);

		spinner.setBounds(568, 855, 42, 20);
		frame.getContentPane().add(spinner);

		JButton btnShowTree = new JButton("Show Tree");
		btnShowTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				treeInlayPanel.removeAll();
				treeInlayPanel.repaint();
				ProgrammManager.paintTree(treeInlayPanel, bigNodesCheckBox.isSelected());
				frame.setVisible(true);

			}
		});
		btnShowTree.setBounds(1096, 854, 106, 23);
		frame.getContentPane().add(btnShowTree);

		JButton btnNewButton = new JButton("Show all Paths");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				treeInlayPanel.removeAll();
				treeInlayPanel.repaint();
				ProgrammManager.paintAllAgents();
				//frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(351, 854, 106, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Use Tree Code");
		btnNewButton_1.setBounds(26, 671, 106, 23);
		frame.getContentPane().add(btnNewButton_1);

		ctePanel = new CTEInlay();
		ctePanel.setBounds(1212, 376, 142, 118);
		frame.getContentPane().add(ctePanel);

		matePanel = new MatePanel();
		matePanel.setBounds(1212, 505, 142, 338);
		frame.getContentPane().add(matePanel);
		
		JButton btnShowStepNumber = new JButton("Show Step");
		btnShowStepNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				int value = (Integer)stepSpinner.getValue();
				paintStep(value);
			}
		});
		btnShowStepNumber.setBounds(620, 854, 95, 23);
		frame.getContentPane().add(btnShowStepNumber);
		
		comboBox = new JComboBox(algos.values());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			startAlgo();
			}
		});
		
		stepSpinner = new JSpinner();
		stepSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int value = (Integer)stepSpinner.getValue();
				paintStep(value);
			}
		});
		stepSpinner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int value = (Integer)stepSpinner.getValue();
				paintStep(value);
			}
		});
		stepSpinner.setBounds(710, 855, 42, 20);
		frame.getContentPane().add(stepSpinner);
		comboBox.setBounds(156, 854, 185, 23);
		
		frame.getContentPane().add(comboBox);
		
		JButton btnShowSteps = new JButton("Show Steps");
		btnShowSteps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintSteps((int)(stepSpinner_2.getValue()));
			}
		});
		btnShowSteps.setBounds(762, 854, 95, 23);
		frame.getContentPane().add(btnShowSteps);
		
		stepSpinner_2 = new JSpinner();
		stepSpinner_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int value = (Integer)stepSpinner_2.getValue();
				paintSteps(value);
			}
		});
		stepSpinner_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int value = (Integer)stepSpinner_2.getValue();
				paintSteps(value);
			}
		});
		stepSpinner_2.setBounds(852, 855, 42, 20);
		frame.getContentPane().add(stepSpinner_2);
		
		
		
		
	}
	
	
	private void startAlgo() {
		switch((algos)comboBox.getSelectedItem()) {
		case cte:
			startCTE();
			break;
		case leftWalker:
			startLeftWalker();
			break;
		case mate:
			startMate();
			break;
			
		}
		
		paintAllPaths();
	}
	
	
	private void startMate() {
		// TODO Auto-generated method stub
		ProgrammManager.startMate();
	}

	private void startLeftWalker() {
		// TODO Auto-generated method stub
		ProgrammManager.calculateLeftWalker();
	}

	private void startCTE() {
		// TODO Auto-generated method stub
		ProgrammManager.calculateCTE();
		
	}

	private void paintStep(int i) {
		treeInlayPanel.removeAll();
		treeInlayPanel.repaint();
		ProgrammManager.paintStep(i);
		frame.setVisible(true);
	}
	
	private void paintSteps(int i) {
		treeInlayPanel.removeAll();
		treeInlayPanel.repaint();
		ProgrammManager.paintSteps(i);
		frame.setVisible(true);
	}
	
	private void paintSingleAgentsPath(int i) {
		treeInlayPanel.removeAll();
		treeInlayPanel.repaint();
		ProgrammManager.paintAgentPaths(i);
		frame.setVisible(true);
	}
	
	private void paintAllPaths() {
		treeInlayPanel.removeAll();
		treeInlayPanel.repaint();
		ProgrammManager.paintAllAgents();
		frame.setVisible(true);
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

package gui;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import treeExploration.ProgrammManager;

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
	public JPanel treeInlayPanel;
	public JSpinner spinner;
	public JSpinner stepSpinner;
	public JSpinner stepSpinner_2;

	Random rand = new Random();
	int seed = rand.nextInt();
	private JTextField maxNodesField;

	private JTextField minNodesField;
	private JTextField numberOfAgentsField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the application.
	 */
	public GuiBuilder() {
		initialize();
		frame.setVisible(true);
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

			

			private void createTree() {				
				ProgrammManager.createTree(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes, minNodes,
						leafFactor);
				rand = new Random(Integer.parseInt(seedField.getText()));
				seedField.setText("" + rand.nextInt());
			}

		});
		btnCreateTree.setBounds(6, 797, 140, 23);
		frame.getContentPane().add(btnCreateTree);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 11, 1302, 868);
		frame.getContentPane().add(scrollPane);

		scrollPane.setViewportView(treeInlayPanel);

		JPanel treeSpecifier = treeSpecificator();

		JTextPane txtfield123 = new JTextPane();
		txtfield123.setBounds(10, 251, 120, 20);
		treeSpecifier.add(txtfield123);
		txtfield123.setText("max Children per Node");
		txtfield123.setOpaque(false);

		maxChildrenField = new JTextField();
		maxChildrenField.setBounds(10, 270, 120, 20);
		treeSpecifier.add(maxChildrenField);
		maxChildrenField.setToolTipText("Enter an Integer value here");
		maxChildrenField.setText("4");
		maxChildrenField.setColumns(10);

		JTextPane txtpnPlaceholder_1 = new JTextPane();
		txtpnPlaceholder_1.setBounds(10, 291, 120, 20);
		treeSpecifier.add(txtpnPlaceholder_1);
		txtpnPlaceholder_1.setToolTipText("minimum number of children per Node");
		txtpnPlaceholder_1.setText("minChildren");
		txtpnPlaceholder_1.setOpaque(false);

		minChildrenField = new JTextField();
		minChildrenField.setBounds(10, 310, 120, 20);
		treeSpecifier.add(minChildrenField);
		minChildrenField.setToolTipText("Enter an Integer value here");
		minChildrenField.setText("0");
		minChildrenField.setColumns(10);

		JTextPane txtpnPlaceholder = new JTextPane();
		txtpnPlaceholder.setBounds(10, 336, 120, 20);
		treeSpecifier.add(txtpnPlaceholder);
		txtpnPlaceholder.setToolTipText(
				"gives a probability that the node is a leaf. And the tree ends. Even with minimum children but a leaffactor, the node can be a leaf. ");
		txtpnPlaceholder.setText("leafFactor");
		txtpnPlaceholder.setOpaque(false);

		leafFactorField = new JTextField();
		leafFactorField.setBounds(10, 355, 120, 20);
		treeSpecifier.add(leafFactorField);
		leafFactorField.setToolTipText("Enter a double value here. Seperated with \".\" instead of \",\"");
		leafFactorField.setText("0,7");
		leafFactorField.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(6, 859, 140, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setBackground(Color.WHITE);
		textField_2.setColumns(10);

		bigNodesCheckBox = new JCheckBox("Big numbered Nodes");
		bigNodesCheckBox.setSelected(true);
		bigNodesCheckBox.setBounds(6, 759, 140, 23);
		frame.getContentPane().add(bigNodesCheckBox);

		JButton btnShowAgent = new JButton("Show Agent");
		btnShowAgent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintSingleAgentsPath((Integer) spinner.getValue());
			}
		});
		btnShowAgent.setBounds(765, 890, 106, 23);
		frame.getContentPane().add(btnShowAgent);

		JButton btnNewButton = new JButton("Show all Paths");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				treeInlayPanel.removeAll();
				treeInlayPanel.repaint();
				ProgrammManager.paintAllAgents();
				// frame.setVisible(true);
			}
		});
		btnNewButton.setBounds(649, 890, 106, 23);
		frame.getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Use Tree Code");
		btnNewButton_1.setBounds(6, 825, 140, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton btnShowStepNumber = new JButton("Show Step");
		btnShowStepNumber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int value = (Integer) stepSpinner.getValue();
				paintStep(value);
			}
		});
		btnShowStepNumber.setBounds(921, 890, 95, 23);
		frame.getContentPane().add(btnShowStepNumber);

		stepSpinner = new JSpinner();
		stepSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int value = (Integer) stepSpinner.getValue();
				paintStep(value);
			}
		});
		stepSpinner.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int value = (Integer) stepSpinner.getValue();
				paintStep(value);
			}
		});
		stepSpinner.setBounds(1011, 891, 42, 20);
		frame.getContentPane().add(stepSpinner);

		JButton btnShowSteps = new JButton("Show Steps");
		btnShowSteps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paintSteps((int) (stepSpinner_2.getValue()));
			}
		});
		btnShowSteps.setBounds(1063, 890, 95, 23);
		frame.getContentPane().add(btnShowSteps);

		stepSpinner_2 = new JSpinner();
		stepSpinner_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int value = (Integer) stepSpinner_2.getValue();
				paintSteps(value);
			}
		});
		stepSpinner_2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				int value = (Integer) stepSpinner_2.getValue();
				paintSteps(value);
			}
		});
		stepSpinner_2.setBounds(1153, 891, 42, 20);
		frame.getContentPane().add(stepSpinner_2);

		JCheckBox chckbxPlay = new JCheckBox("Play");

		chckbxPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Thread animator = new Animator(chckbxPlay);
				animator.start();
			}
		});
		chckbxPlay.setBounds(1201, 890, 45, 23);
		frame.getContentPane().add(chckbxPlay);
		
				JButton btnShowTree = new JButton("Show Tree");
				btnShowTree.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						treeInlayPanel.removeAll();
						treeInlayPanel.repaint();
						ProgrammManager.paintTree(treeInlayPanel, bigNodesCheckBox.isSelected());
						frame.setVisible(true);

					}
				});
				btnShowTree.setBounds(1247, 890, 106, 23);
				frame.getContentPane().add(btnShowTree);

		JButton exportButton = new JButton("export");

		exportButton.setBounds(1363, 890, 95, 23);
		frame.getContentPane().add(exportButton);

		JSpinner spinner = new JSpinner();
		spinner.setBounds(869, 891, 42, 20);
		frame.getContentPane().add(spinner);
		
		JButton runSimulationButton = new JButton("run Simulation");
		runSimulationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validateData();
				startCTE();
				
			}
		});
		runSimulationButton.setBounds(6, 555, 140, 23);
		frame.getContentPane().add(runSimulationButton);

	}

	private JPanel treeSpecificator() {
		JPanel treeSpecifier = new JPanel();
		treeSpecifier.setBackground(SystemColor.controlHighlight);
		treeSpecifier.setBounds(6, 11, 140, 533);
		frame.getContentPane().add(treeSpecifier);
		treeSpecifier.setLayout(null);

		JTextPane txtpnSeed = new JTextPane();
		txtpnSeed.setOpaque(false);
		txtpnSeed.setText("Seed");
		txtpnSeed.setBounds(10, 28, 120, 20);
		treeSpecifier.add(txtpnSeed);

		seedField = new JTextField();
		seedField.setToolTipText("Enter an Integer value here");
		seedField.setText(seed + "");
		seedField.setBounds(10, 47, 120, 20);
		treeSpecifier.add(seedField);
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
		treeSpecifier.add(txtpnTreeSpecifications);

		JTextPane textfield124124 = new JTextPane();
		textfield124124.setToolTipText("maximum depth of the tree ");
		textfield124124.setText("maxDepth");
		textfield124124.setOpaque(false);
		textfield124124.setBounds(10, 71, 120, 20);
		treeSpecifier.add(textfield124124);

		maxDepthField = new JTextField();
		maxDepthField.setToolTipText("Enter an Integer value here");
		maxDepthField.setText("30");
		maxDepthField.setColumns(10);
		maxDepthField.setBounds(10, 90, 120, 20);
		treeSpecifier.add(maxDepthField);

		JTextPane txtpn124124 = new JTextPane();
		txtpn124124.setToolTipText("minimum depth of the tree");
		txtpn124124.setText("minDepth");
		txtpn124124.setOpaque(false);
		txtpn124124.setBounds(10, 114, 120, 20);
		treeSpecifier.add(txtpn124124);

		minDepthField = new JTextField();
		minDepthField.setToolTipText("Enter an Integer value here");
		minDepthField.setText("5");
		minDepthField.setColumns(10);
		minDepthField.setBounds(10, 133, 120, 20);
		treeSpecifier.add(minDepthField);

		JTextPane txtpnMaxnodes = new JTextPane();
		txtpnMaxnodes.setToolTipText("The tree creator will stop after this many nodes are created. ");
		txtpnMaxnodes.setText("maxNodes");
		txtpnMaxnodes.setOpaque(false);
		txtpnMaxnodes.setBounds(10, 158, 120, 20);
		treeSpecifier.add(txtpnMaxnodes);

		maxNodesField = new JTextField();
		maxNodesField.setToolTipText("Enter an Integer value here");
		maxNodesField.setText("100");
		maxNodesField.setColumns(10);
		maxNodesField.setBounds(10, 177, 120, 20);
		treeSpecifier.add(maxNodesField);

		minNodesField = new JTextField();
		minNodesField.setToolTipText("Enter an Integer value here");
		minNodesField.setText("10");
		minNodesField.setColumns(10);
		minNodesField.setBounds(10, 220, 120, 20);
		treeSpecifier.add(minNodesField);

		JTextPane minNodes = new JTextPane();
		minNodes.setToolTipText("the tree creation wont stop before the minimum number of Nodes is reached");
		minNodes.setText("minNodes");
		minNodes.setOpaque(false);
		minNodes.setBounds(10, 201, 120, 20);
		treeSpecifier.add(minNodes);
		
		numberOfAgentsField = new JTextField();
		numberOfAgentsField.setToolTipText("");
		numberOfAgentsField.setText("5");
		numberOfAgentsField.setColumns(10);
		numberOfAgentsField.setBounds(10, 405, 120, 20);
		treeSpecifier.add(numberOfAgentsField);
		
		JTextPane txtpnNumberOfAgents = new JTextPane();
		txtpnNumberOfAgents.setToolTipText("gives a probability that the node is a leaf. And the tree ends. Even with minimum children but a leaffactor, the node can be a leaf. ");
		txtpnNumberOfAgents.setText("Number of Agents");
		txtpnNumberOfAgents.setOpaque(false);
		txtpnNumberOfAgents.setBounds(10, 386, 120, 20);
		treeSpecifier.add(txtpnNumberOfAgents);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setText("100");
		textField.setColumns(10);
		textField.setBounds(10, 450, 120, 20);
		treeSpecifier.add(textField);
		
		JTextPane txtpnNumberOfRuns = new JTextPane();
		txtpnNumberOfRuns.setToolTipText("gives the number of runs that are done for specified tree and  agent settings.");
		txtpnNumberOfRuns.setText("Number of trees");
		txtpnNumberOfRuns.setOpaque(false);
		txtpnNumberOfRuns.setBounds(10, 431, 120, 20);
		treeSpecifier.add(txtpnNumberOfRuns);
		
		JTextPane txtpnMultipleAgentsRuns = new JTextPane();
		txtpnMultipleAgentsRuns.setToolTipText("multiple integer Values that calculate for each run on each tree");
		txtpnMultipleAgentsRuns.setText("multiple Agents Runs");
		txtpnMultipleAgentsRuns.setOpaque(false);
		txtpnMultipleAgentsRuns.setBounds(10, 481, 120, 20);
		treeSpecifier.add(txtpnMultipleAgentsRuns);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("");
		textField_1.setText("100");
		textField_1.setColumns(10);
		textField_1.setBounds(10, 500, 120, 20);
		treeSpecifier.add(textField_1);

		return treeSpecifier;
	}

	private void startAlgo() {
		startCTE();
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

	public int getNumberOfAgents() {
		return Integer.parseInt(numberOfAgentsField.getText());
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
}

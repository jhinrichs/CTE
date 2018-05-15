package gui;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;

import tree.TreeFactory;
import tree.Verteilungsfunktionen;
import treeExploration.SimulationManager;

public class SmallSimulator {

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
	private JTextField maxChildrenField;
	private JTextField minChildrenField;
	private JTextField minDepthField;
	private int maxDepth;
	private int minDepth;
	private int maxBranches;
	private int minBranches;
	private double leafFactor;
	private int maxNodes;
	private int minNodes;
	private int[] numberOfAgents;
	private int numberOfRuns;
	public JSpinner spinner;
	public JComboBox Verteilungsfunktion;

	Random rand = new Random();
	int seed = rand.nextInt();
	private JTextField maxNodesField;

	private JTextField minNodesField;
	private JTextField NumberOfRunsTextfield;
	private JTextField MultiAgentsRunsField;
	public JTextPane outputWindow;

	public static SmallSimulator myWindow = null;
	public static SmallSimulator getSmallWindow() {
		if(myWindow == null) {
			myWindow = new SmallSimulator();
		}
		return myWindow;
	}
	
	
	public static void main(String[] args) {
		getSmallWindow();
	}
	
	/**
	 * Create the application.
	 */
	public SmallSimulator() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 655, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setBounds(6, 394, 140, 135);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		MultiAgentsRunsField = new JTextField();
		MultiAgentsRunsField.setBounds(10, 73, 120, 20);
		panel.add(MultiAgentsRunsField);
		MultiAgentsRunsField.setToolTipText("");
		MultiAgentsRunsField.setText("10;100;500;1000;5000;10000");
		MultiAgentsRunsField.setColumns(10);

		JTextPane txtpnMultipleAgentsRuns = new JTextPane();
		txtpnMultipleAgentsRuns.setBounds(10, 53, 120, 20);
		panel.add(txtpnMultipleAgentsRuns);
		txtpnMultipleAgentsRuns.setToolTipText("multiple integer Values that calculate for each run on each tree");
		txtpnMultipleAgentsRuns.setText("multiple Agents Runs");
		txtpnMultipleAgentsRuns.setOpaque(false);

		JTextPane txtpnNumberOfRuns = new JTextPane();
		txtpnNumberOfRuns.setBounds(10, 11, 120, 20);
		panel.add(txtpnNumberOfRuns);
		txtpnNumberOfRuns
				.setToolTipText("gives the number of runs that are done for specified tree and  agent settings.");
		txtpnNumberOfRuns.setText("Number of Runs");
		txtpnNumberOfRuns.setOpaque(false);

		NumberOfRunsTextfield = new JTextField();
		NumberOfRunsTextfield.setBounds(10, 30, 120, 20);
		panel.add(NumberOfRunsTextfield);
		NumberOfRunsTextfield.setToolTipText("");
		NumberOfRunsTextfield.setText("3");
		NumberOfRunsTextfield.setColumns(10);

		JButton runSimulationButton = new JButton("run Simulation");
		runSimulationButton.setBounds(10, 104, 120, 23);
		panel.add(runSimulationButton);
		
		outputWindow = new JTextPane();
		outputWindow.setEditable(false);
		outputWindow.setBounds(156, 11, 473, 518);
		frame.getContentPane().add(outputWindow);
		runSimulationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				validateData();
//				TreeFactory fac = new TreeFactory(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes,
//						minNodes, leafFactor, (Verteilungsfunktionen) Verteilungsfunktion.getSelectedItem());
				//ProgrammManager.runSimulationThreaded(fac, numberOfAgents, numberOfRuns);
				SimulationManager simman = new SimulationManager(numberOfRuns, new TreeFactory(seed, maxDepth, minDepth, maxBranches, minBranches, maxNodes,
						minNodes, leafFactor, (Verteilungsfunktionen) Verteilungsfunktion.getSelectedItem()), numberOfAgents);
				rand = new Random(Integer.parseInt(seedField.getText()));
				seedField.setText("" + rand.nextInt());
				simman.start();
			}
		});

	}

	private JPanel treeSpecificator() {
		JPanel treeSpecifier = new JPanel();
		treeSpecifier.setBackground(SystemColor.controlHighlight);
		treeSpecifier.setBounds(6, 11, 140, 372);
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
				// System.out.println("Seed updated");
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
		maxNodesField.setText("1000");
		maxNodesField.setColumns(10);
		maxNodesField.setBounds(10, 177, 120, 20);
		treeSpecifier.add(maxNodesField);

		minNodesField = new JTextField();
		minNodesField.setToolTipText("Enter an Integer value here");
		minNodesField.setText("100");
		minNodesField.setColumns(10);
		minNodesField.setBounds(10, 220, 120, 20);
		treeSpecifier.add(minNodesField);

		JTextPane minNodesText = new JTextPane();
		minNodesText.setToolTipText("the tree creation wont stop before the minimum number of Nodes is reached");
		minNodesText.setText("minNodes");
		minNodesText.setOpaque(false);
		minNodesText.setBounds(10, 201, 120, 20);
		treeSpecifier.add(minNodesText);
		
		Verteilungsfunktion = new JComboBox();
		Verteilungsfunktion.setModel(new DefaultComboBoxModel(Verteilungsfunktionen.values()));
		Verteilungsfunktion.setSelectedIndex(0);
		Verteilungsfunktion.setBounds(10, 341, 120, 22);
		treeSpecifier.add(Verteilungsfunktion);

		return treeSpecifier;
	}


	private void validateData() {
		maxDepth = Integer.parseInt(maxDepthField.getText());
		minDepth = Integer.parseInt(minDepthField.getText());
		maxBranches = Integer.parseInt(maxChildrenField.getText());
		minBranches = Integer.parseInt(minChildrenField.getText());
		maxNodes = Integer.parseInt(maxNodesField.getText());
		minNodes = Integer.parseInt(minNodesField.getText());

		numberOfRuns = Integer.parseInt(NumberOfRunsTextfield.getText());
		numberOfAgents = convertNumberOfAgents();
	}

	private int[] convertNumberOfAgents() {
		String tempString[] = MultiAgentsRunsField.getText().split(";");
		int[] numberOfAgents = new int[tempString.length];
		for (int i = 0; i < tempString.length; i++) {
			numberOfAgents[i] = Integer.parseInt(tempString[i]);
		}
		return numberOfAgents;
	}
	
	
	

	//  Add some text



	public void writeLine(String string) {

		StyledDocument doc = outputWindow.getStyledDocument();
		try
		{
		    doc.insertString(0, string +" \n",null);
		}
		catch(Exception e) { System.out.println(e); }
	}
}

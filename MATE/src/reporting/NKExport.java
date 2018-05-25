package reporting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import org.apache.poi.ss.formula.eval.NumericValueEval;

import solutionData.Traversal;
import tree.TreeFactory;

public class NKExport {

	int protocolNumber = 0;
	ExcelExport writer;

	int actualRow = 0;
	int actualColumn = 0;

	private int runs = 0;
	private int[] agents;
	public ArrayList<ExportData> allSolutions = new ArrayList<>();

	public NKExport() {

	}

	Comparator<ExportData> sortingByNumberOfNodesAndAgents = new Comparator<ExportData>() {

		@Override
		public int compare(ExportData t0, ExportData t1) {
			if (t0.numberOfNodes == t1.numberOfNodes) {
				Integer a = t0.numberOfAgents;
				Integer b = t1.numberOfAgents;
				return a.compareTo(b);
			}

			else {
				Integer a = t0.numberOfNodes;
				Integer b = t1.numberOfNodes;
				return a.compareTo(b);
			}
		}

	};

	public NKExport(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {

		agents = numberOfAgents;
		runs = numberOfRuns;
		writer = new ExcelExport(runs + 100, agents.length + 100);
		writer.write(0, 0, "Simulation protocol number: ");
		writer.write(0, 1, "" + protocolNumber);
		writer.write(0, 2, "Date ");
		writer.write(0, 3, new Date().toString());

		writer.write(2, 0, "Tree Settings");
		writer.write(2, 1, "Seed");
		writer.write(2, 2, "MinNodes");
		writer.write(2, 3, "MaxNodes");
		writer.write(2, 4, "MinBranches");
		writer.write(2, 5, "MaxBranches");
		writer.write(2, 6, "Function");
		writer.write(2, 7, "NumberOfAgents");

		writer.write(3, 1, "" + treeFactory.seed);
		writer.write(3, 2, "" + treeFactory.minNodes);
		writer.write(3, 3, "" + treeFactory.maxNodes);
		writer.write(3, 4, "" + treeFactory.minBranches);
		writer.write(3, 5, "" + treeFactory.maxBranches);
		writer.write(3, 6, "" + treeFactory.function);
		writer.write(3, 7, "" + numberOfAgents.toString());
		writer.write(3, 8, "" + numberOfRuns);

		actualRow = 5;
		actualColumn = 0;

	}

	public void addRun(int runNumber, long seed) {

		writer.write(actualRow, 0, "Run Number");
		writer.write(actualRow, 1, "" + runNumber);
		writer.write(actualRow, 3, "Seed");
		writer.write(actualRow, 4, "" + seed);

		actualRow += 2;
	}

	public void addSolutionByNumberOfNodes() {

		allSolutions.sort(sortingByNumberOfNodesAndAgents);
		writer.write(actualRow, 2, "Nodes / Agents");

		// First row of table: write number of agents
		for (int a = 0; a < agents.length; a++) {
			writer.write(actualRow, 3 + a, "agents: " + agents[a]);
		}
		actualRow++;

		int a = 0;
		for (ExportData exportData : allSolutions) {
			// first column: write number of nodes to table
			writer.write(actualRow, 2, "Nodes in Tree : " + exportData.numberOfNodes);
			writer.write(actualRow, 1, "treecode : " + exportData.treeCode);
			// Table: write solution data
			writer.write(actualRow, 3 + a, "steps " + exportData.numberOfSteps + " agents " + exportData.numberOfAgents
					+ " nodes " + exportData.numberOfNodes);

			a++;
			if (a == agents.length - 1) {
				a = 0;
				actualRow++;
			}
		}

	}

	public void save() {
		writer.save();

	}

	public void addSolutions(ExportData[][] allSolutions2) {

		allSolutions2 = sortForNodes(allSolutions2);
		writeSolutions(allSolutions2);

	}

	private void writeSolutions(ExportData[][] allSolutions2) {

		actualColumn = 3;
		// erste zeile mit anzahl der agenten
		for (int j = 0; j < allSolutions2[0].length; j++) {
			writer.write(actualRow, actualColumn, allSolutions2[0][j].numberOfAgents);
			actualColumn++;
		}
		actualRow++;

		for (int i = 0; i < allSolutions2.length; i++) {
			actualColumn = 1;
			writer.write(actualRow, actualColumn, allSolutions2[i][0].treeCode);
			actualColumn++;
			writer.write(actualRow, actualColumn, allSolutions2[i][0].numberOfNodes);
			actualColumn++;

			for (int j = 0; j < allSolutions2[i].length; j++) {
				writer.write(actualRow, actualColumn, allSolutions2[i][j].numberOfSteps);
				actualColumn++;
			}
			actualRow++;
		}
	}

	private ExportData[][] sortForNodes(ExportData[][] allSolutions2) {
		boolean sorted = false;
		while (!sorted) {
			int i = 0;
			sorted = true;
			while (i < allSolutions2.length - 1) {
				if (allSolutions2[i][0].numberOfNodes > allSolutions2[i + 1][0].numberOfNodes) {
					ExportData[] temp = allSolutions2[i];
					allSolutions2[i] = allSolutions2[i + 1];
					allSolutions2[i + 1] = temp;
					sorted = false;
				}
				i++;
			}
		}
		return allSolutions2;
	}

	

	public void writeSolutionPack(ExportData[][] solutionPack) {
		int a = 0;
		writer.write(actualRow, 1, "treecode : " + solutionPack[0][0].treeCode);
		writer.write(actualRow, 2, "Nodes in Tree : " + solutionPack[0][0].numberOfNodes);

		for (int i = 0; i < solutionPack[0].length; i++) {
			writer.write(actualRow, 3, solutionPack[0][i].numberOfSteps);
			writer.write(actualRow, 4, solutionPack[0][i].numberOfAgents);
			writer.write(actualRow, 5, solutionPack[0][i].numberOfNodes);
			writer.write(actualRow, 7, solutionPack[1][i].numberOfSteps);
			writer.write(actualRow, 8, solutionPack[1][i].numberOfAgents);
			writer.write(actualRow, 9, solutionPack[1][i].numberOfNodes);

			// // first column: write number of nodes to table
			//
			//
			// // Table: write solution data for first solution into first a rows.
			// writer.write(actualRow, 3 + a, solutionPack[0][i].numberOfSteps + " agents "
			// + exportData.numberOfAgents
			// + " nodes " + exportData.numberOfNodes);
			// writer.write(actualRow, 3+a, value);
			//
			// // write solution data for second pack into second a rows
			// writer.write(actualRow, 3 + a+agents.length+2, "steps " +
			// exportData.numberOfSteps + " agents " + exportData.numberOfAgents
			// + " nodes " + exportData.numberOfNodes);
			//
			a++;
			if (a == agents.length - 1) {
				a = 0;
				actualRow++;
			}
		}

	}

	public void writeLeftieSolutionData(ExportData[] solutionPack) {
		int a = 0;
		for (ExportData exportData : solutionPack) {
			// first column: write number of nodes to table
			writer.write(actualRow, 5, "treecode : " + exportData.treeCode);
			writer.write(actualRow, 6, "Nodes in Tree : " + exportData.numberOfNodes);
			// Table: write solution data
			writer.write(actualRow, 7 + a, "steps " + exportData.numberOfSteps + " agents " + exportData.numberOfAgents
					+ " nodes " + exportData.numberOfNodes);

			a++;
			if (a == agents.length - 1) {
				a = 0;
				actualRow++;
			}
		}
	}
	

	int treeCodeColumn = 1;
	int minChildColumn = 3;
	int maxChildColumn =2;
	int numberOfNodesColumn =4;
	int agentsColumn = 5;
	int CTEStepsColumn = 6;
	int leftieStepsColumn = 7;

	
	public void writeSolutionsSimulator(TreeFactory fac,ExportData CTE, ExportData leftie, double factor) {
		
		writer.write(actualRow, treeCodeColumn, CTE.treeCode);
		writer.write(actualRow, minChildColumn, fac.minBranches);
		writer.write(actualRow, maxChildColumn, fac.maxBranches);
		writer.write(actualRow, numberOfNodesColumn, CTE.numberOfNodes);
		writer.write(actualRow, agentsColumn, CTE.numberOfAgents);
		writer.write(actualRow, CTEStepsColumn, CTE.numberOfSteps);
		writer.write(actualRow, leftieStepsColumn, leftie.numberOfSteps);

		actualRow++;	
	}
	public void initializeWriteSolutionSimulation() {

		writer.write(actualRow, treeCodeColumn, "Treecode");
		writer.write(actualRow, minChildColumn, "MinChild");
		writer.write(actualRow, maxChildColumn, "MaxChild");
		writer.write(actualRow, numberOfNodesColumn, "Number of Nodes");
		writer.write(actualRow, agentsColumn, "Number of Agents");
		writer.write(actualRow, CTEStepsColumn, "CTE Steps");
		writer.write(actualRow, leftieStepsColumn, "Leftie Steps");
		actualRow++;
	}

	public void addRun(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {
		
		actualRow++;
		writer.write(actualRow, treeCodeColumn, "Next Run");
		actualRow++;
		
	}
}

package reporting;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import org.apache.poi.ss.formula.eval.NumericValueEval;

import solutionData.Traversal;
import tree.TreeFactory;

public class ExportDesigner {

	int protocolNumber = 0;
	ExcelExport writer ;

	int actualRow = 0;
	int actualColumn = 0;

	private int runs =0;
	private int[] agents; 
	public ArrayList<Traversal> allSolutions = new ArrayList<>();

	public ExportDesigner() {

	}

	Comparator<Traversal> sortingByNumberOfNodesAndAgents = new Comparator<Traversal>() {

		@Override
		public int compare(Traversal t0, Traversal t1) {
			if (t0.getNumberOfNodes() == t1.getNumberOfNodes()) {
				Integer a =t0.getAgents().size();
				Integer b =t1.getAgents().size();
				return a.compareTo(b);
			}

			else {
				return t0.getNumberOfNodes().compareTo(t1.getNumberOfNodes());
			}
		}

	};

	public ExportDesigner(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {

		agents = numberOfAgents;
		runs = numberOfRuns;
		writer = new ExcelExport(runs+100,agents.length+100);
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
		for(int a = 0; a<agents.length;a++) {
			writer.write(actualRow,3 +a , "agents: "+agents[a]);
		}
		actualRow++;
		
		int a =0;
		for (Traversal t : allSolutions) {
			//first column: write number of nodes to table
			writer.write(actualRow,2, "Nodes in Tree : "+t.getNumberOfNodes());
			writer.write(actualRow,1, "treecode : " +  t.getTreeCode());							
				//Table: write solution data
				writer.write(actualRow,3 + a, "steps "+t.getNumberOfSteps() + " agents " + t.getAgents().size() + " nodes " + t.getNumberOfNodes());
				
				a++;
				if(a==agents.length-1) {
					a=0;
					actualRow++;
			}
		}
		
	}

	public void save() {
			writer.save();

		
	}

}

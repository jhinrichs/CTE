package reporting;

import java.util.Date;

import tree.TreeFactory;

public class ExportDesigner {
	
	int protocolNumber =0; 
	ExcelExport writer = new ExcelExport();

	int actualRow = 0;
	int actualColumn = 0;
	
	
	private ExcelExport exporter = new ExcelExport();
	
	public ExportDesigner() {
		
		
	}

	public ExportDesigner(TreeFactory treeFactory, int[] numberOfAgents, int numberOfRuns) {
	
		writer.write(0,0,"Simulation protocol number: ");
		writer.write(0, 1, "" + protocolNumber);
		writer.write(0, 2, "Date ");
		writer.write(0, 3, new Date().toString());
		
		writer.write(2, 0, "Tree Settings");
		writer.write(2, 1, "Seed");
		writer.write(2, 2, "MinNodes");
		writer.write(2, 3, "MaxNodes");
		writer.write(2, 4, "MinBranches");
		writer.write(2, 5, "MaxBranches");
		writer.write(2, 5, "Function");
		writer.write(2, 5, "NumberOfAgents");
		writer.write(2, 5, "NumberOfRuns");

		writer.write(3, 1, ""+ treeFactory.seed);
		writer.write(3, 2, ""+ treeFactory.minNodes);
		writer.write(3, 3, ""+ treeFactory.maxNodes);
		writer.write(3, 4, ""+ treeFactory.minBranches);
		writer.write(3, 5, ""+ treeFactory.maxBranches);
		writer.write(3, 5, ""+ treeFactory.function);
		writer.write(3, 5, ""+ numberOfAgents.toString());
		writer.write(3, 5, ""+ numberOfRuns);
		
		actualRow = 5;
		actualColumn =0;
		
		
		
	}

	public void addRun(int runNumber , long seed) {
		
		writer.write(actualRow, 0, "Run Number" );
		writer.write(actualRow, 1, ""+ runNumber );
		writer.write(actualRow, 3, "Seed" );
		writer.write(actualRow, 4, ""+ seed );
	}
	
	

}

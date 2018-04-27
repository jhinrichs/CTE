package reporting;

import solutionData.Traversal;

public class ExportData {
	public int numberOfAgents;
	public int numberOfSteps;
	public int numberOfNodes;
	public String treeCode;
	
	public ExportData(Traversal solution) {
		
		if(solution.isValidSolution()) {
			numberOfAgents = solution.getAgents().size();
			numberOfSteps = solution.getNumberOfSteps();
			numberOfNodes = solution.getNumberOfNodes();
			treeCode = solution.getTreeCode();
		}
	}
	public ExportData() {};
}

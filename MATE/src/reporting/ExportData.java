package reporting;

import solutionData.Traversal;
import tree.Node;

public class ExportData {
	public Node root;
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
			root = solution.getRoot();
		}
	}
	public ExportData() {};
}

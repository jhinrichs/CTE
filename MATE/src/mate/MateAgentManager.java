package mate;

import java.util.List;

import solutionData.RobPosNode;
import tree.Node;

public class MateAgentManager {
	
	private List<MateAgent> mates;
	
	private RobPosNode robPosTree;
	
	private Node exploredTree;
	
	private Node originalTree;
	
	
	public MateAgentManager(Node root, int NumberOfRobots){
		originalTree = root;
		
		for (int i =0 ; i< NumberOfRobots ;i ++){
			mates.add(new MateAgent(root, new DecisionModule()));
		}
	}
	
	public void nextStep(){
		for ( MateAgent mate : mates ){
			mate.calculateMove();
		}
	}
	
	

}

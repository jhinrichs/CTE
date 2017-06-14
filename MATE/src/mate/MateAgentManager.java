package mate;

import java.util.List;

import mate.Brain.BrainModuleType;
import solutionData.RobPosTree;
import tree.INode;
import tree.Node;

public class MateAgentManager {
	
	private List<MateAgent> mates;
	
	private RobPosTree robPosTree;
	
	private INode exploredTree;
	
	private INode originalTree;
	
	
	public MateAgentManager(Node root, int NumberOfRobots, BrainModuleType brainType){
		originalTree = root;
		
		for (int i =0 ; i< NumberOfRobots ;i ++){
			mates.add(new MateAgent(root, brainType));
		}
	}
	
	public void nextStep(){
		for ( MateAgent mate : mates ){
			mate.calculateMove();
		}
	}
	
	

}

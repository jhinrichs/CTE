package mate;

import solutionData.Agent;
import tree.Node;

public class Brain {
	
	IBrainModule myBrain;
	
	public Brain(Agent a, BrainModuleType brainType){
		
		switch (brainType){
		case Simple:
			myBrain = new SimpleBrain(a);
			break;
		case CTE:
			myBrain = new CTEBrain(a);
		}
		
	}
	
	public Node getNextNode(){
		return myBrain.getNextNode();
		 
	}
	

	public enum BrainModuleType{
		Simple,
		Komplex,
		Normal,
		Intelligent,
		AntMode, 
		CTE
	}

	
}

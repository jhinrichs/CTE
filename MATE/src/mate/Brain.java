package mate;

import tree.Node;

public class Brain {
	
	IBrainModule myBrain;
	
	public Brain(MateAgent a, BrainModuleType brainType){
		
		switch (brainType){
		case SIMPLE:
			myBrain = new SimpleBrain(a);
			
		}
		
	}
	
	public Node getNextNode(){
		return myBrain.getNextNode();
		 
	}
	

	public enum BrainModuleType{
		SIMPLE
	}

	
}

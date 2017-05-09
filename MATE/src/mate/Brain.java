package mate;


public class Brain {
	
	IBrainModule myBrain;
	
	public Brain(MateAgent a, BrainModuleType brainType){
		
		switch (brainType){
		case SIMPLE:
			myBrain = new SimpleBrain(a);
			
		}
		
	}

	public void moveAgent() {
		myBrain.moveAgent();
	}
	
	public void getDecision(){
		myBrain.getDecision();
	}
	

	public enum BrainModuleType{
		SIMPLE
	}

	
}

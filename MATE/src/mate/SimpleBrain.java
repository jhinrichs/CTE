package mate;

import javax.swing.text.html.HTMLDocument.Iterator;

import solutionData.RobPosTree;
import tree.Node;
import tree.TreeDataCalculator;

public class SimpleBrain implements IBrainModule {

	
	private MateAgent agent;
	private double value;
	private double thresholdBack = 0 ;
	private double thresholdForward = 0;
	private double decisionValue=0;
	private int searchDepth = 20;
	private double distanceInfluence = 0.2;
	
	private int calculationDepth = 20;
	
	public SimpleBrain(MateAgent a) {
	agent=a;
	}


	@Override
	public Node getNextNode() {
		getDecisionValue();
		
		return null;
		
		
	}
	
	private void getDecisionValue() {
		getDownwardDecisionValue(agent.getActiveNode());
		
		
	}


	private void getDownwardDecisionValue(Node n){
		for(RobPosTree robPos : RobPosTree.activeRobs){
			
			// if n is in subtree of robPos
			if(n.isRootOf(robPos.getOrigin())){
				int[] distances = robPos.getNodesInDistance(searchDepth);
				int distance =1;
				for(int i : distances ){
					double influenceValue = Math.pow(distanceInfluence, distance)*i;
					decisionValue += influenceValue;
				}
			}
			
		}
		
	}
	
	
	

}

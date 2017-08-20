package mate;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import solutionData.Agent;
import solutionData.RobPosTree;
import tree.Node;
import tree.TreeDataCalculator;

public class SimpleBrain implements IBrainModule {

	private Agent agent;
	private double value;
	private double threshold;

	private int calculationDepth = Integer.max(AgentManager.calci.getNumberOfNodes()/4,50);

	public SimpleBrain(Agent a) {
		agent = a;
		threshold = ((Math.random()*50)+10)/100;
		System.out.println("Threshold = " + threshold);
	}

	@Override
	public Node getNextNode() {
		
		List<Node> possibleNodes = new ArrayList<Node>();
		for (Node n : agent.activeNode().getChildren()) {
			if (!n.isFinished()) {
				possibleNodes.add(n);
			}
		}
		if (!agent.activeNode().isRoot()) {
			possibleNodes.add(agent.activeNode().getParent());
		}		
		Node nextNode = agent.activeNode();
		double decisionValue = threshold;
		for(Node n : possibleNodes) {
			double nextValue = getDecisionValue(n);
			if(nextValue> decisionValue) {
				nextNode= n;
				decisionValue=nextValue;
			}
		}
		
		return nextNode;
	}

	private Node getRandomNode(ArrayList<Node> randomNodes) {
		int index = (int) (Math.random() * randomNodes.size());
		return randomNodes.get(index);
	}
	
	

	private double getDecisionValue(Node n) {
		double decisionValue = Math.random();

		return decisionValue;

	}


}

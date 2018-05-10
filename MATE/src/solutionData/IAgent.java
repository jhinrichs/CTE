package solutionData;

import java.util.ArrayList;
import java.util.List;

import optimalExploration.MovingPlan;
import tree.Node;

public interface IAgent {


	int getId();
	void moveAgent(Node nodeToGo);
	int getStepsCount();
	ArrayList<Node> getNodesToVisit();
	ArrayList<Node> getLeafs();
	Node getPosition();
	void addNode(Node node);
	void addNodes(List<Node> subList);
	Node getTree();
	MovingPlan calculateMove();
	
	
}
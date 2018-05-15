package solutionData;

import java.util.ArrayList;
import java.util.List;

import tree.INode;
import tree.Node;

public interface IAgent {

	ArrayList<Node> getLeafs();

	INode getRoot();

	Node getTree();

	int getId();

	Node activeNode();

	boolean enoughEnergy();

	void addNode(Node nodeToVisit);

	void removeNode(INode nodeToRemove);

	ArrayList<Node> getNodesToVisit();

	void addNodes(List<Node> nodes);

	/* 
	 */
	int getStepsCount();

	Node getPosition();

	void moveAgent(Node newNode);

}
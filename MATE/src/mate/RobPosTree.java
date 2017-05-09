package mate;

import java.util.List;

import tree.Node;

public class RobPosTree{
	
	private Node original;

	private int distanceToParent;

	private List<MateAgent> agentsAtNode;
	
	
	public RobPosTree(Node position){
		this.original = position;		
	}

}

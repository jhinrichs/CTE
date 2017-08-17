package mate;

import solutionData.IAgent;
import tree.Node;

public interface IMateAgent extends IAgent {
	public void forward(Node goTo);
	public void backward();
	public void waitAtNode();
}

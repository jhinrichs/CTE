package mate;

import tree.Node;

public interface IMateAgent {
	public void forward(Node goTo);
	public void backward();
	public void waitAtNode();
}

package tree;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.tree.TreeNode;

public interface INode extends TreeNode{

	/**
	 * Counts all nodes in this nodes Tree
	 * 
	 * @return
	 */
	int getTreeNodeCount();

	void addNode();

	INode getParent();

	void setParent(Node parent);

	ArrayList<Node> getChildren();

	void setChildren(ArrayList<Node> children);

	/**
	 * Traverses the tree until an unfinished node is found. If all nodes are
	 * finished the tree is finished as well.
	 * 
	 * @return
	 */
	Boolean checkIfFinished();

	Enumeration<Node> children();

	boolean getAllowsChildren();

	INode getChildAt(int childIndex);

	int getChildCount();

	int getIndex(TreeNode node);

	boolean isLeaf();

	int getId();

	/**
	 * Returns a list of all nodes that are in the tree.
	 * 
	 * @param node
	 * @param listOfNodes
	 * @return
	 */
	List<Node> getLeafList(List<Node> listOfNodes);

	/**
	 * adds the give Node as a child and sets its Parent to this node
	 * 
	 * @param newChild
	 */
	void addChild(Node newChild);

	void setFinished(boolean b);

	/**return 
	 * @return
	 */
	boolean isRoot();

}
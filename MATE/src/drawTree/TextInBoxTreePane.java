/*
 * [The "BSD license"]
 * Copyright (c) 2011, abego Software GmbH, Germany (http://www.abego.org)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the abego Software GmbH nor the names of its 
 *    contributors may be used to endorse or promote products derived from this 
 *    software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 */
package drawTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;

import javax.swing.JComponent;

import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.TreeLayout;

import solutionData.Agent;
import solutionData.Traversal;
import tree.INode;
import tree.Node;

public class TextInBoxTreePane extends JComponent {
	private final TreeLayout<Node> treeLayout;
	private boolean bigNodes = true;
	private Traversal traversal;

	private TreeForTreeLayout<Node> getTree() {
		return treeLayout.getTree();
	}

	private Iterable<Node> getChildren(Node parent) {
		return getTree().getChildren(parent);
	}

	private Rectangle2D.Double getBoundsOfNode(INode node) {
		return (Double) treeLayout.getNodeBounds().get(node);
	}

	/**
	 * Specifies the tree to be displayed by passing in a {@link TreeLayout} for
	 * that tree.
	 * 
	 * @param treeLayout
	 *            the {@link TreeLayout} to be displayed
	 * @param bigNodes2
	 */
	public TextInBoxTreePane(TreeLayout<Node> treeLayout, boolean bigNodes) {

		this.bigNodes = bigNodes;
		this.treeLayout = treeLayout;
		Dimension size = treeLayout.getBounds().getBounds().getSize();
		setPreferredSize(size);
	}

	public TextInBoxTreePane(TreeLayout<Node> treeLayout, boolean bigNodes, Traversal traversal) {
		this.traversal = traversal;
		this.bigNodes = bigNodes;
		this.treeLayout = treeLayout;
		Dimension size = treeLayout.getBounds().getBounds().getSize();
		setPreferredSize(size);
	}

	// -------------------------------------------------------------------
	// painting

	private static int ARC_SIZE = 10;
	private static Color BOX_COLOR = Color.orange;
	private static Color BORDER_COLOR = Color.darkGray;
	private static Color TEXT_COLOR = Color.BLACK;

	private void paintEdges(Graphics g, Node parent) {
		if (!getTree().isLeaf(parent)) {
			Rectangle2D.Double b1 = getBoundsOfNode(parent);
			double x1 = b1.getCenterX();
			double y1 = b1.getCenterY();
			for (Node child : getChildren(parent)) {
				Rectangle2D.Double b2 = getBoundsOfNode(child);
				g.drawLine((int) x1, (int) y1, (int) b2.getCenterX(), (int) b2.getCenterY());

				paintEdges(g, child);
			}
		}
	}

	private void paintBox(Graphics g, INode Node) {
		// draw the box in the background

		g.setColor(BOX_COLOR);
		Rectangle2D.Double box = getBoundsOfNode(Node);
		g.fillRoundRect((int) box.x, (int) box.y, (int) box.width - 1, (int) box.height - 1, ARC_SIZE, ARC_SIZE);
		g.setColor(BORDER_COLOR);
		g.drawRoundRect((int) box.x, (int) box.y, (int) box.width - 1, (int) box.height - 1, ARC_SIZE, ARC_SIZE);

		if (bigNodes) {
			g.setFont(new Font("Serif", Font.ITALIC, 10));
			g.setColor(TEXT_COLOR);
			g.drawString(Node.getId() + "", (int) box.x + 1, (int) box.y + 13);
		}

	}

	@Override
	public void paint(Graphics g) {

		paintEdges(g, getTree().getRoot());

		int i = 0;
		if (traversal != null) {
			for (Agent a : traversal.getAgents()) {

				switch (i % 9) {
				case 0:
					BOX_COLOR = Color.ORANGE;
					break;
				case 1:
					BOX_COLOR = Color.red;
					break;
				case 2:
					BOX_COLOR = Color.yellow;
					break;
				case 3:
					BOX_COLOR = Color.gray;
					break;
				case 4:
					BOX_COLOR = Color.green;
					break;
				case 5:
					BOX_COLOR = Color.blue;
					break;
				case 6:
					BOX_COLOR = Color.darkGray;
					break;
				case 7:
					BOX_COLOR = Color.white;
					break;
				case 8:
					BOX_COLOR = Color.cyan;
					break;

				}
				i++;

				// System.out.println("paint paths");
				// paint the boxes
				for (INode node : a.getNodesToVisit()) {
					if (node.isRoot()) {
						Color tempColor = BOX_COLOR;
						BOX_COLOR = Color.white;
						paintBox(g, node);
						BOX_COLOR = tempColor;
					} else {
						paintBox(g, node);
					}
				}
			}
		} else {
			BOX_COLOR = Color.ORANGE;
			for (INode node : treeLayout.getNodeBounds().keySet()) {

				paintBox(g, node);
			}
		}

	}

	public boolean isBigNodes() {
		return bigNodes;
	}

	public void setBigNodes(boolean bigNodes) {
		this.bigNodes = bigNodes;
	}
}
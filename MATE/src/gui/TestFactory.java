package gui;
import javax.swing.JPanel;
import java.awt.Component;
import java.awt.SystemColor;

public final class TestFactory {
	/**
	 * @wbp.factory
	 * @wbp.factory.parameter.source arg0 txtpnSeed
	 * @wbp.factory.parameter.source arg0_1 seedField
	 * @wbp.factory.parameter.source arg0_2 txtpnTreeSpecifications
	 * @wbp.factory.parameter.source arg0_3 textfield124124
	 * @wbp.factory.parameter.source arg0_4 maxDepthField
	 * @wbp.factory.parameter.source arg0_5 txtpn124124
	 * @wbp.factory.parameter.source arg0_6 minDepthField
	 * @wbp.factory.parameter.source arg0_7 txtpnMaxnodes
	 * @wbp.factory.parameter.source arg0_8 maxNodesField
	 * @wbp.factory.parameter.source arg0_9 minNodesField
	 * @wbp.factory.parameter.source arg0_10 minNodes
	 */
	public static JPanel createJPanel(Component arg0, Component arg0_1, Component arg0_2, Component arg0_3, Component arg0_4, Component arg0_5, Component arg0_6, Component arg0_7, Component arg0_8, Component arg0_9, Component arg0_10) {
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlHighlight);
		panel.setLayout(null);
		panel.add(arg0);
		panel.add(arg0_1);
		panel.add(arg0_2);
		panel.add(arg0_3);
		panel.add(arg0_4);
		panel.add(arg0_5);
		panel.add(arg0_6);
		panel.add(arg0_7);
		panel.add(arg0_8);
		panel.add(arg0_9);
		panel.add(arg0_10);
		return panel;
	}
}
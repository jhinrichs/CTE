package gui;

import javax.swing.JCheckBox;

import treeExploration.ProgrammManager;

public class Animator extends Thread {

	JCheckBox box;

	public Animator(JCheckBox chckbxPlay) {
		box = chckbxPlay;
	}

	@Override
	public void run() {

		int i=0;
		int max = ProgrammManager.recentTraversal.getNumberOfSteps();
		while(box.isSelected()) {
			ProgrammManager.paintSteps(i);
			System.out.println(i);
			i= (i+1)%max;
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

package tree;

import java.io.NotActiveException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author jonas
 *
 */
public class TreeSpecifier {
	private ArrayList<TreeTypes> types = new ArrayList<>();
	private List<Integer> probabilities = new ArrayList<>();

	public TreeSpecifier() {

	}
	public TreeSpecifier(TreeTypes type, int probability) {
		types.add(type);
		probabilities.add(probability);
	}

	/**Add a treeType to the list of possible treeTypes with its related probability. Only takes values 0 to 100
	 * @param type
	 * @param probability
	 */
	public void addTreeType(TreeTypes type, int probability) {
		types.add(type);
		if(probability<0 || probability>100){
			throw new IndexOutOfBoundsException("Probability for a tree must be between 0 and 100. Value" + probability + " is out of range");
		}
			probabilities.add(probability);
		
		
	}

	
	
	/**
	 * returns a TreeType based on the Treespecifiers Type list and its related probabilities
	 * @param d
	 * @return
	 */
	public TreeTypes getTreeType(double d){
		int total=0;
		for(int i : probabilities){
			total+=i;
		}
		
//		sets total to a value between zero and the total number of all probabilities
		total = (int) (total*d);
		
		int i=-1;
		while(total>=0){
			i++;
			total = total - probabilities.get(i);
		}
		
		return types.get(i);
	}

}

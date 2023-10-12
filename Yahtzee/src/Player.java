
public class Player {//Has a name, score and alist for holding dices

	private String name;
	private int score;
	private SingleLinkedList combination;

	public String getName() {
		return name;
	}
	public int getScore() {
		return score;
	}
	public SingleLinkedList getCombination() {
		return combination;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void setCombination(SingleLinkedList combination) {
		this.combination = combination;
	}
}

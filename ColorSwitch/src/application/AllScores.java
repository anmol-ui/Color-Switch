package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AllScores implements Serializable {
	public int score;
	public ArrayList<Integer> scoreList=new ArrayList<>();
	public AllScores(ArrayList<Integer> l) {
		this.scoreList=l;
	}
	public int getScore() {
		return this.score;
	}
	public ArrayList<Integer> getscoreList(){
		return this.scoreList;
	}
}

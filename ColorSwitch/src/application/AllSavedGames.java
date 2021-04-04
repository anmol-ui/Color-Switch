package application;

import java.io.Serializable;
import java.util.ArrayList;

public class AllSavedGames implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6804511970150615455L;
	
	ArrayList<GameState> savedgamelist=new ArrayList<>();
	
	public ArrayList<GameState> getSavedgamesList(){
		return this.savedgamelist;
	}
}

package application;

import java.io.IOException;

import javafx.stage.Window;

public interface HomePage {
	public void StartNew(String s) throws IOException;
	public void ResumeGame(Window window) throws IOException;
	public void exitApp() throws IOException;
	public void HighScores(Window window) throws IOException;
	public void About() throws IOException;
}

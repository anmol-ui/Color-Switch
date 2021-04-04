package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Controller extends Main implements HomePage{
	private FXMLLoader loader0 = new FXMLLoader(getClass().getResource("ColorSwitch.fxml"));
	private FXMLLoader loader1 = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
	private FXMLLoader loader2 = new FXMLLoader(getClass().getResource("Resumegame.fxml"));
	private FXMLLoader loader3 = new FXMLLoader(getClass().getResource("Score.fxml"));
	private FXMLLoader loader4 = new FXMLLoader(getClass().getResource("ExitApp.fxml"));
	private FXMLLoader loader5 = new FXMLLoader(getClass().getResource("PauseScreen.fxml"));
	private FXMLLoader loader6 = new FXMLLoader(getClass().getResource("SaveGameOption.fxml"));
	private FXMLLoader loader7 = new FXMLLoader(getClass().getResource("GameOver.fxml"));
	private FXMLLoader loader8 = new FXMLLoader(getClass().getResource("about.fxml"));

	private Parent root0,root1,root2,root3,root4,root5,root6,root7,root8;
	private Scene scene0,scene1,scene2,scene3,scene4,scene5,scene6,scene7,scene8;
	private Stage HomeStage,HighScoreStage,GameStage,ResumeGameStage,ExitAPPStage,PauseStage,SaveGameStage,GameOverStage,AboutStage;
	
	private boolean GamePaused=false;
	public boolean UseStars=false;

	public Object getExitAppController() {
		return this.loader4.getController();
	}
	public Object getHighScoreController() {
		return this.loader3.getController();
	}
	public Object getResumeGameController() {
		return this.loader2.getController();
	}
	public Object getGameController() {
		return this.loader1.getController();
	}
	public Object getHomePageController() {
		return this.loader0.getController();
	}
	
	@Override
	public void StartNew(String InitialBallColor) throws IOException {
		// TODO Auto-generated method stub
		Object[] data=new Object[10];
		this.GameStage=new Stage();
		Parent root=this.loader1.load();
		Scene scene=new Scene(root);
		this.GameStage.setScene(scene);
		data[0]=this.loader1;
		data[1]=InitialBallColor;
		this.GameStage.setUserData(data);
		this.GameStage.show();
	}
	@Override
	public void ResumeGame(Window window) throws IOException {
		// TODO Auto-generated method stub
		if(this.scene2==null) {
			this.ResumeGameStage=(Stage) window;
			this.root2=this.loader2.load();
			this.scene2=new Scene(root2);
			this.ResumeGameStage.setScene(scene2);
			this.ResumeGameStage.show();
		}
		else {
			this.ResumeGameStage.show();
		}
	}
	@Override
	public void HighScores(Window window) throws IOException {
		// TODO Auto-generated method stub
		if(this.scene3==null) {
			this.HighScoreStage=(Stage) window;
			this.root3=this.loader3.load();
			this.scene3=new Scene(this.root3);
			this.HighScoreStage.setScene(scene3);
			this.HighScoreStage.show();
		}
		else {
			this.HomeStage.show();
		}
	}
	@Override
	public void About() throws IOException {
		// TODO Auto-generated method stub
		if(this.scene8==null) {
			this.AboutStage=new Stage();
			this.root8=this.loader8.load();
			this.scene8=new Scene(this.root8);
			this.AboutStage.setScene(scene8);
			this.AboutStage.show();
		}
		else {
			this.AboutStage.show();
		}
		this.AboutStage.setTitle("About");
	}
	@Override
	public void exitApp() throws IOException {
		// TODO Auto-generated method stub
		if(this.scene4==null) {
			this.ExitAPPStage=new Stage();
			this.root4=this.loader4.load();
			this.scene4=new Scene(this.root4);
			this.ExitAPPStage.setScene(scene4);
			this.ExitAPPStage.show();
		}
		else {
			this.ExitAPPStage.show();
		}
	}
	public void LoadHomePage(Window window) throws IOException {
		// TODO Auto-generated method stub
		if(this.scene0==null) {
			this.HomeStage=(Stage) window;
			this.root0=this.loader0.load();
			this.scene0=new Scene(this.root0);
			this.HomeStage.setScene(scene0);
			this.HomeStage.show();
		}
		else {
			this.HomeStage.show();
		}
	}
	public void GameOver(Window window) throws IOException {
		// TODO Auto-generated method stub
		if(this.root7==null) {
			this.GameOverStage=(Stage) window;
			this.root7=this.loader7.load();
			this.scene7=new Scene(this.root7);
			this.GameOverStage.setScene(scene7);
			this.GameOverStage.show();
		}
		else {
			this.GameOverStage.setScene(this.scene7);
		}
	}
	public void Pause(Window window,Stage game) throws IOException {
		if(this.scene5==null) {
			this.PauseStage=(Stage) window;
			this.root5=this.loader5.load();
			this.root5.getChildrenUnmodifiable().get(0).setUserData(game);
			this.scene5=new Scene(this.root5);
			this.scene5.getStylesheets().add(getClass().getResource("custom.css").toExternalForm());
			this.PauseStage.setScene(scene5);
			this.PauseStage.show();
		}
		else {
			this.PauseStage.show();
		}
	}
	public void SaveGameScreen(Window window,Stage game) throws IOException {
		// TODO Auto-generated method stub
		if(this.scene6==null) {
			this.SaveGameStage=(Stage) window;
			this.root6=this.loader6.load();
			this.root6.getChildrenUnmodifiable().get(0).setUserData(game);
			this.scene6=new Scene(this.root6);
			this.SaveGameStage.setScene(scene6);
			this.SaveGameStage.show();
		}
		else {
			this.SaveGameStage.show();
		}
	}
	public void closeWindow(AnchorPane pane) {
		pane.getScene().getWindow().hide();
	}
	public boolean getPausedStatus() {
		return this.GamePaused;
	}
	public void setPausedStatus(boolean b) {
		this.GamePaused=b;
	}
	public int getBestScore() throws ClassNotFoundException, IOException {
		ObjectInputStream ois=new ObjectInputStream(Files.newInputStream(Paths.get("Score.txt")));
		AllScores object =(AllScores) ois.readObject();
		return Collections.max(object.getscoreList());
	}
}

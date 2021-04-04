package application;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class PauseScreenController extends Controller {
	
	private Scene SaveGameDialog;
	private Stage GameStage=null;
	private GameController c;
	private GameState state;
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private StackPane fxmlData;
	
	public void ContinueGame() throws InterruptedException {
		Stage game=(Stage) this.fxmlData.getUserData();
		Object[] data=(Object[]) game.getUserData();
		FXMLLoader l=(FXMLLoader) data[0];
		GameController c=l.getController();
		this.c=c;
		ResumeObstacles r=new ResumeObstacles();
		Thread t2=new Thread(r);
		t2.start();
		t2.join();
		this.closeWindow(pane);
	}
	
	public void SaveGameDialog() throws IOException{
		this.SaveGameScreen(this.getcurrentStage(),this.getGameStage());
	}
	public Scene getSaveGameDialogScene() {
		return this.SaveGameDialog;
	}
	public Stage getcurrentStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
	public Stage getGameStage() {
		return (Stage) this.fxmlData.getUserData();
	}
	private class ResumeObstacles extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<c.AllObstaclesList.size();i++) {
				c.AllObstaclesList.get(i).resume();
			}
		}	
	}
	@FXML
	public void SaveThisGame() throws IOException, ClassNotFoundException {
		
		Stage game=(Stage) this.fxmlData.getUserData();
		Object[] data=(Object[]) game.getUserData();
		FXMLLoader l=(FXMLLoader) data[0];
		GameController c=l.getController();
		
		//saving state
		this.state=new GameState(c.b.getCenterX(),c.getBall().getTranslateY(),c.currentObstacle.getPosX(),c.currentObstacle.getPosY(),c.nextObstacle.getPosX(),c.nextObstacle.getPosY(),c.AllObstaclesList.get(c.AllObstaclesList.size()-1).getPosY(),c.getCurrentScore(),c.currentObstacle.getClass(),c.nextObstacle.getClass(),c.AllObstaclesList.get(c.AllObstaclesList.size()-1).getClass(),c.ObstacleCounter,c.getBall().getFill().toString(),c.getScrollPane().getVvalue(),c.isInvisible,c.invisibleCounter);
//		System.out.println(c.currentObstacle);
//		System.out.println(c.nextObstacle);

		//saving game in list
		if(Files.exists(Paths.get("SavedGames.txt"))) {
			ObjectInputStream istream=new ObjectInputStream(Files.newInputStream(Paths.get("SavedGames.txt")));
			AllSavedGames obj=(AllSavedGames) istream.readObject();
			obj.savedgamelist.add(state);
			
			ObjectOutputStream ostream=new ObjectOutputStream(Files.newOutputStream(Paths.get("SavedGames.txt")));
			ostream.writeObject(obj);
		}
		else {
			this.createFile();
		}
	}
	private void createFile() throws IOException {
		File file=new File("SavedGames.txt"); 
		ObjectOutputStream ostream=new ObjectOutputStream(new FileOutputStream(file));
		AllSavedGames as=new AllSavedGames();
		as.savedgamelist.add(this.state);
		ostream.writeObject(as);
	}
}

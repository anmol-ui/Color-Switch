package application;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameOver extends Controller implements Initializable {
	FadeTransition ft;
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private Button restart;
	
	@FXML
	private Button continueButton;
	
	@FXML
	private Label StarsAvailable;
	
	@FXML
	private Label BestScore;
	
	@FXML
	private Label FinalScore;
	
	@FXML
	private TextArea textfield;
	
	public boolean canContinue=false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		//animation
		this.fade();
	}
	public void fade() {
		ft=new FadeTransition();
		ft.setNode(restart);
		ft.setDuration(Duration.seconds(1));
		ft.setToValue(0);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.play();
	}
	public void onHover() {
		ft.stop();
		ft.setToValue(0.55);
	}
	@FXML
	public void setScore() throws NumberFormatException, IOException, ClassNotFoundException {
		
		Object[] data=(Object[]) this.anchorPane.getScene().getWindow().getUserData();
		String ans=(String) data[9];
		int starsInthisRound=(int) data[8];
		this.FinalScore.setText(ans);
					
		//serializing total stars
		Stars st=null;
		if(Files.size(Paths.get("1.Save"))!=0) {
			ObjectInputStream ois=new ObjectInputStream(Files.newInputStream(Paths.get("1.Save")));
			st=(Stars) ois.readObject();
		}
		else {
			//Initial case
			st=new Stars();
			st.starsCollected=0;
		}
		
		st.starsCollected+=starsInthisRound;
		ObjectOutputStream oos=new ObjectOutputStream(Files.newOutputStream(Paths.get("1.Save")));
		oos.writeObject(st);
		
		if(this.getBestScore()>Integer.parseInt(this.FinalScore.getText())) {
			this.BestScore.setText(Integer.toString(this.getBestScore()));
		}
		else {
			this.BestScore.setText(this.FinalScore.getText());
		}
		
		this.StarsAvailable.setText(Integer.toString(st.starsCollected));
//		if(starsInthisRound>0) {
//			KeyFrame kf=new KeyFrame(Duration.seconds(0.2),(e->{
//				String s=this.StarsAvailable.getText();
//				int temp=Integer.parseInt(s);
//				if(temp==-1) {
//					temp=-1;
//				}
//				this.StarsAvailable.setText(Integer.toString(temp+1));
//			}));
//			Timeline timeline=new Timeline(kf);
//			timeline.setCycleCount(starsInthisRound);
//			timeline.play();
//		}
	}
	@FXML
	public void GoToHome() throws IOException, ClassNotFoundException {
		
		//Serialization of All Scores
		if(Files.exists(Paths.get("Score.txt"))) {
			ObjectInputStream ois=new ObjectInputStream(Files.newInputStream(Paths.get("Score.txt")));
			AllScores obj=(AllScores) ois.readObject();
			obj.getscoreList().add(Integer.parseInt(this.FinalScore.getText()));
			
			ObjectOutputStream oos=new ObjectOutputStream(Files.newOutputStream(Paths.get("Score.txt")));
			oos.writeObject(obj);
		}
		else {
			File file=new File("Score.txt");
			ObjectOutputStream oos2=new ObjectOutputStream(new FileOutputStream(file));
			AllScores obj=new AllScores(new ArrayList<Integer>());
			obj.score=Integer.parseInt(this.FinalScore.getText());
			obj.scoreList.add(obj.score);
			oos2.writeObject(obj);
		}
	
		this.anchorPane.getScene().getWindow().hide();
		this.LoadHomePage(new Stage());
	}
	@FXML
	public void ContinueGame() throws IOException, ClassNotFoundException {
//		this.UseStars=true;
		if(Integer.parseInt(this.StarsAvailable.getText())>=10) {
			//enable button
			this.canContinue=true;
			
			//update stars file
			this.updateStarsInDatabase();
			
			//continue
			Stage game=(Stage) this.anchorPane.getScene().getWindow();
			Object[] data=(Object[]) game.getUserData();
			FXMLLoader l=(FXMLLoader) data[0];
			Parent root=l.getRoot();
			game.setScene(root.getScene());
		
			GameController c=l.getController();
			c.BringBackBall();
			c.ObstacleHit=false;
		}
		else {
			//disable button
			this.canContinue=false;
			//can't continue
		}
	}
	public void popup() {
		this.textfield.setStyle("-fx-control-inner-background:#000000; -fx-font-family: Consolas; -fx-highlight-fill: #00ff00; -fx-highlight-text-fill: #000000; -fx-text-fill: #00ff00; ");

		if(Integer.parseInt(this.StarsAvailable.getText())>=10) {
			this.textfield.setText("Continue using stars");
		}
		else {
			this.textfield.setText("Atleast 10 stars required to continue");
		}
		this.textfield.autosize();
		
		this.fadePopUp();
	}
	private void fadePopUp() {
		FadeTransition ft=new FadeTransition();
		ft.setNode(textfield);
		ft.setDuration(Duration.seconds(3));
		ft.setToValue(1);
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
	}
	private void updateStarsInDatabase() throws IOException, ClassNotFoundException {
		ObjectInputStream ois=new ObjectInputStream(Files.newInputStream(Paths.get("1.Save")));
		Stars stars=(Stars) ois.readObject();
		stars.starsCollected-=10;
		
		ObjectOutputStream oos=new ObjectOutputStream(Files.newOutputStream(Paths.get("1.Save")));
		oos.writeObject(stars);
	}
	@FXML
	public void restartGame() throws IOException {
		this.anchorPane.getScene().getWindow().hide();
		this.StartNew("00a8f3");
	}
}

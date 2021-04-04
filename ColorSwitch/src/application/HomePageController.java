package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
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
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;




public class HomePageController extends Controller implements Initializable {
	
	@FXML
	private ComboBox combobox;	
	
	@FXML
	private ImageView star1;
	
	@FXML
	private ImageView star2;
	
	@FXML
	private ImageView circle1;
	
	@FXML
	private ImageView circle2;
	
	@FXML
	private ImageView circle3;
	
	@FXML
	private ImageView topscoreBtn;
	
	@FXML
	private ImageView ColorSwitchImg;
	
	@FXML
	private Button exit;
	
	private Button podium;
	
	@FXML
	private Shape yellow;
	
	@FXML
	private Shape pink;
	
	@FXML
	private Shape purple;
	
	@FXML
	private Shape blue;
		
	@FXML 
	AnchorPane pane;
	
	@FXML
	private javafx.scene.control.TextArea t1;
	
	private String Ballcolor="00a8f3";
	private ArrayList<String> list = new ArrayList<String>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		combobox.getItems().add("Blue");
		combobox.getItems().add("Yellow");
		combobox.getItems().add("Pink");
		combobox.getItems().add("Purple");
		this.AnimateHomeScreen();
		this.fade();
	}
	public void onClickingExit() throws IOException {
		this.exitApp();
	}
	public void onClickHighScore() throws IOException {
		this.HighScores(this.getCurrentStage());
	}
	@FXML
	public void onClickResume() throws IOException {
		this.ResumeGame(this.getCurrentStage());
	}
	@FXML
	public void onClickAbout() throws IOException {
		this.About();
	}
	public void StartNewGame() throws IOException {
		this.closeWindow(pane);
		this.StartNew(this.getInitialBallColor());
	}
	public Stage getCurrentStage() {
		return (Stage) this.pane.getScene().getWindow();
	}
	public void AnimateHomeScreen() {
		KeyFrame kf=new KeyFrame(Duration.millis(10), new TimeHandler());

		Timeline timeline=new Timeline(kf);
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}

	private class TimeHandler implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent event) throws NullPointerException {
			star1.setRotate(star1.getRotate()+1);
			star2.setRotate(star2.getRotate()-1);
			circle1.setRotate(circle1.getRotate()+1);
			circle2.setRotate(circle2.getRotate()-1);
			circle3.setRotate(circle3.getRotate()+1);
		}

	}
	public void fade() {
		FadeTransition ft=new FadeTransition();
		ft.setNode(topscoreBtn);
		ft.setDuration(Duration.seconds(1));
		ft.setToValue(0);
		ft.setCycleCount(Animation.INDEFINITE);
		ft.play();
	}
	public void SelectColor() {
		String str=(String) this.combobox.getValue();
		this.ColorSwitchImg.setOpacity(0);
		if(str.contentEquals("Pink")) {
			this.pink.setOpacity(1);
			this.purple.setOpacity(0);
			this.yellow.setOpacity(10);
			this.blue.setOpacity(0);
			this.Ballcolor="ffaec8";
		}
		else if(str.contentEquals("Yellow")) {
			this.pink.setOpacity(0);
			this.purple.setOpacity(0);
			this.yellow.setOpacity(1);
			this.blue.setOpacity(0);
			this.Ballcolor="fff200";
		}
		else if(str.contentEquals("Purple")) {
			this.pink.setOpacity(0);
			this.purple.setOpacity(1);
			this.yellow.setOpacity(0);
			this.blue.setOpacity(0);
			this.Ballcolor="983ae9";
		}
		else if(str.contentEquals("Blue")) {
			this.pink.setOpacity(0);
			this.purple.setOpacity(0);
			this.blue.setOpacity(1);
			this.Ballcolor="00a8f3";
		}
	}
	public String getInitialBallColor() {
		return this.Ballcolor;
	}
}

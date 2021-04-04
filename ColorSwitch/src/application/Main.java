package application;
	

import java.awt.Polygon;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
	boolean isgameover=false;
	public Stage MainStage;
	public static MediaPlayer mediaPlayer;
	@Override
	public void init() {
		
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Controller controller=new Controller();
			controller.LoadHomePage(primaryStage);
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
//		this.addMusic();
	}
	@Override
	public void stop() {
	}
	public static void main(String[] args) {
		launch(args);
	}
	public void addMusic() {
        Media sound = new Media(getClass().getResource("media.wav").toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setStartTime(Duration.seconds(0));
        mediaPlayer.setStopTime(Duration.seconds(50));
        mediaPlayer.play();
    }
}

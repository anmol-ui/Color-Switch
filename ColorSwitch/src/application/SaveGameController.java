package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Obstacles.Obstacles;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SaveGameController extends Controller {
	private ArrayList<FXMLLoader> SavedGamesList=new ArrayList<>();
	
	@FXML
	private AnchorPane SaveGamepane;
	
	@FXML
	private StackPane fxmlData;
	
	public Stage getcurrentStage() {
		return (Stage) this.SaveGamepane.getScene().getWindow();
	}
	@FXML
	public void onClick(MouseEvent event) throws IOException, ClassNotFoundException {
		Button button=(Button) event.getSource();
		if(button.getText().contentEquals("CONFIRM")) {
			this.closeWindow(SaveGamepane);
			this.getGameStage().close();
			this.LoadHomePage(new Stage());
		}
		else if(button.getText().contentEquals("CANCEL")) {
			this.Pause(this.getcurrentStage(),this.getGameStage());
		}
	}
	public Stage getGameStage() {
		return (Stage) this.fxmlData.getUserData();
	}
	public ArrayList<FXMLLoader> getSavedGamesList(){
		return this.SavedGamesList;
	}
}

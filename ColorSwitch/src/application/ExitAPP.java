package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ExitAPP extends Controller {
	
	@FXML
	private AnchorPane RootPane;
	
	public ExitAPP() throws IOException {

	}
//	public boolean StageAliveOrNot() {
//		if(this.stage.isShowing()) {
//			return true;
//		}
//		else {
//			return false;
//		}
//	}
	public void onClick(MouseEvent event) {
		Button btn=(Button) event.getSource();
		if(btn.getText().contentEquals("OKAY")) {
			System.exit(0);
		}
		else {
			this.closeWindow(RootPane);
		}
	}
}

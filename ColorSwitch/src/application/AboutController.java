package application;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;

public class AboutController extends Controller {

    @FXML
    private Group g1;
    @FXML
    private Group g2;


    @FXML
    public void initialize(){
    	
        Rotate rotate=new Rotate();
        rotate.setPivotX(g1.getBoundsInLocal().getCenterX());
        rotate.setPivotY(g1.getBoundsInLocal().getCenterY());
        g1.getTransforms().add(rotate);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4000), new KeyValue(rotate.angleProperty(), 400000)));
        timeline.play();

        Rotate rotate1=new Rotate();
        rotate1.setPivotX(g2.getBoundsInLocal().getCenterX());
        rotate1.setPivotY(g2.getBoundsInLocal().getCenterY());
        g2.getTransforms().add(rotate1);
        Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(4000), new KeyValue(rotate1.angleProperty(), 400000)));
        timeline1.play();
    }

}
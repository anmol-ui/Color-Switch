package application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

import com.sun.glass.ui.Application;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import javafx.scene.transform.Rotate;

public class HighScoreController extends Controller  {


    @FXML
    private ImageView m1;
    @FXML
    Label r1;
    @FXML
    Label r2;
    @FXML
    Label r3;
    @FXML
    Label r4;
    @FXML
    Label r5;
    ArrayList<Integer> list;

    // Populate the ArrayList

    @FXML
    public void initialize() throws IOException, ClassNotFoundException{
		ObjectInputStream ois=new ObjectInputStream(Files.newInputStream(Paths.get("Score.txt")));
		AllScores object =(AllScores) ois.readObject();
		this.list=object.getscoreList();
		
        this.animate(m1);
        this.fade();
        this.changescore();
    }
    private void animate(Node node) {
        ScaleTransition st=new ScaleTransition();
        st.setNode(node);
        st.setByX(0.2);
        st.setByY(0.2);
        st.setDuration(Duration.seconds(2));
        st.setCycleCount(1);
        st.play();
    }
    public void fade() {
        FadeTransition ft=new FadeTransition();
        ft.setNode(m1);
        ft.setDuration(Duration.seconds(2));
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.play();
    }
    public void onClick() throws IOException {
        this.LoadHomePage(this.m1.getScene().getWindow());


    }
    public void changescore(){
    	
        Collections.sort(list);
        if(list.size()==1) {
        	r1.setText(list.get(list.size()-1).toString());
        }
        else if(list.size()==2) {
        	r1.setText(list.get(list.size()-1).toString());
        	r2.setText(list.get(list.size()-2).toString());
        } 
        else if(list.size()==3) {
        	r1.setText(list.get(list.size()-1).toString());
        	r2.setText(list.get(list.size()-2).toString());
        	r3.setText(list.get(list.size()-3).toString());
        }       
        else if(list.size()==4) {
        	r1.setText(list.get(list.size()-1).toString());
        	r2.setText(list.get(list.size()-2).toString());
        	r3.setText(list.get(list.size()-3).toString());
        	r4.setText(list.get(list.size()-4).toString());
        } 
        else if(list.size()>=5) {
        	r1.setText(list.get(list.size()-1).toString());
        	r2.setText(list.get(list.size()-2).toString());
        	r3.setText(list.get(list.size()-3).toString());
        	r4.setText(list.get(list.size()-4).toString());
        	r5.setText(list.get(list.size()-5).toString());
        } 
    }
}

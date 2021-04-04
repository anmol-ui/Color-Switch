package application;

import java.awt.ActiveEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Obstacles.ConcentricRing;
import Obstacles.Cross;
import Obstacles.CrossInRing;
import Obstacles.DoubleCross;
import Obstacles.Obstacles;
import Obstacles.Ring;
import Obstacles.RingPattern;
import Obstacles.bars;
import Obstacles.square;
import Obstacles.triangle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ResumeGameController extends Controller implements Initializable{
	
	@FXML
	private AnchorPane pane;
	private ArrayList<GameState> list;
	private HashMap<Button,Integer> map=new HashMap<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		this.pane.setPrefHeight(1000);
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(Files.newInputStream(Paths.get("SavedGames.txt")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AllSavedGames obj = null;
		try {
			obj = (AllSavedGames) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.list=obj.savedgamelist;
		try {
			this.loadGames();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void onClickBack() throws IOException {
    	this.LoadHomePage(this.pane.getScene().getWindow());
	}
	private void loadGames() throws IOException {
		int posY=0;

		for(int i=0;i<this.list.size();i++) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Obstacles.class.getResource("nodes.fxml"));
			Parent root=loader.load();
			Button btn=(Button) root.getChildrenUnmodifiable().get(1);
			
			btn.setText("G A M E   "+(i+1)+"            SCORE : "+this.list.get(i).score);
			btn.setPrefWidth(580);
			btn.setLayoutX(0);
			btn.setLayoutY(35+posY);
			btn.setTextFill(Color.RED);
			btn.setOnMouseClicked(new MouseClick());
			this.pane.getChildren().add(btn);
			this.map.put(btn,i);
			posY+=65;
		}
	}
	public Obstacles loadObstacles(Class<? extends Obstacles> type,GameController c,double y) throws IOException {
		Obstacles obj=null;
		if(type.equals(square.class)) {
			obj=new square(c.getPane(), 313, y);
			obj.load();
			obj.Animate(0,0);
		}
		else if(type.equals(triangle.class)) {
			obj=new triangle(c.getPane(), 313, y);
			obj.load();
			obj.Animate(0,0);
		}
		else if(type.equals(Ring.class)) {
			obj=new Ring(c.getPane(), 313, y,80,14,null,null,null,null);
			obj.load();
			obj.Animate(360,4);
		}
		else if(type.equals(ConcentricRing.class)) {
			obj=new ConcentricRing(c.getPane(), 313, y);
			obj.load();
		}
		else if(type.equals(bars.class)) {
			obj=new bars(c.getPane(), 313, y);
			obj.load();
			obj.Animate(0,0);
		}
		else if(type.equals(DoubleCross.class)) {
			obj=new DoubleCross(c.getPane(), 313, y);
			obj.load();
			obj.Animate(360,0);
		}
		else if(type.equals(CrossInRing.class)) {
			obj=new CrossInRing(c.getPane(), 313, y);
			obj.load();
		}
		else if(type.equals(RingPattern.class)) {
			obj=new RingPattern(c.getPane(), 313, y);
			obj.load();
		}	
		else if(type.equals(Cross.class)) {
			obj=new Cross(c.getPane(), 313, y);
			obj.load();
			obj.Animate(-400000, 4000);
		}
		return obj;
	}
	public class MouseClick implements EventHandler<MouseEvent>{

		@Override
		public void handle(MouseEvent arg0) {
			Button button=(Button) arg0.getSource();
			int key=map.get(button);
			GameState state=list.get(key);
			
			FXMLLoader ld = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
			Object[] data=new Object[10];
			Parent r = null;
			try {
				r = ld.load();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			GameController c=ld.getController();
			this.setUpGame(c,state);
			
			data[0]=ld;
			data[1]=state.ballColor;
			
			
			Stage stage=new Stage();
			stage.setUserData(data);
			stage.setScene(new Scene(r));
			pane.getScene().getWindow().hide();
			stage.show();
		}
		private void setUpGame(GameController c,GameState state) {
			c.getBall().setTranslateY(state.ballPosY); //135
			c.getScrollPane().setVvalue(state.scrollpanePosY+0.001);
			Class<? extends Obstacles> obst1=state.currentObstType;
			Class<? extends Obstacles> obst2=state.nextObstType;
			Class<? extends Obstacles> obst3=state.lastObstType;
			if(state.obstCounter<=2) {
				c.starCounter=state.score;
				c.setScore(c.starCounter);
			}
			else {
				try {
					Obstacles crrnt=loadObstacles(obst1,c,state.currentObstY);
					crrnt.removeStar();
					Obstacles nxt=null;
					Obstacles lst=null;
					if(state.nextObstType.equals(bars.class)) {
						nxt=loadObstacles(obst2,c,state.nextObstY+100);
						lst=loadObstacles(obst3,c,state.lastObstY+100);
						c.highestObstPos=nxt.getPosY()+200;
					}
					else {
						nxt=loadObstacles(obst2,c,state.nextObstY);
						lst=loadObstacles(obst3,c,state.lastObstY);
						c.highestObstPos=nxt.getPosY()+100;
					}
					if(state.inv) {
						c.getBall().setStrokeWidth(2); c.getBall().setStroke(Color.BLACK); c.getBall().setRadius(10);
						c.isInvisible=state.inv; c.invisibleCounter=state.invCount;
					}
					c.currentObstacle=crrnt;
					c.nextObstacle=nxt;
					c.ObstacleCounter=4;
					c.AllObstaclesList.add(crrnt);
					c.AllObstaclesList.add(nxt);
					c.AllObstaclesList.add(lst);
	//				System.out.println(c.highestObstPos+" "+crrnt.getPosY()+" "+nxt.getPosY()+" "+c.getBallPosY());
					c.starCounter=state.score;
					c.loadColorSwitcher(313,c.highestObstPos+200);
					c.setScore(c.starCounter);
	
	//				c.switcherPos=c.highestObstPos+200;
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}
	}
}

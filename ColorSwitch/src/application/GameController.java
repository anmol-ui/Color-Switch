package application;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import com.sun.javafx.event.EventHandlerManager;

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
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GameController extends Controller implements Initializable,Serializable{
	private boolean ReachedLine=false;
	private ColorSwitcher switcher;
	private String[] colorList= {"ffaec8","00a8f3","fff200","983ae9"};
	
	public int ObstacleCounter=1;
	public ArrayList<Obstacles> AllObstaclesList=new ArrayList<>();
	public Obstacles nextObstacle;
	public Obstacles currentObstacle;
	public boolean NewGame;
	public int starCounter=0;

	private ArrayList<Obstacles> obstacleList=new ArrayList<>();
	private Ball ballObj;
	private Random random=new Random();
	private boolean setUpDone=false;
	
	public boolean isInvisible=false;
	public int invisibleCounter=0;
	
	@FXML
	private Pane Pane;
	
	@FXML
	private AnchorPane anchorpane;
	
	@FXML
	private Circle ball;
	
	@FXML
	private Polygon star;
	
	@FXML
	private Label score;
	
	@FXML
	private Line line;
	
	@FXML
	private ImageView combo;
	
	@FXML
	private ScrollPane scrollPane;
	
	Timeline timeline,timeline2;
	double highestObstPos;
	double switcherPos;
	Bounds b,scrollBound;
	boolean ObstacleHit=false;
	
	public int getCurrentScore() {
		return this.starCounter;
	}
	public Pane getPane() {
		return this.Pane;
	}
	public ScrollPane getScrollPane() {
		return this.scrollPane;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        Pane.setPrefHeight(100000);
        scrollPane.setVvalue(1);
			try {
				Obstacles ring=new Ring(this.Pane,313,494,80,14,null,null,null,null);
				ring.Animate(360,4);
				Obstacles obst2=new ConcentricRing(this.Pane,313,894);
				ring.load();
				obst2.load();
				

				this.AllObstaclesList.add(ring);
				this.AllObstaclesList.add(obst2);
				this.highestObstPos=494;
				
				this.loadColorSwitcher(313,694);
				this.switcherPos=694;
				
				this.currentObstacle=ring;
				this.nextObstacle=ring;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ///////
	}
	private void createObstacles(double y) throws IOException {
		// TODO Auto-generated method stub
		Obstacles ring=new Ring(this.Pane,313,y,80,14,null,null,null,null);
		ring.Animate(360,4);
		Obstacles obst2=new ConcentricRing(this.Pane,313,y);
		if(ObstacleCounter>=20) {
			obst2.Animate(360, 0);
		}
		Obstacles obst3=new Cross(this.Pane,313,y);
		obst3.Animate(-400000, 4000);
		Obstacles obst4=new square(this.Pane, 313, y);
		obst4.Animate(0, 0);
		Obstacles obst5=new triangle(this.Pane, 313, y);
		if(ObstacleCounter>=20) {
			obst5.Animate(0, 0);
		}
		obst5.Animate(0, 0);
		Obstacles obst6=new bars(this.Pane,313,y);
		if(ObstacleCounter>=20) {
			obst6.Animate(0, 0);
		}
		obst6.Animate(0, 0);
		Obstacles obst7=new CrossInRing(this.Pane,313,y);
		obst7.removeStar();
		Obstacles obst8=new DoubleCross(this.Pane,313,y);
		if(ObstacleCounter>=20) {
			obst8.Animate(360, 0);
		}
		obst8.Animate(360, 0);
		Obstacles obst9=new RingPattern(this.Pane,313,y);
		if(ObstacleCounter>=20) {
			obst9.Animate(360, 0);
		}
		if(this.AllObstaclesList.size()<3) {
			this.obstacleList.add(ring);
		}
		if(this.AllObstaclesList.size()>5) {
			this.obstacleList.add(obst2);
		}
		if(this.AllObstaclesList.size()<=4) {
	    	this.obstacleList.add(obst3);
		}
		if(this.AllObstaclesList.size()<5) {
	    	this.obstacleList.add(obst4);
		}
		if(this.AllObstaclesList.size()>7) {
	    	this.obstacleList.add(obst5);
		}
	    if(this.AllObstaclesList.size()>4) {
	    	this.obstacleList.add(obst6);
	    }
	    if(this.AllObstaclesList.size()>8) {
	    	this.obstacleList.add(obst7);
	    }
	    if(this.AllObstaclesList.size()>3) {
	    	this.obstacleList.add(obst8);
	    }
	    if(this.AllObstaclesList.size()>10) {
	    	this.obstacleList.add(obst9);
	    }

		this.highestObstPos=y-400;
		this.loadObstacles();	
	}
	private void loadObstacles() {
		int index=this.random.nextInt(this.obstacleList.size());
		while(this.obstacleList.get(index).getClass().equals(currentObstacle.getClass())) {
			index=this.random.nextInt(this.obstacleList.size());
		}
		this.obstacleList.get(index).load();
		this.AllObstaclesList.add(this.obstacleList.get(index));
		this.obstacleList.clear();
	}
	public void loadColorSwitcher(double x,double y) {
		this.switcher=new ColorSwitcher(this.Pane,x,y,this.anchorpane);	
		this.switcherPos=y;
	}
	@FXML
	public void onKeyPressed(KeyEvent keyEvent) throws IOException, InterruptedException {
		KeyCode key=keyEvent.getCode();
		
		if(key==KeyCode.P) {
			this.pauseGame();
		}
	}
	public void pauseGame() throws IOException, InterruptedException {
		PauseObstacles p=new PauseObstacles();
		p.start();
		p.join();
//		System.out.println(this.scrollPane.getVvalue());
		if(timeline!=null) {
			timeline.setOnFinished(e->{timeline.pause();});
		}
		if(timeline2!=null) {
			timeline2.setOnFinished(e->{timeline2.pause();});
		}
		this.Pause(new Stage(),this.getcurrentStage());
	}
	public Stage getcurrentStage() {
		return (Stage) this.anchorpane.getScene().getWindow();
	}
	public void Up() {
		this.UseStars=false;
		if(this.timeline2!=null) {
			this.timeline2.setOnFinished(null);
		}
			KeyFrame kf=new KeyFrame(Duration.millis(0.1), new MoveUp());
			this.timeline=new Timeline(kf);
			timeline.setCycleCount(40);
			this.timeline.play();
			this.timeline.setOnFinished(e-> this.timeline.play());
	}

	private class MoveUp implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent event) throws NullPointerException {
			b=Pane.sceneToLocal(ball.localToScene(ball.getBoundsInLocal()));
			if(b.getCenterY()>highestObstPos) {
				if(!nextObstacle.getClass().equals(bars.class)) {
					UpdateScore update=new UpdateScore();
					starCounter++;
					invisibleCounter++;
					if(invisibleCounter>12) {
						invisibleCounter=0;
						isInvisible=false;
						ball.setStrokeWidth(0);
						ball.setStroke(null);
						ball.setRadius(8);
						switchColor();
					}
					if(invisibleCounter==10) {
//						combo();
						invisibleBall();
					}
					update.run();
					nextObstacle.removeStar();
				}
				try {
					nextObstacle=AllObstaclesList.get(ObstacleCounter);
					currentObstacle=AllObstaclesList.get(ObstacleCounter-1);
					ObstacleCounter++;
					createObstacles(highestObstPos+800);
//					System.out.println(currentObstacle.getPosY());
//					System.out.println(nextObstacle.getPosY());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(isInvisible==true) {
				if(IntersectColorSwitcher()) {
					switcher.remove();
					loadColorSwitcher(313,switcherPos+400);
				}
			}
			else {
				if(IntersectColorSwitcher()) {
					switchColor();
					switcher.remove();
					loadColorSwitcher(313,switcherPos+400);
				}
				if(nextObstacle.HitObstacle(ball)||currentObstacle.HitObstacle(ball)) {
					ObstacleHit=true;
					shakeStage();
				}
			}
			if(ball.getBoundsInParent().getCenterY()<=line.getBoundsInParent().getCenterY()&&ReachedLine==false) {
				ReachedLine=true;
				this.bringScreenDown();
			}
			else if(ball.getBoundsInParent().getCenterY()>line.getBoundsInParent().getCenterY()) {
				ball.setTranslateY(ball.getTranslateY()-0.1);
				ballObj.setPosY(ball.getTranslateY()-0.1);
			}
			else {
				this.bringScreenDown();
			}
		}
		public void bringScreenDown() {
			scrollBound=Pane.sceneToLocal(scrollPane.localToScene(scrollPane.getBoundsInLocal()));
			scrollPane.setVvalue(scrollPane.getVvalue()-0.000001);
		}
	}
	
	public void fall() {
		this.timeline.setOnFinished(null);
		KeyFrame kf=new KeyFrame(Duration.millis(0.1), new MoveDown());
		this.timeline2=new Timeline(kf);
		timeline2.setCycleCount(40);
		this.timeline2.play();
		this.timeline2.setOnFinished(e-> this.timeline2.play());
	}

	private class MoveDown implements EventHandler<ActionEvent>{
		
		public void handle(ActionEvent event) throws NullPointerException {
			if(nextObstacle.HitObstacle(ball)||currentObstacle.HitObstacle(ball)) {
				ObstacleHit=true;
				shakeStage();
			}
			if(ObstacleHit==false) {
				ball.setTranslateY(ball.getTranslateY()+0.1);
			}
		}
	}
	
	public void CloseGame() {
		this.anchorpane.getScene().getWindow().hide();
	}
	public boolean IntersectColorSwitcher() {
		if(ball.getBoundsInParent().getCenterY()<=switcher.getYinAnchor()) {
			return true;
		}
		else {
			return false;
		}
	}
	public void switchColor() {
		if(nextObstacle.getClass().equals(triangle.class)) {
			ball.setFill(Paint.valueOf(this.colorList[this.random.nextInt(3)]));
		}
		else {
			ball.setFill(Paint.valueOf(this.colorList[this.random.nextInt(4)]));
		}
	}
	public double getBallPosX() {
		return ball.getBoundsInParent().getCenterX();
	}
	public double getBallPosY() {
		return ball.getBoundsInParent().getCenterY();
	}
	int x = 0;
	int y = 0;
	public void shakeStage() {
			this.DestroyBall();
        	this.timeline.pause();
        	if(timeline2!=null) {
        		this.timeline2.pause();
        	}
        	this.transferScore();
	        Timeline timelineX = new Timeline(new KeyFrame(Duration.seconds(0.1), new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent t) {
	                if (x == 0) {
	                    Pane.setTranslateX((Pane.getTranslateX() + 20));
	                    x = 1;
	                } else {
	                    Pane.setTranslateX((Pane.getTranslateX() - 20));
	                    x = 0;
	                }
	            }
	        }));

	        timelineX.setCycleCount(10);
	        timelineX.setAutoReverse(false);
	        timelineX.play();
	        timelineX.setOnFinished(e->{
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						try {
							GameOver(getcurrentStage());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				});
	        });
	}
	public void setUpGame() {
		Stage stage=(Stage) this.anchorpane.getScene().getWindow();
		Object[] data=(Object[]) stage.getUserData();
		if(this.setUpDone==false) {
			this.setInitialBallColor();
		}
		this.ballObj=new Ball(ball.getLayoutX(),ball.getTranslateY(),ball.getFill().toString());
		data[2]=this.ballObj;
		data[3]=this.currentObstacle;
		data[4]=this.nextObstacle;

	}
	private void setInitialBallColor() {
		Object[] data=(Object[]) this.getcurrentStage().getUserData();
		String color=(String) data[1];
		this.ball.setFill(Paint.valueOf(color));
		this.setUpDone=true;
	}
	private class UpdateScore extends Thread{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			int num=Integer.parseInt(score.getText());
			String newScore=Integer.toString(num+1);
			score.setText(newScore);
		}	
	}
	public void setScore(int s) {
		this.score.setText(Integer.toString(s));
	}
	private class PauseObstacles extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=0;i<AllObstaclesList.size();i++) {
				AllObstaclesList.get(i).pause();
			}
		}	
	}
	private void DestroyBall() {
		FadeTransition ft=new FadeTransition();
		ft.setNode(this.ball);
		ft.setToValue(0);
		ft.setDuration(Duration.seconds(0.5));
		ft.setCycleCount(1);
		ft.play();
	}
	public void BringBackBall() {
		this.ball.setTranslateY(this.ball.getTranslateY()+100);
		this.ball.setOpacity(1);
		this.timeline.pause();
		if(this.timeline2!=null) {
			this.timeline2.pause();
		}
	}
	private void transferScore() {
		Object[] data=(Object[]) this.getcurrentStage().getUserData();
		data[9]=this.score.getText();
		data[8]=this.starCounter;
		this.starCounter=0;
	}
	public Circle getBall() {
		return this.ball;
	}
	public void invisibleBall() {
		this.ball.setFill(Paint.valueOf("#3f3c3f"));
		this.ball.setStroke(Color.BLACK);
		this.ball.setStrokeWidth(2);
		this.ball.setRadius(10);
		this.isInvisible=true;
	}
	public void combo() {
		this.combo.setScaleX(0);
		this.combo.setScaleY(0);

		FadeTransition ft=new FadeTransition();
		ft.setNode(this.combo);
		ft.setToValue(1);
		ft.setDuration(Duration.seconds(3));
		ft.setCycleCount(2);
		ft.setAutoReverse(true);
		ft.play();
		
		ScaleTransition st=new ScaleTransition();
		st.setNode(this.combo);
		st.setToX(1);
		st.setToY(1);
		st.setDuration(Duration.seconds(3));
		st.setCycleCount(2);
		st.setAutoReverse(true);
		st.play();
	}
}

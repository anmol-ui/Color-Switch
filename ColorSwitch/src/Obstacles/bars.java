package Obstacles;

import java.io.IOException;

import javafx.animation.PathTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class bars extends Obstacles {
	private Rectangle r1,r2,r3,r4;
	private Rectangle r11,r22,r33,r44;
	private Line l1;
	
	private PathTransition transition,transition1,transition2,transition3,transition4,transition5,transition6,transition7;
	
	public bars(Pane pane,double x,double y) throws IOException {
		this.x=x-0;
		this.y=y-200;
		this.pane=pane;
		
		Parent r=new FXMLLoader(getClass().getResource("nodes.fxml")).load();
		this.star=(Polygon) r.getChildrenUnmodifiable().get(0);
		star.setLayoutY(this.y);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("nodes.fxml"));
		Parent root=loader.load();
		this.l1=(Line) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-5);
		
		this.r1=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-6); this.r1.setFill(Paint.valueOf("00a8f3"));
		this.r2=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-7);
		this.r3=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-8);
		this.r4=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-9);
		
		this.r11=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-1);
		this.r22=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-2); this.r22.setFill(Paint.valueOf("00a8f3"));
		this.r33=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-3);
		this.r44=(Rectangle) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-4);
			
		this.r1.setLayoutY(this.y);
		this.r2.setLayoutY(this.y);
		this.r3.setLayoutY(this.y);
		this.r4.setLayoutY(this.y);
		
		this.r11.setLayoutY(this.y);
		this.r22.setLayoutY(this.y);
		this.r33.setLayoutY(this.y);
		this.r44.setLayoutY(this.y);

	}
	@Override
	public void Animate(double angle, double s) {
		// TODO Auto-generated method stub
        transition=new PathTransition();
        transition.setNode(r1);
        transition.setDuration(Duration.millis(2500));
        transition.setPath(l1);
        transition.setCycleCount(PathTransition.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
        
        transition1=new PathTransition();
        transition1.setNode(r2);
        transition1.setDuration(Duration.millis(2500));
        transition1.setPath(l1);
        transition1.setCycleCount(PathTransition.INDEFINITE);
        transition1.setAutoReverse(true);
        transition1.play();
        
        transition2=new PathTransition();
        transition2.setNode(r3);
        transition2.setDuration(Duration.millis(2500));
        transition2.setPath(l1);
        transition2.setCycleCount(PathTransition.INDEFINITE);
        transition2.setAutoReverse(true);
        transition2.play();
        
        transition3=new PathTransition();
        transition3.setNode(r4);
        transition3.setDuration(Duration.millis(2500));
        transition3.setPath(l1);
        transition3.setCycleCount(PathTransition.INDEFINITE);
        transition3.setAutoReverse(true);
        transition3.play();
        
        transition4=new PathTransition();
        transition4.setNode(r11);
        transition4.setDuration(Duration.millis(2500));
        transition4.setPath(l1);
        transition4.setCycleCount(PathTransition.INDEFINITE);
        transition4.setAutoReverse(true);
        transition4.playFrom(Duration.millis(2500));
        
        transition5=new PathTransition();
        transition5.setNode(r22);
        transition5.setDuration(Duration.millis(2500));
        transition5.setPath(l1);
        transition5.setCycleCount(PathTransition.INDEFINITE);
        transition5.setAutoReverse(true);
        transition5.playFrom(Duration.millis(2500));
        
        transition6=new PathTransition();
        transition6.setNode(r33);
        transition6.setDuration(Duration.millis(2500));
        transition6.setPath(l1);
        transition6.setCycleCount(PathTransition.INDEFINITE);
        transition6.setAutoReverse(true);
        transition6.playFrom(Duration.millis(2500));
        
        transition7=new PathTransition();
        transition7.setNode(r44);
        transition7.setDuration(Duration.millis(2500));
        transition7.setPath(l1);
        transition7.setCycleCount(PathTransition.INDEFINITE);
        transition7.setAutoReverse(true);
        transition7.playFrom(Duration.millis(2500));
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.pane.getChildren().add(this.r1);
		this.pane.getChildren().add(this.r2);
		this.pane.getChildren().add(this.r3);
		this.pane.getChildren().add(this.r4);
		
		this.pane.getChildren().add(this.r11);
		this.pane.getChildren().add(this.r22);
		this.pane.getChildren().add(this.r33);
		this.pane.getChildren().add(this.r44);
	}

	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.pane.getChildren().remove(this.star);	
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.transition.pause();
		this.transition1.pause();
		this.transition2.pause();
		this.transition3.pause();
		this.transition4.pause();
		this.transition5.pause();
		this.transition6.pause();
		this.transition7.pause();
		
		this.transition.pause();
		this.transition1.pause();
		this.transition2.pause();
		this.transition3.pause();
		this.transition4.pause();
		this.transition5.pause();
		this.transition6.pause();
		this.transition7.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.transition.play();
		this.transition1.play();
		this.transition2.play();
		this.transition3.play();
		this.transition4.play();
		this.transition5.play();
		this.transition6.play();
		this.transition7.play();
	}
	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		if(this.r1.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r1.getBoundsInLocal())) {
			if(this.r1.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r2.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r2.getBoundsInLocal())) {
			if(this.r2.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r3.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r3.getBoundsInLocal())) {
			if(this.r3.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r4.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r4.getBoundsInLocal())) {
			if(this.r4.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r11.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r11.getBoundsInLocal())) {
			if(this.r11.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r22.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r22.getBoundsInLocal())) {
			if(this.r22.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r33.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r33.getBoundsInLocal())) {
			if(this.r33.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r44.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.r44.getBoundsInLocal())) {
			if(this.r44.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

}

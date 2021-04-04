package Obstacles;

import java.io.IOException;
import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Path;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Cross extends Obstacles implements Serializable{
	
	private Rectangle p1,p2,p3,p4;
	Group group;
	public Timeline timeline;
	PathTransition pt;
	Circle circle;

	public Cross(Pane pane,double x,double y) throws IOException {
		this.pane=pane;
		this.x=x-20;
		this.y=y-100;
		
		Parent r=new FXMLLoader(getClass().getResource("nodes.fxml")).load();
		this.star=(Polygon) r.getChildrenUnmodifiable().get(0);
		star.setLayoutY(this.y+200);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("nodes.fxml"));
		Parent root=loader.load();
		this.group=(Group) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-10);
		
		this.group.setLayoutX(this.x);
		this.group.setLayoutY(this.y);
		
		this.p1=(Rectangle) this.group.getChildren().get(0);
		this.p2=(Rectangle) this.group.getChildren().get(1);
		this.p3=(Rectangle) this.group.getChildren().get(2);
		this.p4=(Rectangle) this.group.getChildren().get(3);
		
		this.animate(this.star);
	}
	@Override
	public void Animate(double angle, double seconds) {
		// TODO Auto-generated method stub
		  Rotate rotate=new Rotate();
	      rotate.setPivotX(this.group.getBoundsInLocal().getCenterX());
	      rotate.setPivotY(this.group.getBoundsInLocal().getCenterY());
	      this.group.getTransforms().add(rotate);
	      timeline = new Timeline(new KeyFrame(Duration.seconds(seconds), new KeyValue(rotate.angleProperty(), angle)));
	      timeline.play();
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.pane.getChildren().add(this.group);
		this.pane.getChildren().add(this.star);
	}
	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.pane.getChildren().remove(this.star);
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.timeline.pause();
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.timeline.play();
	}
	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		if(this.p1.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.p1.getBoundsInLocal())) {
			if(this.p1.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.p2.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.p2.getBoundsInLocal())) {
			if(this.p2.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.p3.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.p3.getBoundsInLocal())) {
			if(this.p3.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.p4.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.p4.getBoundsInLocal())) {
			if(this.p4.getFill().toString().contentEquals(ball.getFill().toString())) {
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
	public void scale(double x,double y) {
		this.group.setScaleX(x);
		this.group.setScaleY(y);
	}
}

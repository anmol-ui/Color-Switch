package Obstacles;

import java.io.IOException;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class square extends Obstacles {
	Group group;
	Rectangle r1,r2,r3,r4;
	Timeline timeline;
	public square(Pane pane,double x,double y) throws IOException {
		this.x=x-90;
		this.y=y-80;
		this.pane=pane;
		
		Parent r=new FXMLLoader(getClass().getResource("nodes.fxml")).load();
		this.star=(Polygon) r.getChildrenUnmodifiable().get(0);
		star.setLayoutY(this.y+160);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("nodes.fxml"));
		Parent root=loader.load();
		this.group=(Group) root.getChildrenUnmodifiable().get(root.getChildrenUnmodifiable().size()-11);
		
		this.group.setLayoutX(this.x);
		this.group.setLayoutY(this.y);

		this.r1=(Rectangle) this.group.getChildren().get(0);
		this.r2=(Rectangle) this.group.getChildren().get(1);
		this.r3=(Rectangle) this.group.getChildren().get(2);
		this.r4=(Rectangle) this.group.getChildren().get(3);
		
		this.animate(this.star);
	}
	@Override
	public void Animate(double angle, double s) {
		// TODO Auto-generated method stub
		  Rotate rotate=new Rotate();
	      rotate.setPivotX(this.group.getBoundsInLocal().getCenterX());
	      rotate.setPivotY(this.group.getBoundsInLocal().getCenterY());
	      this.group.getTransforms().add(rotate);
	      timeline = new Timeline(new KeyFrame(Duration.seconds(4000), new KeyValue(rotate.angleProperty(), 400000)));
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
		Bounds b=this.group.sceneToLocal(ball.localToScene(ball.getBoundsInLocal()));
		if(this.r1.getBoundsInParent().intersects(b)) {
			if(this.r1.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r2.getBoundsInParent().intersects(b)) {
			if(this.r2.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r3.getBoundsInParent().intersects(b)) {
			if(this.r3.getFill().toString().contentEquals(ball.getFill().toString())) {
				return false;
			}
			else {
				return true;
			}
		}
		else if(this.r4.getBoundsInParent().intersects(b)){
			if(this.r4.getFill().toString().contentEquals(ball.getFill().toString())) {
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

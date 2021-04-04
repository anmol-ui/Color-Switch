package Obstacles;

import java.io.Serializable;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public abstract class Obstacles implements Serializable {
	protected Double x,y;
	protected Polygon star;
	protected Pane pane;
	private void Serializ() {
		// TODO Auto-generated method stub

	}
	public double getPosX() {
		return this.x;
	}
	public double getPosY() {
		return this.y;
	}
	public Polygon getStar() {
		return this.star;
	}
	public Pane getPane() {
		return this.pane;
	}
	public abstract void Animate(double angle,double s);
	public abstract void load();
	public abstract void removeStar();
	public abstract void pause();
	public abstract void resume();
	public void animate(Node node) {
    	ScaleTransition st=new ScaleTransition();
    	st.setNode(node);
    	st.setByX(0.1);
    	st.setByY(0.1);
    	st.setDuration(Duration.seconds(1));
    	st.setCycleCount(Animation.INDEFINITE);
    	st.setAutoReverse(true);
    	st.play();
    }
	public abstract boolean HitObstacle(Circle ball);
}

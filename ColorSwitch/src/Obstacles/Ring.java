package Obstacles;

import java.io.IOException;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Ring extends Obstacles {
	private Circle circle;
	public Arc a1,a2,a3,a4;
	private double radius,width;
	public Timeline animation4,animation5,animation6,animation7;
	
	public Ring(Pane p,double x,double y,double r,double w,String s1,String s2, String s3,String s4) throws IOException {
		a1=new Arc();
		a2=new Arc();
		a3=new Arc();
		a4=new Arc();
		this.pane=p;
//		this.Apane=a;
		this.x=x;
		this.y=y;
		this.radius=r;
		this.width=w;
		
		Parent root=new FXMLLoader(getClass().getResource("nodes.fxml")).load();
		this.star=(Polygon) root.getChildrenUnmodifiable().get(0);
		star.setLayoutY(this.y+60);
				
		a1.setStrokeWidth(width);
		a1.setStrokeType(StrokeType.CENTERED);
		a1.setStrokeLineCap(StrokeLineCap.BUTT);
		a1.setStrokeLineJoin(StrokeLineJoin.MITER);
		a1.setStrokeMiterLimit(10);
		a1.setRadiusX(radius);
		a1.setRadiusY(radius);
		a1.setStartAngle(90);
		a1.setLength(90);
		a1.setLayoutX(x);
		a1.setLayoutY(y);
		a1.setFill(Paint.valueOf("3f3d3c"));
		if(s1==null) {
			a1.setStroke(Color.valueOf("ffaec8"));
		}
		else {
			a1.setStroke(Color.valueOf(s1));
		}
		
		a2.setStrokeWidth(width);
		a2.setStrokeType(StrokeType.CENTERED);
		a2.setStrokeLineCap(StrokeLineCap.BUTT);
		a2.setStrokeLineJoin(StrokeLineJoin.MITER);
		a2.setStrokeMiterLimit(10);
		a2.setRadiusX(radius);
		a2.setRadiusY(radius);
		a2.setStartAngle(0);
		a2.setLength(90);
		a2.setLayoutX(x);
		a2.setLayoutY(y);
		a2.setFill(Paint.valueOf("3f3d3c"));
		if(s2==null) {
			a2.setStroke(Color.valueOf("00a8f3"));
		}
		else {
			a2.setStroke(Color.valueOf(s2));
		}	
		
		a3.setStrokeWidth(width);
		a3.setStrokeType(StrokeType.CENTERED);
		a3.setStrokeLineCap(StrokeLineCap.BUTT);
		a3.setStrokeLineJoin(StrokeLineJoin.MITER);
		a3.setStrokeMiterLimit(10);
		a3.setRadiusX(radius);
		a3.setRadiusY(radius);
		a3.setStartAngle(270);
		a3.setLength(90);
		a3.setLayoutX(x);
		a3.setLayoutY(y);
		a3.setFill(Paint.valueOf("3f3d3c"));
		if(s3==null) {
			a3.setStroke(Color.valueOf("983ae9"));
		}
		else {
			a3.setStroke(Color.valueOf(s3));
		}
		
		a4.setStrokeWidth(width);
		a4.setStrokeType(StrokeType.CENTERED);
		a4.setStrokeLineCap(StrokeLineCap.BUTT);
		a4.setStrokeLineJoin(StrokeLineJoin.MITER);
		a4.setStrokeMiterLimit(10);
		a4.setRadiusX(radius);
		a4.setRadiusY(radius);
		a4.setStartAngle(180);
		a4.setLength(90);
		a4.setLayoutX(x);
		a4.setLayoutY(y);
		a4.setFill(Paint.valueOf("3f3d3c"));
		if(s4==null) {
			a4.setStroke(Color.valueOf("fff200"));
		}
		else {
			a4.setStroke(Color.valueOf(s4));
		}
		
		circle=new Circle();
		circle.setLayoutX(x);
		circle.setLayoutY(y);
		circle.setRadius(radius-5);
		circle.setFill(Color.RED);
		circle.setOpacity(0);

		this.animate(this.star);
	}
	@Override
	public void load() {
		this.pane.getChildren().add(a1);
		this.pane.getChildren().add(a2);
		this.pane.getChildren().add(a3);
		this.pane.getChildren().add(a4);
		this.pane.getChildren().add(this.star);
		this.pane.getChildren().add(this.circle);
	}
	@Override
	public void Animate(double angle,double seconds) {
        animation4 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(a2.startAngleProperty(), a2.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(a2.startAngleProperty(), a2.getStartAngle() - angle, Interpolator.LINEAR))
        );
        animation4.setCycleCount(Animation.INDEFINITE);
        animation4.play();
        animation5 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(a3.startAngleProperty(), a3.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(a3.startAngleProperty(), a3.getStartAngle() - angle, Interpolator.LINEAR))
        );
        animation5.setCycleCount(Animation.INDEFINITE);
        animation5.play();
        animation6 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(a4.startAngleProperty(), a4.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(a4.startAngleProperty(), a4.getStartAngle() - angle, Interpolator.LINEAR))
        );
        animation6.setCycleCount(Animation.INDEFINITE);
        animation6.play();
        animation7 = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(a1.startAngleProperty(), a1.getStartAngle(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(seconds), new KeyValue(a1.startAngleProperty(), a1.getStartAngle() - angle, Interpolator.LINEAR))
        );
        animation7.setCycleCount(Animation.INDEFINITE);
        animation7.play();
	}
	@Override
	public void removeStar() {
		this.pane.getChildren().remove(this.star);
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.animation4.pause();
		this.animation5.pause();
		this.animation6.pause();
		this.animation7.pause();
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.animation4.play();
		this.animation5.play();
		this.animation6.play();
		this.animation7.play();
	}
	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		if(this.circle.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.circle.getBoundsInLocal())) {
			return false;
		}
		else {
			if(this.a1.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.a1.getBoundsInLocal())) {
				if(this.a1.getStroke().toString().contentEquals(ball.getFill().toString())) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(this.a2.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.a2.getBoundsInLocal())) {
				if(this.a2.getStroke().toString().contentEquals(ball.getFill().toString())) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(this.a3.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.a3.getBoundsInLocal())) {
				if(this.a3.getStroke().toString().contentEquals(ball.getFill().toString())) {
					return false;
				}
				else {
					return true;
				}
			}
			else if(this.a4.sceneToLocal(ball.localToScene(ball.getBoundsInLocal())).intersects(this.a4.getBoundsInLocal())) {
				if(this.a4.getStroke().toString().contentEquals(ball.getFill().toString())) {
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
}

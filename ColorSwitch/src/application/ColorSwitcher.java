package application;

import java.io.Serializable;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;

public class ColorSwitcher implements Serializable  {
	private Arc a1,a2,a3,a4;	
	protected Double x,y;
	protected Pane pane;
	protected AnchorPane Apane;

	public ColorSwitcher(Pane p,double x,double y,AnchorPane a) {
		a1=new Arc();
		a2=new Arc();
		a3=new Arc();
		a4=new Arc();
		this.pane=p;
		this.Apane=a;
		this.x=x;
		this.y=y;
		
		a1.setType(ArcType.ROUND);
		a1.setRadiusX(15);
		a1.setRadiusY(15);
		a1.setStartAngle(90);
		a1.setLength(90);
		a1.setLayoutX(x);
		a1.setLayoutY(y);
		a1.setFill(Paint.valueOf("ffaec8e0"));

		a2.setType(ArcType.ROUND);
		a2.setRadiusX(15);
		a2.setRadiusY(15);
		a2.setStartAngle(0);
		a2.setLength(90);
		a2.setLayoutX(x);
		a2.setLayoutY(y);
		a2.setFill(Paint.valueOf("00a8f3"));

		a3.setType(ArcType.ROUND);
		a3.setRadiusX(15);
		a3.setRadiusY(15);
		a3.setStartAngle(270);
		a3.setLength(90);
		a3.setLayoutX(x);
		a3.setLayoutY(y);
		a3.setFill(Paint.valueOf("983ae9"));

		a4.setType(ArcType.ROUND);
		a4.setRadiusX(15);
		a4.setRadiusY(15);
		a4.setStartAngle(180);
		a4.setLength(90);
		a4.setLayoutX(x);
		a4.setLayoutY(y);
		a4.setFill(Paint.valueOf("fff200"));
		
		this.setOnPane();
		this.getYinAnchor();
	}
	private void setOnPane() {
		this.pane.getChildren().add(a1);
		this.pane.getChildren().add(a2);
		this.pane.getChildren().add(a3);
		this.pane.getChildren().add(a4);
	}
	public double getYinAnchor() {
		Bounds b=this.Apane.sceneToLocal(a1.localToScene(a1.getBoundsInLocal()));
		return b.getMaxY();
	}
	public void remove() {
		this.pane.getChildren().remove(this.a1);
		this.pane.getChildren().remove(this.a2);
		this.pane.getChildren().remove(this.a3);
		this.pane.getChildren().remove(this.a4);

	}
}

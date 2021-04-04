package application;

import java.io.Serializable;

public class Ball implements Serializable {
	
	private double posX,posY;
	private String color;
	
	public Ball(double x,double y,String c){
		this.posX=x;
		this.posY=y;
		this.color=c;
	}
	public double getPosX() {
		return this.posX;
	}
	public double getPosY() {
		return this.posY;
	}
	public String getColor() {
		return this.color;
	}
	public void setPosX(double x) {
		this.posX=x;
	}
	public void setPosY(double y) {
		this.posY=y;
	}
}

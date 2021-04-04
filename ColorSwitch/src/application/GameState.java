package application;

import java.io.Serializable;
import java.util.ArrayList;

import Obstacles.Obstacles;
import javafx.scene.shape.Circle;

public class GameState implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4928478555691923412L;
	double ballPosX,ballPosY;
	double currentObstX,currentObstY,nextObstX,nextObstY,lastObstY;
	int score,obstCounter,invCount;
	Class<? extends Obstacles> currentObstType,nextObstType,lastObstType;
	String ballColor;
	double scrollpanePosY;
	boolean inv;
	
	public GameState(double x,double y,double x1,double y1,double x2,double y2,double y3,int s,Class<? extends Obstacles> class2,Class<? extends Obstacles> class1,Class<? extends Obstacles> class0,int c,String color,double inp,boolean b,int invc) {
		this.ballPosX=x;
		this.ballPosY=y;
		this.scrollpanePosY=inp;
		this.ballColor=color;
		
		this.currentObstX=x1;
		this.currentObstY=y1;
		this.nextObstX=x2;
		this.nextObstY=y2;
		this.lastObstY=y3;
		
		this.score=s;
		this.obstCounter=c;
		
		this.currentObstType=class2;
		this.nextObstType=class1;		
		this.lastObstType=class0;
		
		this.inv=b;
		this.invCount=invc;
	}
}

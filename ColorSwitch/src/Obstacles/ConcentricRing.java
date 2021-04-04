package Obstacles;

import java.io.IOException;
import java.io.Serializable;

import javafx.geometry.Bounds;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ConcentricRing extends Obstacles implements Serializable {
	Ring ring1,ring2;
	
	public ConcentricRing(Pane p,double x,double y) throws IOException{
		this.pane=p;
		this.x=x;
		this.y=y;
		ring1=new Ring(this.pane,x,y,90,14,null,null,null,null);
		ring2=new Ring(this.pane,x,y,70,11,"00a8f3","ffaec8","fff200","983ae9");
		this.Animate(360,4);
	}

	@Override
	public void Animate(double angle,double s) {
		// TODO Auto-generated method stub
		ring1.Animate(angle,s);
		ring2.Animate(-angle,s);
	}
	@Override
	public void load() {
		ring1.load();
		ring2.load();
	}

	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.ring1.removeStar();
		this.ring2.removeStar();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.ring1.pause();
		this.ring2.pause();
		
		this.ring1.pause();
		this.ring2.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.ring1.resume();
		this.ring2.resume();
	}

	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		return this.ring1.HitObstacle(ball)||this.ring2.HitObstacle(ball);
	}
}

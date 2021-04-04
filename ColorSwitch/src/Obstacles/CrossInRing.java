package Obstacles;

import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class CrossInRing extends Obstacles {
	private Ring ring;
	private Cross cross;
	
	public CrossInRing(Pane p,double x,double y) throws IOException {
		this.pane=p;
		this.x=x;
		this.y=y;
		
		this.ring=new Ring(this.pane,x,y,110,20,null,null,null,null);
		this.cross=new Cross(this.pane, x-10, y+20);
		this.cross.scale(0.6, 0.6);
		this.Animate(-360,4);
	}
	@Override
	public void Animate(double angle, double s) {
		// TODO Auto-generated method stub
		this.ring.Animate(angle,3);
		this.cross.Animate(-400000,8000);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.ring.load();
		this.cross.load();
		this.ring.removeStar();
	}

	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.ring.removeStar();
		this.cross.removeStar();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.ring.pause();
		this.cross.pause();
		this.ring.pause();
		this.cross.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.ring.resume();
		this.cross.resume();
	}

	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		boolean a=this.ring.HitObstacle(ball);
		boolean b=this.cross.HitObstacle(ball);
		return a||b;
	}

}

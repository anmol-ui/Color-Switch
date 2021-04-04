package Obstacles;

import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class DoubleCross extends Obstacles {
	private Cross cross1,cross2;

	public DoubleCross(Pane p,double x,double y) throws IOException {
		this.pane=p;
		this.x=x;
		this.y=y;
		
		this.cross1=new Cross(this.pane, x+10, y+20);
		this.cross2=new Cross(this.pane, x-150, y+20);
		this.cross1.scale(-0.7, 0.7);
		this.cross2.scale(1.2, 1.2);
//		this.Animate(-360,4);
	}
	@Override
	public void Animate(double angle, double s) {
		// TODO Auto-generated method stub
		this.cross1.Animate(400000, 4000);
		this.cross2.Animate(400000, 4000);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		this.cross1.load();
		this.cross2.load();
	}

	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.cross1.removeStar();
		this.cross2.removeStar();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.cross1.pause();
		this.cross2.pause();
		this.cross1.pause();
		this.cross2.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.cross1.resume();
		this.cross2.resume();
	}

	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		return this.cross1.HitObstacle(ball)||this.cross2.HitObstacle(ball);
	}

}

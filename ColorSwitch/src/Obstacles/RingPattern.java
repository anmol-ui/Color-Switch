package Obstacles;

import java.io.IOException;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class RingPattern extends Obstacles{
	Ring ring1,ring2,ring3;

	public RingPattern(Pane p,double x,double y) throws IOException {
		this.pane=p;
		this.x=x;
		this.y=y-110;
		ring1=new Ring(this.pane,this.x,this.y,50,9,null,null,null,null);
		ring2=new Ring(this.pane,this.x,this.y+110,50,9,"fff200","983ae9","00a8f3","ffaec8");
		ring3=new Ring(this.pane,this.x,this.y+220,50,9,null,null,null,null);
		this.Animate(360,4);
	}

	@Override
	public void Animate(double angle, double s) {
		// TODO Auto-generated method stub
		ring3.Animate(-angle,s);
		ring2.Animate(angle,s);
		ring1.Animate(-angle,s);
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		ring1.load();
		ring2.load();
		ring3.load();
		
		this.ring1.removeStar();
		this.ring3.removeStar();
	}

	@Override
	public void removeStar() {
		// TODO Auto-generated method stub
		this.ring2.removeStar();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		this.ring1.pause();
		this.ring2.pause();
		this.ring3.pause();
		this.ring1.pause();
		this.ring2.pause();
		this.ring3.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		this.ring1.resume();
		this.ring2.resume();
		this.ring3.resume();
	}

	@Override
	public boolean HitObstacle(Circle ball) {
		// TODO Auto-generated method stub
		return this.ring1.HitObstacle(ball)||this.ring2.HitObstacle(ball)||this.ring3.HitObstacle(ball);
	}
}

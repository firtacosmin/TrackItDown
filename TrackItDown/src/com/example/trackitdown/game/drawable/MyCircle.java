package com.example.trackitdown.game.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.example.trackitdown.game.logics.levelManagers.Trajectory;

public class MyCircle {
	private static int LAST_INSTANCE = 0;
	private int _instanceNo;

	private static int INITIAL_TRAJECTORY_A = 1;
	private static int INITIAL_TRAJECTORY_B = 1;
	
	private static int DEFAULT_RADIUS = 10;

	private Paint _thePaint;
	private int _radius;
	private Trajectory _theTrajectory;
	
	private Drawable _circleImage = null;
	
	

	public MyCircle(){
		_thePaint = new Paint();
		_thePaint.setARGB(255, 120, 120, 120);
		_radius = DEFAULT_RADIUS;
		_theTrajectory = new Trajectory(INITIAL_TRAJECTORY_A, INITIAL_TRAJECTORY_B, Trajectory.DIRECTION_X.LEFT, new Point(0,0));
		_instanceNo = LAST_INSTANCE;
		MyCircle.LAST_INSTANCE++;
	}
	public MyCircle(int a, int b, Trajectory.DIRECTION_X xDir, Point initP){

		_thePaint = new Paint();
		_thePaint.setARGB(255, 120, 120, 120);
		_radius = DEFAULT_RADIUS;
		_theTrajectory = new Trajectory(a,b,xDir,initP);
		_instanceNo = LAST_INSTANCE;
		MyCircle.LAST_INSTANCE++;
	}
	
	public boolean equals(Object obj){
		try{
			if (_instanceNo == ((MyCircle)obj).hashCode()){
				return true;
			}
			return false;
		}catch(Exception ex){
			return false;
		}
	}
	
	public int hashCode() {
		return _instanceNo;
	}
	
	

	public Paint getPaint(){
		return _thePaint;
	}
	
	public void setRadius(int r){
		_radius = r;
	}
	public int getRadius(){
		return _radius;
	}
	
	public void setPaint(Paint p){
		_thePaint = p;
	}
	
	public Trajectory getTrajectory(){
		return _theTrajectory;
	}
	public void setImage(Drawable img){
		_circleImage = img;
	}

	public void draw(Canvas c){
//		if ( _circleImage != null ){
			/*recalculate rectangle position from circle center*/
			int rectX = _theTrajectory.getCurrentPoint().x - _radius;
			int rectY = _theTrajectory.getCurrentPoint().y - _radius;
			_circleImage.setBounds(rectX, 
					               rectY, 
					               rectX + 2 * _radius, 
					               rectY + 2 * _radius);
			_circleImage.draw(c);
//		}else{
//			c.drawCircle(_theTrajectory.getCurrentPoint().x, _theTrajectory.getCurrentPoint().y, _radius, _thePaint);
//			Paint p = new Paint();
//			p.setARGB(255, 0, 250, 0);
//			c.drawCircle(_theTrajectory.getCurrentPoint().x, _theTrajectory.getCurrentPoint().y, 3, p);
//		}
	}
	
	
	
	
}

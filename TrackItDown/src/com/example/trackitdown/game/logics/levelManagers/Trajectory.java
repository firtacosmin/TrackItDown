package com.example.trackitdown.game.logics.levelManagers;

import android.graphics.Point;

public class Trajectory {

	public static enum DIRECTION_X{RIGHT, LEFT};
	public static enum DIRECTION_Y{UP, DOWN};
	
	private int _a;
	private int _b;
	private DIRECTION_X _Xdir;
	private DIRECTION_Y _Ydir;
	private int _Xspeed = 3;
	private int _Yspeed = 5;
	private Point _lastPoint;
	private Point _currentPoint;
	
	public Trajectory(int a, int b, DIRECTION_X dir, Point startPoint){
		_a = a;
		_b = b;
		_Xdir = dir;
		_Ydir = DIRECTION_Y.UP;
		_lastPoint = startPoint;
		_currentPoint = startPoint;
	}
	
	public void setXDirection(DIRECTION_X d){
		_Xdir = d;
	}
	public void setYDirection(DIRECTION_Y d){
		_Ydir = d;
	}
	
	
	public int get_a() {
		return _a;
	}

	public void set_a(int _a) {
		this._a = _a;
	}

	public int get_b() {
		return _b;
	}

	public void set_b(int _b) {
		this._b = _b;
	}

	public void setXSpeed(int speed){
		_Xspeed = speed;
	}
	public int getXSpeed(){
		return _Xspeed;
	}
	public void setYSpeed(int speed){
		_Yspeed = speed;
	}
	public int getYSpeed(){
		return _Yspeed;
	}
	public DIRECTION_X getXDirection(){
		return _Xdir;
	}
	public DIRECTION_Y getYDirection(){
		return _Ydir;
	}
	public void flipXDirection(){
			if (_Xdir == DIRECTION_X.RIGHT ){
				_Xdir= DIRECTION_X.LEFT;
			}else{
				_Xdir = DIRECTION_X.RIGHT;
			}
	}
	public void flipYDirection(){
			if (_Ydir == DIRECTION_Y.UP ){
				_Ydir= DIRECTION_Y.DOWN;
			}else{
				_Ydir = DIRECTION_Y.UP;
			}
	}
	public Point getLastPoint(){
		return _lastPoint;
	}
	public Point getCurrentPoint(){
		return _currentPoint;
	}
	
	public Point getNextPoint(){
		_lastPoint = _currentPoint;
		if ( _Xdir == DIRECTION_X.RIGHT )
			_currentPoint.x += _Xspeed;
		else
			_currentPoint.x -= _Xspeed;
		if ( _Ydir == DIRECTION_Y.DOWN )
			_currentPoint.y += _Yspeed;
		else
			_currentPoint.y -= _Yspeed;
		return _currentPoint;
	}
	
	/**
	 * @desc method to calculate the next point but not to save it;
	 * @return the calculated next point
	 */
	public Point testNextPoint(){
		/*save current point*/
		Point current =  new Point(_currentPoint);
		/*calculate next point*/
		Point next = new Point(getNextPoint());
		/*restore current point*/
		_currentPoint.x = current.x;
		_currentPoint.y = current.y;
		/*return next point*/
		return next;
		
	}
	
	
}

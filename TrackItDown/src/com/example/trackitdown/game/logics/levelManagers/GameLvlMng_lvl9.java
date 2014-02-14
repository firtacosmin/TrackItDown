package com.example.trackitdown.game.logics.levelManagers;

import android.graphics.Paint;

public class GameLvlMng_lvl9 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl9_circleNumber = 1;
	private int _lvl9_speed = 1;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl9_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl9_seekTime = 100000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl9_circleRadius = 25;
	
	

	
	
	public GameLvlMng_lvl9(){
		super(true);
		_speed = _lvl9_speed;
		_circleNumber = _lvl9_circleNumber;
		_observeTime = _lvl9_observeTime;
		_seekTime = _lvl9_seekTime;
		_circleRadius = _lvl9_circleRadius;
		/*make circles blink*/
		_blinkFlag = false;
		
		/*all the circle will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 255, 255, 255);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 81, 142, 255);
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 255, 152, 73);
		_winningCircleNo = 1;
		
		generateStartRandomCircles();
		
		
		
	}

}

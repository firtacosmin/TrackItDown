package com.example.trackitdown.game.logics.levelManagers;

import android.graphics.Paint;

public class GameLvlMng_lvl3 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl3_circleNumber = 20;
	private int _lvl3_speed = 6;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl3_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl3_seekTime = 10000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl3_circleRadius = 20;
	
	

	
	
	public GameLvlMng_lvl3(){
		super(true);
		_speed = _lvl3_speed;
		_circleNumber = _lvl3_circleNumber;
		_observeTime = _lvl3_observeTime;
		_seekTime = _lvl3_seekTime;
		_circleRadius = _lvl3_circleRadius;
		
		/*all the circle will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 255, 255, 255);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 81, 142, 255);
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 255, 152, 73);
		
		
		generateStartRandomCircles();
		
		
		
	}

}

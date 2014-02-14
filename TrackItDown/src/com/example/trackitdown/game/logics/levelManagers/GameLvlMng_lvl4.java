package com.example.trackitdown.game.logics.levelManagers;

import android.graphics.Paint;

public class GameLvlMng_lvl4 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl4_circleNumber = 20;
	private int _lvl4_speed = 4;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl4_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl4_seekTime = 7000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl4_circleRadius = 20;
	
	
	

	
	
	public GameLvlMng_lvl4(){
		super(true);
		_speed = _lvl4_speed;
		_circleNumber = _lvl4_circleNumber;
		_observeTime = _lvl4_observeTime;
		_seekTime = _lvl4_seekTime;
		_circleRadius = _lvl4_circleRadius;
		/*make circles blink*/
		_blinkFlag = true;
		
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

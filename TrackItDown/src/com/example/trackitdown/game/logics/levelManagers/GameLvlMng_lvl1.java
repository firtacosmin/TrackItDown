package com.example.trackitdown.game.logics.levelManagers;

import com.example.trackitdown.R;

import android.content.Context;
import android.graphics.Paint;

public class GameLvlMng_lvl1  extends GameLvlMng_lvl {

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl1_circleNumber = 10;
	private int _lvl1_speed = 1;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl1_observeTime = 3000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl1_seekTime = 5000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl1_circleRadius = 25;
	
	

	
	
	public GameLvlMng_lvl1(Context c){
		super(true);
		_speed = _lvl1_speed;
		_circleNumber = _lvl1_circleNumber;
		_observeTime = _lvl1_observeTime;
		_seekTime = _lvl1_seekTime;
		_circleRadius = _lvl1_circleRadius;
		
		/*all the circle will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 255, 255, 255);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 0, 255, 0);
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 0, 255, 0);
		
		setWinningCircleImg(c.getResources().getDrawable(R.drawable.ball_1));
		setCircleImg(c.getResources().getDrawable(R.drawable.ball_2));
		setBackgroundImg(c.getResources().getDrawable(R.drawable.bk_1));
		
		
		generateStartRandomCircles();
		
		
		
	}

}

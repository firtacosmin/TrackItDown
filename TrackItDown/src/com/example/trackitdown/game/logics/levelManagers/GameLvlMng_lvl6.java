package com.example.trackitdown.game.logics.levelManagers;

import android.content.Context;
import android.graphics.Paint;

import com.example.trackitdown.R;

public class GameLvlMng_lvl6  extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl6_circleNumber = 15;
	private int _lvl6_speed = 3;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl6_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl6_seekTime = 7000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl6_circleRadius = 25;
	
	

	
	
	public GameLvlMng_lvl6(Context c){
		super(true);
		_speed = _lvl6_speed;
		_circleNumber = _lvl6_circleNumber;
		_observeTime = _lvl6_observeTime;
		_seekTime = _lvl6_seekTime;
		_circleRadius = _lvl6_circleRadius;
		
		/*all the circle will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 255, 255, 255);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 81, 142, 255);
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 255, 152, 73);
		_winningCircleNo = 2;

		
		setWinningCircleImg(c.getResources().getDrawable(R.drawable.ball_1));
		setCircleImg(c.getResources().getDrawable(R.drawable.ball_2));
		setBackgroundImg(c.getResources().getDrawable(R.drawable.bk_1));
		
		generateStartRandomCircles();
		
		
		
	}

}

package com.example.trackitdown.game.logics.levelManagers;

import android.content.Context;
import android.graphics.Paint;

import com.example.trackitdown.R;

public class GameLvlMng_lvl7 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl7_circleNumber = 18;
	private int _lvl7_speed = 4;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl7_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl7_seekTime = 10000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl7_circleRadius = 20;
	
	

	
	
	public GameLvlMng_lvl7(Context c){
		super(true);
		_speed = _lvl7_speed;
		_circleNumber = _lvl7_circleNumber;
		_observeTime = _lvl7_observeTime;
		_seekTime = _lvl7_seekTime;
		_circleRadius = _lvl7_circleRadius;
		
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

package com.example.trackitdown.game.logics.levelManagers;

import android.content.Context;
import android.graphics.Paint;

import com.example.trackitdown.R;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl.CIRCLE_SIZE;


public class GameLvlMng_lvl2  extends GameLvlMng_lvl {

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl2_circleNumber = 15;
	private int _lvl2_speed = 2;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl2_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl2_seekTime = 7000;
	
	

	
	
	public GameLvlMng_lvl2(Context c){
		super(true);
		_speed = _lvl2_speed;
		_circleNumber = _lvl2_circleNumber;
		_observeTime = _lvl2_observeTime;
		_seekTime = _lvl2_seekTime;
		set_circleRadius_sizeFactor(CIRCLE_SIZE.LARGE);
		
		/*all the circle will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 255, 255, 255);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 0, 255, 0);
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 0, 0, 255);

		setWinningCircleImg(c.getResources().getDrawable(R.drawable.ball_1));
		setCircleImg(c.getResources().getDrawable(R.drawable.ball_2));
		setBackgroundImg(c.getResources().getDrawable(R.drawable.bk_1));
		
		generateStartRandomCircles();
		
		
		
	}
	
}

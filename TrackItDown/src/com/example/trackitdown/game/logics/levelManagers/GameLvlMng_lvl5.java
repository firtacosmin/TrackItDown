package com.example.trackitdown.game.logics.levelManagers;

import android.content.Context;
import android.graphics.Paint;

import com.example.trackitdown.R;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl.CIRCLE_SIZE;

public class GameLvlMng_lvl5 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl5_circleNumber = 10;
	private int _lvl5_speed = 2;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl5_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl5_seekTime = 5000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl5_circleRadius = 25;
	
	

	
	
	public GameLvlMng_lvl5(Context c){
		super(true);
		_speed = _lvl5_speed;
		_circleNumber = _lvl5_circleNumber;
		_observeTime = _lvl5_observeTime;
		_seekTime = _lvl5_seekTime;
//		_circleRadius = _lvl5_circleRadius;
		set_circleRadius_sizeFactor(CIRCLE_SIZE.LARGE);
		
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

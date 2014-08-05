package com.example.trackitdown.game.logics.levelManagers;

import android.content.Context;
import android.graphics.Paint;

import com.example.trackitdown.R;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl.CIRCLE_SIZE;

public class GameLvlMng_lvl8 extends GameLvlMng_lvl{

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl8_circleNumber = 15;
	private int _lvl8_speed = 3;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl8_observeTime = 2000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl8_seekTime = 8000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl8_circleRadius = 25;
	
	

	
	
	public GameLvlMng_lvl8(Context c){
		super(true);
		_speed = _lvl8_speed;
		_circleNumber = _lvl8_circleNumber;
		_observeTime = _lvl8_observeTime;
		_seekTime = _lvl8_seekTime;
//		_circleRadius = _lvl8_circleRadius;
		set_circleRadius_sizeFactor(CIRCLE_SIZE.LARGE);
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
		_winningCircleNo = 2;

		
		setWinningCircleImg(c.getResources().getDrawable(R.drawable.ball_1));
		setCircleImg(c.getResources().getDrawable(R.drawable.ball_2));
		setBackgroundImg(c.getResources().getDrawable(R.drawable.bk_1));
		
		generateStartRandomCircles();
		
		
		
	}

}

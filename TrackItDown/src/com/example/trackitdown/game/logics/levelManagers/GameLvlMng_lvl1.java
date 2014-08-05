package com.example.trackitdown.game.logics.levelManagers;

import java.util.Iterator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.example.trackitdown.R;
import com.example.trackitdown.game.drawable.MyCircle;
import com.example.trackitdown.game.drawable.lvlManagementGraphics.HintPrinter;

public class GameLvlMng_lvl1  extends GameLvlMng_lvl {

	/*
	 * CONFIGURABLE
	 * */
	
	private int _lvl1_circleNumber = 20;
	private int _lvl1_speed = 1;	
	/**
	 * the amount of time the win circle has different color.
	 */
	private long _lvl1_observeTime = 7000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private long _lvl1_seekTime = 7000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	private int _lvl1_circleRadius = 25;
	
	
	private boolean _hintObserve = false;
	private boolean _observevTimerStart;
	private boolean _hintSeek = false;
	private boolean _seekTimerStart;
	private boolean _hintChoose = false;
	private int _displayTime = 3000;
	private boolean _passedTime = false;
	private boolean _textHintToPrint = true;
	private boolean _timerRunning = false;
	
	private HintPrinter _hintPrinter;
	
	
	public GameLvlMng_lvl1(Context c){
		super(true);
		_hintPrinter = new HintPrinter();
		
		
		_speed = _lvl1_speed;
		_circleNumber = _lvl1_circleNumber;
		_observeTime = _lvl1_observeTime;
		_seekTime = _lvl1_seekTime;
//		_circleRadius = _lvl1_circleRadius;
		set_circleRadius_sizeFactor(CIRCLE_SIZE.LARGE);
		
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
	
	public void setWindowSize(int width, int height){
		super.setWindowSize(width, height);
		setHintDetails();
		
	}
	
	
	public void Draw(Canvas c){
		super.Draw(c);
		if ( _gameState == GAME_STATES.PLAY && !_winCircleHidden && !_hintObserve ){
			/*draw the hint for play Observe state*/
			_hintPrinter.printHintMessage(_theContext.getString(R.string.hint_lvl1_observe), c);
			printHint(c);
			Log.d("DrawHint","Drawing Printer Hint Seek: "+_hintObserve+" _passedTime: "+_passedTime);
			if ( _passedTime ){
				_hintObserve = true;
				_passedTime = false;
			}else{
				startHintTimer();
			}
		}else if ( _gameState == GAME_STATES.PLAY && _winCircleHidden && !_hintSeek ){
			/*draw the hint for play seek state*/
			_hintPrinter.printHintMessage(_theContext.getString(R.string.hint_lvl1_seek), c);
			printHint(c);
			Log.d("DrawHint","Drawing Printer Hint Observe: "+_hintSeek+" _passedTime: "+_passedTime);
			if ( _passedTime ){
				_hintSeek = true;
				_passedTime = false;
			}else{
				startHintTimer();
			}
		}else if ( _gameState == GAME_STATES.CHOSE && !_hintChoose ){
			_hintPrinter.printHintMessage(_theContext.getString(R.string.hint_lvl1_chose), c);
			if ( _passedTime ){
				_hintChoose = true;
				_passedTime = false;
			}else{
				startHintTimer();
			}
		}
		
	}
	
	/**
	 * @desc will print the hint and increment the print counter.
	 * @param c
	 */
	private void printHint(Canvas c){
		Iterator<MyCircle> it = _winningCircle.iterator();
		it = _winningCircle.iterator();
		while ( it.hasNext() ){
			MyCircle circ = it.next();
			_hintPrinter.printHintCircle(circ.getTrajectory().getCurrentPoint(), c);
		}
	}
	
	private void startHintTimer(){
		if ( !_timerRunning ){
			Thread timer = new Thread(){	
				@Override
				public void run() {
					try{
						_passedTime = false;
						_timerRunning = true;
						Thread.sleep(_displayTime);
						_passedTime = true;
						_timerRunning = false;
					}catch(Exception ex){
						
					}
				}
			};
			timer.start();
		}
	}

	protected void restart(){	
		super.restart();
		
		_hintObserve = false;
		_hintChoose = false;
		_hintSeek = false;
		_passedTime = false;
		
		
	}
	
	private void setHintDetails(){
		
		int hintTxtHeight = _screenHeight/7;
		int hintTxtWidth = 80 * _screenWidth / 100;
		
		Rect txtPos = new Rect();
		txtPos.set((_screenWidth - hintTxtWidth) / 2,
				   _screenHeight - 2*hintTxtHeight,
				   hintTxtWidth + (_screenWidth - hintTxtWidth) / 2,
				   hintTxtHeight + _screenHeight - 2*hintTxtHeight);
		_hintPrinter.set_textPosition(txtPos);
	}
}

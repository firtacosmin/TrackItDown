package com.example.trackitdown.game.logics.levelManagers;

import com.example.trackitdown.R;
import com.example.trackitdown.game.drawable.lvlManagementGraphics.HintPrinter;

import android.content.Context;
import android.graphics.Paint;

public class GameLvlMng_custom extends GameLvlMng_lvl  {

	private int _ballSpeed;
	private int _ballNo;
	private int _winBallNo;
	private Boolean _myblinkFlg;
	private String _ballColor;
	private String _winBallColor;
	private int _ballSize;
	
	
	private int[] _allCircleRadiuses = {25, 20, 15};
	

	public GameLvlMng_custom(Context c) {
		super(true);
		_theContext = c;
	}
	
	/**
	 * @desc set the size of the ball
	 * 0 - big
	 * 1 - medium
	 * 2 - small
	 * 
	 * @param _ballSize
	 */
	public void set_ballSize(int _ballSize){
		this._ballSize = _ballSize;
	}
	/**
	 * @desc set the speed of the ball
	 * 
	 * 0 - slow
	 * 1 - medium
	 * 2 - fast
	 * 3 - fastest
	 *
	 * @param _ballSpeed
	 */
	public void set_ballSpeed(int _ballSpeed) {
		this._ballSpeed = _ballSpeed + 1;
	}

	public void set_ballNo(int _ballNo) {
		this._ballNo = _ballNo;
	}

	public void set_winBallNo(int _winBallNo) {
		this._winBallNo = _winBallNo;
	}

	public void set_blinkFlg(Boolean _blinkFlg) {
		this._myblinkFlg = _blinkFlg;
	}

	public void set_ballColor(String _ballColor) {
		this._ballColor = _ballColor;
	}

	public void set_winBallColor(String _winBallColor) {
		this._winBallColor = _winBallColor;
	}
	
	/**
	 * @desc method that will be called after all the 
	 *       characteristics of the level have been set 
	 *       and the level can be generated using the set characteristics
	 */
	public void generateLevel(){
		
		
		_speed = _ballSpeed;
		_circleNumber = _ballNo;
		_observeTime = 7000;
		_seekTime = 2000;
		_winningCircleNo = _winBallNo;
		/*teh circle size*/
		_circleRadius = _allCircleRadiuses[_ballSize];
		_blinkFlag = _myblinkFlg;
		
		int ballID = getImgIDFromColor(_winBallColor);
		setWinningCircleImg(_theContext.getResources().getDrawable(ballID));
		ballID = getImgIDFromColor(_ballColor);
		setCircleImg(_theContext.getResources().getDrawable(ballID));
		setBackgroundImg(_theContext.getResources().getDrawable(R.drawable.bk_1));
				
		generateStartRandomCircles();
	}
	
	/**
	 * @desc compare the passed color string with the color string 
	 *       array and return the image of the ball with the same color
	 * @param color
	 * @return
	 */
	private int getImgIDFromColor(String color){
		int imgID = 0;
		String[] colorArray = _theContext.getResources().getStringArray(R.array.ball_color_entry_array);
		
		/*red = ball_3*/
		if ( color.equals(colorArray[0]) ){
			imgID = R.drawable.ball_3;
		}
		/*green = ball_2*/
		if ( color.equals(colorArray[1]) ){
			imgID = R.drawable.ball_2;
		}
		/*blue = ball_1*/
		if ( color.equals(colorArray[2]) ){
			imgID = R.drawable.ball_1;
		}
		
		return imgID;
	}

	
	

}

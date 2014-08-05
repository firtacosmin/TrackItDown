package com.example.trackitdown.game.logics;

import com.example.trackitdown.GameActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameMainThread extends Thread {

	private SurfaceHolder _theSurfaceHolder;
	private GameActivity _theContext;
	private Handler _theHandler;
	
	private Boolean _run;
	
	
	private int _speed;
	private GameLvlMng _gameLvl;
	
	private int _screenWidth;
	private int _screenHeight;
	
	public GameMainThread(SurfaceHolder surfaceHolder, Context context,
            Handler handler){
		
		_theSurfaceHolder = surfaceHolder;
		_theContext = (GameActivity)context;
		_theHandler = handler;
		
		_run = true;
		_speed = 15;
//		
//		_circles[0].setPosition(50, 100);
//		_circles[0].setRadius(20);
//
//		_circles[1].setPosition(200, 100);
//		_circles[1].setRadius(20);

		
	}
	
	
	
	public GameLvlMng getLvl(){
		return _gameLvl;
	}
	
	public void stopGame(){
		_run = false;
	}
	public void startGame(){
		_run = true;
		this.start();
	}
	public void setDisplaySize(int width, int height){
		_gameLvl.setWindowSize(width, height);
		_screenWidth = width;
		_screenHeight = height;
	}
	
	
	public void run(){

		Looper.prepare();
		_gameLvl.startGame();
		
		while( _run ){
			
			Canvas c = null;
			try{
				updatePhysics();
				c = _theSurfaceHolder.lockCanvas(null);
				synchronized (_theSurfaceHolder) {
					DrawCanvas(c);
				}
			}catch(Exception x){
				Log.d("Error:","Exception:\n"+x.getStackTrace() );
			}finally{
				if ( c!=null ){
					_theSurfaceHolder.unlockCanvasAndPost(c);
				}
			}

			if ( _gameLvl.gameHasStoped() ){
				/*game won => stop thread*/
				stopGame();
				_theContext.finish();
				if ( _gameLvl.checkWin() ){
					/*if the game has been wan then increment the level to unlock it on the grid*/
					GameLvlMngGenerator.setLevel(GameLvlMngGenerator.getCurrentLevel() + 1);
				}
			}else if ( _gameLvl.checkWin()  ){
				/*game won*/
				/*go to next level*/
				_gameLvl = GameLvlMngGenerator.nextLevel(_theContext);
				_gameLvl.setContext(_theContext);
				_gameLvl.setWindowSize(_screenWidth, _screenHeight);
				_gameLvl.startGame();
			}
			
			try {
				Thread.sleep(_speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	
	private void updatePhysics(){
		_gameLvl.DoPhysics();
//		if ( _pos == 1 ){
//			_pos = 2;
//			for ( int i=0; i<_circles.length; i++ ){
//				_circles[i].setRadius(10);
//			}
//		}else{
//			_pos = 1;
//			for ( int i=0; i<_circles.length; i++ ){
//				_circles[i].setRadius(20);
//			}
//		}
	}
	
	/**
	 * @desc method to draw the canvas.
	 * @param c
	 */
	private void DrawCanvas(Canvas c){

		try{
//			Paint p = new Paint();
	//		p.setARGB(255, 255, 255, 255);
//			c.drawRect(0, 0, c.getWidth(), c.getHeight(), p);
	//		p.setARGB(255, 120, 120, 120);
	//		c.drawCircle(50, 100, 10*_pos, p );
			
	
//			for ( int i=0; i<_circles.length; i++ ){
//				_circles[i].draw(c);
//			}
			
			_gameLvl.Draw(c);
			
			
		}catch(Exception ex){
			Log.e(ex.getLocalizedMessage(), ex.getMessage());
		}
	
	}

	public void setGameLvlMng(GameLvlMng gameLvlMng) {
		_gameLvl = gameLvlMng;
		_gameLvl.setContext(_theContext);
	}
	
}

/**
 * 
 */
package com.example.trackitdown.game.logics;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;


/**
 * @author Cosmin
 *
 */
public interface GameLvlMng {
	
	/**
	 * @desc method for passing the context to the game manager
	 * @param c
	 */
	public void setContext(Context c);
	
	/**
	 * @desc  method that redraws all the circles.
	 * @param c
	 */
	public void Draw(Canvas c);
	
	/**
	 * @desc method that recalculates the new positions of the 
	 * 		 circles in taking to consideration the last position,
	 *       speed and direction
	 */
	public void DoPhysics();
	
	/**
	 * @desc returns true if the games is won
	 * @return
	 */
	public boolean checkWin();
	/**
	 * @desc  method with which the lvl mng generator 
	 *        will pass the size of the screen to the lvl mng
	 * @param width
	 * @param height
	 */
	public void setWindowSize(int width, int height);
	
	/**
	 * @desc method called before the start of the game thread to indicate that the game is starting 
	 */
	public void startGame();
	
	/**
	 * @desc method that receives the motion events from the view
	 */
	public void onEvent(MotionEvent ev);
	/**
	 * @desc method that tells if the game has stoped and the thread should stop
	 * @return
	 */
	public boolean gameHasStoped();
	
	/**
	 * @desc method that will stop the game in the middle
	 */
	public void cancelGame();
}

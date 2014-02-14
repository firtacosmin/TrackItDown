package com.example.trackitdown.game.drawable.lvlManagementGraphics;

import java.util.Iterator;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.trackitdown.game.drawable.MyCircle;

/**
 * @author cfirta
 *
 */
public class LvlGraphics {

	
	private Vector<MyCircle> _theCircles;
	private Vector <MyCircle> _theWinningCircles;
	private Context _theContext;
	private int _screenWidth;
	private int _screenHeight;
	private Paint _backgroundPaint;
	private Paint _circlesPaint;
	private Paint _winningCirclePaint;
	
	/**
	 * @param theCircles
	 * @param theWinningCirles
	 */
	public LvlGraphics(Vector<MyCircle> theCircles, Vector<MyCircle> theWinningCirles){
		_theCircles = theCircles;
		_theWinningCircles = theWinningCirles;
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 255, 255, 255);
		/*all the cirlce will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 0, 0, 0);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 255, 0, 0);
		
	}
	
	public void setContext(Context c){
		_theContext = c;
	}
	public void setScreenSize(int w, int h){
		_screenWidth = w;
		_screenHeight = h;
	}
	
	public void Draw(Canvas c){
		/*draw the background*/
		c.drawRect(0, 0, _screenWidth , _screenHeight , _backgroundPaint);
		
		/*draw the circles*/
		Iterator<MyCircle> circleIt = _theCircles.iterator();
		while(circleIt.hasNext()){
			MyCircle circ = circleIt.next();
			circ.draw(c);
		}
	}
	
	
}

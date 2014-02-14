package com.example.trackitdown.game.drawable;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.trackitdown.game.logics.GameMainThread;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private GameMainThread _theThread;
    /** Pointer to the text view to display "Paused.." etc. */
	public GameSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		SurfaceHolder holder = getHolder();
		holder.addCallback(this);
		_theThread = new GameMainThread(holder, context, new Handler());
		
	}
	
	public GameMainThread getThread(){
		return _theThread;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		_theThread.startGame();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Boolean retry = true;
		_theThread.stopGame();
		while ( retry ){
			try{
				_theThread.join();
				retry=false;
			}catch(InterruptedException ex){}
		}
	}
	
	public void OnClickEvent(View view){
		
	}
	public boolean onTouchEvent(MotionEvent event) {

			_theThread.getLvl().onEvent(event);
		return false;
		 
	}

}

package com.example.trackitdown;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.example.trackitdown.game.drawable.GameSurfaceView;
import com.example.trackitdown.game.logics.GameLvlMng;
import com.example.trackitdown.game.logics.GameLvlMngGenerator;
import com.example.trackitdown.game.logics.GameMainThread;

public class GameActivity extends Activity {

	//private GameSurfaceView mGLView;
	
	private GameSurfaceView _gameView;
	private GameMainThread _gameThread;

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_game);
		_gameView = (GameSurfaceView)findViewById(R.id.gameView);
		_gameThread = _gameView.getThread();
		GameLvlMngGenerator.LEVELS lvl = (GameLvlMngGenerator.LEVELS)getIntent().getSerializableExtra(LevelSelectionView.GAME_LVL);
		Display display = getWindowManager().getDefaultDisplay();
		int width;
		int height;
		if (android.os.Build.VERSION.SDK_INT >= 13){ 
			Point size = new Point();
			display.getSize(size); 
			width = size.x; 
			height = size.y; 
		} else {
			width = display.getWidth(); 
			height = display.getHeight(); 
		}
		GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(lvl,this);
		_gameThread.setGameLvlMng(gameLvlMng);
		_gameThread.setDisplaySize(width, height);
		
		
		//_gameThread = _gameView.getThread();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);

		
		
		return true;
	}
	
	

}

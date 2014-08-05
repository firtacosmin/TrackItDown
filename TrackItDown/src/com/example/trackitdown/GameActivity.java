package com.example.trackitdown;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;

import com.example.trackitdown.game.drawable.GameSurfaceView;
import com.example.trackitdown.game.logics.GameLvlMng;
import com.example.trackitdown.game.logics.GameLvlMngGenerator;
import com.example.trackitdown.game.logics.GameMainThread;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_custom;

public class GameActivity extends Activity {

	//private GameSurfaceView mGLView;
	
	private GameSurfaceView _gameView;
	private GameMainThread _gameThread;
	private GameLvlMngGenerator.LEVELS _theLevel;
	private GameLvlMng_custom _customLvl;
	private GameLvlMng _gameLvlMng;
	public static final String GAME_LVL = "com.example.trackitdown.GAME_LVL";
	public static final String CUSTOM_GAME_LVL_BUNDLE = "com.example.trackitdown.CUSTOM_GAME_LVL";
	public static final String CUSTOM_GAME_BUNDLE_BALL_NO = "com.example.trackitdown.customgame.bundle.ballno";
	public static final String CUSTOM_GAME_BUNDLE_WIN_BALL_NO = "com.example.trackitdown.customgame.bundle.winballno";
	public static final String CUSTOM_GAME_BUNDLE_SPEED = "com.example.trackitdown.customgame.bundle.speed";
	public static final String CUSTOM_GAME_BUNDLE_BALL_SIZE = "com.example.trackitdown.customgame.bundle.ballsize";
	public static final String CUSTOM_GAME_BUNDLE_BALl_COLOR = "com.example.trackitdown.customgame.bundle.ballcolor";
	public static final String CUSTOM_GAME_BUNDLE_WIN_BALL_COLOR = "com.example.trackitdown.customgame.bundle.winballcolor";
	public static final String CUSTOM_GAME_BUNDLE_BLINK = "com.example.trackitdown.customgame.bundle.blink";


	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_game);
		_gameView = (GameSurfaceView)findViewById(R.id.gameView);
		_gameThread = _gameView.getThread();
		/*try to get the passed level*/
		try{
			_theLevel = (GameLvlMngGenerator.LEVELS)getIntent().getSerializableExtra(GAME_LVL);
		}catch(Exception ex){
			/*if no level is passed then initialize with null*/
			_theLevel = null;
		}
		/*try to get the passed custom level*/
		try{
			Bundle customLvlBundel = (Bundle)getIntent().getBundleExtra(CUSTOM_GAME_LVL_BUNDLE);
			_customLvl = genCustomLvl(customLvlBundel);
		}catch(Exception ex){
			/*if no custom level is passed then initialize with null*/
			_customLvl = null;
		}
		/*get the level manager*/
		_gameLvlMng = null;
		if ( _theLevel != null ){
			/*level is passed*/
			_gameLvlMng = GameLvlMngGenerator.getLvl(_theLevel,this);
		}else if ( _customLvl != null ){
			/*if no level is passed then get the custom level*/
			_gameLvlMng = _customLvl;
		}
		
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
		
		_gameThread.setGameLvlMng(_gameLvlMng);
		_gameThread.setDisplaySize(width, height);
		
		
		//_gameThread = _gameView.getThread();
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);

		
		
		return true;
	}
	public void onStop(){
		super.onStop();
		_gameLvlMng.cancelGame();
		
	}
	/**
	 * @desc will get the data from the bundle and will create a custom game level
	 * @param gameBundle
	 * @return the custom game level
	 */
	private GameLvlMng_custom genCustomLvl(Bundle gameBundle){

		GameLvlMng_custom gameLvl = new GameLvlMng_custom(this);
		
		/*set bundle data*/
		/*set ball size*/
		gameLvl.set_ballSize(gameBundle.getInt(CUSTOM_GAME_BUNDLE_BALL_SIZE));
		/*set ball speed*/
		gameLvl.set_ballSpeed(gameBundle.getInt(CUSTOM_GAME_BUNDLE_SPEED));
		/*set ball no*/
		gameLvl.set_ballNo(gameBundle.getInt(CUSTOM_GAME_BUNDLE_BALL_NO));
		/*set win ball no*/
		gameLvl.set_winBallNo(gameBundle.getInt(CUSTOM_GAME_BUNDLE_WIN_BALL_NO));
		/*set blink flag*/
		gameLvl.set_blinkFlg(gameBundle.getBoolean(CUSTOM_GAME_BUNDLE_BLINK));
		/*set ball color*/
		gameLvl.set_ballColor(gameBundle.getString(CUSTOM_GAME_BUNDLE_BALl_COLOR));
		/*set win ball color*/
		gameLvl.set_winBallColor(gameBundle.getString(CUSTOM_GAME_BUNDLE_WIN_BALL_COLOR));
		
		/*apply the changes and generate the level*/
		gameLvl.generateLevel();
		
		return gameLvl;
	}
	

}

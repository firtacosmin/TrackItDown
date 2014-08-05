package com.example.trackitdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.trackitdown.game.logics.GameLvlMngGenerator;
import com.example.trackitdown.game.logics.db.ProgressDB;
import com.example.trackitdown.game.logics.db.ProgressWrapper;

public class MainActivity extends Activity {

	
	private int _currentLevel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * @param view
	 * @desc method called when the start game button is pressed
	 */
	public void startLevelSelection(View view){
//		int currentLevel = _currentLevel;
//		/*
//		 * if allready played and the level reached in the game is bigger then 
//		 * the one saved into the db then set that one as current level
//		 * */
//		if ( _currentLevel < GameLvlMngGenerator.getCurrentLevel() ){
//			currentLevel = GameLvlMngGenerator.getCurrentLevel();
//		}
		Intent startActivIntent = new Intent(this, LevelSelectionView.class);
		startActivIntent.putExtra(GameActivity.GAME_LVL, _currentLevel);
		startActivity(startActivIntent);
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * @activity save the level into the database
	 */
	public void onResume(){
		super.onResume();
		getCurrentLevel();
	}
	
	public void onDestroy(){
		super.onDestroy();
		

	}
	public void onStop(){
		super.onStop();
		/*save the current level in the db*/
		ProgressDB p = ProgressWrapper.getInstance(this);
		p.setProgress(GameLvlMngGenerator.getCurrentLevel() );
	}
	
	
	public void exitApp(View view){
		finish();
	}
	
	/**
	 * @desc method called when "Start custom app" btn is pressed.
	 * 		 It will start the custom app if the reached level is good
	 * @param view
	 */
	public void startCustom(View view){
		Intent intent = new Intent(this, CustomGameSettings.class);
		startActivity(intent);
	}
	
	/**
	 * @desc method that wil get the current Level from the db and from 
	 * GameLvlMngGenerator and will save as the currentLevel the biggest one
	 */
	private void getCurrentLevel(){
		int dbLevel = 0;
		try{
			dbLevel = getCurrentLevelFromDb();
			int mgrLevel = GameLvlMngGenerator.getCurrentLevel();
			if ( dbLevel > mgrLevel ){
				_currentLevel = dbLevel;
				GameLvlMngGenerator.setLevel(_currentLevel);
			}else{
				_currentLevel = mgrLevel;
			}
		}catch(NullPointerException ex){
			ex.printStackTrace();
			_currentLevel = dbLevel;
			GameLvlMngGenerator.setLevel(_currentLevel);
		}catch(Exception ex){
			ex.printStackTrace();
			_currentLevel = 0;
			GameLvlMngGenerator.setLevel(_currentLevel);
		}
	}
	/**
	 * @desc get the last level from the database
	 */
	private int getCurrentLevelFromDb(){
		ProgressDB p = ProgressWrapper.getInstance(this);
		int currentLevel = p.getProgress();
		Toast.makeText(this, 
			       "Current db Level:"+currentLevel, 
			       Toast.LENGTH_SHORT).show();
		return currentLevel;
	}

}

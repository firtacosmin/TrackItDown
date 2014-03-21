package com.example.trackitdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.trackitdown.game.logics.GameLvlMngGenerator;
import com.example.trackitdown.game.logics.db.ProgressDB;
import com.example.trackitdown.game.logics.db.ProgressWrapper;

public class MainActivity extends Activity {

	private int _lvlFromDb;
	private boolean _created = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		getCurrentLevelFromDb();

		_created = true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void startLevelSelection(View view){
		Intent startActivIntent = new Intent(this, LevelSelectionView.class);
		startActivIntent.putExtra(LevelSelectionView.GAME_LVL, _lvlFromDb);
		startActivity(startActivIntent);
		
	}
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onResume()
	 * @activity save the level into the database
	 */
	public void onResume(){
		super.onResume();
		if ( !_created ){
			/*if not called after oncreate()*/
			ProgressDB p = ProgressWrapper.getInstance(this);
			p.setProgress(GameLvlMngGenerator.getCurrentLevel() );
			_lvlFromDb = GameLvlMngGenerator.getCurrentLevel();
		}
		else
		{
			/*if called after oncreate();*/
			/*note that the next time will not be after oncreate*/
			_created = false;
		}
	}
	
	
	public void exitApp(View view){
		finish();
	}
	/**
	 * @desc get the last level from the database
	 */
	private void getCurrentLevelFromDb(){
		ProgressDB p = ProgressWrapper.getInstance(this);
		int currentLevel = p.getProgress();
		Toast.makeText(this, 
			       "Current Level:"+currentLevel, 
			       Toast.LENGTH_SHORT).show();
		_lvlFromDb = currentLevel;
	}

}

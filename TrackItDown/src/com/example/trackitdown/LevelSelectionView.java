package com.example.trackitdown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.trackitdown.game.drawable.LvlSelectionImageAdapter;
import com.example.trackitdown.game.logics.GameLvlMngGenerator;

public class LevelSelectionView extends Activity {

	private Intent _startGameIntent;
	private int _currentLevel;
	private LvlSelectionImageAdapter _lvlGridAdaptor;
	public static final int LVL_CHANGE_REQUEST_CODE=1; 
	public static final String LVL_CHANGE_EXTRA_NAME="NEW_LVL";
	private GridView gridview ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_selection_view);
		// Show the Up button in the action bar.
		setupActionBar();
		gridview = (GridView) findViewById(R.id.gridview);
		/*get the level from main activity*/
		_currentLevel = getIntent().getIntExtra(GameActivity.GAME_LVL,0);
		GameLvlMngGenerator.setLevel(_currentLevel);
		_lvlGridAdaptor = new LvlSelectionImageAdapter(this,_currentLevel);
	    gridview.setAdapter(_lvlGridAdaptor);
	    _startGameIntent = new Intent(this, GameActivity.class);
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            //Toast.makeText(LevelSelectionView.this, "" + position, Toast.LENGTH_SHORT).show();
	        	if ( position <= _currentLevel ){
		        	_startGameIntent.putExtra(GameActivity.GAME_LVL, GameLvlMngGenerator.LEVELS.values()[position]);
		    		startActivity(_startGameIntent);
	        	}else{
	        		/*if level still locked*/
	        		Toast.makeText(LevelSelectionView.this, getString(R.string.lvl_locked_open_msj), Toast.LENGTH_SHORT).show();
	        	}
//	        	}
	        }
	    });

	}

	public void onStart(){
		super.onStart();
		int newLvl = GameLvlMngGenerator.getCurrentLevel();
		if ( newLvl > _currentLevel  ){
		//_lvlGridAdaptor.setCurrentLevel(_currentLevel);
			_currentLevel = newLvl;
			_lvlGridAdaptor = new LvlSelectionImageAdapter(this, _currentLevel); 
			gridview.setAdapter(_lvlGridAdaptor);
		}
	}
	
	
	
	public int getCurrentLevel(){
		return _currentLevel;
	}
	public void setCurrentLevel(int lvl){
		_currentLevel = lvl;
	}

	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	//@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		//if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			//getActionBar().setDisplayHomeAsUpEnabled(true);
		//}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_selection_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//switch (item.getItemId()) {
		//case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			//NavUtils.navigateUpFromSameTask(this);
			//return true;
		//}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * @param view
	 */
	public void startLevel1( ){
		
		//GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(GameLvlMngGenerator.LEVELS.LEVEL_1);
		
		Intent startGameIntent = new Intent(this, GameActivity.class);
		startGameIntent.putExtra(GameActivity.GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_1);
		startActivity(startGameIntent);
	}
	
	public void startLevel2( ){
		
		//GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(GameLvlMngGenerator.LEVELS.LEVEL_1);
		
		Intent startGameIntent = new Intent(this, GameActivity.class);
		startGameIntent.putExtra(GameActivity.GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_2);
		startActivity(startGameIntent);
	}
	
public void startLevel3( ){
		
		//GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(GameLvlMngGenerator.LEVELS.LEVEL_1);
		
		Intent startGameIntent = new Intent(this, GameActivity.class);
		startGameIntent.putExtra(GameActivity.GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_3);
		startActivity(startGameIntent);
	}

}

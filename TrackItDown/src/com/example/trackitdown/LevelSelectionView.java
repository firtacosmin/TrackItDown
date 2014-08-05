package com.example.trackitdown;

import android.app.Activity;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.example.trackitdown.game.drawable.LvlSelectionImageAdapter;
import com.example.trackitdown.game.logics.GameLvlMngGenerator;

public class LevelSelectionView extends Activity implements OnItemClickListener{

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
		gridview = (GridView) findViewById(R.id.gridview);
		/*get the level from main activity*/
		_currentLevel = getIntent().getIntExtra(GameActivity.GAME_LVL,0);
		_lvlGridAdaptor = new LvlSelectionImageAdapter(this,_currentLevel);
	    gridview.setAdapter(_lvlGridAdaptor);
	    gridview.setOnItemClickListener(this);

	}

	public void onStart(){
		super.onStart();
		int newLvl = GameLvlMngGenerator.getCurrentLevel();
		if ( newLvl > _currentLevel  ){
			//TODO: Boolshit :) )) nu trebuie recreat adaptorul
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.level_selection_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
	 * @desc when a level is selected this method is called.
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //Toast.makeText(LevelSelectionView.this, "" + position, Toast.LENGTH_SHORT).show();
	    _startGameIntent = new Intent(this, GameActivity.class);
    	if ( position <= _currentLevel + 1 ){
        	_startGameIntent.putExtra(GameActivity.GAME_LVL, GameLvlMngGenerator.LEVELS.values()[position]);
    		startActivity(_startGameIntent);
    	}else{
    		/*if level still locked*/
    		Toast.makeText(LevelSelectionView.this, getString(R.string.lvl_locked_open_msj), Toast.LENGTH_SHORT).show();
    	}
//    	}
    }

}

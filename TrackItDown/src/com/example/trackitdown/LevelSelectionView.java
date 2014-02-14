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

	public static final String GAME_LVL = "com.example.trackitdown.GAME_LVL";
	private Intent _startGameIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_level_selection_view);
		// Show the Up button in the action bar.
		setupActionBar();
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new LvlSelectionImageAdapter(this));
	    _startGameIntent = new Intent(this, GameActivity.class);
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            //Toast.makeText(LevelSelectionView.this, "" + position, Toast.LENGTH_SHORT).show();

	        	switch (position){
	        	case 0:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_1);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 1:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_2);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 2:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_3);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 3:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_4);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 4:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_5);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 5:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_6);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 6:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_7);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 7:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_8);
		    		startActivity(_startGameIntent);
	        		break;
	        	case 8:
	        		_startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_9);
		    		startActivity(_startGameIntent);
	        		break;
	        	default:
		            Toast.makeText(LevelSelectionView.this, "" + position, Toast.LENGTH_SHORT).show();
	        		break;
	        	}
	        }
	    });

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
		startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_1);
		startActivity(startGameIntent);
	}
	
	public void startLevel2( ){
		
		//GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(GameLvlMngGenerator.LEVELS.LEVEL_1);
		
		Intent startGameIntent = new Intent(this, GameActivity.class);
		startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_2);
		startActivity(startGameIntent);
	}
	
public void startLevel3( ){
		
		//GameLvlMng gameLvlMng = GameLvlMngGenerator.getLvl(GameLvlMngGenerator.LEVELS.LEVEL_1);
		
		Intent startGameIntent = new Intent(this, GameActivity.class);
		startGameIntent.putExtra(GAME_LVL, GameLvlMngGenerator.LEVELS.LEVEL_3);
		startActivity(startGameIntent);
	}

}

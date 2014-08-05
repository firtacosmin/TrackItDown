package com.example.trackitdown;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class CustomGameSettings extends Activity {
private int _balldimension;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_game_settings);
		// Show the Up button in the action bar.
		/*initialize the dimension of the balls*/
		_balldimension = 0;
		/*add on item selected listener to the ball size spinner*/
		Spinner ballSize = (Spinner)findViewById(R.id.ball_size_spin);
		ballSize.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parrentView, View selectedItemView,
					int position, long id) {
				/*recalculate maximum number of balls*/
				if ( position != _balldimension ){
					calculateMaxNoOfBalls(position);
					_balldimension = position;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		calculateMaxNoOfBalls(_balldimension);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.custom_game_settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * @desc called when start game button is pressed
	 * @param view
	 */
	public void startCustomGame(View view){		
		Intent _startGameIntent = new Intent(this, GameActivity.class);
		//_startGameIntent.putExtra(GameActivity.CUSTOM_GAME_LVL, gameLvl);
		Bundle customGameBundle = createGameBundle();
		_startGameIntent.putExtra(GameActivity.CUSTOM_GAME_LVL_BUNDLE, customGameBundle);
		startActivity(_startGameIntent);
	}
	
	

	/**
	 * @desc method that will calculate the maximum number of balls that can be selected
	 * 		 taking in consideration the size of the screen and the size of the balls
	 * @param size - 1,2,3 -> big, medium, small
	 */	
	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	private void calculateMaxNoOfBalls(int ballsize){
		/*get display dimension*/
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
		/*get the spinners*/
		/*get the spinner with the total number of balls*/
		Spinner ballNo = (Spinner)findViewById(R.id.ball_no_spin);
		/*get the spinner with the number of winning balls*/
		Spinner winBallNo = (Spinner)findViewById(R.id.win_ball_no_spin);
		
		int posBallNo = ballNo.getSelectedItemPosition();
		int posWinBallNo = winBallNo.getSelectedItemPosition();
		
		int maxBallNo = (ballsize + 1) * 15;
		addValuesToSpinner(maxBallNo,ballNo);
		addValuesToSpinner(maxBallNo - 1, winBallNo);
		
		if ( posBallNo <= maxBallNo){
			/*reselect the last value*/
			ballNo.setSelection(posBallNo,true);
		}
		if ( posWinBallNo <= maxBallNo - 1){
			/*reselect the last value*/
			winBallNo.setSelection(posWinBallNo, true);
		}
		
	}
	
	private void addValuesToSpinner(int maxVal, Spinner s){

        String[] array_spinner=new String[maxVal];
        for ( Integer i=1; i<=maxVal; i++ ){
        	array_spinner[i-1] = i.toString();
        }
        ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, array_spinner);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(spinAdapter);
	}
	

	private Bundle createGameBundle() {
		
		Bundle theBundle = new Bundle();
		/*the size*/
		/*
		 * 0 - big
		 * 1 - medium
		 * 2 - small
		 * */
		int ballSize = ((Spinner)findViewById(R.id.ball_size_spin)).getSelectedItemPosition();
		theBundle.putInt(GameActivity.CUSTOM_GAME_BUNDLE_BALL_SIZE, ballSize);
		
		/*the ball speed*/
		/*
		 * 0 - slow
		 * 1 - medium
		 * 2 - fast
		 * 3 - fastest
		 * */
		int ballSpeed = ((Spinner)findViewById(R.id.circSpeedspin)).getSelectedItemPosition();
		theBundle.putInt(GameActivity.CUSTOM_GAME_BUNDLE_SPEED, ballSpeed);
		
		/*the ball no*/
		int ballNo = ((Spinner)findViewById(R.id.ball_no_spin)).getSelectedItemPosition()+ 1;
		theBundle.putInt(GameActivity.CUSTOM_GAME_BUNDLE_BALL_NO, ballNo);
		
		/*the winning ball no*/
		int winBallNo = ((Spinner)findViewById(R.id.win_ball_no_spin)).getSelectedItemPosition() + 1;
		theBundle.putInt(GameActivity.CUSTOM_GAME_BUNDLE_WIN_BALL_NO, winBallNo);
		
		/*the blink flag*/
		Boolean blinkFlg = ((CheckBox)findViewById(R.id.use_bkink_chk)).isChecked();
		theBundle.putBoolean(GameActivity.CUSTOM_GAME_BUNDLE_BLINK, blinkFlg);
		
		/*the ball color*/
		String ballColor = (String)((Spinner)findViewById(R.id.circColorSpin)).getSelectedItem();
		theBundle.putString(GameActivity.CUSTOM_GAME_BUNDLE_BALl_COLOR, ballColor);
		
		/*the winning ball color*/
		String winBallColor = (String)((Spinner)findViewById(R.id.wincircColorSpin)).getSelectedItem();
		theBundle.putString(GameActivity.CUSTOM_GAME_BUNDLE_WIN_BALL_COLOR, winBallColor);
		
		
		return theBundle;
	}
	

}

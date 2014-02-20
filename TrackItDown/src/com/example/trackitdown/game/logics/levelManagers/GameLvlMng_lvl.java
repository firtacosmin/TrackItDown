package com.example.trackitdown.game.logics.levelManagers;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.trackitdown.R;
import com.example.trackitdown.game.drawable.MyCircle;
import com.example.trackitdown.game.drawable.lvlManagementGraphics.LvlGraphics;
import com.example.trackitdown.game.logics.GameLvlMng;

public class GameLvlMng_lvl implements GameLvlMng {

	private enum GAME_STATES{PLAY, CHOSE, STATUS_WIN, STATUS_LOSE};
	private enum PHISICS_TO_DO{WALL, COLLISION};
	
	private static final int _STATUS_WAIT_TIME=500;
	
	/**
	 * @desc the aspect ratio of the message images * 100 
	 */
	private static final int STATUS_IMG_ASPECT_RATIO = 85;
	/**
	 * @desc the upper padding of the status message
	 */
	private static final int STATUS_IMG_UP_PADDING = 50;
	private static final double STATUS_IMG_SCREEN_OCUPATION = 0.33;
	private static final double STATUS_BTN_SCREEN_OCUPATION = 0.11;
	
	/*
	 * CONFIGURABLE
	 * */
	protected int _speed = 1;
	protected int _circleNumber = 10;	
	protected int _circleRadius = 25;
	/**
	 * the amount of time the win circle has different color.
	 */
	protected long _observeTime = 3000;
	/**
	 * the amount of time the ball has the same color with the others
	 */
	protected long _seekTime = 5000;
	protected Paint _backgroundPaint;
	protected Paint _circlesPaint;
	protected Paint _winningCirclePaint;
	
	
	
	protected Vector<MyCircle> _theCircles;
	protected  Vector<MyCircle> _winningCircle;
	protected LvlGraphics _theGraphics;
	
	protected int _winningCircleNo = 1;
	
	/*FEATURE FLAGS*/
	
	/**
	 * @desc flag to activate blinking feature
	 */
	protected boolean _blinkFlag = false;
	
	
	private int _screenWidth = 100;
	private int _screenHeight = 100;
	
	private PHISICS_TO_DO _phisicsToDo = PHISICS_TO_DO.WALL;
	
	private Context _theContext = null;
	

	
	private boolean _win = false;
	private boolean _stop = false;
	
	
	private GAME_STATES _gameState;
	
	private Drawable _retry_img;
	private Drawable _retry_img_btn;
	private Drawable _menu_img_btn;
	private Drawable _nextLvl_img_btn;
	private Drawable _winner_img;
	
	private Rect _messageImgRect;
	private Rect _retryBtnImgRect;
	private Rect _nextLvlBtnImgRect;
	private Rect _menuBtnImgRect;
	
	
	

	/**
	 * @desc describes the stage of the blink.
	 * 		 if 1 then all circles will be ordinary colored
	 * 		 if 0 then all circles will be winning colored
	 */
	private int _blinkStage = 1; 
	
	/**
	 * @desc the number of circles are hit in the chose state
	 */
	private int _noCirclesHit = 0;
	private boolean _winCircleHidden = false;
	
	public GameLvlMng_lvl(){
		
		this(true);
		
		/*init the background paint*/
		_backgroundPaint = new Paint();
		_backgroundPaint.setARGB(255 , 255, 255, 255);
		
		/*all the cirlce will be yellow*/
		_circlesPaint = new Paint();
		_circlesPaint.setARGB(255, 0, 0, 0);
		/*the winning circle will be blue*/
		_winningCirclePaint = new Paint();
		_winningCirclePaint.setARGB(255, 255, 0, 0);
		
		generateStartRandomCircles();

		
		
		
	}
	
	public GameLvlMng_lvl(boolean noGeneration){
		_messageImgRect    = new Rect();
		_retryBtnImgRect   = new Rect();
		_nextLvlBtnImgRect = new Rect();
		_menuBtnImgRect    = new Rect();

		//_theGraphics = new LvlGraphics();
		
	}
	
	/**
	 * @desc method called before the start of the game thread to indicate that the game is starting 
	 */
	public void startGame(){
		_gameState = GAME_STATES.PLAY;
		startStageTimer();
	}
	
	/**
	 * @desc method for passing the context to the game manager
	 * @param c
	 */
	public void setContext(Context c){
		_theContext = c;

		_retry_img = _theContext.getResources().getDrawable(R.drawable.retry_img);
		_retry_img_btn = _theContext.getResources().getDrawable( R.drawable.retry_btn);
		_menu_img_btn = _theContext.getResources().getDrawable( R.drawable.menu_btn);
		_winner_img = _theContext.getResources().getDrawable( R.drawable.winner_img);
		_nextLvl_img_btn = _theContext.getResources().getDrawable( R.drawable.farward);
	}
	
	
	/**
	 * @desc  method that redraws all the circles.
	 * @param c
	 */
	public void Draw(Canvas c){
		/*draw the background*/
		c.drawRect(0, 0, _screenWidth , _screenHeight , _backgroundPaint);
		
		/*draw the circles*/
		Iterator<MyCircle> circleIt = _theCircles.iterator();
		while(circleIt.hasNext()){
			MyCircle circ = circleIt.next();
			drawCircle(c, circ);
		}

		if ( _winCircleHidden && _blinkFlag ){
			if ( _blinkStage == 1){
				_blinkStage = 0;
			}else{
				_blinkStage = 1;
			}
		}
			
			
		if ( _gameState == GAME_STATES.STATUS_LOSE ){
			/*draw the retry image and buttons*/
			_retry_img.draw(c);
			_retry_img_btn.draw(c);
			_menu_img_btn.draw(c);
		}else if ( _gameState == GAME_STATES.STATUS_WIN ){
			/*draw the winner image and buttons*/
			_winner_img.draw(c);
			_retry_img_btn.draw(c);
			_menu_img_btn.draw(c);
			_nextLvl_img_btn.draw(c);
		}
		
	}
	

	
	/**
	 * @desc method that recalculates the new positions of the 
	 * 		 circles in taking to consideration the last position,
	 *       speed and direction
	 */
	public void DoPhysics(){
		if ( _gameState == GAME_STATES.PLAY ){
			if ( _phisicsToDo == PHISICS_TO_DO.COLLISION ){
				checkColision();
			}else{
				checkWallHit();
			}
			//next point
			Iterator<MyCircle> circleIt = _theCircles.iterator();
			while(circleIt.hasNext()){
				MyCircle circ = circleIt.next();
				circ.getTrajectory().getNextPoint();
			}
		}

		
	}
	
	public void onEvent(MotionEvent ev){

		if ( _gameState != GAME_STATES.PLAY ){
			
			if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			
				int x = (int)ev.getX();
				int y = (int)ev.getY();
				
				switch (_gameState){
				case CHOSE:
					boolean hit = testWinCondition(x,y);
					/*test if the number of hit circles is the number of winning circles*/
					if (hit)
						_noCirclesHit++;
					if (hit && _noCirclesHit == _winningCircle.size()){
						/*touched all the winning circle*/
						_gameState = GAME_STATES.STATUS_WIN;
						doWin();
						_noCirclesHit = 0;
					}else if (!hit &&  touchedCircle(x,y) ) {
						_gameState = GAME_STATES.STATUS_LOSE;
						_noCirclesHit = 0;
						doMiss();
					}else if ( hit ){
						Toast.makeText(_theContext, 
									   _theContext.getString(R.string.touch_an_other_circle), 
									   Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(_theContext, 
								       _theContext.getString(R.string.circle_not_touched), 
								       Toast.LENGTH_SHORT).show();
					}
					break;
					
				case STATUS_LOSE:
					/*see what the player pressed*/
					if ( _retryBtnImgRect.contains(x, y) ){
						/*pressed retry button*/
						_gameState = GAME_STATES.PLAY;
						restart();
					}else if ( _menuBtnImgRect.contains(x, y) ){
						/*press go to menu button*/
						_stop = true;
					}
					break;
					
				case STATUS_WIN:
					/*see what player pressed*/
					if ( _retryBtnImgRect.contains(x, y) ){
						/*pressed retry button*/
						_gameState = GAME_STATES.PLAY;
						restart();
					}else if ( _menuBtnImgRect.contains(x, y) ){
						/*press go to menu button*/
						_stop = true;
					}else if ( _nextLvlBtnImgRect.contains(x,y) ){
						_win = true;
					}
					break;
				
				default:
					break;
				}
			}
		}else{
			Toast.makeText(_theContext, "Not Yet!", Toast.LENGTH_SHORT).show();
		}
		
	}
	


	public boolean checkWin(){
		return _win;
	}	
	public boolean stopGame(){
		return _stop;
	}
	
	@Override
	public void setWindowSize(int width, int height) {
		_screenWidth = width;
		_screenHeight = height;

		/*recalculate the positions of the circles*/
		regenerateCirclePositions();
		/*recalculate the dimensions of the messages*/
		calculateMessagePositions();
	}

	
	/*
	 * 
	 * PROTECTED METHODS
	 * 
	 * */
	

	/**
	 * @desc the method that initializes the circles and generates 
	 *       random positions for them 
	 */
	protected void generateStartRandomCircles(){
		_winningCircle = new Vector<MyCircle>();
		_theCircles =  new Vector<MyCircle>();

		/*generate the number of winning circles*/
		for ( int i=0; i<_winningCircleNo; i++ ){
		
			MyCircle winningCircle = new MyCircle();
			winningCircle.setPaint(_winningCirclePaint);
			winningCircle.getTrajectory().setXSpeed(_speed);
			if ( (new Random()).nextInt(100) % 2 == 0){
				winningCircle.getTrajectory().flipXDirection();
			}
			winningCircle.setRadius(_circleRadius);
			_winningCircle.add(winningCircle);
			/*add the winning circle*/
			_theCircles.add(winningCircle);
		}

		/*add all the circles to the vector*/
		for ( int i=0; i<_circleNumber - _winningCircleNo; i++ ){
			MyCircle c = new MyCircle();
			c.setPaint(_circlesPaint);
			Point pos = new Point(1,1);//generateRandomPoint();
			c.getTrajectory().getCurrentPoint().x = pos.x;
			c.getTrajectory().getCurrentPoint().y = pos.y;
			/*half of the circles should go in different direction*/
			if ( i%2 == 0 ){
				c.getTrajectory().flipXDirection();
			}
			c.getTrajectory().setXSpeed(_speed);
			c.setRadius(_circleRadius);
			_theCircles.add(c);
		}
	}
	
	
	/*
	 * 
	 * PRIVATE METHODS
	 * 
	 * */
	
	/**
	 * @desc the method that will draw a circle to the canvas 
	 *       after it will change the color if the blink flag is set
	 * @param c
	 * @param circ
	 */
	private void drawCircle(Canvas c, MyCircle circ){
		if ( _winCircleHidden && _blinkFlag ){
			/*if the circles are hidden and the blink flag is activates*/
			if ( _gameState == GAME_STATES.PLAY ){
				/*if the circles are still moving*/
				if ( _blinkStage == 1){
					circ.setPaint(_circlesPaint);
				}else{
					circ.setPaint(_winningCirclePaint);
				}
			}
		}
		circ.draw(c);
	}
	
	/**
	 * @desc if the passed point coordinates are in a winning circle 
	 *       then it will return true
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean testWinCondition(int x, int y) {
		

		Iterator<MyCircle> circleIt = _winningCircle.iterator();
		while(circleIt.hasNext()){
			MyCircle circ = circleIt.next();
			
			int winX = circ.getTrajectory().getCurrentPoint().x;
			int winY = circ.getTrajectory().getCurrentPoint().y;
			
			if ( x <= winX + _circleRadius && 
				 x >= winX - _circleRadius && 
				 y <= winY + _circleRadius &&
				 y >= winY - _circleRadius){
				return true;
			}
		}

		return false;
	}
	
	/**
	 * @desc method to generate random positions for the circles
	 */
	private void regenerateCirclePositions(){
		Iterator<MyCircle> circleIt = _theCircles.iterator();
		while(circleIt.hasNext()){
			MyCircle circ = circleIt.next();
			Point pos = generateRandomPoint();
			circ.getTrajectory().getCurrentPoint().x = pos.x;
			circ.getTrajectory().getCurrentPoint().y = pos.y;
		}
	}
	
	/**
	 * @desc the method that calculates the positions and 
	 *       dimensions of the message images and buttons
	 */
	private void calculateMessagePositions(){
		
		/*calculations for the message image*/
		int imgHeight = (int) Math.round(_screenHeight * STATUS_IMG_SCREEN_OCUPATION);
		int imgWidth = imgHeight * STATUS_IMG_ASPECT_RATIO / 100;
		int imgX = (_screenWidth - imgWidth)/2;
		int imgY = STATUS_IMG_UP_PADDING;
		/*add calculated values to property*/
		_messageImgRect.left = imgX;
		_messageImgRect.top = imgY;
		_messageImgRect.right = imgWidth + imgX;
		_messageImgRect.bottom = imgHeight + imgY;
		
		/*calculations for the buttons*/
		int btnHeight = (int) Math.round(_screenHeight * STATUS_BTN_SCREEN_OCUPATION);
		int btnWidth = btnHeight; /* aspect ratio of 1 */
		int retryBtnX = (_screenWidth - 3*btnWidth)/2;
		int nextLvlBtnX = retryBtnX + btnWidth + 2;
		int MenuBtnX = nextLvlBtnX + btnWidth + 2;
		int btnY = imgY + imgHeight + 5;
		
		
		/*add calculated values to properties*/
		_retryBtnImgRect.left = retryBtnX;
		_retryBtnImgRect.top = btnY;
		_retryBtnImgRect.right = btnWidth + retryBtnX;
		_retryBtnImgRect.bottom = btnHeight + btnY;
		
		_nextLvlBtnImgRect.left = nextLvlBtnX;
		_nextLvlBtnImgRect.top = btnY;
		_nextLvlBtnImgRect.right = btnWidth + nextLvlBtnX;
		_nextLvlBtnImgRect.bottom = btnHeight + btnY;

		_menuBtnImgRect.left = MenuBtnX;
		_menuBtnImgRect.top = btnY;
		_menuBtnImgRect.right = btnWidth + MenuBtnX;
		_menuBtnImgRect.bottom = btnHeight + btnY;
		
		
		/*add the rects to the bounds of the images*/
		_retry_img.setBounds(_messageImgRect);
		_winner_img.setBounds(_messageImgRect);
		_retry_img_btn.setBounds(_retryBtnImgRect);
		_menu_img_btn.setBounds(_menuBtnImgRect);
		_nextLvl_img_btn.setBounds(_nextLvlBtnImgRect);
		
		
	}
	
	private void checkColision(){
		
		for ( int i=0; i<_circleNumber-1; i++ ){
			for ( int j=i+1; j< _circleNumber; j++ ){
				colide(i,j);
			}
		}
		
		_phisicsToDo = PHISICS_TO_DO.WALL;
	}
	

	

	
	
	/**
	 * @desc method that will check for wall hitting and will redirect the circles;
	 */
	private void checkWallHit(){
		Iterator<MyCircle> circleIt = _theCircles.iterator();
		while(circleIt.hasNext()){
			MyCircle circ = circleIt.next();
			//circ.getTrajectory().getNextPoint();
			
			/*check if x walls are hit*/
			/* and if it is going in the right direction*/
			if (circ.getTrajectory().getCurrentPoint().x 
					>= 
				_screenWidth - _circleRadius                 && 
			    circ.getTrajectory().getXDirection() 
			    	== 
			    Trajectory.DIRECTION_X.RIGHT                 || 
			    circ.getTrajectory().getCurrentPoint().x 
			    	<= 
			    _circleRadius                                && 
			    circ.getTrajectory().getXDirection() 
			    	== 
			    Trajectory.DIRECTION_X.LEFT ){
				circ.getTrajectory().flipXDirection();
			}else if ( circ.getTrajectory().getCurrentPoint().y 
							>= 
					   _screenHeight - _circleRadius         &&
					   circ.getTrajectory().getYDirection() 
					   		== 
					   Trajectory.DIRECTION_Y.DOWN           ||
					   circ.getTrajectory().getCurrentPoint().y 
					   		<= 
					   _circleRadius                         && 
					   circ.getTrajectory().getYDirection() 
					   		== 
					   Trajectory.DIRECTION_Y.UP){
				circ.getTrajectory().flipYDirection();
			}
		}
		_phisicsToDo = PHISICS_TO_DO.COLLISION;
	}
	
	
	/**
	 * @desc  the method that will check if the circles collide between them.
	 * @param first
	 * @param second
	 */
	private void colide(int first, int second) {

		Point firstP = _theCircles.get(first).getTrajectory().getCurrentPoint();
		Point secondP = _theCircles.get(second).getTrajectory().getCurrentPoint();
		double distance = Math.sqrt((secondP.x - firstP.x)*(secondP.x - firstP.x) + (secondP.y - firstP.y)*(secondP.y - firstP.y));
		if ( distance < 2.5*_circleRadius )
		{
			Log.d("Colision", first + " with " +second );
			/*Collision detected*/
			if ( areGettingCloser(_theCircles.get(first).getTrajectory(), _theCircles.get(second).getTrajectory()) ){
				/*detect direction*/
				Trajectory.DIRECTION_X first_x_dir = _theCircles.get(first).getTrajectory().getXDirection();
				Trajectory.DIRECTION_X second_x_dir = _theCircles.get(second).getTrajectory().getXDirection();
				Trajectory.DIRECTION_Y first_y_dir = _theCircles.get(first).getTrajectory().getYDirection();
				Trajectory.DIRECTION_Y second_y_dir = _theCircles.get(second).getTrajectory().getYDirection();
				
				
				/*if the two circles are getting closer*/
				if ( first_x_dir == second_x_dir &&
					 first_y_dir != second_y_dir ){
					
					_theCircles.get(first).getTrajectory().flipYDirection();
					_theCircles.get(second).getTrajectory().flipYDirection();	
				}else if ( first_x_dir != second_x_dir &&
						   first_y_dir != second_y_dir ){
					_theCircles.get(first).getTrajectory().flipXDirection();
					_theCircles.get(first).getTrajectory().flipYDirection();
					_theCircles.get(second).getTrajectory().flipXDirection();
					_theCircles.get(second).getTrajectory().flipYDirection();
					
				}else if ( first_x_dir != second_x_dir &&
						   first_y_dir == second_y_dir ){
					_theCircles.get(first).getTrajectory().flipXDirection();
					_theCircles.get(second).getTrajectory().flipXDirection();
				}else if ( first_x_dir == second_x_dir &&
						   first_y_dir == second_y_dir ){
					/*flip x speed with y speed*/
					int prevFirstX = _theCircles.get(first).getTrajectory().getXSpeed();
					int prevFirstY = _theCircles.get(first).getTrajectory().getYSpeed();
					_theCircles.get(first).getTrajectory().setXSpeed(prevFirstY);
					_theCircles.get(first).getTrajectory().setYSpeed(prevFirstX);
					
					int prevSecX = _theCircles.get(second).getTrajectory().getXSpeed();
					int prevSecY = _theCircles.get(second).getTrajectory().getYSpeed();
					_theCircles.get(second).getTrajectory().setXSpeed(prevSecY);
					_theCircles.get(second).getTrajectory().setYSpeed(prevSecX);
				}
			}
			
		}
	}
	
	
	/**
	 * @desc method for detecting if two circles are getting closer
	 * @param traj1
	 * @param traj2
	 * @return true if the circles are getting closer
	 */
	private boolean areGettingCloser(Trajectory traj1, Trajectory traj2){
		Point traj1P1 = traj1.getCurrentPoint();
		Point traj2P1 = traj2.getCurrentPoint();
		Point traj1P2 = new Point();
		Point traj2P2 = new Point();
		
		/*
		 * Calculate the next point for the first trajectory
		 * */
		traj1P2 = traj1.testNextPoint();

		/*
		 * Calculate the next point for the second trajectory
		 * */
		traj2P2 = traj2.testNextPoint();
		
		/*
		 * Calculating distances
		 * */
		double d1 = Math.sqrt((traj2P1.x - traj1P1.x)*(traj2P1.x - traj1P1.x) + (traj2P1.y - traj1P1.y)*(traj2P1.y - traj1P1.y));
		double d2 = Math.sqrt((traj2P2.x - traj1P2.x)*(traj2P2.x - traj1P2.x) + (traj2P2.y - traj1P2.y)*(traj2P2.y - traj1P2.y));
		
		if ( d1 > d2 ){
			/*
			 * if first current distance is smaller then the future distance
			 * then the circles are getting closer
			 * */
			return true;
		}else{
			/*
			 * if first current distance is bigger or equal then the future distance
			 * then the circles are getting farther
			 * */
			return false;
		}
		
	}

	
	/**
	 * @desc   method for generating a random point in the display
	 * @return a random generated point in the display
	 */
	private Point generateRandomPoint(){
		Random rand = new Random();
		Point p = new Point();
		Boolean retry = true;
		while(retry){
			p.x = rand.nextInt(_screenWidth - 2*_circleRadius);
			p.y = rand.nextInt(_screenHeight - 2*_circleRadius);
			Iterator<MyCircle> circleIt = _theCircles.iterator();
			retry = false;
			while(circleIt.hasNext() && !retry){
				MyCircle circ = circleIt.next();
				if ( circ.getTrajectory().getCurrentPoint().x <= p.x+2*_circleRadius &&
					 circ.getTrajectory().getCurrentPoint().x >= p.x-2*_circleRadius && 
					 circ.getTrajectory().getCurrentPoint().y <= p.y+2*_circleRadius &&
					 circ.getTrajectory().getCurrentPoint().y >= p.y-2*_circleRadius ){
					retry = true;
				}
			}
		}
		return p;
	}
	
	
	/**
	 *  @desc method that will start the timer for observing step 
	 */
	private void startStageTimer(){
		
		Thread t = new Thread( new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(_observeTime);
					hideWinnerCircles();
					
					Thread.sleep(_seekTime);
					_gameState = GAME_STATES.CHOSE;
					hideWinnerCircles();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		
	}
	

	/**
	 * @desc method that will paint the winning circles in the other circle colors;
	 */
	private void hideWinnerCircles() {
		Iterator<MyCircle> circleIt = null;

		if ( _blinkFlag ){
			circleIt = _theCircles.iterator();
			while(circleIt.hasNext()){
	
				MyCircle circ = circleIt.next();
				circ.setPaint(_circlesPaint);
			}
		}else{
			circleIt = _theCircles.iterator();
			while(circleIt.hasNext()){
				circleIt.next().setPaint(_circlesPaint);
			}
		}
		_winCircleHidden = true;
	}
	
	/**
	 * @DESC method for painting the winning circles into the winning color
	 */
	private void revealWinningCircles(){

		Iterator<MyCircle> circleIt = null;

		if ( _blinkFlag ){
			circleIt = _theCircles.iterator();
			while(circleIt.hasNext()){
	
				MyCircle circ = circleIt.next();
				circ.setPaint(_circlesPaint);
			}
		}
		circleIt = _winningCircle.iterator();
		while(circleIt.hasNext()){

			MyCircle circ = circleIt.next();
			circ.setPaint(_winningCirclePaint);
		}
		
		_winCircleHidden = false;
	}
	
	/**
	 * @desc method called when the player misses the balls
	 */
	private void doMiss(){
		/*start status display thread*/
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					/*display the winning ball/s*/
					revealWinningCircles();
					Thread.sleep(_STATUS_WAIT_TIME);
					/*wait for user input*/
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		t.start();
	}
	
	/**
	 * @desc method that starts the threads for displaying the messages in case of winning
	 */
	private void doWin(){
		/*start status display thread*/
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					/*display the winning ball/s*/
					revealWinningCircles();
					Thread.sleep(_STATUS_WAIT_TIME);
					/*wait for user input*/
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		t.start();
	}
	
	/**
	 * @desc restarts the game if the user does not guess the circle
	 */
	private void restart(){		
		regenerateCirclePositions();
		_winningCircle.get(0).setPaint(_winningCirclePaint);
		startStageTimer();
		
	}
	

	
	
	/**
	 * @desc checks to see if a point with the passed coordinates hits any of the circles
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean touchedCircle(int x, int y){
		
		Iterator<MyCircle> circleIt = _theCircles.iterator();
		while(circleIt.hasNext()){

			MyCircle circ = circleIt.next();
			int winX = circ.getTrajectory().getCurrentPoint().x;
			int winY = circ.getTrajectory().getCurrentPoint().y;
			
			if ( x <= winX + _circleRadius && 
				 x >= winX - _circleRadius && 
				 y <= winY + _circleRadius &&
				 y >= winY - _circleRadius){
				/*touched the winning circle*/
				return true;
			}
		}
		
		return false;
	}

	public void set_speed(int _speed) {
		this._speed = _speed;
	}

	public void set_circleNumber(int _circleNumber) {
		this._circleNumber = _circleNumber;
	}

	public void set_circleRadius(int _circleRadius) {
		this._circleRadius = _circleRadius;
	}

	public void set_observeTime(long _observeTime) {
		this._observeTime = _observeTime;
	}

	public void set_seekTime(long _seekTime) {
		this._seekTime = _seekTime;
	}

	public void set_backgroundPaint(int _backgroundPaint) {
		this._backgroundPaint = new Paint(_backgroundPaint);
	}

	public void set_circlesPaint(int _circlesPaint) {
		this._circlesPaint = new Paint(_circlesPaint);
	}

	public void set_winningCirclePaint(int _winningCirclePaint) {
		this._winningCirclePaint = new Paint(_winningCirclePaint);
	}

	public void set_winningCircleNo(int _winningCircleNo) {
		this._winningCircleNo = _winningCircleNo;
	}

	public void set_blinkFlag(boolean _blinkFlag) {
		this._blinkFlag = _blinkFlag;
	}




}

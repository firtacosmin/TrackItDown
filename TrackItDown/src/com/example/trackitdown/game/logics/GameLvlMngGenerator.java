package com.example.trackitdown.game.logics;

import android.content.Context;
import android.content.res.Resources;

import com.example.trackitdown.R;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl1;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl2;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl3;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl4;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl5;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl6;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl7;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl8;
import com.example.trackitdown.game.logics.levelManagers.GameLvlMng_lvl9;

public class GameLvlMngGenerator {

	public static enum LEVELS{LEVEL_1, LEVEL_2, LEVEL_3, LEVEL_4, LEVEL_5, 
		                      LEVEL_6, LEVEL_7, LEVEL_8, LEVEL_9, LEVEL_10, 
		                      LEVEL_11, LEVEL_12, LEVEL_13, LEVEL_14, LEVEL_15, 
		                      LEVEL_16, LEVEL_17, LEVEL_18, LEVEL_19, LEVEL_20, 
		                      LEVEL_21, LEVEL_22, LEVEL_23, LEVEL_24, LEVEL_25, 
		                      LEVEL_26, LEVEL_27, LEVEL_28, LEVEL_29, LEVEL_30};
	
	private static LEVELS _currentLevel;
	
	public static GameLvlMng getLvl(LEVELS lvl, Context c){
		_currentLevel = lvl;
		
		Resources res = c.getResources();
		int[] bits = res.getIntArray(R.array.levels);
		int lvl_no = lvl.ordinal() + 1;
		
		int circle_no = 0;
		int winning_circle_no = 0;
		int circle_speed = 0;
		int observe_time = 0;
		int seek_time = 0;
		int circle_radius = 0;
		int circle_color = 0;
		int win_circ_color = 0;
		int back_color = 0;
		boolean blink_flg = true;
		
		
		
		GameLvlMng_lvl lvlMng = null;
		switch(lvl){
		case LEVEL_1:
			lvlMng = new GameLvlMng_lvl1(c);
//			 circle_no = res.getInteger(R.integer.circle_no_1);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_1);
//			 circle_speed = res.getInteger(R.integer.circle_speed_1);
//			 observe_time = res.getInteger(R.integer.observe_time_1);
//			 seek_time = res.getInteger(R.integer.seek_time_1);
//			 circle_radius = res.getInteger(R.integer.circle_radius_1);
//			 circle_color = res.getColor(R.color.circle_img_1);
//			 win_circ_color = res.getColor(R.color.win_circle_img_1);
//			 back_color = res.getColor(R.color.back_img_1);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_1);
			break;
		case LEVEL_2:
			lvlMng = new GameLvlMng_lvl2();
//			 circle_no = res.getInteger(R.integer.circle_no_2);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_2);
//			 circle_speed = res.getInteger(R.integer.circle_speed_2);
//			 observe_time = res.getInteger(R.integer.observe_time_2);
//			 seek_time = res.getInteger(R.integer.seek_time_2);
//			 circle_radius = res.getInteger(R.integer.circle_radius_2);
//			 circle_color = res.getColor(R.color.circle_img_2);
//			 win_circ_color = res.getColor(R.color.win_circle_img_2);
//			 back_color = res.getColor(R.color.back_img_2);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_2);
			break;
		case LEVEL_3:
			lvlMng = new GameLvlMng_lvl3();
//			 circle_no = res.getInteger(R.integer.circle_no_3);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_3);
//			 circle_speed = res.getInteger(R.integer.circle_speed_3);
//			 observe_time = res.getInteger(R.integer.observe_time_3);
//			 seek_time = res.getInteger(R.integer.seek_time_3);
//			 circle_radius = res.getInteger(R.integer.circle_radius_3);
//			 circle_color = res.getColor(R.color.circle_img_3);
//			 win_circ_color = res.getColor(R.color.win_circle_img_3);
//			 back_color = res.getColor(R.color.back_img_3);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_3);
			break;
		case LEVEL_4:
			lvlMng = new GameLvlMng_lvl4();
//			 circle_no = res.getInteger(R.integer.circle_no_4);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_4);
//			 circle_speed = res.getInteger(R.integer.circle_speed_4);
//			 observe_time = res.getInteger(R.integer.observe_time_4);
//			 seek_time = res.getInteger(R.integer.seek_time_4);
//			 circle_radius = res.getInteger(R.integer.circle_radius_4);
//			 circle_color = res.getColor(R.color.circle_img_4);
//			 win_circ_color = res.getColor(R.color.win_circle_img_4);
//			 back_color = res.getColor(R.color.back_img_4);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_4);
			break;
		case LEVEL_5:
			lvlMng = new GameLvlMng_lvl5();
//			 circle_no = res.getInteger(R.integer.circle_no_5);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_5);
//			 circle_speed = res.getInteger(R.integer.circle_speed_5);
//			 observe_time = res.getInteger(R.integer.observe_time_5);
//			 seek_time = res.getInteger(R.integer.seek_time_5);
//			 circle_radius = res.getInteger(R.integer.circle_radius_5);
//			 circle_color = res.getColor(R.color.circle_img_5);
//			 win_circ_color = res.getColor(R.color.win_circle_img_5);
//			 back_color = res.getColor(R.color.back_img_5);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_5);
			break;
		case LEVEL_6:
			lvlMng = new GameLvlMng_lvl6();
//			 circle_no = res.getInteger(R.integer.circle_no_6);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_6);
//			 circle_speed = res.getInteger(R.integer.circle_speed_6);
//			 observe_time = res.getInteger(R.integer.observe_time_6);
//			 seek_time = res.getInteger(R.integer.seek_time_6);
//			 circle_radius = res.getInteger(R.integer.circle_radius_6);
//			 circle_color = res.getColor(R.color.circle_img_6);
//			 win_circ_color = res.getColor(R.color.win_circle_img_6);
//			 back_color = res.getColor(R.color.back_img_6);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_6);
			break;
		case LEVEL_7:
			lvlMng = new GameLvlMng_lvl7();
//			 circle_no = res.getInteger(R.integer.circle_no_7);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_7);
//			 circle_speed = res.getInteger(R.integer.circle_speed_7);
//			 observe_time = res.getInteger(R.integer.observe_time_7);
//			 seek_time = res.getInteger(R.integer.seek_time_7);
//			 circle_radius = res.getInteger(R.integer.circle_radius_7);
//			 circle_color = res.getColor(R.color.circle_img_7);
//			 win_circ_color = res.getColor(R.color.win_circle_img_7);
//			 back_color = res.getColor(R.color.back_img_7);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_7);
			break;
		case LEVEL_8:
			lvlMng = new GameLvlMng_lvl8();
//			 circle_no = res.getInteger(R.integer.circle_no_8);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_8);
//			 circle_speed = res.getInteger(R.integer.circle_speed_8);
//			 observe_time = res.getInteger(R.integer.observe_time_8);
//			 seek_time = res.getInteger(R.integer.seek_time_8);
//			 circle_radius = res.getInteger(R.integer.circle_radius_8);
//			 circle_color = res.getColor(R.color.circle_img_8);
//			 win_circ_color = res.getColor(R.color.win_circle_img_8);
//			 back_color = res.getColor(R.color.back_img_8);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_8);
			break;
		case LEVEL_9:
			lvlMng = new GameLvlMng_lvl9();
//			 circle_no = res.getInteger(R.integer.circle_no_9);
//			 winning_circle_no = res.getInteger(R.integer.winning_circle_no_9);
//			 circle_speed = res.getInteger(R.integer.circle_speed_9);
//			 observe_time = res.getInteger(R.integer.observe_time_9);
//			 seek_time = res.getInteger(R.integer.seek_time_9);
//			 circle_radius = res.getInteger(R.integer.circle_radius_9);
//			 circle_color = res.getColor(R.color.circle_img_9);
//			 win_circ_color = res.getColor(R.color.win_circle_img_9);
//			 back_color = res.getColor(R.color.back_img_9);
//			 blink_flg = res.getBoolean(R.bool.blink_flag_9);
			break;
		default:
			lvlMng = new GameLvlMng_lvl1(c);
			break;
		}
		 
		

		
//		GameLvlMng_lvl lvl_mng = new GameLvlMng_lvl(true);
//		lvl_mng.set_circleNumber(circle_no);
//		lvl_mng.set_winningCircleNo(winning_circle_no);
//		lvl_mng.set_speed(circle_speed);
//		lvl_mng.set_observeTime(observe_time);
//		lvl_mng.set_seekTime(seek_time);
//		lvl_mng.set_circleRadius(circle_radius);
//		lvl_mng.set_circlesPaint(circle_color);
//		lvl_mng.set_winningCirclePaint(win_circ_color);
//		lvl_mng.set_backgroundPaint(back_color);
//		lvl_mng.set_blinkFlag(blink_flg);
//
//		lvl_mng.generateStartRandomCircles();
		
		return lvlMng;
	}
	
	public static GameLvlMng nextLevel(Context c){
		GameLvlMng lvlMng = null;
		
		LEVELS new_lvl = LEVELS.values()[_currentLevel.ordinal() + 1];
		lvlMng = getLvl(new_lvl, c);
//		
		switch(_currentLevel){
		case LEVEL_1:
			lvlMng = getLvl(LEVELS.LEVEL_2, c);
			_currentLevel = LEVELS.LEVEL_2;
			break;
		case LEVEL_2:
			lvlMng = new GameLvlMng_lvl3();
			_currentLevel = LEVELS.LEVEL_3;
			break;
		case LEVEL_3:
			lvlMng = new GameLvlMng_lvl4();
			_currentLevel = LEVELS.LEVEL_4;
			break;
		case LEVEL_4:
			lvlMng = new GameLvlMng_lvl5();
			_currentLevel = LEVELS.LEVEL_5;
			break;
		case LEVEL_5:
			lvlMng = new GameLvlMng_lvl6();
			_currentLevel = LEVELS.LEVEL_6;
			break;
		case LEVEL_6:
			lvlMng = new GameLvlMng_lvl7();
			_currentLevel = LEVELS.LEVEL_7;
			break;
		case LEVEL_7:
			lvlMng = new GameLvlMng_lvl8();
			_currentLevel = LEVELS.LEVEL_8;
			break;
		case LEVEL_8:
			lvlMng = new GameLvlMng_lvl1(c);
			_currentLevel = LEVELS.LEVEL_1;
			break;
		default:
			lvlMng = new GameLvlMng_lvl1(c);
			_currentLevel = LEVELS.LEVEL_1;
			break;
		}
		 
		return lvlMng;
	}
	
	
	
	
}

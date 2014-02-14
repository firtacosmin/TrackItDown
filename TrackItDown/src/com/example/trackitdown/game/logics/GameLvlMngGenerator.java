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
		
		GameLvlMng_lvl lvlMng = null;
		switch(lvl){
		case LEVEL_1:
			lvlMng = new GameLvlMng_lvl1();
			break;
		case LEVEL_2:
			lvlMng = new GameLvlMng_lvl2();
			break;
		case LEVEL_3:
			lvlMng = new GameLvlMng_lvl3();
			break;
		case LEVEL_4:
			lvlMng = new GameLvlMng_lvl4();
			break;
		case LEVEL_5:
			lvlMng = new GameLvlMng_lvl5();
			break;
		case LEVEL_6:
			lvlMng = new GameLvlMng_lvl6();
			break;
		case LEVEL_7:
			lvlMng = new GameLvlMng_lvl7();
			break;
		case LEVEL_8:
			lvlMng = new GameLvlMng_lvl8();
			break;
		case LEVEL_9:
			lvlMng = new GameLvlMng_lvl9();
			break;
		default:
			lvlMng = new GameLvlMng_lvl1();
			break;
		}
		 
		return lvlMng;
	}
	
	public static GameLvlMng nextLevel(){
		GameLvlMng_lvl lvlMng = null;
		switch(_currentLevel){
		case LEVEL_1:
			lvlMng = new GameLvlMng_lvl2();
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
			lvlMng = new GameLvlMng_lvl1();
			_currentLevel = LEVELS.LEVEL_1;
			break;
		default:
			lvlMng = new GameLvlMng_lvl1();
			_currentLevel = LEVELS.LEVEL_1;
			break;
		}
		 
		return lvlMng;
	}
	
	
	
	
}

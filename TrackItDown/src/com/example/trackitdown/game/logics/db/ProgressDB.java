package com.example.trackitdown.game.logics.db;

import android.content.Context;

public interface ProgressDB {

	/**
	 * @desc method for returning the singleton for Progress table manipulation
	 * @param c
	 * @return the instance of Progress
	 */
	public int getProgress();
	public void setProgress(int level);
	
}

package com.example.trackitdown.game.logics.db;

import android.provider.BaseColumns;

public class ProgressContract {
	
	public ProgressContract(){}
	
	/* Inner class that defines the table contents */
    public static abstract class Progress implements BaseColumns {
    	public static final String TABLE_NAME="progress";
    	public static final String COLUMN_CURRENT_LEVEL="currentlevel";
    }
}

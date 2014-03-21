package com.example.trackitdown.game.logics.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.trackitdown.game.logics.db.ProgressContract.Progress;

public class ProgressWrapper implements ProgressDB {

	private static ProgressWrapper _theInstance;
	private Context _theContext;
	
	private ProgressDbHelper _dbHelper;
	
	private ProgressWrapper(Context theContext){
		_theContext = theContext;
		_dbHelper = new ProgressDbHelper(_theContext);
	}

	/* (non-Javadoc)
	 * @see com.example.trackitdown.game.logics.db.Progress#getInstance(android.content.Context)
	 */
	public static ProgressDB getInstance(Context c) {
		if ( _theInstance == null ){
			_theInstance = new ProgressWrapper(c);
		}
		return _theInstance;
	}

	@Override
	public int getProgress() {
		
		int level = 0;
		SQLiteDatabase db = _dbHelper.getReadableDatabase();
		String[] columns={Progress._ID,
						  Progress.COLUMN_CURRENT_LEVEL};
		Cursor c ;
		try{
		c = db.query(Progress.TABLE_NAME, columns, null, null, null, null, null);
		}
		catch(Exception x){
			Log.e("SQListe exception", "Exception: "+Log.getStackTraceString(x));
			c = null;
		}
		if ( c != null && c.moveToFirst()){
			level = c.getInt(c.getColumnIndexOrThrow(Progress.COLUMN_CURRENT_LEVEL));
		}else{
			/*the current level is not saved in the database*/
			/*add level 0*/
			SQLiteDatabase db1 = _dbHelper.getWritableDatabase();
			db1.execSQL("INSERT INTO "+Progress.TABLE_NAME+" ("+Progress._ID+","+Progress.COLUMN_CURRENT_LEVEL+")values (1, 0)");
			db1.close();
		}
		
		db.close();
				
		return level;
	}

	@Override
	public void setProgress(int level) {
		SQLiteDatabase db = _dbHelper.getWritableDatabase();
		String sql = "UPDATE " + Progress.TABLE_NAME + " set "+ Progress.COLUMN_CURRENT_LEVEL + " = "+level;
		db.execSQL(sql);
		db.close();
	}
	
	
}

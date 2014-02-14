package com.example.trackitdown.game.drawable;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.ViewGroup;

import com.example.trackitdown.R;

public class LvlResultView extends ViewGroup implements SurfaceHolder.Callback{

	public LvlResultView(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.retry_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }
    
    public void onLayout(boolean bol, int a, int b, int c, int d ){
    	
    }

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		
	}
 
}

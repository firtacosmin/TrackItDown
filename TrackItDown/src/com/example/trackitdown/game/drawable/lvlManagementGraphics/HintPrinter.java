package com.example.trackitdown.game.drawable.lvlManagementGraphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;

public class HintPrinter {

	private int _textColor;
	private int _circleColor;
	private int _circleRadius;
	private int _textBackgroundColor;
	private Rect _textPosition;
	public HintPrinter(){
		
		_textColor = Color.BLACK;
		_textBackgroundColor = Color.GRAY;
		_circleColor = Color.RED;
		_circleRadius = 35;
		
		_textPosition = new Rect();
		_textPosition.top = 10;
		_textPosition.left = 10;
		_textPosition.bottom = 50;
		_textPosition.right = 210;
	}
	
	public void printHintMessage(String message, Canvas c){
		Paint p = new Paint();
		/*draw Text*/
		/*  - draw background*/
		p.setColor(_textBackgroundColor);
		p.setAlpha(150);
		p.setStyle(Paint.Style.FILL);
		c.drawRect(_textPosition, p);
		/*  - draw background frame*/
		p.setStyle(Paint.Style.STROKE);
		p.setColor(_textColor);
		c.drawRect(_textPosition, p);
		/*  - draw text*/
		p.setStyle(Paint.Style.FILL_AND_STROKE);
		p.setColor(_textColor);
		p.setTextSize(calculateTxtSize(message));
		c.drawText(message, _textPosition.left + 3, _textPosition.top + (_textPosition.bottom - _textPosition.top)/2, p);
		
	}
	
	public void printHintCircle(Point location, Canvas c){
		
		try{
			Paint p = new Paint();
			
			/*draw circle*/
			p.setColor(_circleColor);
			p.setStyle(Paint.Style.STROKE);
			c.drawCircle(location.x, location.y, _circleRadius, p);
			
		}catch(Exception ex){
			Log.d("HintPrinter Error", ex.getMessage());
		}
		
	}

	public int get_circleRadius() {
		return _circleRadius;
	}

	public void set_circleRadius(int _circleRadius) {
		this._circleRadius = _circleRadius;
	}

	public Rect get_textPosition() {
		return _textPosition;
	}

	public void set_textPosition(Rect _textPosition) {
		this._textPosition = _textPosition;
	}

	public void set_textColor(int _textColor) {
		this._textColor = _textColor;
	}

	public void set_circleColor(int _circleColor) {
		this._circleColor = _circleColor;
	}

	public void set_textBackgroundColor(int _textBackgroundColor) {
		this._textBackgroundColor = _textBackgroundColor;
	}

	
	private int calculateTxtSize(String txt){
	    int size = 0;       
	    Paint paint = new Paint();

	    do {
	        paint.setTextSize(++ size);
	    } while(paint.measureText(txt) < _textPosition.width());
	    
	    return size;

	}
	
}

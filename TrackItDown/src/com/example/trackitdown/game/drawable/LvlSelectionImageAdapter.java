package com.example.trackitdown.game.drawable;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.trackitdown.R;

public class LvlSelectionImageAdapter extends BaseAdapter{

	
	private Context _theContext;
	
	public LvlSelectionImageAdapter(Context c){
		_theContext = c;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _thumbs.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		return _thumbs[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView img;
		if ( convertView == null ){// if it's not recycled, initialize some attributes
			img = new ImageView(_theContext);
			img.setLayoutParams(new GridView.LayoutParams(85, 85));
			img.setScaleType(ImageView.ScaleType.CENTER_CROP);
			img.setPadding(8, 8, 8, 8);
		}else{
			img = (ImageView) convertView;
		}
		img.setImageResource(_thumbs[position]);
		return img;
	}
	
	// references to our images
    private Integer[] _thumbs = {
    		R.drawable.lvl_1, R.drawable.lvl_2, 
    		R.drawable.lvl_3, R.drawable.lvl_4, 
    		R.drawable.lvl_5, R.drawable.lvl_6, 
    		R.drawable.lvl_7, R.drawable.lvl_8, 
    		R.drawable.lvl_9, R.drawable.lvl_10, 
    		R.drawable.lvl_11, R.drawable.lvl_12, 
    		R.drawable.lvl_13, R.drawable.lvl_14, 
    		R.drawable.lvl_15, R.drawable.lvl_16, 
    		R.drawable.lvl_17, R.drawable.lvl_18, 
    		R.drawable.lvl_19, R.drawable.lvl_20
    };

}

package com.example.dial;

import android.content.Context;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CustomRelativeLayout extends RelativeLayout{

	private Context mContext;
	private Button exitBtn;
	
    private WindowManager wm = (WindowManager) getContext()
            .getApplicationContext().getSystemService("window");
    private WindowManager.LayoutParams wmParams = null;


	public CustomRelativeLayout(Context context, WindowManager.LayoutParams params) {
		
		super(context);
		mContext = context;
		wmParams = params;
		
		
	}

}

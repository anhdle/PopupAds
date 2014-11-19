package com.example.dial;

import java.util.Timer;

import android.app.ActionBar.LayoutParams;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomPhoneStateListener extends PhoneStateListener {

	
    //private static final String TAG = "PhoneStateChanged";
    private static Context context; //Context to make Toast if required
     
    LinearLayout ly = null;
    boolean removed = true;

    TextView text;
    public CustomPhoneStateListener(Context c) {
        super();
        this.context = c;        
    }

    @Override
    public void onCallStateChanged( int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        switch (state) {
        case TelephonyManager.CALL_STATE_IDLE:
            //when Idle i.e no call
        	
        	//Toast.makeText(context, "Phone state Idle", Toast.LENGTH_LONG).show();
        	removePop(context);
        			
        		
        	
            
            
            break;
        case TelephonyManager.CALL_STATE_OFFHOOK:
            //when Off hook i.e in call
            //Make intent and start your service here
        	// Toast.makeText(context, "Phone state Off hook", Toast.LENGTH_LONG).show();
        	
        	
        	 
        	 WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.TYPE_SYSTEM_ALERT |
                WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);

            params.height = 300;
            params.width = LayoutParams.MATCH_PARENT;
            params.format = PixelFormat.TRANSLUCENT;

            params.gravity = Gravity.CENTER;
          //  called = true;
            
            
            if(ly != null)
            	removePop(context);
            //ly.setBackgroundColor(Color.BLUE);
            ly = new LinearLayout(context);
            ly.setBackgroundResource(R.drawable.ads);
            ly.setOrientation(LinearLayout.VERTICAL);
        //    text.setText("123-4567");
         //   ly.addView(text);
        	wm.addView(ly, params);
        	removed = false;
        	/*
        	
        	 final Handler handler = new Handler();
        	 handler.postDelayed(new Runnable() 
        	 {
        	   @Override
        	   public void run() {
        		
        		   Toast.makeText(context, String.valueOf(removed), Toast.LENGTH_LONG).show();
    			
        	   }
        		   
        	   
        	 }, 10000);*/
        	// wm.removeView(ly);

            

           
            break;
        case TelephonyManager.CALL_STATE_RINGING:
            //when Ringing
        	

           // Toast.makeText(context, "Phone state Ringing", Toast.LENGTH_LONG).show();
            
            break;
        default:
            break;
        }
    }
    
    
    public void removePop(Context c){
    	WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
    	if (ly!= null){
    		ly.removeAllViews();
			wm.removeView(ly);
			ly = null;
    	}
		
    }
    



}
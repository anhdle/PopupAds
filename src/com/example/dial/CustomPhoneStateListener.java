package com.example.dial;

import java.util.Timer;


import com.example.dial.OverlayService;

import android.app.ActionBar.LayoutParams;
import android.app.ActivityManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;

import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


	
   
    
     
 
public class CustomPhoneStateListener extends BroadcastReceiver{

	Context context;
	
	public void onReceive(Context context, Intent intent){
		this.context = context;
        TelephonyManager tmgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                
        //Create Listener
    CustomStateListener PhoneListener = new CustomStateListener();
    
    // Register listener for LISTEN_CALL_STATE
    tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);
	
	}

	
	
    public class CustomStateListener extends PhoneStateListener{
    	
    	
        RelativeLayout ly;
        Button mailButton;
        Button bookButton;
        Button laterButton;
        boolean booked = false;
        

	    @Override
	    public void onCallStateChanged( int state, String incomingNumber) {
	        super.onCallStateChanged(state, incomingNumber);
	
	        switch (state) {
	        case TelephonyManager.CALL_STATE_IDLE:
	            //when Idle i.e no call
	        	
	        	//Toast.makeText(context, "Phone state Idle", Toast.LENGTH_LONG).show();
	        	removePop(context);
	        	
	        	if(booked){
	        		booked = false;
	        		toggleService();
	        		
	        	}
	        			
	        		
	        	
	            
	            
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
	
	            params.height = LayoutParams.WRAP_CONTENT;
	            params.width = LayoutParams.MATCH_PARENT;
	            params.format = PixelFormat.TRANSLUCENT;
	
	            params.gravity = Gravity.CENTER;
	            
	            mailButton = new Button(context);
	            //mailButton.setClickable(true);
	            mailButton.setText("Email");
	            
	
	            mailButton.setTextColor(Color.BLACK);
	            mailButton.setBackgroundColor(Color.WHITE);
	            mailButton.setAlpha((float) 0.5);
	            mailButton.setScaleX((float) 0.7);
	            mailButton.setScaleY((float) 0.7);
	            //mailButton.setPadding(-5,-5,-5,-5);
	            mailButton.setId(1);
	            bookButton = new Button(context);
	            //mailButton.setClickable(true);
	            bookButton.setText("Book");
	
	            bookButton.setTextColor(Color.BLACK);
	            bookButton.setBackgroundColor(Color.WHITE);
	            bookButton.setAlpha((float) 0.5);
	            bookButton.setScaleX((float) 0.7);
	            bookButton.setScaleY((float) 0.7);
	            //bookButton.setPadding(-5,-5,-5,-5);
	            bookButton.setId(2);
	            laterButton = new Button(context);
	            //mailButton.setClickable(true);
	            laterButton.setText("Later");
	          
	            laterButton.setTextColor(Color.BLACK);
	            laterButton.setBackgroundColor(Color.WHITE);
	            laterButton.setAlpha((float) 0.5);
	            laterButton.setScaleX((float) 0.7);
	            laterButton.setScaleY((float) 0.7);
	            //laterButton.setPadding(-5,-5,-5,-5);
	            laterButton.setId(3);
	            
	
	            RelativeLayout.LayoutParams mparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	            mparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            mparams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
	            mparams.setMargins(-27, -20, -27, 0);
	            
	            
	
	            RelativeLayout.LayoutParams bparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	            bparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            bparams.addRule(RelativeLayout.LEFT_OF,1);
	            bparams.setMargins(-27, -20, -27, 0);
	            
	            
	            RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
	            lparams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
	            lparams.addRule(RelativeLayout.LEFT_OF,2);
	            lparams.setMargins(-27, -20, -27, 0);
	            if(ly != null)
	            	removePop(context);
	            //ly.setBackgroundColor(Color.BLUE);
	            ly = new RelativeLayout(context);
	            ly.setBackgroundResource(R.drawable.disney);
	           
	        	
	
	            ly.addView(mailButton,mparams);
	            ly.addView(bookButton,bparams);
	            ly.addView(laterButton,lparams);
	            wm.addView(ly, params);
	            
	            bookButton.setOnClickListener(new OnClickListener(){
	            	
	            	@Override
	                public void onClick(View v) {
	                  //  Toast.makeText(v.getContext(), "adding to blacklist..", Toast.LENGTH_LONG).show();
	                  //  v.setBackgroundDrawable(ct.getResources().getDrawable(R.drawable.images));
	            		
	            		booked = true;
	            		
	
	            	    	
	   
	                }
	            	
	            })    ;
	        	
	        	
	        	 final Handler handler = new Handler();
	        	 handler.postDelayed(new Runnable() 
	        	 {
	        	   @Override
	        	   public void run() {
	        		
	        		   removePop(context);
	    			
	        	   }
	        		   
	        	   
	        	 }, 10000);
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
	    	
	    	if (ly!= null){
	    		WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
	    		ly.removeAllViews();
				wm.removeView(ly);
				ly = null;
	    	}
			
	    }
    };
    private void toggleService(){
        
        // Try to stop the service if it is already running
        // Otherwise start the service
	
		
		Intent overlayIntent=new Intent(context, OverlayService.class);
		overlayIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if(!context.stopService(overlayIntent)){
            context.startService(overlayIntent);
        }
    }

}




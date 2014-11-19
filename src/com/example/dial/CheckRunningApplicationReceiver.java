package com.example.dial;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActionBar.LayoutParams;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;
 
public class CheckRunningApplicationReceiver extends BroadcastReceiver {
 
    public final String TAG = "CRAR"; // CheckRunningApplicationReceiver
    private static Context context; //Context to make Toast if required
    
    LinearLayout ly = null;
    boolean removed = true;
    public int limit = 1;
 
    @Override
    public void onReceive(final Context aContext, Intent anIntent) {
         
        try {
             
            // Using ACTIVITY_SERVICE with getSystemService(String)
            // to retrieve a ActivityManager for interacting with the global system state.
             
            ActivityManager am = (ActivityManager) aContext
                    .getSystemService(Context.ACTIVITY_SERVICE);
             
            // Return a list of the tasks that are currently running,
            // with the most recent being first and older ones after in order.
            // Taken 1 inside getRunningTasks method means want to take only
            // top activity from stack and forgot the olders.
             
            List<ActivityManager.RunningTaskInfo> alltasks = am.getRunningTasks(1);
            //
            
              for (ActivityManager.RunningTaskInfo aTask : alltasks) {
            	//Toast.makeText(aContext,aTask.baseActivity.getClassName(), Toast.LENGTH_LONG).show();  
            	
                 
                // Used to check for CALL screen 
                 
                if (aTask.topActivity.getClassName().equals("com.android.phone.InCallScreen")
                    || aTask.topActivity.getClassName().equals("com.android.contacts.DialtactsActivity"))
                {
                    // When user on call screen show a alert message
                    Toast.makeText(aContext, "Phone Call Screen.", Toast.LENGTH_LONG).show();  
                }
                 
                // Used to check for SMS screen
                 
                if (aTask.topActivity.getClassName().equals("com.android.mms.ui.ConversationList")
                        || aTask.topActivity.getClassName().equals("com.android.mms.ui.ComposeMessageActivity"))
                {
                    // When user on Send SMS screen show a alert message
                	if(limit > 0){
                		--limit;
	                    WindowManager wm = (WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE);
	
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
	
	                    params.gravity = Gravity.TOP;
	                  //  called = true;
	                    
	                    
	                    if(ly != null)
	                    	removePop(aContext);
	                    //ly.setBackgroundColor(Color.BLUE);
	                    ly = new LinearLayout(aContext);
	                    ly.setBackgroundResource(R.drawable.ads);
	                    ly.setOrientation(LinearLayout.VERTICAL);
	                    
	                    wm.addView(ly, params);
	                	
	                	
	                	
	                	 final Handler handler = new Handler();
	                	 handler.postDelayed(new Runnable() 
	                	 {
	                	   @Override
	                	   public void run() {
	                		
	                		   
	                		   removePop(aContext);
	                		   
	            			
	                	   }
	                		   
	                	   
	                	 }, 5000);
	                	 
	                	 
                	}
                    Toast.makeText(aContext, "Send SMS Screen.", Toast.LENGTH_LONG).show();
                	
                }
                 
                 
                // Used to check for CURRENT example main screen
                 
                String packageName = "com.example.checkcurrentrunningapplication";
                 
                if (aTask.topActivity.getClassName().equals(
                        packageName + ".Main"))
                {
                   // When opens this example screen then show a alert message
                   Toast.makeText(aContext, "Check Current Running Application Example Screen.",
                                   Toast.LENGTH_LONG).show();  
                }
                 
                 
                // These are showing current running activity in logcat with
                // the use of different methods
                 
                Log.i(TAG, "===============================");
                 
                    Log.i(TAG, "aTask.baseActivity: "
                                + aTask.baseActivity.flattenToShortString());
                     
                    Log.i(TAG, "aTask.baseActivity: "
                                + aTask.baseActivity.getClassName());
                     
                    Log.i(TAG, "aTask.topActivity: "
                                + aTask.topActivity.flattenToShortString());
                     
                    Log.i(TAG, "aTask.topActivity: "
                                + aTask.topActivity.getClassName());
                 
                Log.i(TAG, "===============================");
                 
                 
            }
 
        } catch (Throwable t) {
            Log.i(TAG, "Throwable caught: "
                        + t.getMessage(), t);
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
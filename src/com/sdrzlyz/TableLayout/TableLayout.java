package com.sdrzlyz.TableLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class TableLayout extends Activity {
    /**
     * Called when the activity is first created.
     */

    private int currentColor = 0;
    final int[] colors = new int[] {
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6};

    final int[] names =new int[]{
            R.id.view01,
            R.id.view02,
            R.id.view03,
            R.id.view04,
            R.id.view05,
            R.id.view06};

    TextView[] views = new TextView[names.length];

    //to handle message send from app,if receives right msg, the code inside will run.
    Handler handler =new Handler(){
      @Override
    public void handleMessage(Message msg){
          if (msg.what == 0x123){
              for (int i=0;i<names.length;i++){
                 views[i].setBackgroundResource(colors[(i+currentColor)%names.length]);
              }
              currentColor++;
          }
          super.handleMessage(msg);
      }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame);
        for (int i =0;i<names.length; i++){
            views[i] = (TextView) findViewById(names[i]);
        }

        //param2 indicate that the delay of first-time run
        //param3 indicate that the delay between two loop
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        },0,5);
    }
}

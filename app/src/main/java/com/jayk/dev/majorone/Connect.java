package com.jayk.dev.majorone;

/*
* This Activity plays major role in our project.
* we have to start our connection to network from this Acivity itself.
* it consists of a logout option,
* EditText to enter remoteId,
* button to Connect to remotePhone.
* It consists of TextView which notifys your connection status
* 1.Not Connected
* 2.Ready to Connect
* 3.Someone is about to Connect
* 4.what else??
*
* Once connected we will be redirected to REmoteControlActivity
* */

//Note:Don't include the code in repository without testing


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Connect extends AppCompatActivity {

   Button b;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_connect);
   }
}

package com.jayk.dev.majorone;


/*
* Splash Screen of Remoter which redirects the new user to
* LoginOrRegister Activity . If the user is once logged in
* the Splash Screen will redirect directly to ConnectActivity
* */

//Note:Don't include the code in repository without testing

import android.app.Activity;
import android.os.Bundle;

public class SplashAcitvity extends Activity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash_acitvity);
   }
}

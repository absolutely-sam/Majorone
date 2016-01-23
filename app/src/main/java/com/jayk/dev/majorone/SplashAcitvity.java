package com.jayk.dev.majorone;


/*
* Splash Screen of Remoter which redirects the new user to
* LoginOrRegister Activity . If the user is once logged in
* the Splash Screen will redirect directly to ConnectActivity
* */

//Note:Don't include the code in repository without testing

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashAcitvity extends Activity {

   private final String MY_PREFERENCE = "ReMoTeR";
   private final String USER = "username";
   private final String ERROR = "NOT_FOUND";

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_splash_acitvity);

      new Handler().postDelayed(new Runnable() {

         @Override
         public void run() {

            SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFERENCE, MODE_PRIVATE);
            String result = sharedPreferences.getString(USER, ERROR);

            if (result.equals(ERROR)) {
               startActivity(new Intent(SplashAcitvity.this, LoginOrRegister.class));
            } else {
               startActivity(new Intent(SplashAcitvity.this, Connect.class));
            }

         }
      }, 2000l);
   }
}

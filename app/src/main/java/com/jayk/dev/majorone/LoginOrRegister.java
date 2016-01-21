package com.jayk.dev.majorone;


/*
* Consists of Login or Register Options for User.
* This actually consists of two fragments login and register.
* we have to change fragment according to choice of user.
* Once registered, you much give him the login page
* Once Logged in , we have to move to ConnectActivity
* */

//Note:Don't include the code in repository without testing

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginOrRegister extends AppCompatActivity {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login_or_register);
   }
}

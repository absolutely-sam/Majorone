package com.jayk.dev.majorone;


/*
* Consists of Login or Register Options for User.
* This actually consists of two fragments login and register.
* we have to change fragment according to choice of user.
* Once registered, you much give him the login page
* Once Logged in , we have to move to ConnectActivity
* */

//Note:Don't include the code in repository without testing

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

public class LoginOrRegister extends AppCompatActivity {


   private static boolean isReg = false;
   TextView textView;
   ActionBar actionBar;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_login_or_register);
      Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      setSupportActionBar(toolbar);

      actionBar = getSupportActionBar();

      setActionBarText("Login Page");

      textView = (TextView) findViewById(R.id.register);

      ToggleFragment();

      textView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            ToggleFragment();
         }
      });

   }

   public void ToggleFragment() {
      if (!isReg) {
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.setCustomAnimations(R.anim.fragmentin, R.anim.fragmentout);
         ft.replace(R.id.holder_frame, new UserFragment());
         ft.commit();
         setActionBarText("Login Page");
         isReg = true;
      } else {
         FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
         ft.setCustomAnimations(R.anim.fragmentin, R.anim.fragmentout);
         ft.replace(R.id.holder_frame, new RegFragment());
         ft.commit();
         setActionBarText("Register Here");
         isReg = false;
      }
   }

   private void setActionBarText(String text) {

      if (actionBar != null)
         actionBar.setTitle(Html.fromHtml("<font color=\"#ffffff\">" + text + "</font>"));

   }

}

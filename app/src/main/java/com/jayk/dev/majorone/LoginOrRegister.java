package com.jayk.dev.majorone;


/*
* Consists of Login or Register Options for User.
* This actually consists of two fragments login and register.
* we have to change fragment according to choice of user.
* Once registered, you much give him the login page
* Once Logged in , we have to move to ConnectActivity
* */

//Note:Don't include the code in repository without testing

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;

public class LoginOrRegister extends FragmentActivity {


   public static final int NUM_PAGES = 2;
   static ViewPager slideView;
   PagerAdapter slideViewPagerAdapter;

   public static void goToRegister() {
      slideView.setCurrentItem(1, true);
   }

   public static void goToLogin() {
      slideView.setCurrentItem(0, true);
   }

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      if (Build.VERSION.SDK_INT < 16) {
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                 WindowManager.LayoutParams.FLAG_FULLSCREEN);
      }
      if (Build.VERSION.SDK_INT > 15 && Build.VERSION.SDK_INT < 19) {
         View decorView = getWindow().getDecorView();
         int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
         decorView.setSystemUiVisibility(uiOptions);
      }

      setContentView(R.layout.activity_login_or_register);
      slideView = (ViewPager) findViewById(R.id.slide_view);
      slideViewPagerAdapter = new MySlideAdapter(getSupportFragmentManager());
      slideView.setAdapter(slideViewPagerAdapter);
   }

   class MySlideAdapter extends FragmentStatePagerAdapter {


      public MySlideAdapter(FragmentManager fm) {
         super(fm);
      }

      @Override
      public Fragment getItem(int position) {
         switch (position) {
            case 0:
               return new LoginFragment();
            case 1:
               return new RegFragment();
         }
         return null;
      }

      @Override
      public int getCount() {
         return NUM_PAGES;
      }
   }

}

package com.jayk.dev.majorone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

   Context context;
   boolean loginEmail = false, loginPassword = false;
   CoordinatorLayout coordinatorLayout;
   RelativeLayout loginRoot;
   TextInputLayout loginUserLayout, loginPassLayout;
   EditText userEdit, passEdit;
   CheckBox checkBox;
   Button signIn;
   int WIDTH, HEIGHT;
   RelativeLayout loginHolder;

   public static boolean checkPattern(String text) {
      String emailRegrex = "^[A-Za-z][A-Za-z0-9$%-_]{2,}@[A-Za-z]{3,}\\.[A-Za-z]{2,4}";
      Pattern emailPattern = Pattern.compile(emailRegrex);
      return emailPattern.matcher(text).matches();
   }

   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      this.context = context;
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.login_fragment, container, false);

      coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.my_coordinator);
      loginRoot = (RelativeLayout) view.findViewById(R.id.login_root);
      loginHolder = (RelativeLayout) view.findViewById(R.id.login_holder);

      loginUserLayout = (TextInputLayout) view.findViewById(R.id.user);
      loginPassLayout = (TextInputLayout) view.findViewById(R.id.pass);

      userEdit = (EditText) view.findViewById(R.id.user_et);
      passEdit = (EditText) view.findViewById(R.id.pass_et);

      TextView goToReg = (TextView) view.findViewById(R.id.reg_text);
      goToReg.setText(Html.fromHtml("<font color=\"#ffffff\">Don't have an account?</font><font color=\"#02E57A\">Sign Up</font>"));
      goToReg.setOnClickListener(new MyClickListener());
      checkBox = (CheckBox) view.findViewById(R.id.login_view_hide);
      checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
               passEdit.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
               passEdit.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
         }
      });
      checkBox.setChecked(true);

      adjustPaddingTop();

      loginUserLayout.setErrorEnabled(false);
      loginPassLayout.setErrorEnabled(false);


      signIn = (Button) view.findViewById(R.id.login_sign_in);
      signIn.setOnClickListener(new MyClickListener());

      userEdit.addTextChangedListener(new MyWatcher(userEdit));
      passEdit.addTextChangedListener(new MyWatcher(passEdit));

      return view;
   }

   public void setUserError() {
      String userText = userEdit.getText().toString().trim();
      if (userText.trim().length() > 0) {
         boolean matched = checkPattern(userText);
         if (!matched) {
            loginEmail = false;
            loginUserLayout.setError("Invalid Email");
         } else {
            loginUserLayout.setErrorEnabled(false);
            loginEmail = true;
            setPasswordError();
         }
      } else {
         loginEmail = false;
         loginUserLayout.setError("Email cannot be empty");
      }
   }

   public void setPasswordError() {
      if (passEdit.getText().toString().length() < 6) {
         loginEmail = false;
         loginPassLayout.setError("Invalid Password");
         loginPassLayout.setErrorEnabled(true);
      } else {
         loginPassLayout.setErrorEnabled(false);
         loginPassword = true;
      }
   }

   public int getStatusBarHeight() {
      int result = 0;
      int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
      if (resourceId > 0) {
         result = getResources().getDimensionPixelSize(resourceId);
      }
      return result;
   }

   public void adjustPaddingTop() {
      coordinatorLayout.setPadding(coordinatorLayout.getPaddingLeft(), getStatusBarHeight() + coordinatorLayout.getPaddingTop()
              , coordinatorLayout.getPaddingRight(), coordinatorLayout.getPaddingBottom());
   }

   class MyWatcher implements TextWatcher {

      View view;

      MyWatcher(View v) {
         view = v;
      }

      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {

      }

      @Override
      public void afterTextChanged(Editable editable) {
         switch (view.getId()) {
            case R.id.user_et:
               loginUserLayout.setErrorEnabled(false);
               break;
            case R.id.pass_et:
               loginPassLayout.setErrorEnabled(false);
               break;

         }
      }
   }

   public class MyClickListener implements View.OnClickListener {

      @Override
      public void onClick(View v) {
         int viewId = v.getId();

         switch (viewId) {
            case R.id.login_sign_in:
               setUserError();
               if (loginEmail && loginPassword) {
                  Log.e("success", "onClick: signed up");
               }
               break;
            case R.id.reg_text:
               LoginOrRegister.goToRegister();
               break;
         }

      }
   }

}
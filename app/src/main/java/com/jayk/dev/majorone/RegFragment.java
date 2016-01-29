package com.jayk.dev.majorone;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by admin on 23-Jan-16.
 */
public class RegFragment extends Fragment {

   EditText regName, regEmail, regPassword;
   TextInputLayout regNameLayout, regEmailLayout, regPasswordLayout;
   TextView loginText;
   CheckBox regcheckbox;
   Button signUp;
   int WIDTH, HEIGHT;
   RelativeLayout regHolder;
   RelativeLayout regRoot;
   ImageView regLogo;

   boolean regNameOk, regEmailOk, regPasswordOk;


   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.register_fragment, container, false);

      regName = (EditText) view.findViewById(R.id.full_name_et);
      regEmail = (EditText) view.findViewById(R.id.reg_user_et);
      regPassword = (EditText) view.findViewById(R.id.reg_pass_et);
      signUp = (Button) view.findViewById(R.id.reg_sign_up);
      regLogo = (ImageView) view.findViewById(R.id.reg_logo);
      loginText = (TextView) view.findViewById(R.id.login_text);
      regcheckbox = (CheckBox) view.findViewById(R.id.reg_view_hide);
      regcheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
               regPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            } else {
               regPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
         }
      });
      regcheckbox.setChecked(true);

      loginText.setText(Html.fromHtml("<font color=\"#ffffff\">Already a member?</font><font color=\"#02E57A\">Sign In</font>"));
      loginText.setOnClickListener(new MyListener());

      regRoot = (RelativeLayout) view.findViewById(R.id.reg_root);
      regHolder = (RelativeLayout) view.findViewById(R.id.reg_holder);

      adjustStatusBar();

      regName.addTextChangedListener(new MyWatcher(regName));
      regEmail.addTextChangedListener(new MyWatcher(regEmail));
      regPassword.addTextChangedListener(new MyWatcher(regPassword));

      regNameLayout = (TextInputLayout) view.findViewById(R.id.full_name);
      regEmailLayout = (TextInputLayout) view.findViewById(R.id.reg_user);
      regPasswordLayout = (TextInputLayout) view.findViewById(R.id.reg_pass);

      regName.setFilters(new InputFilter[]{
              new MyFilter()
      });

      signUp.setOnClickListener(new MyListener());

      return view;
   }

   public void checkEmail() {
      String userText = regEmail.getText().toString().trim();
      if (userText.trim().length() > 0) {
         boolean matched = LoginFragment.checkPattern(userText);
         if (!matched) {
            regEmailLayout.setError("Invalid Email");
            regEmailOk = false;
         } else {
            regEmailLayout.setErrorEnabled(false);
            regEmailOk = true;
            checkPassword();
         }
      } else {
         regEmailLayout.setError("Email cannot be empty");
         regEmailOk = false;
      }
   }

   public void checkPassword() {
      if (regPassword.getText().toString().length() < 6) {
         regPasswordLayout.setError("Minimum 6 characters long");
         regPasswordOk = false;
      } else {
         regPasswordLayout.setErrorEnabled(false);
         regPasswordOk = true;
      }
   }

   public void checkName() {
      if (regName.getText().toString().trim().length() == 0) {
         regNameLayout.setError("Name Cannot be Empty");
         regNameOk = false;
      } else {
         regNameLayout.setErrorEnabled(false);
         regNameOk = true;
         checkEmail();
      }
   }


   public void getSizes() {
      Display display = getActivity().getWindowManager().getDefaultDisplay();
      Point outSize = new Point();
      display.getSize(outSize);
      WIDTH = outSize.x;
      HEIGHT = outSize.y;
   }

   public int getStatusBarHeight() {
      int result = 0;
      if (Build.VERSION.SDK_INT > 19) {
         int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
         if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
         }
      }
      return result;
   }

   public void adjustStatusBar() {
      regRoot.setPadding(regRoot.getPaddingLeft(), regRoot.getPaddingTop() + getStatusBarHeight(),
              regRoot.getPaddingRight(), regRoot.getPaddingBottom());
   }

   class MyListener implements View.OnClickListener {

      @Override
      public void onClick(View v) {
         int viewId = v.getId();
         switch (viewId) {
            case R.id.login_text:
               LoginOrRegister.goToLogin();
               break;
            case R.id.reg_sign_up:
               checkName();
               if (regNameOk && regEmailOk && regPasswordOk) {
                  Log.e("validation", "onClick: every thing is valid");
               }
               break;
         }
      }
   }

   class MyFilter implements InputFilter {

      @Override
      public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
         if (source.equals("")) {
            return source;
         }
         if (source.toString().matches("[A-Za-z ]+")) {
            return source;
         }
         return "";

      }
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
            case R.id.full_name_et:
               regNameLayout.setErrorEnabled(false);
               break;
            case R.id.reg_user_et:
               regEmailLayout.setErrorEnabled(false);
               break;
            case R.id.reg_pass_et:
               regPasswordLayout.setErrorEnabled(false);
               break;
         }
      }
   }

}

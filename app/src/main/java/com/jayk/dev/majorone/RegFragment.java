package com.jayk.dev.majorone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 23-Jan-16.
 */
public class RegFragment extends Fragment {

   EditText regName, regEmail, regPassword;
   TextInputLayout regNameLayout, regEmailLayout, regPasswordLayout;
   Button signIn;

   boolean regNameOk, regEmailOk, regPasswordOk;


   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.register_fragment, container, false);
      regName = (EditText) view.findViewById(R.id.full_name_et);
      regEmail = (EditText) view.findViewById(R.id.reg_user_et);
      regPassword = (EditText) view.findViewById(R.id.reg_pass_et);
      signIn = (Button) view.findViewById(R.id.sign_in);

      regName.addTextChangedListener(new MyWatcher(regName));
      regEmail.addTextChangedListener(new MyWatcher(regEmail));
      regPassword.addTextChangedListener(new MyWatcher(regPassword));

      regNameLayout = (TextInputLayout) view.findViewById(R.id.full_name);
      regEmailLayout = (TextInputLayout) view.findViewById(R.id.reg_user);
      regPasswordLayout = (TextInputLayout) view.findViewById(R.id.reg_pass);

      regName.setFilters(new InputFilter[]{
              new MyFilter()
      });

      signIn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            checkName();
            checkEmail();
            checkPassword();
            if (regNameOk && regEmailOk && regPasswordOk) {
               Log.e("validation", "onClick: every thing is valid");
            }
         }
      });

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

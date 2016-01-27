package com.jayk.dev.majorone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class LoginFragment extends Fragment {

   Context context;
   TextInputLayout loginUserLayout, loginPassLayout;
   EditText userEdit, passEdit;
   Button signUp;

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


      loginUserLayout = (TextInputLayout) view.findViewById(R.id.user);
      loginUserLayout.setErrorEnabled(false);
      loginPassLayout = (TextInputLayout) view.findViewById(R.id.pass);
      loginPassLayout.setErrorEnabled(false);

      userEdit = (EditText) view.findViewById(R.id.user_et);
      passEdit = (EditText) view.findViewById(R.id.pass_et);
      signUp = (Button) view.findViewById(R.id.sign_up);
      signUp.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            setUserError();
            setPasswordError();
         }
      });

      userEdit.addTextChangedListener(new MyWatcher(userEdit));
      passEdit.addTextChangedListener(new MyWatcher(passEdit));

      return view;
   }

   public void setUserError() {
      String userText = userEdit.getText().toString().trim();
      if (userText.trim().length() > 0) {
         boolean matched = checkPattern(userText);
         if (!matched) {
            loginUserLayout.setError("Invalid Email");
         } else {
            loginUserLayout.setErrorEnabled(false);
         }
      } else {
         loginUserLayout.setError("Email cannot be empty");
      }
   }

   public void setPasswordError() {
      if (passEdit.getText().toString().length() < 6) {
         loginPassLayout.setError("Invalid Password");
         loginPassLayout.setErrorEnabled(true);
      } else {
         loginPassLayout.setErrorEnabled(false);
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
            case R.id.user_et:
               loginUserLayout.setErrorEnabled(false);
               break;
            case R.id.pass_et:
               loginPassLayout.setErrorEnabled(false);
               break;

         }
      }
   }
}
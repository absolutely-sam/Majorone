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
import android.widget.EditText;

public class LoginFragment extends Fragment {

   Context context;
   TextInputLayout loginUserLayout, loginPassLayout;
   EditText userEdit, passEdit;

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
      loginPassLayout = (TextInputLayout) view.findViewById(R.id.pass);

      userEdit = (EditText) view.findViewById(R.id.user_et);
      passEdit = (EditText) view.findViewById(R.id.pass_et);

      userEdit.addTextChangedListener(new MyWatcher(userEdit));
      passEdit.addTextChangedListener(new MyWatcher(passEdit));

      return view;
   }

   public boolean validEmail(String s) {
//     String expression = "^$"

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
               String userText = userEdit.getText().toString().trim();


               break;
            case R.id.pass_et:
               loginPassLayout.setError("Invalid Password");
               break;

         }
      }
   }
}
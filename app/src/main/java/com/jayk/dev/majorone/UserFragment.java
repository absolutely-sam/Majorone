package com.jayk.dev.majorone;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class UserFragment extends Fragment {

   Context context;

   @Override
   public void onAttach(Context context) {
      super.onAttach(context);
      this.context = context;
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.login_fragment, container, false);
      Button b = (Button) view.findViewById(R.id.sign_up);
      b.setOnClickListener(new View.OnClickListener() {

         @Override
         public void onClick(View v) {
            Toast.makeText(context, "signUp", Toast.LENGTH_LONG).show();
         }
      });
      return view;
   }
}
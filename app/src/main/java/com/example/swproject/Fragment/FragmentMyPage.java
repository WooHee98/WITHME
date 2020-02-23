package com.example.swproject.Fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swproject.Activitiies.LoginActivity;
import com.example.swproject.Activitiies.UserUpdateActivity;
import com.example.swproject.R;
import com.example.swproject.StaticValues;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class FragmentMyPage extends Fragment {

    View view;
    TextView idtext;
    TextView logout, userout, userupdate;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("User");


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (View) inflater.inflate(R.layout.fragment_mypage, container, false);

        idtext = view.findViewById(R.id.idText);
        idtext.setText(StaticValues.u_name);

        logout = view.findViewById(R.id.logout);
        userout = view.findViewById(R.id.userout);
        userupdate = view.findViewById(R.id.userupdate);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StaticValues.u_id = "";

                if (StaticValues.u_name.length() > 0) {

                    Toast.makeText(getContext(), "로그아웃 되었습니다!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    } else {
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    startActivity(intent);

                }}
        });



        userout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StaticValues.u_name.length() > 0) {
                    rootRef.child(StaticValues.u_id).removeValue();
                    Toast.makeText(getContext(), "탈퇴 되었습니다!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                    } else {
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    startActivity(intent);

                }
            }
        });

        userupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UserUpdateActivity.class);
                startActivity(intent);

            }
        });



        return view;
        }

    }

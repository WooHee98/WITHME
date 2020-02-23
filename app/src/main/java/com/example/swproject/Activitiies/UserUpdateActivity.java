package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swproject.Datas.UserItem;
import com.example.swproject.R;
import com.example.swproject.StaticValues;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class UserUpdateActivity extends AppCompatActivity {

    ImageButton backbutton;
    TextView username, userid;
    EditText userpasswordchange, userpasswordchange_check;
    Button updatebutton;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("User");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_update);


        backbutton = findViewById(R.id.user_info_backbutton);
        username = findViewById(R.id.username);
        userid = findViewById(R.id.userid);
        userpasswordchange = findViewById(R.id.userpasswordchange);
        userpasswordchange_check = findViewById(R.id.userpasswordchange_check);
        updatebutton = findViewById(R.id.UpdateButton);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUpdateActivity.super.onBackPressed();
            }
        });

        username.setText(StaticValues.u_name);
        userid.setText(StaticValues.u_id);

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!userpasswordchange.getText().toString().equals(userpasswordchange_check.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    userpasswordchange.setText("");
                    userpasswordchange_check.setText("");
                    userpasswordchange.requestFocus();
                    return;
                }

                if (userpasswordchange.getText().toString().equals(userpasswordchange_check.getText().toString())) {
                    rootRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            Map<String, Object> updateMap = new HashMap<>();
                            updateMap.put("password", userpasswordchange.getText().toString());
                            UserItem user = dataSnapshot.child(StaticValues.u_id).getValue(UserItem.class);
                            rootRef.child(StaticValues.u_id).updateChildren(updateMap);
                            Toast.makeText(getApplicationContext(), "비밀번호 변경 완료되었습니다.", Toast.LENGTH_LONG).show();

                            if (StaticValues.u_name.length() > 0) {
                                try {
                                    Thread.sleep(2000);
                                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.GINGERBREAD_MR1) {
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    } else {
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    }
                                    startActivity(intent);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }


}
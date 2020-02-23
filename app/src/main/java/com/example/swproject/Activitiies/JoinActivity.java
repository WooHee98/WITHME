package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swproject.Datas.UserItem;
import com.example.swproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JoinActivity extends AppCompatActivity {


    ImageButton closebtn;
    Button registerbtn;
    TextView ename, eid, epassword, epasswordcheck, ephone;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference rootRef = database.getReference("User");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        ename = findViewById(R.id.nameText);
        eid = findViewById(R.id.idText);
        epassword = findViewById(R.id.passwordText);
        epasswordcheck = findViewById(R.id.passwordcheckText);
        ephone = findViewById(R.id.phoneText);
        closebtn = findViewById(R.id.closebutton);
        registerbtn = findViewById(R.id.registerButton);

        closebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                onBackPressed();
            }
        });


        //가입하기
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이름
                if (ename.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름를 입력해주세요", Toast.LENGTH_SHORT).show();
                    ename.requestFocus();
                    return;
                }

                //아이디
                if (eid.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
                    eid.requestFocus();
                    return;
                }

                //비밀번호
                if (epassword.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    epassword.requestFocus();
                    return;
                }

                //비밀번호체크
                if (epasswordcheck.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "비밀번호체크를 입력해주세요", Toast.LENGTH_SHORT).show();
                    epasswordcheck.requestFocus();
                    return;
                }

                //비밀번호 일치 여부
                if (!epassword.getText().toString().equals(epasswordcheck.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                    epassword.setText("");
                    epasswordcheck.setText("");
                    epassword.requestFocus();
                    return;
                }

                //전화번호
                if (ephone.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "전화번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    ephone.requestFocus();
                    return;
                }


                rootRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(eid.getText().toString()).exists()){
                            Toast.makeText(getApplicationContext(), "회원정보가 있습니다.", Toast.LENGTH_LONG).show();
                        }else{
                            UserItem user= new UserItem(ename.getText().toString(), ephone.getText().toString(), epassword.getText().toString());
                            rootRef.child(eid.getText().toString()).setValue(user);
                            Toast.makeText(getApplicationContext(), "가입완료되었습니다..", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}

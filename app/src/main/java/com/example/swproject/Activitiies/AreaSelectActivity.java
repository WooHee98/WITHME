package com.example.swproject.Activitiies;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.swproject.R;

public class AreaSelectActivity extends AppCompatActivity {

    ImageButton closebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_select);

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AreaSelectActivity.super.onBackPressed();
            }
        });

    }
}

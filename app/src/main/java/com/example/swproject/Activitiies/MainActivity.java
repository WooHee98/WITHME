package com.example.swproject.Activitiies;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.swproject.Fragment.FragmentBoard;
import com.example.swproject.Fragment.FragmentEvent;
import com.example.swproject.Fragment.FragmentHome;
import com.example.swproject.Fragment.FragmentMyPage;
import com.example.swproject.R;


public class MainActivity extends AppCompatActivity{


    private BottomNavigationView bottomNavigationView;

    //fragment를 위한 enum
    private enum Tabs {
        Home, Event, Board, Page
    }

    //로딩하기 위한 코드
    private FragmentHome home;
    private FragmentEvent event;
    private FragmentBoard board;
    private FragmentMyPage page;

    //탭이 어디로 가는지
    private Tabs nowTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //(new ApiTask()).execute();


    }
    protected void onStart() {
        //****
        super.onStart();
        switch (nowTab) {
            case Home:
                bottomNavigationView.setSelectedItemId(R.id.navigation_menu1);
                break;
            case Event:
                bottomNavigationView.setSelectedItemId(R.id.navigation_menu2);
                break;
            case Board:
                bottomNavigationView.setSelectedItemId(R.id.navigation_menu3);
                break;
            case Page:
                bottomNavigationView.setSelectedItemId(R.id.navigation_menu4);
                break;
        }
    }
    private void init() {
        nowTab = Tabs.Home;
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        home = new FragmentHome();
        event = new FragmentEvent();
        board = new FragmentBoard();
        page= new FragmentMyPage();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_menu1:
                        //add는 백 버튼 눌렀을 떄 메모리에 누적한다.replace는 새로
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, home).commit();
                        return true;
                    case R.id.navigation_menu2:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, event).commit();
                        return true;
                    case R.id.navigation_menu3:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, board).commit();
                        return true;
                    case R.id.navigation_menu4:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, page).commit();
                        return true;
                    default:
                        return false;
                }

            }
        });
    }


}





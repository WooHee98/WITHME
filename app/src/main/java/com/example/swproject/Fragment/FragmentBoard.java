package com.example.swproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swproject.Activitiies.AreaSelectActivity2;
import com.example.swproject.Datas.BoardDataItem;
import com.example.swproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBoard extends Fragment {

    View view;
    private Context context;
    Button area1, area2, area3, area4, area5, area6, area7, area8, area9, area10, area11, area12, area13, area14, area15, area16, area17;

    private OnClickListItemListener listener;

    public void setOnClickListItemLister(final OnClickListItemListener listener) {
        this.listener = listener;
    }

    public interface OnClickListItemListener {
        void onItemSelected(View view, BoardDataItem item);
    }


    public FragmentBoard() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_board, container, false);
        area1 = view.findViewById(R.id.area1);
        area2 = view.findViewById(R.id.area2);
        area3 = view.findViewById(R.id.area3);
        area4 = view.findViewById(R.id.area4);
        area5 = view.findViewById(R.id.area5);
        area6 = view.findViewById(R.id.area6);
        area7 = view.findViewById(R.id.area7);
        area8 = view.findViewById(R.id.area8);
        area9 = view.findViewById(R.id.area9);
        area10 = view.findViewById(R.id.area10);
        area11 = view.findViewById(R.id.area11);
        area12 = view.findViewById(R.id.area12);
        area13 = view.findViewById(R.id.area13);
        area14 = view.findViewById(R.id.area14);
        area15 = view.findViewById(R.id.area15);
        area16 = view.findViewById(R.id.area16);
        area17 = view.findViewById(R.id.area17);


        BtnOnClickListener onClickListener = new BtnOnClickListener() ;
        area1.setOnClickListener(onClickListener) ;
        area2.setOnClickListener(onClickListener) ;
        area3.setOnClickListener(onClickListener) ;
        area4.setOnClickListener(onClickListener) ;
        area5.setOnClickListener(onClickListener) ;
        area6.setOnClickListener(onClickListener) ;
        area7.setOnClickListener(onClickListener) ;
        area8.setOnClickListener(onClickListener) ;
        area9.setOnClickListener(onClickListener) ;
        area10.setOnClickListener(onClickListener) ;
        area11.setOnClickListener(onClickListener) ;
        area12.setOnClickListener(onClickListener) ;
        area13.setOnClickListener(onClickListener) ;
        area14.setOnClickListener(onClickListener) ;
        area15.setOnClickListener(onClickListener) ;
        area16.setOnClickListener(onClickListener) ;
        area17.setOnClickListener(onClickListener) ;

        return view;

    }
    class BtnOnClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.area1:
                    Intent intent1= new Intent(getContext(), AreaSelectActivity2.class);
                    intent1.putExtra("area", "1");
                    startActivity(intent1);
                    break;
                case R.id.area2:
                    Intent intent2= new Intent(getContext(), AreaSelectActivity2.class);
                    intent2.putExtra("area", "2");
                    startActivity(intent2);
                    break;
                case R.id.area3:
                    Intent intent3= new Intent(getContext(), AreaSelectActivity2.class);
                    intent3.putExtra("area", "3");
                    startActivity(intent3);
                    break;
                case R.id.area4:
                    Intent intent4= new Intent(getContext(), AreaSelectActivity2.class);
                    intent4.putExtra("area", "4");
                    startActivity(intent4);
                    break;
                case R.id.area5:
                    Intent intent5= new Intent(getContext(), AreaSelectActivity2.class);
                    intent5.putExtra("area", "5");
                    startActivity(intent5);
                    break;
                case R.id.area6:
                    Intent intent6= new Intent(getContext(), AreaSelectActivity2.class);
                    intent6.putExtra("area", "6");
                    startActivity(intent6);
                    break;
                case R.id.area7:
                    Intent intent7= new Intent(getContext(), AreaSelectActivity2.class);
                    intent7.putExtra("area", "7");
                    startActivity(intent7);
                    break;
                case R.id.area8:
                    Intent intent8= new Intent(getContext(), AreaSelectActivity2.class);
                    intent8.putExtra("area", "8");
                    startActivity(intent8);
                    break;
                case R.id.area9:
                    Intent intent9= new Intent(getContext(), AreaSelectActivity2.class);
                    intent9.putExtra("area", "31");
                    startActivity(intent9);
                    break;
                case R.id.area10:
                    Intent intent10= new Intent(getContext(), AreaSelectActivity2.class);
                    intent10.putExtra("area", "32");
                    startActivity(intent10);
                    break;
                case R.id.area11:
                    Intent intent11= new Intent(getContext(), AreaSelectActivity2.class);
                    intent11.putExtra("area", "33");
                    startActivity(intent11);
                    break;
                case R.id.area12:
                    Intent intent12 = new Intent(getContext(), AreaSelectActivity2.class);
                    intent12.putExtra("area", "34");
                    startActivity(intent12);
                    break;
                case R.id.area13:
                    Intent intent13= new Intent(getContext(), AreaSelectActivity2.class);
                    intent13.putExtra("area", "35");
                    startActivity(intent13);
                    break;
                case R.id.area14:
                    Intent intent14= new Intent(getContext(), AreaSelectActivity2.class);
                    intent14.putExtra("area", "36");
                    startActivity(intent14);
                    break;
                case R.id.area15:
                    Intent intent15= new Intent(getContext(), AreaSelectActivity2.class);
                    intent15.putExtra("area", "37");
                    startActivity(intent15);
                    break;
                case R.id.area16:
                    Intent intent16= new Intent(getContext(), AreaSelectActivity2.class);
                    intent16.putExtra("area", "38");
                    startActivity(intent16);
                    break;
                case R.id.area17:
                    Intent intent17= new Intent(getContext(), AreaSelectActivity2.class);
                    intent17.putExtra("area", "39");
                    startActivity(intent17);
                    break;
            }
        }
    }

}

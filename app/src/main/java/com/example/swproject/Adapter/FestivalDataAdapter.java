package com.example.swproject.Adapter;

import android.app.MediaRouteButton;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swproject.Datas.FestivalItem;
import com.example.swproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FestivalDataAdapter extends RecyclerView.Adapter<FestivalDataAdapter.FestivalDataHolder> {
    private Context context;
    private ArrayList<FestivalItem> list;
    private onClickviewListener listener;

    public interface onClickviewListener {
        void onClick(View view, FestivalItem item);
    }

    public void setOnClickviewListener(onClickviewListener listener) {
        this.listener = listener;
    }


    public FestivalDataAdapter(ArrayList<FestivalItem> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public FestivalDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_festival, viewGroup, false);
        return new FestivalDataHolder(view);
    }




    //get 데이터
    public void onBindViewHolder(@NonNull FestivalDataHolder festivalDataHolder, int i) {
        FestivalItem item = list.get(i);

        festivalDataHolder.festival_name.setText(item.getF_name());
        festivalDataHolder.festival_date1.setText(item.getF_date1());
        festivalDataHolder.festival_date2.setText(item.getF_date2());
        festivalDataHolder.festival_area.setText(item.getF_area());

        if (item.getF_image().isEmpty()) {
            Picasso.get().load(R.drawable.no_image).into(festivalDataHolder.festival_image);

        } else {
            Picasso.get().load(item.getF_image()).into(festivalDataHolder.festival_image);
        }


       // Picasso.get().load(item.getF_image()).into(festivalDataHolder.festival_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class FestivalDataHolder extends RecyclerView.ViewHolder {
        public MediaRouteButton clanImage;
        ImageView festival_image; //이미지
        TextView festival_name;//제목
        TextView festival_date1, festival_date2;//날짜
        TextView festival_area;//주소


        public FestivalDataHolder(final View itemview) {
            super(itemview);
            festival_image = itemview.findViewById(R.id.festival_image);
            festival_name = itemview.findViewById(R.id.festival_name);
            festival_date1 = itemview.findViewById(R.id.festival_date1);
            festival_date2 = itemview.findViewById(R.id.festival_date2);
            festival_area = itemview.findViewById(R.id.festival_area);




            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //position을 받아온다.----------------------------------------------
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onClick(itemView, list.get(position));

                    }

                }
            });
        }
    }
}

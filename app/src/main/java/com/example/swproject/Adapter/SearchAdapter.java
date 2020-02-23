package com.example.swproject.Adapter;

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

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchDataHolder>{
    private Context context;
    private ArrayList<FestivalItem> list;
    private onClickviewListener listener;

    public interface onClickviewListener {
        void onClick(View view, FestivalItem item);
    }

    public void setOnClickviewListener(onClickviewListener listener) {
        this.listener = listener;
    }


    public SearchAdapter(ArrayList<FestivalItem> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SearchDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_festival2, viewGroup, false);
        return new SearchDataHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchDataHolder searchDataHolder, int i) {
        FestivalItem item = list.get(i);
        searchDataHolder.festival_name.setText(item.getF_name());
        searchDataHolder.festival_area.setText(item.getF_area());

        if (item.getF_image().isEmpty()) {
            Picasso.get().load(R.drawable.no_image).into(searchDataHolder.festival_image);

        } else {
            Picasso.get().load(item.getF_image()).into(searchDataHolder.festival_image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchDataHolder extends RecyclerView.ViewHolder {
        ImageView festival_image; //이미지
        TextView festival_name;//제목
        TextView festival_area;//주소

        public SearchDataHolder(final View itemView) {
            super(itemView);
            festival_image = itemView.findViewById(R.id.festival_image);
            festival_name = itemView.findViewById(R.id.festival_name);
            festival_area = itemView.findViewById(R.id.festival_area);



            itemView.setOnClickListener(new View.OnClickListener() {
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

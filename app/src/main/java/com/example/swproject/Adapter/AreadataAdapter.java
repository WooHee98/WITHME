package com.example.swproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swproject.Datas.AreaItem;
import com.example.swproject.R;

import java.util.ArrayList;

public class AreadataAdapter extends RecyclerView.Adapter<AreadataAdapter.AreaHolder> {
    private Context context;
    private ArrayList<AreaItem> list;
    private onClickareaListener listener;

    public interface  onClickareaListener{
        void  onclick(View view, AreaItem item);
    }
    public void seronClickareaListener(onClickareaListener listener){
        this.listener=listener;
    }

    public AreadataAdapter(ArrayList<AreaItem> list, Context context) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public AreaHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_area, viewGroup, false);
        return new AreaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AreaHolder areaHolder, int i) {
        AreaItem item= list.get(i);
        areaHolder.area.setText(item.getArea());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class AreaHolder extends RecyclerView.ViewHolder {
        TextView area;

        public AreaHolder(@NonNull final View itemView) {
            super(itemView);
            area= itemView.findViewById(R.id.area);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //position을 받아온다.----------------------------------------------
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onclick(itemView, list.get(position));
                    }
                }
            });
        }
    }
}

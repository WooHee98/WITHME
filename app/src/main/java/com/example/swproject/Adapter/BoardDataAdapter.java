package com.example.swproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.swproject.Datas.BoardDataItem;
import com.example.swproject.R;

import java.util.ArrayList;

public class BoardDataAdapter extends RecyclerView.Adapter<BoardDataAdapter.BoardDataHolder> {
    private Context context;
    private ArrayList<BoardDataItem> list;
    private onClickviewListener listener;

    public interface onClickviewListener {
        void onClick(View view, BoardDataItem item);
    }

    public void setOnClickviewListener(onClickviewListener listener) {
        this.listener = listener;
    }


    public BoardDataAdapter(ArrayList<BoardDataItem> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public BoardDataHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_board, viewGroup, false);
        return new BoardDataHolder(view);
    }




    //get 데이터!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public void onBindViewHolder(@NonNull BoardDataHolder BoardDataHolder, int i) {
        BoardDataItem item = list.get(i);

        BoardDataHolder.board_story.setText(item.getB_story());
        BoardDataHolder.user_id.setText(item.getU_id());




/*        Glide.with(context)
                .load(item.getM_image_url())
                //centerCrop 로 이미지 크게
                .centerCrop()
                //내가 이 미지를 쓰겠다라는 것
                .into(movieDataHolder.reserlist_image);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class BoardDataHolder extends RecyclerView.ViewHolder {
        ImageView board_image1, board_image2, board_image3;
        TextView board_story;//내용
        TextView user_id; //회원 아이디


        public BoardDataHolder(final View itemview) {
            super(itemview);
            board_image1 = itemview.findViewById(R.id.boardimage1);
            board_image2 = itemview.findViewById(R.id.boardimage2);
            board_image3 = itemview.findViewById(R.id.boardimage3);
            board_story = itemview.findViewById(R.id.boardstory);
            user_id = itemview.findViewById(R.id.userid);



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

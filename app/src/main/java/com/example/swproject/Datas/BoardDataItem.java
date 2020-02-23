package com.example.swproject.Datas;

public class BoardDataItem {

    String u_id;
    String b_story;


    public BoardDataItem() {
        this.u_id = "None";
        this.b_story = "None";
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getB_story() {
        return b_story;
    }

    public void setB_story(String b_story) {
        this.b_story = b_story;
    }
}

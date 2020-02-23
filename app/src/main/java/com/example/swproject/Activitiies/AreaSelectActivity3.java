package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.swproject.Adapter.SearchAdapter;
import com.example.swproject.Datas.FestivalItem;
import com.example.swproject.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AreaSelectActivity3 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    String id="";
    String areaname="";
    String areacode="";
    //축제 아이디값
    String contentid="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_select3);

        Intent intent= getIntent();
        areaname =intent.getStringExtra("areaname");
        Log.d("areaname", areaname);
        areacode =intent.getStringExtra("areacode");
        Log.d("areacode", areacode);

        (new ApiTask1()).execute();
    }



    public class ApiTask1 extends AsyncTask<String, Void, String> {
        String clientKey = "tc0tnldlU5MiHXEg0Nh%2FjMbmMiAcZa%2FufND1SVQmeYOhSyN3S87N4aZ5sz%2F31o%2FJejwFhVBGBQ34iMaREdodnQ%3D%3D";
        private String str, receiveMsg;

        String addr1 = "";
        String firstimage = "";
        String title ="";


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<FestivalItem> temp = new ArrayList<>();

            try {
                JSONObject jobject = new JSONObject(s).getJSONObject("response").getJSONObject("body").getJSONObject("items");
                String jsonString = jobject+"";
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("item");

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jObject = jarray.getJSONObject(i);

                    firstimage = jObject.optString("firstimage");
                    title = jObject.optString("title");
                    addr1 = jObject.optString("addr1");
                    contentid = jObject.optString("contentid");
                    Log.d("아이디", contentid);



                    FestivalItem item = new FestivalItem();
                    item.setF_id(contentid);
                    item.setF_image(firstimage);
                    item.setF_name(title);
                    item.setF_area(addr1);

                    temp.add(item);
                }

                adapter = new SearchAdapter(temp, getApplicationContext());
                adapter.setOnClickviewListener(new SearchAdapter.onClickviewListener() {
                    @Override
                    public void onClick(View view, FestivalItem item) {
                        Toast.makeText(getApplicationContext(), item.getF_id(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), FestivalDetailActivity.class);
                        intent.putExtra("contentid",item.getF_id());
                        startActivity(intent);
                    }
                });

                recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            URL url1 = null;

            try {
                Log.d("areaname", areaname);
                Log.d("areacode", areacode);
                url1 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+clientKey+"&contentTypeId=15&areaCode="+areaname+"&sigunguCode="+areacode+"&MobileOS=ETC&MobileApp=AppTest&_type=json");

                HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
                conn.setRequestProperty("x-waple-authorization", clientKey);

                if (conn.getResponseCode() == conn.HTTP_OK) {
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                    BufferedReader reader = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();
                    while ((str = reader.readLine()) != null) {
                        buffer.append(str);
                    }
                    receiveMsg = buffer.toString();

                    reader.close();
                } else {
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return receiveMsg;
        }
    }
}

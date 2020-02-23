package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.swproject.Adapter.AreadataAdapter;
import com.example.swproject.Datas.AreaItem;
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

public class AreaSelectActivity2 extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AreadataAdapter areadataAdapter;
    //intent로 지역코드 가져오기
    String area = "";
    //api에서 받아온 값 저장
    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.area_select2);

        Intent intent = getIntent();
        area = intent.getStringExtra("area");
        Log.d("area", area);

        (new ApiTask()).execute();
    }

    public class ApiTask extends AsyncTask<String, Void, String> {
        String clientKey = "6XzuI9VQiZrOuLFFXYF%2FJL2B0DecsHj%2Fhbjlv4hp1x5Sscjs4bOls0Ha%2BzFEPSuDaGdjcYkfCY9%2BDLhctqSCJw%3D%3D";
        private String str, receiveMsg;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<AreaItem> temp = new ArrayList<>();
            try {
                JSONObject jobject = new JSONObject(s).getJSONObject("response").getJSONObject("body").getJSONObject("items");
                Log.d("object", jobject+"");
                String jsonString = jobject+"";
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("item");
                Log.d("array", jarray+"");


                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jObject = jarray.getJSONObject(i);
                    AreaItem item = new AreaItem();
                    name = jObject.optString("name");
                    item.setArea(name);
                    item.setArea_code(jObject.optString("code"));
                    temp.add(item);
                }

                areadataAdapter = new AreadataAdapter(temp, getApplicationContext());
                areadataAdapter.seronClickareaListener(new AreadataAdapter.onClickareaListener() {
                    @Override
                    public void onclick(View view, AreaItem item) {
                        Intent intent= new Intent(getApplicationContext(), AreaSelectActivity3.class);
                        intent.putExtra("areaname", area);
                        intent.putExtra("areacode", item.getArea_code());
                        startActivity(intent);
                    }
                });

                recyclerView = (RecyclerView)findViewById(R.id.recyclerview1);
                recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(areadataAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            URL url1 = null;

            try {
                url1 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey=" + clientKey + "&areaCode=" + area + "&numOfRows=25&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json");
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
                    Log.i("receiveMsg : ", receiveMsg);

                    reader.close();
                } else {
                    Log.i("통신 결과", conn.getResponseCode() + "에러");
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

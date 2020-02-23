package com.example.swproject.Activitiies;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swproject.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FestivalDetailActivity extends AppCompatActivity {

    ImageButton backbutton;
    TextView title, address, homepage, tel, overview,spendtimefesitval,usetimefestival,playtime,agelimit;
    String contentid = "";
    String firstimage = "";
    ImageView imageView;
    String homepageid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.festival_detail);

        backbutton = findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FestivalDetailActivity.super.onBackPressed();
            }
        });

        imageView = findViewById(R.id.festival_image1);
        title = findViewById(R.id.festival_name);
        address = findViewById(R.id.festival_area);
        homepage = findViewById(R.id.festival_homepage);
        tel = findViewById(R.id.festival_tel);
        overview = findViewById(R.id.festival_info);
        spendtimefesitval = findViewById(R.id.festival_spendtimefesitval);
        usetimefestival = findViewById(R.id.festival_usetimefesitval);
        playtime = findViewById(R.id.festival_playtime);
        agelimit = findViewById(R.id.festival_agelimit);

        Intent intent = getIntent();
        contentid = intent.getStringExtra("contentid");
        Log.d("contentid", intent.getStringExtra("contentid"));


        (new Festival1ApiTask()).execute();
        (new Festival2ApiTask()).execute();


        homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GoSiteActivity.class);
                intent.putExtra("hompage",homepageid );
                startActivity(intent);

            }
        });

    }

    public class Festival1ApiTask extends AsyncTask<String, Void, String> {
        String clientKey = "tc0tnldlU5MiHXEg0Nh%2FjMbmMiAcZa%2FufND1SVQmeYOhSyN3S87N4aZ5sz%2F31o%2FJejwFhVBGBQ34iMaREdodnQ%3D%3D";
        private String str, receiveMsg;
        String homepagetext="";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                JSONObject jobject = new JSONObject(s).getJSONObject("response").getJSONObject("body").getJSONObject("items");
                Log.d("object", jobject + "");
                String jsonString = jobject + "";
                JSONObject jobject1 = new JSONObject(jsonString).getJSONObject("item");
                Log.d("array", jobject1 + "");

                firstimage = jobject1.optString("firstimage");
                title.setText(jobject1.optString("title"));
                address.setText(jobject1.optString("addr1"));

                homepagetext = jobject1.optString("homepage");
                Log.d("homepage", homepagetext);

                String[] array = homepagetext.split("\"");
                homepageid=array[1];
                homepage.setText(array[1]);

                tel.setText(jobject1.optString("tel"));
                overview.setText(jobject1.optString("overview"));

                if (firstimage.isEmpty()) {
                    Picasso.get().load(R.drawable.no_image).into(imageView);
                } else {
                    Picasso.get().load(firstimage).into(imageView);
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            URL url1 = null;

            try {
                url1 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=" + clientKey + "&contentId=" + contentid + "&defaultYN=Y&firstImageYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&MobileOS=ETC&MobileApp=AppTest&_type=json");
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


    public class Festival2ApiTask extends AsyncTask<String, Void, String> {
        String clientKey = "tc0tnldlU5MiHXEg0Nh%2FjMbmMiAcZa%2FufND1SVQmeYOhSyN3S87N4aZ5sz%2F31o%2FJejwFhVBGBQ34iMaREdodnQ%3D%3D";
        private String str, receiveMsg;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                JSONObject jobject = new JSONObject(s).getJSONObject("response").getJSONObject("body").getJSONObject("items");
                Log.d("object1", jobject + "");
                String jsonString = jobject + "";
                JSONObject jobject1 = new JSONObject(jsonString).getJSONObject("item");
                Log.d("array1", jobject1 + "");

                spendtimefesitval.setText(jobject1.optString("spendtimefesitval"));
                usetimefestival.setText(jobject1.optString("usetimefestival"));
                playtime.setText(jobject1.optString("playtime"));
                agelimit.setText(jobject1.optString("agelimit"));





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            URL url1 = null;

            try {
                url1 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?serviceKey="+clientKey+"&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&contentId="+contentid+"&contentTypeId=15&_type=json");
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
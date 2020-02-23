package com.example.swproject.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class FragmentHome extends Fragment {

    View view;
    TextView T1H,RN1,REH;
    ImageView SKY;
    TextView todaydate, todaytime;
    ImageView image1, image2, image3, image4;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = (View) inflater.inflate(R.layout.fragement_home, container, false);

        T1H = view.findViewById(R.id.t3h);
        RN1 = view.findViewById(R.id.pop);
        REH = view.findViewById(R.id.reh);
        SKY = view.findViewById(R.id.sky);

        todaydate = view.findViewById(R.id.todaydate);
        todaytime = view.findViewById(R.id.todaytime);


        image1 = view.findViewById(R.id.image1);
        image2 = view.findViewById(R.id.image2);
        image3 = view.findViewById(R.id.image3);
        image4 = view.findViewById(R.id.image4);


        image1.setImageResource(R.drawable.image1);
        image2.setImageResource(R.drawable.image2);
        image3.setImageResource(R.drawable.image3);
        image4.setImageResource(R.drawable.image4);



        (new WeatherApiTask()).execute();


        return view;

    }


    public class WeatherApiTask extends AsyncTask<String, Void, String> {
        String clientKey = "tc0tnldlU5MiHXEg0Nh%2FjMbmMiAcZa%2FufND1SVQmeYOhSyN3S87N4aZ5sz%2F31o%2FJejwFhVBGBQ34iMaREdodnQ%3D%3D";
        private String str, receiveMsg;

        String category = "";
        String fcstValue = "";
        String fcstTime="";


        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);
        String date1 = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.getDefault()).format(currentTime);
        String time = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(currentTime);
        String time1 = new SimpleDateFormat("HHmm", Locale.getDefault()).format(currentTime);
        String time2 =new SimpleDateFormat("HH00", Locale.getDefault()).format(currentTime);



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


            try {
                JSONObject jobject = new JSONObject(s).getJSONObject("response").getJSONObject("body").getJSONObject("items");
                Log.d("sss", jobject+"");
                String jsonString = jobject+"";
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("item");
                Log.d("aaa", jarray+"");


                for (int i = 0; i < jarray.length(); i++) {

                    JSONObject jObject = jarray.getJSONObject(i);
                    category = jObject.optString("category");
                    fcstValue = jObject.optString("fcstValue");
                    fcstTime = jObject.optString("fcstTime");

                    todaydate.setText(date1);
                    todaytime.setText(time);

                    //02이면 2로 나오고 14이면 14로 나오니까 정수형 문자열로 바꿀때 처리
                    Log.d("time2", time2);
                    int time22 = Integer.parseInt(time2)+100;
                    String time222 = "0" + Integer.toString(time22);
                    Log.d("time222", time222);

                    if(fcstTime.equals(time222)){

                        if(category.equals("RN1")){
                            RN1.setText(jObject.optString("fcstValue"));

                        }else if(category.equals("REH")){
                            REH.setText(jObject.optString("fcstValue"));

                        }else if(category.equals("T1H")){
                            T1H.setText(jObject.optString("fcstValue"));

                        }else if(category.equals("SKY") && fcstValue.equals("1")){
                            Log.d("fff", fcstValue+"");
                            SKY.setImageResource(R.drawable.weather1);
                        }else if(category.equals("SKY") && fcstValue.equals("3")) {
                            Log.d("fff", fcstValue + "");
                            SKY.setImageResource(R.drawable.weather2);
                        }else if(category.equals("SKY") && fcstValue.equals("4")) {
                            Log.d("fff", fcstValue + "");
                            SKY.setImageResource(R.drawable.weather3);
                        }
                    }

                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... strings) {
            URL url1 = null;

            try {
                url1 = new URL("http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtFcst?serviceKey="+clientKey+"&pageNo=1&numOfRows=100&dataType=JSON&base_date="+date+"&base_time="+time1+"&nx=60&ny=125");

                HttpURLConnection conn;
                conn = (HttpURLConnection) url1.openConnection();
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
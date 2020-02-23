package com.example.swproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.swproject.Activitiies.FestivalDetailActivity;
import com.example.swproject.Activitiies.SearchActivity;
import com.example.swproject.Adapter.FestivalDataAdapter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentEvent extends Fragment {

    View view;
    Button areabutton;
    private Context context;
    private RecyclerView recyclerView;
    private RelativeLayout search;

    private FestivalDataAdapter adapter;



    public FragmentEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = (View) inflater.inflate(R.layout.fragment_event, container, false);

        (new ApiTask()).execute();


        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;

    }


    public class ApiTask extends AsyncTask<String, Void, String> {
        String clientKey = "tc0tnldlU5MiHXEg0Nh%2FjMbmMiAcZa%2FufND1SVQmeYOhSyN3S87N4aZ5sz%2F31o%2FJejwFhVBGBQ34iMaREdodnQ%3D%3D";
        private String str, receiveMsg;

        String addr1 = "";
        String firstimage = "";
        String title ="";
        String eventstartdate = "";
        String eventenddate = "";
        String contentid="";

        Date currentTime = Calendar.getInstance().getTime();
        String date = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);

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
                Log.d("object", jobject+"");
                String jsonString = jobject+"";
                JSONArray jarray = new JSONObject(jsonString).getJSONArray("item");
                Log.d("array", jarray+"");

                for (int i = 0; i < jarray.length(); i++) {
                    JSONObject jObject = jarray.getJSONObject(i);


                    firstimage = jObject.optString("firstimage");
                    title = jObject.optString("title");
                    eventstartdate = jObject.optString("eventstartdate");
                    eventenddate = jObject.optString("eventenddate");
                    addr1 = jObject.optString("addr1");
                    contentid = jObject.optString("contentid");

                    String startdate = eventstartdate.substring(4,6);
                    String monthdate = date.substring(4,6);




                    if(startdate.equals(monthdate)){
                        Log.d("date123", monthdate+startdate);
                        FestivalItem item = new FestivalItem();
                        item.setF_id(contentid);
                        item.setF_image(firstimage);
                        item.setF_name(title);
                        item.setF_date1(eventstartdate.substring(0,4)+"-"+eventstartdate.substring(4,6)+"-"+eventstartdate.substring(6,8));
                        item.setF_date2(eventenddate.substring(0,4)+"-"+eventenddate.substring(4,6)+"-"+eventenddate.substring(6,8));
                        item.setF_area(addr1);
                        temp.add(item);




                    }else{

                    }
                }

                adapter = new FestivalDataAdapter(temp, getContext());
                adapter.setOnClickviewListener(new FestivalDataAdapter.onClickviewListener() {
                    @Override
                    public void onClick(View view, FestivalItem item) {
                        Toast.makeText(getContext(), item.getF_id(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), FestivalDetailActivity.class);
                        intent.putExtra("contentid",item.getF_id());
                        startActivity(intent);
                    }
                });


                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview1);
                recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
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
                url1 = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchFestival?serviceKey=" + clientKey+ "&MobileOS=ETC&MobileApp=AppTest&arrange=A&numOfRows=300&listYN=Y&eventStartDate="+date+"&_type=json");

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
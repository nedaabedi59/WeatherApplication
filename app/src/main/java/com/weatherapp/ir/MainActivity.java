package com.weatherapp.ir;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.weatherapp.ir.model.Forecast;
import com.weatherapp.ir.model.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ir.aid.library.Frameworks.helper.ConnectionHelper;
import ir.aid.library.Frameworks.utils.SharedPreferenceUtils;
import ir.aid.library.Interfaces.OnGetResponse;

public class MainActivity extends AppCompatActivity {

    private static final String CITY_KEY = "city";
    private SharedPreferenceUtils preference;
    private TextView tvTemp,tvWeatherState,tvCity,tvDate;
    private EditText edtSearch;
    private ImageView iconWeather;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preference = new SharedPreferenceUtils(this);
        checkCity();
        recyclerView=findViewById(R.id.recyclerId);
        tvCity=findViewById(R.id.tvCityId);
        tvDate=findViewById(R.id.tvDateId);
        tvWeatherState=findViewById(R.id.tvWeatherId);
        iconWeather=findViewById(R.id.imgWeatherId);
        tvTemp=findViewById(R.id.tvTempId);
        edtSearch=findViewById(R.id.edtSearchId);
        Button btnSearch = findViewById(R.id.btnSearchId);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtSearch.getText().toString().equals("")){
                    edtSearch.setError("Empty");
                }else{
                    preference.writeString(CITY_KEY , edtSearch.getText().toString()); // اینم موقع زدن دکمه جستوجو پرفرنس رو با مقدار شهر کنونی پر میکنه

                    con(edtSearch.getText().toString());
                }
            }
        });
    }

    private void checkCity(){ // این چک میکنه که شهری از قبل هستش یا نه
        if (!preference.readString(CITY_KEY , "").equals("")){ // اگر شهر مخالف خالی بود اطلاعات شهر رو درجا بگیره از سرور
            con(preference.readString(CITY_KEY , ""));
        }
    }

    private void con(String city){
        String url="http://phoenix-iran.ir/Files_php_App/WeatherApi/newApiWeather.php";
        new ConnectionHelper(url, 5000).addStringRequest("city", city).addStringRequest("unit", "c").getResponse(new OnGetResponse() {
            @Override
            public void notConnection(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();


                    }
                });
            }

            @Override
            public void success(final String result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result.equals("null")) {
                            Toast.makeText(MainActivity.this, "شهر مورد نظر شما یافت نشد مجددا وارد کنید", Toast.LENGTH_SHORT).show();
                            preference.writeString(CITY_KEY , ""); // اگر شهر پیدا نشد داخل پرفرنس رو خالی میکنه

                        } else {
                            Gson gson = new Gson();

                            Weather api = gson.fromJson(result, Weather.class);

                            initData(api);
                        }
                    }
                });
            }

            @Override
            public void nullable(String result) {

            }
        });
    }

    private void initData(Weather api){
        List<Forecast> forecasts = api.getResult().getForecasts();
        NameAdapter adapter=new NameAdapter(forecasts,MainActivity.this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this
        ,LinearLayoutManager.VERTICAL,false));
        String cityName=api.getResult().getLocation().getCountry()+","+ api.getResult().getLocation().getCity();
        tvCity.setText(cityName);
        String date= getDate(Long.valueOf(api.getResult().getPubDate()));
        tvDate.setText(date);
        String txtState=api.getResult().getCondition().getText();
        tvWeatherState.setText(txtState);
        String temp=String.valueOf(api.getResult().getCondition().getTemperature()+" °C");
        tvTemp.setText(temp);
        iconWeather.setImageDrawable(getResources().getDrawable(
                WeatherPhoto.getPhotoWeather(api.getResult().getCondition().getText()) ));

    }

    private String getDate(Long time){
        Date date = new Date(time*1000L);
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-mm-dd");
        return simpleDateFormat.format(date);
    }
}

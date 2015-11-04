package itdeveapps.baustudents;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import dataweather.Channel;
import dataweather.Forcast;
import dataweather.Item;
import servicesweather.YahooServiceCallBack;
import servicesweather.YahooWeatherService;

public class WeatherActivity extends AppCompatActivity implements YahooServiceCallBack {
    Dialog dialogs;
    private ImageView img;
    private TextView temp;
    private TextView loctaion;
    private TextView condition;
    private YahooWeatherService service;
    private ProgressDialog dialog;
    private Day[] days;
    private ImageView im;
    private TextView h;
    private TextView l;
    private TextView d;
    private Button change;
    private Spinner li;
    private TextView current_weather;

    public void showDialog() {
        dialogs = new Dialog(this);


        dialogs.setContentView(R.layout.select);
        change = (Button) dialogs.findViewById(R.id.change);
        li = (Spinner) dialogs.findViewById(R.id.uni);
        dialogs.setCancelable(false);
        dialogs.show();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogs.hide();
                String c = li.getSelectedItem().toString();
                Forcast.index = 0;
                service = new YahooWeatherService(WeatherActivity.this);
                dialog = new ProgressDialog(WeatherActivity.this);
                dialog.setMessage("Loading...");
                dialog.show();
                if (c.contains("المركز"))
                    service.refreshWeather("Al Balqa, Yarqa");
                else if (c.contains("العقبة"))
                    service.refreshWeather("Al Aqaba, Jordan");
                else if (c.contains("الهندسة"))
                    service.refreshWeather("Amman, marka");
                else if (c.contains("مادبا")) {
                    temp.setText("\uD83D\uDC94");
                    loctaion.setText("No Madaba ");
                    condition.setText("\uD83D\uDE22");
                    dialog.hide();
                } else if (c.contains("الحصن"))
                    service.refreshWeather("Irbid, Jordan");
                else if (c.contains("إربد"))
                    service.refreshWeather("Irbid, Jordan");
                else if (c.contains("الزرقاء"))
                    service.refreshWeather("Al Zarqa, Jordan");
                else if (c.contains("المالية")) {
                    service.refreshWeather("Amman, Jordan");
                    loctaion.setText("Sweifieh ,Amman");
                } else if (c.contains("الكرك"))
                    service.refreshWeather("Al Karak, Jordan");
                else if (c.contains("معان"))
                    service.refreshWeather("Maan, Jordan");
                else if (c.contains("الشوبك"))
                    service.refreshWeather("Maan, Alshoubak");
                else if (c.contains("عجلون"))
                    service.refreshWeather("Ajloun, Jordan");





            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        }
        current_weather = (TextView) findViewById(R.id.current_weather);
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("weather_status", MODE_PRIVATE);
        String status = prefs.getString("status", "");
        current_weather.setText(status);

        img = (ImageView) findViewById(R.id.img);
        temp = (TextView) findViewById(R.id.temp);
        loctaion = (TextView) findViewById(R.id.location);
        condition = (TextView) findViewById(R.id.condetion);
        Forcast.index = 0;
        days = new Day[5];
        im = (ImageView) findViewById(R.id.img1);
        h = (TextView) findViewById(R.id.max1);
        l = (TextView) findViewById(R.id.low1);
        d = (TextView) findViewById(R.id.day1);
        days[0] = new Day(im, h, l, d);
        im = (ImageView) findViewById(R.id.img2);
        h = (TextView) findViewById(R.id.max2);
        l = (TextView) findViewById(R.id.low2);
        d = (TextView) findViewById(R.id.day2);
        days[1] = new Day(im, h, l, d);
        im = (ImageView) findViewById(R.id.img3);
        h = (TextView) findViewById(R.id.max3);
        l = (TextView) findViewById(R.id.low3);
        d = (TextView) findViewById(R.id.day3);
        days[2] = new Day(im, h, l, d);
        im = (ImageView) findViewById(R.id.img4);
        h = (TextView) findViewById(R.id.max4);
        l = (TextView) findViewById(R.id.low4);
        d = (TextView) findViewById(R.id.day4);
        days[3] = new Day(im, h, l, d);
        im = (ImageView) findViewById(R.id.img5);
        h = (TextView) findViewById(R.id.max5);
        l = (TextView) findViewById(R.id.low5);
        d = (TextView) findViewById(R.id.day5);
        days[4] = new Day(im, h, l, d);

        service = new YahooWeatherService(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading...");
        dialog.show();

        service.refreshWeather("Al Balqa, Yarqa");


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Forcast.index = 0;
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Forcast.index = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void servicesucsess(Channel channel) {
        dialog.hide();
        Item item = channel.getItem();
        int resources = getResources().getIdentifier("drawable/icon_" + item.getCondition().getCode(), null, getPackageName());
        @SuppressWarnings("deprecation") Drawable imagdrawble = getResources().getDrawable(resources);
        this.img.setImageDrawable(imagdrawble);
        temp.setText(item.getCondition().getTemp() + " \u00b0 " + channel.getUnit().getTemperature());
        condition.setText(item.getCondition().getDescription());
        loctaion.setText(service.getLocation());
        int cc[] = item.getForcast().getCodes();
        int hh[] = item.getForcast().getHigh();
        int ll[] = item.getForcast().getLow();
        String da[] = item.getForcast().getDays();


        for (int i = 0; i < 5; i++) {
            days[i].getD().setText(da[i]);

            days[i].h.setText(hh[i] + "");

            days[i].getL().setText(ll[i] + "");

            int res1 = getResources().getIdentifier("drawable/icon_" + cc[i], null, getPackageName());
            //noinspection deprecation
            imagdrawble = getResources().getDrawable(res1);
            days[i].getImg().setImageDrawable(imagdrawble);

        }

    }

    @Override
    public void servicefail(Exception ex) {
        dialog.dismiss();
        Intent i = new Intent(this, NoInternetConnectionActivity.class);
        finish();
        startActivity(i);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather, menu);


        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.selectuni) {

            showDialog();
        }
        return super.onOptionsItemSelected(item);
    }
}

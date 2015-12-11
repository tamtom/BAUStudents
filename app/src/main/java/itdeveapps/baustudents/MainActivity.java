package itdeveapps.baustudents;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton weatherBtn;
    private ImageButton calcBtn;
    private ImageButton noteBtn;
    private ImageButton adsBtn;
    private ImageButton mapsBtn;
    private ImageButton newsBtn;
    private boolean oriented;

    public boolean isOriented() {
        return oriented;
    }

    public void setOriented(boolean oriented) {
        this.oriented = oriented;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("oriented", isOriented());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = getSharedPreferences("first", MODE_PRIVATE);
        int ii = prefs.getInt("once", 0);
        if (ii == 0) {
            SharedPreferences.Editor editor = getSharedPreferences("first", MODE_PRIVATE).edit();

            editor.putInt("once", 1);
            editor.apply();
            new AlertDialog.Builder(this)
                    .setTitle("ضبط الاشعارات")
                    .setMessage("للحصول على افضل تجربه مع التطبيق يرجى ضبط الاعدادات التي تناسبك")
                    .setPositiveButton("الذهاب الى الاعدادات", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
        setOriented(false);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#43A047")));
        }
        newsBtn = (ImageButton) findViewById(R.id.news);
        newsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
            }
        });

        weatherBtn = (ImageButton) findViewById(R.id.weather_btn);
        weatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                if (haveNetworkConnection()) {
                    i = new Intent(MainActivity.this, WeatherActivity.class);
                } else {
                    i = new Intent(MainActivity.this, NoInternetConnectionActivity.class);

                }
                startActivity(i);
            }
        });
        calcBtn = (ImageButton) findViewById(R.id.avg_calc);
        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(i);
            }
        });
        noteBtn = (ImageButton) findViewById(R.id.notes);
        noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainNoteActivity.class);
                startActivity(i);
            }
        });
        adsBtn = (ImageButton) findViewById(R.id.adsbutton);
        adsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i;
                if (haveNetworkConnection()) {
                    i = new Intent(MainActivity.this, AnnouncementMainActivity.class);
                } else {
                    i = new Intent(MainActivity.this, NoInternetConnectionActivity.class);
                }
                startActivity(i);
            }
        });
        mapsBtn = (ImageButton) findViewById(R.id.maps);
        mapsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Coming Soon :)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        finish();
        System.exit(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
        if (id == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }
        if (id == R.id.important_sites) {
            startActivity(new Intent(this, ImportantLinks.class));
        }

        return super.onOptionsItemSelected(item);
    }
}

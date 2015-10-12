package itdeveapps.baustudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton weather_btn;
    private ImageButton avg_calc;
    private ImageButton note_activity;
    private ImageButton adsbutton;
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
      /*  oriented = savedInstanceState.getBoolean("oriented");
        if (oriented) {
            takedata();
        }*/
    }

    /*public void takedata() {
        final Dialog takeinfo = new Dialog(MainActivity.this);
        takeinfo.setContentView(R.layout.hours_marks);
        final EditText hours = (EditText) takeinfo.findViewById(R.id.hours_txt);
        final EditText avg = (EditText) takeinfo.findViewById(R.id.avg_txt);
        Button next = (Button) takeinfo.findViewById(R.id.next);
        Button cancel = (Button) takeinfo.findViewById(R.id.cancel);
        takeinfo.setCancelable(true);
        takeinfo.show();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOriented(false);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                takeinfo.hide();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int hours_done = 0;
                double current_avg = 0;
                if (hours.getText().toString().isEmpty() || avg.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Enter DATA PLEASE !!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    hours_done = Integer.parseInt(hours.getText().toString());
                    current_avg = Double.parseDouble(avg.getText().toString());
                }

                if (current_avg > 4.0 || current_avg < 0 || hours_done < 0) {
                    Toast.makeText(MainActivity.this, "قطاعة -.-", Toast.LENGTH_LONG).show();
                    return;

                }

                Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
                i.putExtra("hours", hours_done);
                i.putExtra("current_avg", current_avg);
                takeinfo.hide();

                startActivity(i);


            }
        });
    } */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOriented(false);

        weather_btn = (ImageButton) findViewById(R.id.weather_btn);
        weather_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WeatherActivity.class);
                startActivity(i);
            }
        });
        avg_calc = (ImageButton) findViewById(R.id.avg_calc);
        avg_calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* setOriented(true);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                takedata(); */
                Intent i = new Intent(MainActivity.this, CalculatorActivity.class);
                startActivity(i);
            }
        });
        note_activity = (ImageButton) findViewById(R.id.notes);
        note_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainNoteActivity.class);
                startActivity(i);
            }
        });
        adsbutton = (ImageButton) findViewById(R.id.adsbutton);
        adsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AnnouncementMainActivity.class);
                startActivity(i);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

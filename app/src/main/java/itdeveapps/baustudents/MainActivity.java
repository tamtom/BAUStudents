package itdeveapps.baustudents;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageButton weather_btn;
    private ImageButton avg_calc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Parse.initialize(this, "fGk54ikFVF8U0vS9d2AHTe8z2XN9HVHyPW9jpeU1", "KJ9XvTeCi6gPmLmy266hecwR32kSfSMny4YOrowu");
        //ParseInstallation.getCurrentInstallation().saveInBackground();

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
                final Dialog takeinfo = new Dialog(MainActivity.this);
                takeinfo.setContentView(R.layout.hours_marks);
                final EditText hours = (EditText) takeinfo.findViewById(R.id.hours_txt);
                final EditText avg = (EditText) takeinfo.findViewById(R.id.avg_txt);
                Button next = (Button) takeinfo.findViewById(R.id.next);
                takeinfo.setCancelable(false);
                takeinfo.show();
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

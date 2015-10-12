package itdeveapps.baustudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class AvgResult extends AppCompatActivity {
    private TextView trakomi;
    private TextView fasli;
    private Button again;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Locale[] locales = Locale.getAvailableLocales();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avg_result);
        trakomi = (TextView) findViewById(R.id.trakomires);
        fasli = (TextView) findViewById(R.id.faslires);
        Bundle e = getIntent().getExtras();
        double t = e.getDouble("altrakomi");
        double f = e.getDouble("alfasli");
        t = Math.round(t * 100.0) / 100.0;
        f = Math.round(f * 100.0) / 100.0;
        trakomi.setText(t + "");
        fasli.setText(f + "");
        again = (Button) findViewById(R.id.again);
        again
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avg_result, menu);
        return true;
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

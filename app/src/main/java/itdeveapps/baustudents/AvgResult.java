package itdeveapps.baustudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AvgResult extends AppCompatActivity {
    private TextView trakomi;
    private TextView fasli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

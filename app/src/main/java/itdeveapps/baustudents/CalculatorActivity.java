package itdeveapps.baustudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.ToggleButton;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity {
    private HashMap<String, Double> points;
    private TableRow row1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] spinners = {R.id.avg_txt};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        points = new HashMap<String, Double>();
        points.put("A", 4.0);
        points.put("B+", 3.5);
        points.put("B", 3.0);
        points.put("C+", 2.5);
        points.put("C", 2.0);
        points.put("D+", 1.5);
        points.put("D", 1.0);
        points.put("F", 0.5);
        row1 = (TableRow) findViewById(R.id.row1);
        final Spinner s1 = (Spinner) row1.findViewById(R.id.spinner1);
        final ToggleButton tt = (ToggleButton) row1.findViewById(R.id.ttot);
        tt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tt.getText().toString().equals(tt.getTextOn()))
                    s1.setVisibility(View.INVISIBLE);
                else
                    s1.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculator, menu);
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

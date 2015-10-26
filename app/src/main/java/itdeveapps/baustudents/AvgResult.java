package itdeveapps.baustudents;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class AvgResult extends Activity {
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
        SpannableString c = new SpannableString(t + "");
        c.setSpan(new UnderlineSpan(), 0, c.length(), 0);
        trakomi.setText(c);
        SpannableString c2 = new SpannableString(f + "");
        c2.setSpan(new UnderlineSpan(), 0, c2.length(), 0);
        fasli.setText(c2);
        again = (Button) findViewById(R.id.again);
        again
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }


}

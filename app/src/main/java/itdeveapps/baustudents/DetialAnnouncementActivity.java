package itdeveapps.baustudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetialAnnouncementActivity extends AppCompatActivity {
    TextView header;
    TextView content;
    TextView link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_announcement);
        header = (TextView) findViewById(R.id.headerd);
        content = (TextView) findViewById(R.id.contentd);
        link = (TextView) findViewById(R.id.link);
        link.setVisibility(View.INVISIBLE);
        Bundle e = getIntent().getExtras();
        if (e != null) {
            String head = e.getString("header");
            String c = e.getString("content");
            String link = e.getString("link");
            header.setText(head);
            content.setText(c);
            Log.d("link after", link);
            if (link != null) {
                this.link.setVisibility(View.VISIBLE);
                this.link.setText(
                        Html.fromHtml(
                                link));
                this.link.setMovementMethod(LinkMovementMethod.getInstance());

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detial_announcement, menu);
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

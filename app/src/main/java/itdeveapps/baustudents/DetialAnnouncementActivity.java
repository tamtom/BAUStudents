package itdeveapps.baustudents;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetialAnnouncementActivity extends AppCompatActivity {
    TextView header;
    TextView content;
    Button link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detial_announcement);

        StringBuilder br;
        header = (TextView) findViewById(R.id.headerd);
        content = (TextView) findViewById(R.id.contentd);
        link = (Button) findViewById(R.id.link);
        link.setVisibility(View.INVISIBLE);
        Bundle e = getIntent().getExtras();
        if (e != null) {
            String head = e.getString("header");
            br = new StringBuilder();
            String headerlong[] = head.split(" ");
            if (headerlong.length > 6) {
                for (int i = 0; i < headerlong.length; i++) {
                    if (i % 6 == 0)
                        br.append("\n");
                    br.append(headerlong[i] + " ");
                }
                header.setText(br.toString());

            } else {
                header.setText(head);

            }
            br = new StringBuilder();
            String c = e.getString("content");
            String contentlong[] = c.split(" ");
            int spitter = 6;
            if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
                spitter = 8;
            }
            if (contentlong.length > spitter) {
                for (int i = 0; i < contentlong.length; i++) {
                    if (i % spitter == 0)
                        br.append("\n");
                    br.append(contentlong[i] + " ");
                }
                content.setText(br.toString());
            } else {
                content.setText(c);
            }
            final String linkbutton = e.getString("link");


            if (linkbutton != null) {
                this.link.setVisibility(View.VISIBLE);
                this.link.setText("attachment");

                this.link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Uri uri = Uri.parse(linkbutton);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

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

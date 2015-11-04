package itdeveapps.baustudents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SelectedNews extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    String[] values;
    String url;
    String url2;
    private String[] linksDiscription;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_news);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        }
        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            url = extras.getString("url");

        }
        listView = (ListView) findViewById(R.id.lesview2);
        new GetItems().execute();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                url2 = linksDiscription[position];
                new showDesc().execute();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selected_news, menu);
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

    private class showDesc extends AsyncTask<Void, Void, Void> {
        String header;
        String conent;
        String link;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(SelectedNews.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Getting news");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d("the link is ", url2 + "");
                Document doc = Jsoup.connect(url2)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")
                        .timeout(10000).get();
                Element e = doc.getElementById("ContentPlaceHolderBody_ContentBody_lbl_News");
                e.getElementsByTag("p");
                conent = e.text();

                Element h = doc.getElementById("ContentPlaceHolderBody_ContentBody_lbl_NewsHead");
                header = h.text();
                Element s = doc.getElementById("ContentPlaceHolderBody_ContentBody_lblOtherPics");
                Elements f = s.getElementsByTag("a");
                for (Element ee : f) {
                    if (ee.attr("abs:href").contains("pdf")) {
                        link = ee.attr("abs:href");
                        break;
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (header == null) {
                Intent i = new Intent(SelectedNews.this, NoInternetConnectionActivity.class);

                startActivity(i);
            } else {
                mProgressDialog.dismiss();
                Intent i = new Intent(SelectedNews.this, DetialAnnouncementActivity.class);
                i.putExtra("header", header);
                Log.d("the header before aci", header + "");
                i.putExtra("content", conent);
                i.putExtra("link", link);
                startActivity(i);
            }
            //Toast.makeText(AnnouncementMainActivity.this,header+"\n"+conent,Toast.LENGTH_SHORT).show();
        }
    }

    private class GetItems extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(SelectedNews.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Getting news");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {

                Document document = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")
                        .timeout(10000).get();
                Elements e = document.select("a.bauliveportallinks");
                values = new String[e.size()];
                linksDiscription = new String[e.size()];

                int i = 0;
                for (Element s : e) {
                    if (i < values.length) {
                        values[i] = s.text();
                        linksDiscription[i] = s.attr("abs:href");
                    }
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (values != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SelectedNews.this, android.R.layout.simple_list_item_1, android.R.id.text1, values);
            listView.setAdapter(adapter);
            if (values.length > 0)
                mProgressDialog.dismiss();
            }
            else {
                mProgressDialog.dismiss();
                Intent i = new Intent(SelectedNews.this, NoInternetConnectionActivity.class);

                startActivity(i);

            }
        }
    }
}

package itdeveapps.baustudents;

import android.app.ProgressDialog;
import android.content.Intent;
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
import java.util.ArrayList;

public class AnnouncementMainActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    private ArrayList<String> Titles;
    private ArrayList<String> linksDiscription;
    private ListView l;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anouncment_main);
        l = (ListView) findViewById(R.id.listView);
        new AddTitle().execute();
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                url = linksDiscription.get(position);
                new showDesc().execute();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_anouncment_main, menu);
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
            mProgressDialog = new ProgressDialog(AnnouncementMainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Getting Announcement");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d("url", url);
                Document doc = Jsoup.connect(url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")
                        .get();
                Elements clas = doc.getElementsByClass("post-body");
                Log.d("header ", clas.select("b").text());
                header = clas.select("b").text();
                conent = clas.select("i").text();
                link = clas.select("a").toString();
                Log.d("link style", link);

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            mProgressDialog.dismiss();
            Intent i = new Intent(AnnouncementMainActivity.this, DetialAnnouncementActivity.class);
            i.putExtra("header", header);
            i.putExtra("content", conent);
            i.putExtra("link", link);
            startActivity(i);

            //Toast.makeText(AnnouncementMainActivity.this,header+"\n"+conent,Toast.LENGTH_SHORT).show();
        }
    }

    private class AddTitle extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(AnnouncementMainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Getting Announcement");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                linksDiscription = new ArrayList<>();
                Titles = new ArrayList<>();
                Document doc = Jsoup.connect("http://tamtomexperience.blogspot.com/")
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36")
                        .get();
                Elements clas = doc.getElementsByClass("post-title");
                ArrayList<Elements> ee = new ArrayList<>();
                for (Element e : clas) {

                    ee.add(e.getElementsByAttribute("href"));
                }
                for (int i = 0; i < ee.size() && i < 20; i++) {
                    Titles.add(ee.get(i).text());
                    linksDiscription.add(ee.get(i).attr("abs:href"));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            String v[] = new String[Titles.size()];
            v = Titles.toArray(v);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(AnnouncementMainActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, v);
            l.setAdapter(adapter);
            if (v.length > 0)
            mProgressDialog.dismiss();
            else {
                mProgressDialog.dismiss();
                Intent i = new Intent(AnnouncementMainActivity.this, NoInternetConnectionActivity.class);
                finish();
                startActivity(i);

            }
        }
    }
}

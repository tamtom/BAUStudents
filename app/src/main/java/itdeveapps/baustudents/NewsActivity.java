package itdeveapps.baustudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsActivity extends AppCompatActivity {
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        lv = (ListView) findViewById(R.id.lesview);
        String[] values = new String[]{"القبو والتسجيل"
                ,
                "الدراسات العليا"
                ,
                "كلية الامير عبدالله بن غازي "
                ,
                "كلية العلوم "
                ,
                "كلية الهندسة"
                ,
                "كلية الزراعة"
                ,
                "كلية الاعمال "
                ,
                "كلية السلط للعلوم الانسانية "

        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        lv.setAdapter(adapter);
        final String[] linksindex = new String[]{
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=22"
                ,
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=18",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=31",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=2",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=9",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=4",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=3",
                "http://live.bau.edu.jo/BauLivePortal/NewsByDetail.aspx?userid=15"};
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String url = linksindex[position];
                Intent i = new Intent(NewsActivity.this, SelectedNews.class);
                i.putExtra("url", url);
                startActivity(i);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news, menu);
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

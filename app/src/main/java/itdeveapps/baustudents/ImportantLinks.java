package itdeveapps.baustudents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ImportantLinks extends AppCompatActivity {
    private String sites[];
    private String links[];
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_important_links);
        lv = (ListView) findViewById(R.id.list123);
        sites = new String[]{"موقع الجامعة",
                "موقع الاكاديمية",
                "صفحة فيسبوك الجامعة",
                "جريدة المواد",
                "التسجيل",
                "صفحة طلاب الامير عبدالله بن غازي "};
        links = new String[]{"http://www.bau.edu.jo/",
                "http://elearning.bea.edu.jo/",
                "https://www.facebook.com/BAU.SALTOne",
                "http://app2.bau.edu.jo:7777/courses/index.jsp",
                "http://app1.bau.edu.jo:7777/reg_new/index.jsp"
                , "https://www.facebook.com/itdevelopers.bau"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, sites);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uri uri = Uri.parse(links[position]);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_important_links, menu);
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

package itdeveapps.baustudents;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

import java.util.ArrayList;

import database.DataBaseHandler;

public class MainNoteActivity extends AppCompatActivity {
    private DataBaseHandler dba;
    private ArrayList<MyNote> dbLists = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private ListView listView;

    @Override
    public void onBackPressed() {
        //Intent i = new Intent(this,MainActivity.class);
        finish();
        //startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_note);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03A9F4")));
        }
        listView = (ListView) findViewById(R.id.list);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setSize(FloatingActionButton.SIZE_MINI);
        fab.setColor(Color.parseColor("#0095ff"));
// NOTE invoke this method after setting new values!
        fab.initBackground();
// NOTE standard method of ImageView
        fab.setImageResource(R.drawable.ic_add_white_24dp);
        listView.setOnTouchListener(new ShowHideOnScroll(fab));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainNoteActivity.this, AddNewNoteActivity.class);
                finish();
                startActivity(i);
            }
        });
        refreshData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_note, menu);
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

    private void refreshData() {
        dbLists.clear();
        dba = new DataBaseHandler(getApplicationContext());

        final ArrayList<MyNote> notesFromDB = dba.getWishes();

        for (int i = 0; i < notesFromDB.size(); i++) {

            String title = notesFromDB.get(i).getTitle();
            String dateText = notesFromDB.get(i).getRecoredDate();
            String content = notesFromDB.get(i).getContent();
            int mid = notesFromDB.get(i).getItemId();


            //Log.v("IDs: " , String.valueOf(mid));

            MyNote myNote = new MyNote();
            myNote.setTitle(title);
            myNote.setContent(content);
            myNote.setRecoredDate(dateText);
            myNote.setItemId(mid);


            dbLists.add(myNote);


        }
        dba.close();

        //setup adapter
        noteAdapter = new NoteAdapter(MainNoteActivity.this, R.layout.note_row, dbLists);
        listView.setAdapter(noteAdapter);
        noteAdapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainNoteActivity.this, NoteDetialActivity.class);
                i.putExtra("content", notesFromDB.get(position).getContent());
                i.putExtra("date", notesFromDB.get(position).getRecoredDate());
                i.putExtra("title", notesFromDB.get(position).getTitle());
                i.putExtra("id", notesFromDB.get(position).getItemId());

                MainNoteActivity.this.finish();
                startActivity(i);


            }
        });


    }

    public class NoteAdapter extends ArrayAdapter<MyNote> {
        Activity activity;
        int layoutResource;
        MyNote note;
        ArrayList<MyNote> mData = new ArrayList<>();

        public NoteAdapter(Activity act, int resource, ArrayList<MyNote> data) {
            super(act, resource, data);
            activity = act;
            layoutResource = resource;
            mData = data;
            notifyDataSetChanged();


        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public MyNote getItem(int position) {
            return mData.get(position);
        }

        @Override
        public int getPosition(MyNote item) {
            return super.getPosition(item);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            ViewHolder holder = null;

            if (row == null || (row.getTag()) == null) {

                LayoutInflater inflater = LayoutInflater.from(activity);

                row = inflater.inflate(layoutResource, null);
                holder = new ViewHolder();

                holder.mTitle = (TextView) row.findViewById(R.id.name);
                holder.mDate = (TextView) row.findViewById(R.id.dateText);


                row.setTag(holder);

            } else {

                holder = (ViewHolder) row.getTag();
            }

            holder.myNote = getItem(position);

            holder.mTitle.setText(holder.myNote.getTitle());
            holder.mDate.setText(holder.myNote.getRecoredDate());


            final ViewHolder finalHolder = holder;
            holder.mTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String text = finalHolder.myNote.getContent().toString();
                    String dateText = finalHolder.myNote.getRecoredDate().toString();
                    String title = finalHolder.myNote.getTitle().toString();

                    int mid = finalHolder.myNote.getItemId();

                    Log.v("MyId: ", String.valueOf(mid));


//


                    Intent i = new Intent(MainNoteActivity.this, NoteDetialActivity.class);
                    i.putExtra("content", text);
                    i.putExtra("date", dateText);
                    i.putExtra("title", title);
                    i.putExtra("id", mid);


                    startActivity(i);


                }
            });


            return row;

        }


        class ViewHolder {

            MyNote myNote;
            TextView mTitle;
            int mId;
            TextView mContent;
            TextView mDate;

        }

    }
}

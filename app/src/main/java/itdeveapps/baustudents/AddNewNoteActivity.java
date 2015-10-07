package itdeveapps.baustudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import database.DataBaseHandler;

public class AddNewNoteActivity extends AppCompatActivity {

    private EditText title;
    private EditText content;
    private Button saveButton;
    private DataBaseHandler dba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_note);

        dba = new DataBaseHandler(AddNewNoteActivity.this);

        title = (EditText) findViewById(R.id.titleEditText);
        content = (EditText) findViewById(R.id.wishEditText);
        saveButton = (Button) findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                saveToDB();
            }
        });


    }

    private void saveToDB() {

        MyNote wish = new MyNote();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());


        dba.addWishes(wish);
        dba.close();

        //clear
        title.setText("");
        content.setText("");

        Intent i = new Intent(AddNewNoteActivity.this, MainNoteActivity.class);
        startActivity(i);


    }
}

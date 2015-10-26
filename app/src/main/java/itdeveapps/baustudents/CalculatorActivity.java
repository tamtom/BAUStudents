package itdeveapps.baustudents;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    double[] oldMarks;
    private HashMap<String, Double> points;
    private ToggleButton[] t_btns;
    private Spinner[] h_spinner;
    private Spinner[] old_spinner;
    private Spinner[] new_spinner;
    private CheckBox[] check_boxs;
    private int toggle_id[];
    private int hours_id[];
    private int oldM_id[];
    private int newM_id[];
    private int checkBox_id[];
    private int cutHour;
    private double currentAvg;
    private EditText cHour;
    private EditText cAVG;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        cHour = (EditText) findViewById(R.id.cut_hour);
        cAVG = (EditText) findViewById(R.id.current_avg);
        //  int[] spinners = {R.id.avg_txt};
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009688")));
        points = new HashMap<>();
        points.put("A", 4.0);
        points.put("B+", 3.5);
        points.put("B", 3.0);
        points.put("C+", 2.5);
        points.put("C", 2.0);
        points.put("D+", 1.5);
        points.put("D", 1.0);
        points.put("F", 0.5);
        toggle_id = new int[]{R.id.t1, R.id.t2, R.id.t3, R.id.t4, R.id.t5, R.id.t6, R.id.t7, R.id.t8, R.id.t9, R.id.t10};
        hours_id = new int[]{R.id.h1, R.id.h2, R.id.h3, R.id.h4, R.id.h5, R.id.h6, R.id.h7, R.id.h8, R.id.h9, R.id.h10};
        oldM_id = new int[]{R.id.o1, R.id.o2, R.id.o3, R.id.o4, R.id.o5, R.id.o6, R.id.o7, R.id.o8, R.id.o9, R.id.o10};
        newM_id = new int[]{R.id.m1, R.id.m2, R.id.m3, R.id.m4, R.id.m5, R.id.m6, R.id.m7, R.id.m8, R.id.m9, R.id.m10};
        checkBox_id = new int[]{R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.c6, R.id.c7, R.id.c8, R.id.c9, R.id.c10};


        t_btns = new ToggleButton[10];
        h_spinner = new Spinner[10];
        old_spinner = new Spinner[10];
        new_spinner = new Spinner[10];
        check_boxs = new CheckBox[10];
        for (int i = 0; i < 10; i++) {
            t_btns[i] = (ToggleButton) findViewById(toggle_id[i]);

            t_btns[i].setEnabled(false);

            h_spinner[i] = (Spinner) findViewById(hours_id[i]);
            h_spinner[i].setEnabled(false);

            old_spinner[i] = (Spinner) findViewById(oldM_id[i]);
            old_spinner[i].setEnabled(false);

            new_spinner[i] = (Spinner) findViewById(newM_id[i]);
            new_spinner[i].setEnabled(false);
            check_boxs[i] = (CheckBox) findViewById(checkBox_id[i]);

        }
        setListenerForToggle();
        setListenerForCheckBoxes();

    }

    private boolean handelrow() {
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked()) {
                if ((!Character.isDigit(h_spinner[i].getSelectedItem().toString().charAt(0))) || (new_spinner[i].getSelectedItem().toString().equals("العلامةالمتوقعة"))) {
                    Toast.makeText(CalculatorActivity.this, "الرجاء ادخال المعلومات كاملة في المادة  " + (i + 1), Toast.LENGTH_LONG).show();
                    return false;
                }

            }
            if (old_spinner[i].getSelectedItem().toString().equals("العلامةالسابقة") && t_btns[i].getTextOn().equals(t_btns[i].getText())) {
                Toast.makeText(CalculatorActivity.this, (i + 1) + "  الرجاء ادخال المعلومات كاملة في المادة   ", Toast.LENGTH_LONG).show();
                return false;

            }
        }
        return true;
    }

    public double altrakomi() {
        double convert = cutHour * currentAvg;
        Log.d("after convert : ", "" + convert);
        double pointInSemester = 0;
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked()) {
                pointInSemester += Integer.parseInt(h_spinner[i].getSelectedItem().toString()) * points.get(new_spinner[i].getSelectedItem().toString());
            }
        }
        Log.d("pointsemester : ", "" + pointInSemester);
        pointInSemester += convert;
        setOldMarks();
        double oldp = 0;
        for (int i = 0; i < oldMarks.length; i++) {
            oldp += oldMarks[i];
        }
        Log.d("old marks", oldp + "");

        pointInSemester = pointInSemester - oldp;
        Log.d("all Semester ", "" + pointInSemester);
        Log.d("cutted hour 1", cutHour + "");
        cutHour -= getsumOldHour();
        Log.d("cutted hour 2", cutHour + "");
        Log.d("all hours", getsumAllHour() + "");
        return (pointInSemester / (getsumAllHour() + cutHour));
    }

    public double getSemesterMark() {
        double sum = 0;
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked()) {
                sum += Integer.parseInt(h_spinner[i].getSelectedItem().toString()) * points.get(new_spinner[i].getSelectedItem().toString());
            }
        }

        return (sum / getsumAllHour());
    }

    private int getsumAllHour() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked()) {
                sum += Integer.parseInt(h_spinner[i].getSelectedItem().toString());
            }


        }
        return sum;
    }



    private int getsumOldHour() {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked() && t_btns[i].getTextOn().equals(t_btns[i].getText())) {
                sum += Integer.parseInt(h_spinner[i].getSelectedItem().toString());
            }


        }
        return sum;
    }




    private void setOldMarks() {
        oldMarks = new double[getNumberOfOldSubjects()];
        for (int i = 0, index = 0; i < 10 && index < oldMarks.length; i++) {
            if (check_boxs[i].isChecked() && t_btns[i].getTextOn().equals(t_btns[i].getText())) {
                oldMarks[index] = points.get(old_spinner[i].getSelectedItem().toString()) * Integer.parseInt(h_spinner[i].getSelectedItem().toString());
                index++;
            }
        }

    }

    private int getNumberOfOldSubjects() {
        int c = 0;
        for (int i = 0; i < 10; i++) {
            if (check_boxs[i].isChecked() && t_btns[i].getTextOn().equals(t_btns[i].getText()))
                c++;
        }
        return c;
    }

    private void setListenerForCheckBoxes() {
        for (int i = 0; i < 10; i++) {
            check_boxs[i].setOnClickListener(this);
        }
    }

    public int getIndexC(int v) {
        for (int i = 0; i < 10; i++) {
            if (v == checkBox_id[i])
                return i;
        }
        return 0;
    }

    public int getIndexV(int v) {
        for (int i = 0; i < 10; i++) {
            if (v == toggle_id[i])
                return i;
        }
        return 0;
    }

    public void setListenerForToggle() {
        for (int i = 0; i < 10; i++) {
            t_btns[i].setOnClickListener(this);
        }
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

            boolean ok = true;
            if (cHour.getText().toString().isEmpty() || cAVG.getText().toString().isEmpty()) {
                Toast.makeText(CalculatorActivity.this, "Enter DATA PLEASE !!", Toast.LENGTH_SHORT).show();
                ok = false;
            } else {
                cutHour = Integer.parseInt(cHour.getText().toString());
                currentAvg = Double.parseDouble(cAVG.getText().toString());
            }

            if (currentAvg > 4.0 || currentAvg < 0 || cutHour < 0) {
                Toast.makeText(CalculatorActivity.this, "قطاعة -.-", Toast.LENGTH_LONG).show();
                ok = false;

            }
            if (handelrow() && ok) {
            Intent i = new Intent(CalculatorActivity.this, AvgResult.class);

            i.putExtra("altrakomi", altrakomi());
            i.putExtra("alfasli", getSemesterMark());

                // finish();
                startActivity(i);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof ToggleButton) {

            int i = getIndexV(v.getId());

            ToggleButton temp = (ToggleButton) findViewById(v.getId());
            if (temp.getTextOff().equals(temp.getText().toString())) {
                old_spinner[i].setEnabled(false);

            } else {

                old_spinner[i].setEnabled(true);
            }
        } else if (v instanceof CheckBox) {
            CheckBox temp = (CheckBox) findViewById(v.getId());
            int i = getIndexC(v.getId());
            if (temp.isChecked()) {
                t_btns[i].setEnabled(true);
                h_spinner[i].setEnabled(true);
                new_spinner[i].setEnabled(true);
            } else {
                t_btns[i].setEnabled(false);
                h_spinner[i].setEnabled(false);
                new_spinner[i].setEnabled(false);
            }
        }


    }

    @Override
    public void onBackPressed() {
        //Intent i = new Intent(this, MainActivity.class);
        finish();
        //  startActivity(i);

    }
}

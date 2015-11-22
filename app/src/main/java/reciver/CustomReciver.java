package reciver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import itdeveapps.baustudents.AnnouncementMainActivity;
import itdeveapps.baustudents.CalculatorActivity;
import itdeveapps.baustudents.MainActivity;
import itdeveapps.baustudents.MainNoteActivity;
import itdeveapps.baustudents.NewsActivity;
import itdeveapps.baustudents.R;
import itdeveapps.baustudents.WeatherActivity;

/**
 * Created by omar on 10/5/2015.
 */
public class CustomReciver extends ParsePushBroadcastReceiver {
    String open;
    private Intent parseIntent;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        if (intent == null)
            return;

        try {
            JSONObject json = new JSONObject(intent.getExtras().getString("com.parse.Data"));

            Log.e("hellowWorld", "Push received: " + json);

            parseIntent = intent;

            parsePushJson(context, json);

        } catch (JSONException e) {
            Log.e("parse exeption", "Push message json exception: " + e.getMessage());
        }
    }

    private void parsePushJson(Context context, JSONObject json) {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        boolean sunday = SP.getBoolean("sunday", false);
        boolean monday = SP.getBoolean("monday", false);
        boolean tusday = SP.getBoolean("tusday", false);
        boolean winsday = SP.getBoolean("winsday", false);
        boolean thursday = SP.getBoolean("thursday", false);
        ArrayList<Integer> ls = new ArrayList<>();
        if (sunday)
            ls.add(1);
        if (monday)
            ls.add(2);
        if (tusday)
            ls.add(3);
        if (winsday)
            ls.add(4);
        if (thursday)
            ls.add(5);

        try {
            JSONObject data = json.getJSONObject("data");
            String messege = data.getString("message");
            String title = data.getString("title");
            try {
                open = data.getString("open");
            } catch (JSONException ex) {
                open = "";
            }
            setOpen(open);
            Intent result;
            if (getOpen().equals("ads"))
                result = new Intent(context, AnnouncementMainActivity.class);
            else if (getOpen().equals("calc"))
                result = new Intent(context, CalculatorActivity.class);
            else if (getOpen().equals("weather"))
                result = new Intent(context, WeatherActivity.class);
            else if (getOpen().equals("note"))
                result = new Intent(context, MainNoteActivity.class);
            else if (getOpen().equals("news"))
                result = new Intent(context, NewsActivity.class);
            else
                result = new Intent(context, MainActivity.class);
            if (open.equals("weather")) {
                SharedPreferences.Editor editor = context.getApplicationContext().getSharedPreferences("weather_status", 0).edit();
                editor.putString("status", data.getString("status"));

                editor.commit();
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                Log.d("day", day + "");
                Log.d("", "" + tusday);
                if (ls.contains(day))
                    showNotification(messege, title, context, result);
            }
            if (!open.equals("weather"))
                showNotification(messege, title, context, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(String messege, String title, Context context, Intent intent) {

        int icon = R.drawable.notif_ic;
        int mNotificationId = 100;
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );


        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(resultPendingIntent).setTicker(title)
                .setContentTitle(title)
                .setContentText(messege)

                .setSmallIcon(icon).setAutoCancel(true);
        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(mNotificationId, notification);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
        Log.d("onpush", "we are in");


    }
}

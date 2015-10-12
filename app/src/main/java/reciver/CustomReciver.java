package reciver;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import itdeveapps.baustudents.AnnouncementMainActivity;

/**
 * Created by omar on 10/5/2015.
 */
public class CustomReciver extends ParsePushBroadcastReceiver {
    private Intent parseIntent;

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
        try {
            JSONObject data = json.getJSONObject("data");
            String messege = data.getString("message");
            String title = data.getString("title");
            showNotification(messege, title, context);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showNotification(String messege, String title, Context context) {
        Log.d("notific", messege + "   " + title);
        Notification mBuilder = new NotificationCompat.Builder(context).setTicker(title).setContentText(messege)
                .build();

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(00, mBuilder);
    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);

        Intent i = new Intent(context, AnnouncementMainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);


    }
}

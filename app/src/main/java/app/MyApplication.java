package app;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by omar on 10/5/2015.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "fGk54ikFVF8U0vS9d2AHTe8z2XN9HVHyPW9jpeU1", "KJ9XvTeCi6gPmLmy266hecwR32kSfSMny4YOrowu");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

}

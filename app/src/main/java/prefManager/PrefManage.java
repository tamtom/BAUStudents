package prefManager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by omar on 10/5/2015.
 */
public class PrefManage {
    private static final String PREF_NAME = "DONE";
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;

    public PrefManage(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void initnotification() {
        editor.putBoolean("isInit", true);
        editor.commit();
    }

    public boolean isInit() {
        Log.d("isInit", pref.getBoolean("isInit", false) + "");
        return pref.getBoolean("isInit", false);
    }
}

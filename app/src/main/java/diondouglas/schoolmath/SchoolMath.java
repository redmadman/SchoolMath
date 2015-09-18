package diondouglas.schoolmath;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;


public class SchoolMath extends Application {
    private static Context context;
    private static SharedPreferences mPrefs;

    public static SharedPreferences getmPrefs() {
        return mPrefs;
    }


    private Activity myCurrentActivity = null;

    public Activity getMyCurrentActivity() {
        return myCurrentActivity;
    }

    public void setMyCurrentActivity(Activity myCurrentActivity) {
        this.myCurrentActivity = myCurrentActivity;
    }

    public void onCreate() {
        super.onCreate();
        SchoolMath.context = getApplicationContext();
        context.getSharedPreferences("schoolMathPrefs", 0);
    }

    public static Context getAppContext() {
        return SchoolMath.context;
    }
}

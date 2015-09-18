package diondouglas.schoolmath;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dion on 9/14/2015.
 */
public class SchoolMath extends Application {
    private static Context context;

    public static SharedPreferences getmPrefs() {
        return mPrefs;
    }

    private static SharedPreferences mPrefs;

    public void onCreate(){
        super.onCreate();
        SchoolMath.context = getApplicationContext();
        context.getSharedPreferences("schoolMathPrefs",0);
    }
    public static Context getAppContext(){
        return SchoolMath.context;
    }

}


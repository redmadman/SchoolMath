package diondouglas.firstgrademathbydion;

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
    private static int currentGradeLevel;
    private static boolean addReward =false;

    public static String currentOperator;

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
        mPrefs = this.getSharedPreferences("schoolMathPrefs", 0);
    }


    //Getters & Setters
    public static Context getAppContext() {
        return SchoolMath.context;
    }
    public static int getCurrentGradeLevel(){
        int gradeLevel;
        gradeLevel = mPrefs.getInt("currentGradeLevel", 0);
        return gradeLevel;
    }
    public static void setCurrentGradeLevel(int i){
        currentGradeLevel=i;
    }
    public static String getCurrentOperator() {
        return currentOperator;
    }
    public static void setCurrentOperator(String currentOperator) {
        SchoolMath.currentOperator = currentOperator;
    }
    public static boolean isAddReward() {
        return addReward;
    }
    public static void setAddReward(boolean addReward) {
        SchoolMath.addReward = addReward;
    }

}



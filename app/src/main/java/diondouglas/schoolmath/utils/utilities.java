package diondouglas.schoolmath.utils;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.view.View;

import diondouglas.schoolmath.MathActivityFragment;
import diondouglas.schoolmath.R;
import diondouglas.schoolmath.SchoolMath;

public class utilities {

    public static void OpenSplashScreen(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new SplashScreenFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
    public static void OpenRewardsScreen(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new rewardScreen());
        ft.addToBackStack(null);
        ft.commit();

    }
    public static void OpenGenderSelect(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new genderSelect());
        ft.addToBackStack(null);
        ft.commit();

    }
    public static void openMainActivity(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.addToBackStack(null).replace(R.id.mainFragment, new MathActivityFragment()).commit();
    }

    public static void openGradeLevelSelect(View view){
        Activity activity=(Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.addToBackStack(null).replace(R.id.mainFragment, new SelectGradeLevel()).commit();
    }

    public static boolean getGender(View view){
        //Defaults to Girl if anything went wrong
        SharedPreferences mPrefs;
        mPrefs = SchoolMath.getmPrefs();
        return mPrefs.getBoolean("SelectGender", true);
    }

    public static int getGenderTheme(View view){
        if(getGender(view)){
            return R.style.girl;
        }else {
            return R.style.boy;
        }
    }


}

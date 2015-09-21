package diondouglas.firstgrademathbydion.utils;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import diondouglas.firstgrademathbydion.R;
import diondouglas.firstgrademathbydion.SchoolMath;
import diondouglas.firstgrademathbydion.fragments.firstgrade.MathActivityFragment;
import diondouglas.firstgrademathbydion.fragments.general.SelectGradeLevel;
import diondouglas.firstgrademathbydion.fragments.general.SplashScreenFragment;
import diondouglas.firstgrademathbydion.fragments.general.genderSelect;
import diondouglas.firstgrademathbydion.fragments.firstgrade.gradeOneRewardScreen;

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
        ft.replace(R.id.mainFragment, new gradeOneRewardScreen());
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
        try {
            FileOutputStream fos = SchoolMath.getAppContext().openFileOutput("test", Context.MODE_PRIVATE);
            ObjectOutputStream is = new ObjectOutputStream(fos);
            is.writeObject(SchoolMath.getmPrefs());
            is.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
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

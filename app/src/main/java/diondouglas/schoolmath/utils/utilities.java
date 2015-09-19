package diondouglas.schoolmath.utils;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;

import diondouglas.schoolmath.MainActivityFragment;
import diondouglas.schoolmath.R;

public class utilities {

    public static void OpenSplashScreen(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new SplashScreenFragment()).addToBackStack(null).commit();
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
        ft.addToBackStack(null).replace(R.id.mainFragment, new MainActivityFragment()).commit();
    }

}

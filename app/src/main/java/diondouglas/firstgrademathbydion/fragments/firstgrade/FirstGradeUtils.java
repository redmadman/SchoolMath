package diondouglas.firstgrademathbydion.fragments.firstgrade;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.view.View;

import diondouglas.firstgrademathbydion.R;

/**
 * Created by Dion on 9/23/2015.
 */
public class FirstGradeUtils {
    public static void OpenHowMany (View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new CountingThingsFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
    public static void OpenEasyAdd (View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new MathActivityFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

    public static void OpenFirstGradeGameSelector(View view){
        Activity activity = (Activity)view.getContext();
        FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment, new FirstGradeGameSelector());
        ft.addToBackStack(null);
        ft.commit();
    }
}

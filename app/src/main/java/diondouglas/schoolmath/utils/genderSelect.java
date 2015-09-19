package diondouglas.schoolmath.utils;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import diondouglas.schoolmath.MainActivityFragment;
import diondouglas.schoolmath.R;
import diondouglas.schoolmath.SchoolMath;

public class genderSelect extends Fragment {


    private static SharedPreferences mPrefs;

    public genderSelect() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = this.getActivity().getSharedPreferences("schoolMathPrefs", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_gender_select, container, false);
        ImageButton boy = (ImageButton)view.findViewById(R.id.imageButtonBoy);
        boy.setOnClickListener(new View.OnClickListener()
        {
            @Override
                    public void onClick(View v){
                selectBoy();
            }
        });
        ImageButton girlButton = (ImageButton)view.findViewById(R.id.imageButtonGirl);
        girlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectGirl();
            }
        });

        return view;
    }


    public void selectGirl(){
        mPrefs=this.getActivity().getSharedPreferences("schoolMathPrefs",0);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean("SelectGender", true);
        editor.apply();
        setRan();
        nextPref();

    }
    public void selectBoy(){
        mPrefs= SchoolMath.getmPrefs();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean("SelectGender", false);
        editor.apply();
        setRan();
        nextPref();
    }

    public void nextPref(){

        //TOFO MORE preferences page if/when we add more
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment,new SplashScreenFragment(), "Splash Screen").commit();

    }

    public void setRan(){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean("firstRun", false);
        editor.apply();
    }


}

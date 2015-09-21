package diondouglas.firstgrademathbydion.fragments.general;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import diondouglas.firstgrademathbydion.R;
import diondouglas.firstgrademathbydion.SchoolMath;


public class SelectGradeLevel extends Fragment {


    public SelectGradeLevel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_select_grade_level, container, false);
        return view;
    }


    public static void clickButton(View view){
        //TODO Logic to select grade level
        Button button = (Button)view;
        int i;
        switch (button.getText().toString()){
            case "K":
                i=0;
                break;
            default:
                i=Integer.parseInt(button.getText().toString());
        }
        SharedPreferences.Editor editor = SchoolMath.getmPrefs().edit();
        editor.putInt("currentGradeLevel",i);
        editor.commit();
        SchoolMath.setCurrentGradeLevel(i);
    }
}

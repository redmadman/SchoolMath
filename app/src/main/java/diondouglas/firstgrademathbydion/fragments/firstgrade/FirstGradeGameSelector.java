package diondouglas.firstgrademathbydion.fragments.firstgrade;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import diondouglas.firstgrademathbydion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstGradeGameSelector extends Fragment {


    public FirstGradeGameSelector() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_grade_game_selector, container, false);
    }


}

package diondouglas.schoolmath.utils;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import diondouglas.schoolmath.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class selectOperator extends Fragment {


    public selectOperator() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_operator, container, false);
    }


}

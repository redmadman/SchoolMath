package diondouglas.schoolmath;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import diondouglas.schoolmath.main_top_fragment.*;
import diondouglas.schoolmath.keyPad.*;
/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements keyPadListener, mainTopListener{


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        return root;
    }


    public void onKeyPadInteraction(Uri uri){
    }

    public void onMainTopInteraction(Uri uri){
    }


    // TODO: Update argument type and name






}

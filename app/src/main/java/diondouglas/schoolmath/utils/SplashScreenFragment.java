package diondouglas.schoolmath.utils;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import diondouglas.schoolmath.MainActivityFragment;
import diondouglas.schoolmath.R;


public class SplashScreenFragment extends Fragment {
    private OnSplashScreenInteractionListener mListener;


    public SplashScreenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_splash_screen, container, false);
        Button okButton = (Button)view.findViewById(R.id.SplashScreenOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFragment,new MainActivityFragment(), "MainActivityFragment").commit();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSplashScreenInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSplashScreenInteractionListener {
        // TODO: Update argument type and name
        void onSplashScreenInteraction(Uri uri);
    }

}

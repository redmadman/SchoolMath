package diondouglas.firstgrademathbydion.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import diondouglas.firstgrademathbydion.R;
import diondouglas.firstgrademathbydion.utils.utilities;


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
                utilities.openMainActivity(v);
            }
        });
        Button settingsButton = (Button)view.findViewById(R.id.SplashScreenSettingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilities.OpenGenderSelect(v);
            }
        });
        Button rewardsButton = (Button)view.findViewById(R.id.RewardsButton);
        rewardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilities.OpenRewardsScreen(v);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSplashScreenInteractionListener {

        void onSplashScreenInteraction(Uri uri);
    }

}

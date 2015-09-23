package diondouglas.firstgrademathbydion.utils;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import diondouglas.firstgrademathbydion.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KeyPadFragment extends Fragment {


    public KeyPadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_key_pad, container, false);
        onAttach(getParentFragment());
        addButtonListeners(view);
        return view;
    }

    public interface KeyPadListener{
        void KeyPadClick(View view);
    }

    public void addButtonListeners(View view){
        String string;
        int resId;
        Button button;
        button = (Button) view.findViewById(R.id.keyPadButtonClear);
        addListener(button);
        button = (Button) view.findViewById(R.id.keyPadButtonCheck);
        addListener(button);
        for (int i=0;i<10;i++){
            string = "keyPadButton" + i;
            resId = getActivity().getResources().getIdentifier(string, "id", getActivity().getPackageName());
            button = (Button)view.findViewById(resId);
            addListener(button);
        }
    }

    private void addListener(Button button){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mKeyPadListener.KeyPadClick(v);
            }
        });
    }
    KeyPadFragment.KeyPadListener mKeyPadListener;

    public void onAttach(Fragment fragment)
    {
        try
        {
            mKeyPadListener = (KeyPadFragment.KeyPadListener) fragment;
        } catch (ClassCastException e)
        {
            throw new ClassCastException(fragment.toString() + " must implement OnPlayerSelectionSetListener");
        }
    }

}

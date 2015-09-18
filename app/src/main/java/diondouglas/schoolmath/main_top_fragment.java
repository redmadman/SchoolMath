package diondouglas.schoolmath;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;


public class main_top_fragment extends Fragment{
    private mainTopListener mListener;
    private static boolean answerLocked = false;
    private static View myView;
    private static Random myRandom;
    private static Activity activity;


    public main_top_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myRandom = new Random();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main_top_fragment, container, false);
        myView = v;
        activity = this.getActivity();
        PopulateFields();

        return v;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onMainTopInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface mainTopListener {
        // TODO: Update argument type and name
        void onMainTopInteraction(Uri uri);
    }

    public static void UpdateAnswer(String str){

        TextView tv = (TextView)myView.findViewById(R.id.AnswerTextField);
        String tmpstr;

        switch (str){
            case "CLEAR":
                answerLocked=false;
                tv.setText("");
                tv.setBackgroundColor(Color.TRANSPARENT);
                break;
            case "Check":
                answerLocked=true;
                CheckAnswer();
                break;
            default:
                if(!answerLocked){
                    tmpstr = tv.getText().toString()+str;
                    if (!(tv.getText().toString().length()>=2)){
                        tv.setText(tmpstr);
                    }
                }
                break;
        }

    }

    public static void PopulateFields(){

        //TODO Get operator from somewhere
        String OPERATOR= "+";
        //TODO GET DIFFICULTY
        int topNumber, bottomNumber;
        int topNumberDifficulty=10;
        int answerNumberDifficulty=10;



        switch (OPERATOR){
            case "+":
                topNumber = myRandom.nextInt(topNumberDifficulty);
                bottomNumber = myRandom.nextInt(answerNumberDifficulty-topNumber);

                break;
            default:
                topNumber = myRandom.nextInt(10);
                bottomNumber = myRandom.nextInt(10);
        }

        TextView textView = (TextView)myView.findViewById(R.id.OeratorTextField);
        textView.setText(OPERATOR);
        textView =(TextView)myView.findViewById(R.id.TopNumberTextField);
        textView.setText(Integer.toString(topNumber));
        textView=(TextView)myView.findViewById(R.id.BottomNumberTextField);
        textView.setText(Integer.toString(bottomNumber));

    }

    public static void CheckAnswer(){
        String OPERATOR = "+";
        int topNumber, bottomNumber, answer, guess;
        TextView textView =(TextView) myView.findViewById(R.id.TopNumberTextField);
        topNumber = Integer.parseInt(textView.getText().toString());
        textView = (TextView)myView.findViewById(R.id.BottomNumberTextField);
        bottomNumber = Integer.parseInt(textView.getText().toString());
        textView = (TextView)myView.findViewById(R.id.AnswerTextField);
        guess = Integer.parseInt(textView.getText().toString());
        switch (OPERATOR){
            case "+":
                answer = topNumber+bottomNumber;
                break;
            default:
                answer = topNumber+bottomNumber;
        }

        if(answer==guess){
            //TODO Correct Answer Logic
            textView.setText("");
            PopulateFields();
            answerLocked = false;
            MediaPlayer mp = MediaPlayer.create(activity.getApplicationContext(), R.raw.tada);
            mp.start();
            updateProgressBar();

            //If correct
        }else {
            //TODO Wrong Answer Logic
            //if wrong
            MediaPlayer mp = MediaPlayer.create(activity.getApplicationContext(), R.raw.wrong);
            mp.start();

        }

    }

    private static void updateProgressBar(){
        ProgressBar progressBar = (ProgressBar) myView.findViewById(R.id.topPanelProgressBar);
        int i = progressBar.getProgress();
        if (i<progressBar.getMax()){
            i++;
            progressBar.setProgress(i);
            if (i==progressBar.getMax()){
                progressBar.setMax(progressBar.getMax()+progressBar.getMax());
                progressBar.setProgress(0);
            }
        }

    }
}

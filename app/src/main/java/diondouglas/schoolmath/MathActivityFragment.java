package diondouglas.schoolmath;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;


public class MathActivityFragment extends Fragment{

    //private mainTopListener mListener;
    private static boolean answerLocked = false;
    private static View myView;
    private static Random myRandom;
    private static Activity activity;


    public MathActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_main, container, false);
        View root = inflater.inflate(R.layout.mainactivityfragment, container, false);
        myView = root;
        myRandom = new Random();
        activity = (Activity)root.getContext();
        PopulateFields(root);
        startProgressBar(root);

        return root;
    }

    public static void UpdateAnswer(View view){

        Button b = (Button)view;
        String str = b.getText().toString();
        View v = view;
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
                CheckAnswer(v);
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

    public static void PopulateFields(View view){

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

    public static void CheckAnswer(View view){


        String OPERATOR = "+";
        int topNumber, bottomNumber, answer, guess;
        TextView textView =(TextView) myView.findViewById(R.id.TopNumberTextField);
        topNumber = Integer.parseInt(textView.getText().toString());
        textView = (TextView)myView.findViewById(R.id.BottomNumberTextField);
        bottomNumber = Integer.parseInt(textView.getText().toString());
        textView = (TextView)myView.findViewById(R.id.AnswerTextField);

        if(!(textView.getText().toString().equals(""))) {
            guess = Integer.parseInt(textView.getText().toString());
        }else {
            textView.setText("0");
            return; //send user back if field is empty
        }

        switch (OPERATOR){
            case "+":
                answer = topNumber+bottomNumber;
                break;
            default:
                answer = topNumber+bottomNumber;
        }

        if(answer==guess){
            textView.setText("");
            PopulateFields(view);
            answerLocked = false;
            correctAnswer(view);

        }else {
            //TODO Wrong Answer Logic
            //if wrong
            MediaPlayer mp = MediaPlayer.create(activity.getApplicationContext(), R.raw.wrong);
            mp.start();

        }

    }

    private static void correctAnswer(View view){
        MediaPlayer mp = MediaPlayer.create(activity.getApplicationContext(), R.raw.tada);
        mp.start();
        updateProgressBar(view);
    }

    private static void startProgressBar(View view){
        //TODO Make level/grade specific eventually
        ProgressBar progressBar = (ProgressBar)myView.findViewById(R.id.topPanelProgressBar);

        SharedPreferences mPrefs = SchoolMath.getmPrefs();
        //***************
        mPrefs = view.getContext().getApplicationContext().getSharedPreferences("schoolMathPrefs",0);
        //****************

        int i = mPrefs.getInt("rewardsProgress", 2);
        progressBar.setMax(i);
    }

    private static void updateProgressBar(View view){
        ProgressBar progressBar = (ProgressBar) myView.findViewById(R.id.topPanelProgressBar);
        int i = progressBar.getProgress();
        if (i<progressBar.getMax()){
            i++;
            progressBar.setProgress(i);
            if (i==progressBar.getMax()){
                progressBar.setMax(progressBar.getMax() + progressBar.getMax());
                progressBar.setProgress(0);
                SharedPreferences mPrefs = SchoolMath.getmPrefs();
                //***************
                mPrefs = view.getContext().getApplicationContext().getSharedPreferences("schoolMathPrefs",0);
                //****************
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putInt("rewardsProgress", i*2);
                editor.commit();
                diondouglas.schoolmath.utils.utilities.OpenRewardsScreen(view);
            }
        }

    }

    public void KeyPadClick(View v){
        UpdateAnswer(v);
    }







}

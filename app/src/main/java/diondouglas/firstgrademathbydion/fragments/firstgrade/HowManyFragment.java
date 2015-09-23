package diondouglas.firstgrademathbydion.fragments.firstgrade;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import diondouglas.firstgrademathbydion.MainActivity;
import diondouglas.firstgrademathbydion.R;
import diondouglas.firstgrademathbydion.SchoolMath;
import diondouglas.firstgrademathbydion.utils.KeyPadFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HowManyFragment extends Fragment implements KeyPadFragment.KeyPadListener{


    private View myView;
    private Random random = new Random();
    private int ANSWER;
    private boolean answerLocked = false;
    private String QUESTION_STRING= null;
    private String currentPicture;
    public HowManyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_how_many, container, false);
        myView = view;
        getPictures(view);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.HowManyImages);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.speak(QUESTION_STRING);
            }
        });
        return view;
    }

    private void getPictures(View view){
        clearPictures(myView);
        int OldAnswer = ANSWER;
        do {
            ANSWER = random.nextInt(10)+1;
        }while (OldAnswer==ANSWER);
        ImageView image;
        int resID;
        resID = getSpecialImageID();
        for(int i=1;i<=ANSWER;i++){
            String string = "HowManyImage" + i;
            int imageID = myView.getResources().getIdentifier(string, "id", getActivity().getPackageName());
            ImageView imageView= (ImageView)myView.findViewById(imageID);
            imageView.setImageBitmap(decodeSampledBitmapFromResource(getResources(), resID, 150, 150));
        }
        QUESTION_STRING = "How many " + getResources().getResourceEntryName(resID) + "s are there?";
        TextView tv = (TextView)myView.findViewById(R.id.HowManyQuestion);
        tv.setText(QUESTION_STRING);
        //MainActivity.speak(QUESTION_STRING);
        //view.invalidate();
    }

    private void clearPictures(View view){
        for (int i=1; i<11;i++){
            String string = "HowManyImage" + i;
            int resID = view.getResources().getIdentifier(string, "id", getActivity().getPackageName());
            ImageView imageView= (ImageView)view.findViewById(resID);
            imageView.setImageBitmap(null);
        }
    }

    private int getSpecialImageID(){
        int resID;
        String oldPicture = currentPicture;
        if (oldPicture==null)oldPicture="";
        String[]arr=getResources().getStringArray(R.array.howmanypics);
        do {
            int i = random.nextInt(arr.length);
            resID = getActivity().getResources().getIdentifier(arr[i].toLowerCase(), "drawable", getActivity().getPackageName());
            currentPicture = getResources().getResourceEntryName(resID);
        }while (oldPicture.equals(currentPicture));
        return resID;
    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public void KeyPadClick(View view) {
        UpdateAnswer(view);
    }
    public void UpdateAnswer(View view){

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

    private void CheckAnswer(View v){
        TextView textView = (TextView)myView.findViewById(R.id.AnswerTextField);
        int guess;
        String[] goodjob = getResources().getStringArray(R.array.goodjob);
        if(!(textView.getText().toString().equals(""))) {
            guess = Integer.parseInt(textView.getText().toString());
        }else {
            answerLocked=false;
            MainActivity.speak("Please enter an answer before checking");
            return; //send user back if field is empty
        }
        if (guess==ANSWER){
            if(ANSWER==1){
                MainActivity.speak(goodjob[random.nextInt(goodjob.length)] +"! There was " + ANSWER + " " + currentPicture);
            }else {
                MainActivity.speak(goodjob[random.nextInt(goodjob.length)] + "! There were " + ANSWER + " " + currentPicture + "s.");
            }
            getPictures(v);
            textView.setText("");
            answerLocked=false;

        }else {
            if (ANSWER==1){
                MainActivity.speak("No, there is " + ANSWER + " " + currentPicture+".");
            }else {
                MainActivity.speak("No, there are " + ANSWER + " " + currentPicture + "s.");
            }
            answerLocked=false;
            textView.setText("");
            getPictures(v);
        }

    }

}

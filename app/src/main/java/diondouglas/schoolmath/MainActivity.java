package diondouglas.schoolmath;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import diondouglas.schoolmath.utils.SplashScreenFragment;
import diondouglas.schoolmath.utils.genderSelect;

public class MainActivity extends Activity  {
    private static boolean firstRun;
    private static SharedPreferences mySharedPreferences;

    protected SchoolMath mySchoolMath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySchoolMath = (SchoolMath)this.getApplicationContext();
        hideSystemUI(this);
        setContentView(R.layout.activity_main);
        mySharedPreferences = this.getSharedPreferences("schoolMathPrefs",0);
        if(getFirstRun()){
            openSettings();
        }else {
            openSplashScreen();
        }

    }

    private boolean getFirstRun(){
        return mySharedPreferences.getBoolean("firstRun", true);
    }

    public void openSettings(){
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment,new genderSelect(), "GenderSelect").commit();
    }
    public void openSplashScreen(){
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFragment,new SplashScreenFragment(), "Splash Screen").commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void hideSystemUI(Activity activity) {
        //activity = (Activity)SchoolMath.getAppContext();

        activity.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public  void KeyPadClick(View view){
        Button b = (Button)view;
        String str = b.getText().toString();
        main_top_fragment.UpdateAnswer(str);
    }






    //Activity level stuff
    protected void onResume() {
        super.onResume();
        mySchoolMath.setMyCurrentActivity(this);
    }
    protected void onPause() {
        clearReferences();
        super.onPause();
    }
    protected void onDestroy() {
        clearReferences();
        super.onDestroy();
    }
    private void clearReferences(){
        Activity currActivity = mySchoolMath.getMyCurrentActivity();
        if (currActivity != null && currActivity.equals(this))
            mySchoolMath.setMyCurrentActivity(null);
    }

    //TESTING PURPOSES
    public void FRAGMENT_TEST_CLICK(View view){
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.main_top_fragment,new keyPad(), "KeyPad2").commit();
    }

}

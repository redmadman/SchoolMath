package diondouglas.firstgrademathbydion.utils;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import diondouglas.firstgrademathbydion.SchoolMath;

/**
 * Created by Dion on 9/21/2015.
 */
public class SaveData {

    //The variables for the save data
    private static boolean gender;
    private static int gradeLevel;
    private static String[] gradeOneRewards;

    public SaveData(){

    }

    private void setJSonData(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("gender",gender);
            jsonObject.put("gradeLevel", gradeLevel);
            jsonObject.put("gradeOneRewards", gradeOneRewards);
        }catch (JSONException e){
            e.printStackTrace();
        }
        SharedPreferences mPrefs = SchoolMath.getmPrefs();
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("savedata", jsonObject.toString());
    }

    private void getJSonData(){
        SharedPreferences mPrefs = SchoolMath.getmPrefs();
        String saveData = mPrefs.getString("savedata", null);
        if (saveData!=null){
            try {
                JSONObject jsonObj = new JSONObject(saveData);
                gender = jsonObj.getBoolean("gender");
                gradeLevel = jsonObj.getInt("gradeLevel");
                JSONArray arr = jsonObj.getJSONArray("gradeOneRewards");

            }catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

}

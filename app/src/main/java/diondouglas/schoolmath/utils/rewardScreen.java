package diondouglas.schoolmath.utils;

import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import diondouglas.schoolmath.R;
import diondouglas.schoolmath.SchoolMath;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class rewardScreen extends Fragment {
    SharedPreferences mPrefs;
    //MathUtils mathUtils;
    static int intGenderLayout;
    GridView gridView;
    private static String[]ALL_REWARDS;
    private static String[]PLAYER_OWNED_REWARDS;
    static boolean getAllRewardsDone = false;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.reward_layout, container, false);
        mPrefs = SchoolMath.getmPrefs();
        ALL_REWARDS=getAllRewards();
        PLAYER_OWNED_REWARDS=getOwnedRewards();
        addOwnedReward();
        gridView = (GridView)view.findViewById(R.id.gridView1);
        gridView.setAdapter(new RewardAdapter(getActivity(), PLAYER_OWNED_REWARDS));
        Button b = (Button)view.findViewById(R.id.RewardsOKButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }

    public rewardScreen(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = this.getActivity().getSharedPreferences("schoolMathPrefs", 0);
    }

    public void addOwnedReward(){
        //TODO Put in method to find a new reward
        mPrefs=getActivity().getSharedPreferences("schoolMathPrefs",0);
        ArrayList<String> list = new ArrayList<String>();
        SharedPreferences.Editor editor = mPrefs.edit();
        Set<String> set;
        String[] ownedRewards = getOwnedRewards();
        String[] allRewards = getAllRewards();
        String[] unOwnedRewards;
        String newReward;
        Random rand = new Random();
        //make an array of unknown rewards
        ArrayList <String> unOwnedList = new ArrayList<String>();
        ArrayList <String> newOwnedList;

        /******This is a debugging method*********************
        String[]tempArray= {"Hedgehog","Penguin"};
        set = new HashSet<String>(Arrays.asList(tempArray));
        ******************************************************/

        //Get the currently owned rewards and all the rewards

        if(PLAYER_OWNED_REWARDS!=null){
            newOwnedList = new ArrayList<String>(Arrays.asList(ownedRewards)); //Initialize with previous variable
            for (String str : allRewards) {
                if(!unOwnedList.contains(str)){
                    if(!isRewardOwned(str)){
                        unOwnedList.add(str);
                    }
                }
            }

            if(unOwnedList.size()!=0){  //make sure we do have some that we don't own.....
                newOwnedList.add(unOwnedList.get(rand.nextInt(unOwnedList.size())));}  //randomly pick one
            set = new HashSet<String>(newOwnedList); //set the set
        }else {
            ///randomly pick one from all rewards if no current rewards
            int i = rand.nextInt(allRewards.length);
            String tmpString = allRewards[i];
            String[]tempArray = {tmpString};
            set = new HashSet<String>(Arrays.asList(tempArray));
            //set= new HashSet<String>(newOwnedList);
        }
        //update preferences
        editor.putStringSet("owned_rewards", set);
        editor.commit();
        PLAYER_OWNED_REWARDS = getOwnedRewards(); //Refresh static variable
    }

    public boolean isRewardOwned(String s){
        boolean b = Arrays.asList(PLAYER_OWNED_REWARDS).contains(s);
        return Arrays.asList(PLAYER_OWNED_REWARDS).contains(s);
    }

    static public String[] getAllRewards(){
        String[] arr;
        String[] str;
        String tempstr;
        //List<String> arrayList = new ArrayList<>();
        //List<String> list;
        arr = SchoolMath.getAppContext().getResources().getStringArray(R.array.rewards);

        /*  BUTTERFLY TESTING
        list = Arrays.asList(arr);
        for (int x = 1; x<=10;x++){
            tempstr = "butterfly"+x;
            arrayList.add(tempstr);
        }
        arrayList.addAll(list);*/

        //str = arrayList.toArray(new String[arrayList.size()]);
        getAllRewardsDone = true;
        return arr;
    }


    public String[] getOwnedRewards(){

        String[] rewards = ALL_REWARDS;
        mPrefs=getActivity().getSharedPreferences("schoolMathPrefs",0);
        ArrayList<String> list = new ArrayList<String>();
        Set<String> set;
        Set<String> nullSet = null;
        String str[];

        set = mPrefs.getStringSet("owned_rewards", nullSet);
        if(set!=nullSet){//Returns  null if no owned_rewazrds found
            String [] ownRewards1 = set.toArray(new String[set.size()]);
            for (String s : rewards) {
                for (String s2 : ownRewards1) {
                    if (s.equals(s2)){
                        list.add(s);
                    }
                }
            }
            str=list.toArray(new String[list.size()]);
        }else{
            str = null;}
        return str;

    }



}

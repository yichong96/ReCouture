package com.example.recouture.utils;
import android.app.Activity;
import android.app.FragmentManager;
import android.util.Log;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.content.Intent;
import com.example.recouture.HomePage.HomepageActivity;
import com.example.recouture.HomePage.PromptFragment;
import com.example.recouture.Likes.LikesActivity;
import com.example.recouture.Profile.ProfileActivity;
import com.example.recouture.Calendar.CalendarActivity;
import com.example.recouture.Add.AddActivity;
import com.example.recouture.R;
import android.view.MenuItem;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import android.app.FragmentTransaction;



public class BottomNavigationViewHelper {

    private static final String TAG = "BottomNavigationViewHel";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        Log.d(TAG, "setupBottomNavigationView: Setting up BottomNavigationView");
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomepageActivity.class);//ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;

                    case R.id.ic_calendar:
                        Intent intent2 = new Intent(context, CalendarActivity.class);//ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;


                    case R.id.ic_add:
                        addFragmentView(context);
                        break;


                    case R.id.ic_like:
                        Intent intent4 = new Intent(context, LikesActivity.class);//ACTIVITY_NUM = 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_profile:
                        Intent intent5 = new Intent(context, ProfileActivity.class);//ACTIVITY_NUM = 4
                        context.startActivity(intent5);
                        break;
                }
                return false;
            }
        });
    }

    private static void addFragmentView(Context context) {
        FragmentManager fragmentManager = ((Activity)context).getFragmentManager();
        PromptFragment fragment = new PromptFragment();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.promptFragment,fragment);
        fragmentTransaction.commit();
    }

}


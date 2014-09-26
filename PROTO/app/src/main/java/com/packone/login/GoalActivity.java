package com.packone.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import info.androidhive.slidingmenu.MainActivity;


public class GoalActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_goal);

    }

    public void onClick(View view) {
        Intent intent = new Intent(GoalActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}

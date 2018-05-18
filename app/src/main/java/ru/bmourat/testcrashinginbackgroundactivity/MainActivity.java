package ru.bmourat.testcrashinginbackgroundactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.utils.HockeyLog;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HockeyLog.setLogLevel(Log.VERBOSE);
        mainActivity = this;

        findViewById(R.id.bStartSecondActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.bCrashNonStatic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crashNonStatic();
            }
        });

        findViewById(R.id.bCrashStatic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crashStatic();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        CrashManager.register(this);
    }

    public static void crashStatic() {
        throw new IllegalArgumentException("Static crash");
    }

    public void crashNonStatic() {
        throw new IllegalArgumentException("Non static crash");
    }
}

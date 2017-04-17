package com.github.mvpstatelibexample.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mvpstatelibexample.R;
import com.github.mvpstatelibexample.ui.adapters.SamplePagerAdapter;
import com.github.mvpstatelibexample.ui.fragments.FragmentInteractionListener;

public class ThirdActivity extends AppCompatActivity implements FragmentInteractionListener {

    private PagerAdapter adapter;
    private ViewPager viewPager;

    public static void start(final Context context) {
        final Intent intent = new Intent(context, ThirdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.activity_third_pager);

        adapter = new SamplePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onFragmentAction() {

    }
}

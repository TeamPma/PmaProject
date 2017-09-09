package com.example.maja.myapplication.presentation.mvp.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.announcement.AnnouncementDetailActivity;
import com.example.maja.myapplication.presentation.mvp.dogDetails.DogDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.dogList.DogListFragment;
import com.example.maja.myapplication.presentation.mvp.googleMap.MapFragment;
import com.example.maja.myapplication.presentation.mvp.newsList.NewsListFragment;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.shelterList.ShelterListFragment;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private static final String TAG = "MainActivity";
    private String[] mMenuItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;;
    private String mActivityTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMenuItems = new String[]{getString(R.string.shelterList), getString(R.string.dogList), getString(R.string.newsList), "Map"};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mMenuItems));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setupDrawer();
    }
    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_menu, menu);
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

        // Activate the navigation drawer toggle
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public Context getActivityContext() {
        return this;
    }


    @Override
    public void showShelter(Shelter shelter) {
        Log.d(TAG, "showShelter: " + shelter);
        Intent intent = new Intent(this, ShelterDetailsActivity.class);
        intent.putExtra("shelter",shelter);
        startActivity(intent);
        finish();
    }

    @Override
    public void showAnnouncement(Announcement announcement) {
        Log.d(TAG, "showAnnouncement: ");
        Intent intent = new Intent(MainActivity.this, AnnouncementDetailActivity.class);
        intent.putExtra("announcement",announcement);
        startActivity(intent);
        finish();
    }

    @Override
    public void showDog(Dog dog) {
        Log.d(TAG, "showDog: ");
        Intent intent = new Intent(MainActivity.this, DogDetailsActivity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
        finish();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        int title = 0;
        switch (position){
            case 0:
                fragment = new ShelterListFragment();
                title = R.string.shelterList_fragment;
                break;
            case 1:
                fragment = new DogListFragment();
                title = R.string.dogList_fragment;
                break;
            case 2:
                fragment = new NewsListFragment();
                title = R.string.newsList_fragment;
                break;
            case 3:
                fragment = new MapFragment();
                break;
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        mDrawerLayout.closeDrawer(mDrawerList);

    }
//
//    @Override
//    public void setTitle(CharSequence title) {
//
//
//    }
}


package com.example.maja.myapplication.presentation.mvp.main;

import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
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
import android.widget.Toast;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.announcement.AnnouncementDetailActivity;
import com.example.maja.myapplication.presentation.mvp.dogDetails.DogDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.dogList.DogListFragment;
import com.example.maja.myapplication.presentation.mvp.googleMap.MapActivity;
import com.example.maja.myapplication.presentation.mvp.login.LoginActivity;
import com.example.maja.myapplication.presentation.mvp.newsList.NewsListFragment;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.shelterList.ShelterListFragment;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;
    private String[] mMenuItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;;
    private String mActivityTitle;
    private boolean gmapEnabled = true;
    public static int isAdmin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMenuItems = new String[]{getString(R.string.shelterList), getString(R.string.dogList), getString(R.string.newsList), getString(R.string.mapShelter), getString(R.string.logOut)};
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
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        String isAdminKey = "com.example.maja.myapplication.isAdmin";
        isAdmin = prefs.getInt(isAdminKey, 0);
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
        Log.d(TAG, "onConfigurationChanged: ");
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
        checkGmapPermision();
    }

    private void checkGmapPermision() {
        Log.d(TAG, "checkGmapPermision: ");
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },
                    MY_PERMISSIONS_REQUEST_LOCATION);
            gmapEnabled = false;
            return;
        }
        else{
            gmapEnabled = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: ");
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    gmapEnabled = true;
                } else {
                    gmapEnabled = false;
                }
                return;
            }

        }
    }

    @Override
    public Context getActivityContext() {
        return this;
    }


    @Override
    public void showShelter(Shelter shelter) {
        Log.d(TAG, "showShelter: " + shelter);
        Intent intent = new Intent(this, ShelterDetailsActivity.class);
        intent.putExtra("shelterId",shelter.getIdShelter());
        startActivity(intent);
        //finish();
    }

    @Override
    public void showAnnouncement(Announcement announcement) {
        Log.d(TAG, "showAnnouncement: ");
        Intent intent = new Intent(MainActivity.this, AnnouncementDetailActivity.class);
        intent.putExtra("announcement",announcement);
        startActivity(intent);
        //finish();
    }

    @Override
    public void showDog(Dog dog) {
        Log.d(TAG, "showDog: ");
        Intent intent = new Intent(MainActivity.this, DogDetailsActivity.class);
        intent.putExtra("dog",dog);
        startActivity(intent);
        //finish();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        Log.d(TAG, "selectItem: ");
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
                Log.d(TAG, "selectItem: "+ gmapEnabled);
                if(gmapEnabled){
                    startActivity(new Intent(MainActivity.this, MapActivity.class));
                }
                else{
                    Toast.makeText(this,"Google map is not available", Toast.LENGTH_LONG).show();
                }
                break;
            case 4:
                logout();
                break;
        }
        if(position!=3) {
            Log.d(TAG, "selectItem: ");
            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getFragmentManager();
            if(fragment != null) {
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
            }
            // Highlight the selected item, update the title, and close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerLayout.closeDrawer(mDrawerList);
        }

    }

    private void logout() {
        Log.d(TAG, "logout: ");
        SharedPreferences prefs = this.getSharedPreferences(
                "com.example.maja.myapplication", Context.MODE_PRIVATE);
        prefs.edit().clear().apply();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    //
//    @Override
//    public void setTitle(CharSequence title) {
//
//
//    }
}


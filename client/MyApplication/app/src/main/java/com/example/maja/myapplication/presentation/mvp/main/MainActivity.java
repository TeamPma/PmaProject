package com.example.maja.myapplication.presentation.mvp.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.dogList.DogListFragment;
import com.example.maja.myapplication.presentation.mvp.googleMap.MapFragment;
import com.example.maja.myapplication.presentation.mvp.newsList.AddNewsActivity;
import com.example.maja.myapplication.presentation.mvp.newsList.NewsListFragment;
import com.example.maja.myapplication.presentation.mvp.shelter.ShelterDetailsActivity;
import com.example.maja.myapplication.presentation.mvp.shelterList.ShelterListFragment;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    private static final String TAG = "MainActivity";
    private String[] mMenuItems;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
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
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void openAddNewsActivity() {
        Intent intent = new Intent(this, AddNewsActivity.class);
        startActivity(intent);
    }

    @Override
    public void showShelter(Shelter shelter) {
        Log.d(TAG, "showShelter: " + shelter);
        Intent intent = new Intent(this, ShelterDetailsActivity.class);
        intent.putExtra("shelter",shelter);
        startActivity(intent);
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


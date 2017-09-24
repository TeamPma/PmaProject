package com.example.maja.myapplication.presentation.mvp.favoriteDogs;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.dogList.DogListAdapter;
import com.example.maja.myapplication.presentation.mvp.dogList.DogListPresenter;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

/**
 * Created by Maja on 19.9.2017.
 */

public class FavoriteDogListFragment extends Fragment implements FavoriteDogListContact.View {

    private static final String TAG = FavoriteDogListFragment.class.getSimpleName();
    private FragmentListener parentActivity;
    private FavoriteDogListPresenter presenter;
    private AlertDialog.Builder builder;
    private DogListAdapter dogListAdapter;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new FavoriteDogListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dogs_list, container, false);
        builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        ListView listView = (ListView) view.findViewById(R.id.dogList);
        dogListAdapter = new DogListAdapter(getActivity(), presenter);
        listView.setAdapter(dogListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Dog dog = (Dog) dogListAdapter.getItem(position);
                Log.d(TAG, "onItemClick: " + dog);
                parentActivity.showDog(dog);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
        presenter.start();
    }

    @Override
    public void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
        presenter.stop();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause: ");
        super.onPause();
        presenter.pause();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
        presenter.getFavoriteDogs(parentActivity.getUserId());
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            parentActivity = (FragmentListener) context;
        }
    }

    @Override
    public void handleError(String message) {
        Log.d(TAG, "handleError: ");
        builder.setTitle("Getting DogList not successful")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void showFavoriteDogs(ArrayList<Dog> dogList) {
        dogListAdapter.setDogList(dogList);
        dogListAdapter.notifyDataSetChanged();
    }
}

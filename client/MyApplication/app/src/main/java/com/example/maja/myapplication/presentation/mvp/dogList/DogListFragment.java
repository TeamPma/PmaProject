package com.example.maja.myapplication.presentation.mvp.dogList;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.addDog.AddDogActivity;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

public class DogListFragment extends Fragment implements DogListContact.View{

    private static final String TAG = DogListFragment.class.getSimpleName();
    private AlertDialog.Builder builder;
    private FragmentListener parentActivity;
    private DogListPresenter presenter;
    private DogListAdapter dogListAdapter;
    private FloatingActionButton btnAddDog;
    private Dog dog;

    public DogListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DogListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dogs_list, container, false);
        builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        btnAddDog = (FloatingActionButton) view.findViewById(R.id.btnAddDog);

        initListener();
        presenter.getDogList();

        ListView listView = (ListView) view.findViewById(R.id.dogList);
        dogListAdapter = new DogListAdapter(getActivity());
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
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
        presenter.destroy();
    }

    private void initListener() {
        Log.d(TAG, "initListener: ");
        btnAddDog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(getActivity(), AddDogActivity.class);
                intent.putExtra("dog", dog);
                getActivity().startActivity(intent);
                getActivity().finish();
            }
        });
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
    public void getDogListSuccessfull(ArrayList<Dog> dogList) {
        dogListAdapter.setDogList(dogList);
        dogListAdapter.notifyDataSetChanged();
    }
}

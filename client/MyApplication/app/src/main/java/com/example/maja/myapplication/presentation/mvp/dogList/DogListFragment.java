package com.example.maja.myapplication.presentation.mvp.dogList;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.EventLogTags;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

public class DogListFragment extends Fragment implements DogListContact.View{

    private static final String TAG = DogListFragment.class.getSimpleName();
    private AlertDialog.Builder builder;
    private FragmentListener parentActivity;
    private DogListPresenter presenter;
    private DogListAdapter dogListAdapter;

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
        presenter.getDogList();

        ListView listView = (ListView) view.findViewById(R.id.dogList);
        dogListAdapter = new DogListAdapter(getActivity());
        listView.setAdapter(dogListAdapter);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof FragmentListener){
            parentActivity = (FragmentListener) context;
        }
    }

    @Override
    public void getDogListSuccessfull(ArrayList<Dog> dogList) {
        Log.d(TAG, "getDogListSuccessfull: ");
        dogListAdapter.setDogList(dogList);
        dogListAdapter.notifyDataSetChanged();

    }

    @Override
    public void getDogListNotSuccessfull(String message) {
        Log.d(TAG, "getDogListNotSuccessfull: ");
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
}

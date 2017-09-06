package com.example.maja.myapplication.presentation.mvp.shelterList;

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
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

public class ShelterListFragment extends Fragment implements ShelterListContact.View
{
    private static final String TAG = ShelterListFragment.class.getSimpleName();
    private AlertDialog.Builder builder;
    private FragmentListener parentActivity;
    private ShelterListPresenter presenter;
    private ShelterListAdapter shelterListAdapter;
    public ShelterListFragment() {
        // Required empty public constructor
        // parentActivity.getActivityContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ShelterListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelter_list, container, false);
        presenter.getShelterList();
        ListView listView = (ListView) view.findViewById(R.id.shelterList);
        shelterListAdapter = new ShelterListAdapter(getActivity());
        listView.setAdapter(shelterListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Shelter shelter = (Shelter)shelterListAdapter.getItem(position);
                Log.d(TAG, "onItemClick: " + shelter);
                parentActivity.showShelter(shelter);

            }
        });
//        listView.setOnItemClickListener(shelterListAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(shelterListAdapter, listView, int position, long l) {
//
//            }
//        });
        // Inflate the layout for this fragment
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
    public void getShelterListSuccessfull(ArrayList<Shelter> shelterList) {
        Log.d(TAG, "getShelterListSuccessfull: +");
        shelterListAdapter.setShelterList(shelterList);
        shelterListAdapter.notifyDataSetChanged();
    }

    @Override
    public void getShelterListNotSuccessfull(String message) {
        Log.d(TAG, "getShelterListNotSuccessfull: ");
        builder.setTitle("Getting ShelterList not successful")
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

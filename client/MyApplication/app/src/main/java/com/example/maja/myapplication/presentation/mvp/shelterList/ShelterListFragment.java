package com.example.maja.myapplication.presentation.mvp.shelterList;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.addNews.AddNewsActivity;
import com.example.maja.myapplication.presentation.mvp.addShelter.AddShelterActivity;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;
import com.example.maja.myapplication.presentation.mvp.shelterDetails.ShelterDetailsActivity;

import java.util.ArrayList;

public class ShelterListFragment extends Fragment implements ShelterListContact.View
{
    private static final String TAG = ShelterListFragment.class.getSimpleName();
    private AlertDialog.Builder builder;
    private FragmentListener parentActivity;
    private ShelterListPresenter presenter;
    private ShelterListAdapter shelterListAdapter;
    private FloatingActionButton btnAddShelter;
    private Shelter shelter;

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
        builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        btnAddShelter = (FloatingActionButton) view.findViewById(R.id.btnAddShelter);

        initListener();
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
        // Inflate the layout for this fragment
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
        btnAddShelter.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: ");
                Intent intent = new Intent(getActivity(), AddShelterActivity.class);
                intent.putExtra("shelterId", shelter.getIdShelter());
                getActivity().startActivity(intent);
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

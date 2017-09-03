package com.example.maja.myapplication.presentation.mvp.shelterList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

public class ShelterListFragment extends Fragment {

    private FragmentListener parentActivity;

    public ShelterListFragment() {
        // Required empty public constructor
        // parentActivity.getActivityContext();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shelter_list, container, false);

        ArrayList<Shelter> shelterList = new ArrayList<Shelter>();
        Shelter sh1 = new Shelter(1,"Shelter1","Address","1","lokacija","Novi Sad",1234567891);
        Shelter sh2 = new Shelter(2,"Shelter2","Address","2","lokacija","Novi Sad",1234567892);
        Shelter sh3 = new Shelter(3,"Shelter3","Address","3","lokacija","Novi Sad",1234567893);
        shelterList.add(sh1);
        shelterList.add(sh2);
        shelterList.add(sh3);

        ListView listView = (ListView) view.findViewById(R.id.shelterList);

        ShelterListAdapter shelterListAdapter = new ShelterListAdapter(getActivity(),shelterList);
        listView.setAdapter(shelterListAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
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
}

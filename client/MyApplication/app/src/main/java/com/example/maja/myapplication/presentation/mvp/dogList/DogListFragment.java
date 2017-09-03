package com.example.maja.myapplication.presentation.mvp.dogList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;

public class DogListFragment extends Fragment {

    private FragmentListener parentActivity;

    public DogListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dogs_list, container, false);

        ArrayList<Dog> dogList = new ArrayList<Dog>();
        Dog d1 = new Dog(1,"Kasper","Sibirski haski",1,1,25,30,1,1,"Anamnesis1",1);
        Dog d2 = new Dog(2,"Dzeki","Nemacki ovcar",1,1,25,30,1,1,"Anamnesis2",2);;
        Dog d3 = new Dog(3,"Lara","Zlatni retriver",1,1,25,30,1,1,"Anamnesis3",3);;
        dogList.add(d1);
        dogList.add(d2);
        dogList.add(d3);

        ListView listView = (ListView) view.findViewById(R.id.dogList);

        DogListAdapter dogListAdapter = new DogListAdapter(getActivity(),dogList);
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
}

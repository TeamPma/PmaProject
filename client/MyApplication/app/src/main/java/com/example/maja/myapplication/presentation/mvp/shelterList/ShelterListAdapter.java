package com.example.maja.myapplication.presentation.mvp.shelterList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Jovana on 3.9.2017..
 */

public class ShelterListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Shelter> shelterList = new ArrayList<Shelter>();
    LayoutInflater inflater;

    public ShelterListAdapter(Context context, ArrayList<Shelter> shelterList) {
        this.context = context;
        this.shelterList = shelterList;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return shelterList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Shelter shelter = shelterList.get(i);
        view = inflater.inflate(R.layout.shelter_list_item,null);
        TextView shelterName = view.findViewById(R.id.shelterName);
        TextView shelterCity = view.findViewById(R.id.shelterCity);
        TextView shelterAddress = view.findViewById(R.id.shelterAddress);
        shelterName.setText(shelter.getName());
        shelterCity.setText(shelter.getCity());
        shelterAddress.setText(shelter.getAddress());
        return view;
    }
}

package com.example.maja.myapplication.presentation.mvp.dogList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;

import java.util.ArrayList;

/**
 * Created by Jovana on 3.9.2017..
 */

public class DogListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Dog> dogList = new ArrayList<Dog>();
    LayoutInflater inflater;

    public DogListAdapter(Context context, ArrayList<Dog> dogList) {
        this.context = context;
        this.dogList = dogList;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return dogList.size();
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
        Dog dog = dogList.get(i);
        view = inflater.inflate(R.layout.dog_list_item,null);
        TextView dogName = (TextView)view.findViewById(R.id.dogName);
        TextView dogBread = (TextView)view.findViewById(R.id.dogBread);
        TextView dogAge = (TextView)view.findViewById(R.id.dogAge1);
        dogName.setText(dog.getName());
        dogBread.setText(dog.getBread());
//        dogAge.setText(dog.getAge());
        return view;
    }
}

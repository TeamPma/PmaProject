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

    public DogListAdapter(Context context) {
        this.context = context;
        this.dogList = dogList;
        inflater = (LayoutInflater.from(context));
    }

    public void setDogList(ArrayList<Dog> dogList) {
        this.dogList = dogList;
    }

    @Override
    public int getCount() {
        return dogList.size();
    }

    @Override
    public Object getItem(int i) {
        return dogList.get(i);
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
        TextView dogGender = (TextView)view.findViewById(R.id.dogGender);
        TextView dogAge = (TextView)view.findViewById(R.id.dogAge);
        dogName.setText(dog.getName());
        dogBread.setText(dog.getBread());
        int gender = dog.getGender();
        if(gender == 0){
            dogGender.setText("Female");
        } else if(gender == 1){
            dogGender.setText("Male");
        } else{
            dogGender.setText("Not valid");
        }
        //dogGender.setText(String.valueOf(dog.getGender()));
        dogAge.setText(String.valueOf(dog.getAge()));
        return view;
    }
}

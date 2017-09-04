package com.example.maja.myapplication.presentation.mvp.newsList;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;

import java.util.ArrayList;
import java.util.Date;

public class NewsListFragment extends Fragment implements NewsContact.View{

    private FragmentListener parentActivity;

    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ArrayList<Announcement> newsList = new ArrayList<Announcement>();
        Announcement a = new Announcement(1,1,"Comment", new Date(), "url");
        Announcement a1 = new Announcement(2,2,"Comment1", new Date(), "url1");
        newsList.add(a);
        newsList.add(a1);
        //napuni ovu listu
        ListView listView = (ListView) view.findViewById(R.id.newsList);

        NewsListAdapter newsListAdapter = new NewsListAdapter(getActivity(),newsList);
        listView.setAdapter(newsListAdapter);
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
    public void handleError(String message) {

    }

    @Override
    public void getAllNewsSuccesfull(ArrayList<Announcement> news) {

    }
}

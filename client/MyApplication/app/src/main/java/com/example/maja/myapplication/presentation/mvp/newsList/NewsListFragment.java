package com.example.maja.myapplication.presentation.mvp.newsList;


import android.app.Fragment;
import android.app.ProgressDialog;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.maja.myapplication.R;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.presentation.mvp.main.FragmentListener;
import com.example.maja.myapplication.presentation.mvp.main.MainActivity;

import java.util.ArrayList;
import java.util.Date;

public class NewsListFragment extends Fragment implements NewsContact.View{
    private static final String TAG = "NewsListFragment";
    private AlertDialog.Builder builder;
    private FragmentListener parentActivity;
    private NewsPresenter presenter;
    private NewsListAdapter newsListAdapter;
    private EditText searchTitle;
    private Button search;

    ProgressDialog progress;


    public NewsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewsPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        ListView listView = (ListView) view.findViewById(R.id.newsList);
        newsListAdapter = new NewsListAdapter(getActivity());
        listView.setAdapter(newsListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Announcement announcement = (Announcement) newsListAdapter.getItem(position);
                Log.d(TAG, "onItemClick: " + announcement);
                parentActivity.showAnnouncement(announcement);
            }
        });
        searchTitle = view.findViewById(R.id.searchTitle);
        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newsListAdapter.setNewsList(presenter.getNewsByTitle(searchTitle.getText()+""));
                newsListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
        presenter.resume();
        progress = ProgressDialog.show(parentActivity.getActivityContext(), "",
                "Please wait", true);
        presenter.getAllNews();
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
        if (progress != null) {
            progress.dismiss();
        }
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

    @Override
    public void getAllNewsSuccesfull(ArrayList<Announcement> news) {
        newsListAdapter.setNewsList(news);
        newsListAdapter.notifyDataSetChanged();
        if (progress != null) {
            progress.dismiss();
        }
    }

}

package com.example.submission2.TVShow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submission2.R;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {
    private RecyclerView rvCategory;
    private String[] dataImage;
    private String[] dataName;
    private String[] dataRelease;
    private String[] dataScore;
    private String[] dataGenre;
    private String[] dataOverview;
    private String[] dataPhoto;
    private String[] dataTrailer;
    private TVShowAdapter adapter;
    private ArrayList<TVShow> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_list, container, false);

        adapter = new TVShowAdapter(getActivity());

        rvCategory = rootView.findViewById(R.id.recycler_view);
        rvCategory.setHasFixedSize(true);

        //Menyipakan data dari resource
        prepare();

        //Menambahkan data dari resource ke adapter
        addItem();
        showTVShow();
        return rootView;
    }

    private void prepare() {
        dataImage = getResources().getStringArray(R.array.tvshow_image);
        dataName = getResources().getStringArray(R.array.tvshow_name);
        dataRelease = getResources().getStringArray(R.array.tvshow_release);
        dataScore = getResources().getStringArray(R.array.tvshow_score);
        dataGenre = getResources().getStringArray(R.array.tvshow_genre);
        dataOverview = getResources().getStringArray(R.array.tvshow_overview);
        dataPhoto = getResources().getStringArray(R.array.tvshow_photo);
        dataTrailer = getResources().getStringArray(R.array.tvshow_trailer);
    }
    private void addItem() {
        list = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            TVShow tvshow = new TVShow();
            tvshow.setImage(dataImage[i]);
            tvshow.setName(dataName[i]);
            tvshow.setRelease(dataRelease[i]);
            tvshow.setScore(dataScore[i]);
            tvshow.setGenre(dataGenre[i]);
            tvshow.setOverview(dataOverview[i]);
            tvshow.setPhoto(dataPhoto[i]);
            tvshow.setTrailer(dataTrailer[i]);
            list.add(tvshow);
        }

        adapter.setListTVShow(list);
    }
    private void showTVShow() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        TVShowAdapter tvshowAdapter = new TVShowAdapter(getActivity(), new TVShowAdapter.OnClick(){
            @Override
            public void onClick(Object tvshow) {
                TVShow parce = (TVShow) tvshow;
                Intent intent = new Intent(getActivity(), TVShowDetail.class);
                intent.putExtra("tvshow", parce);
                getActivity().startActivity(intent);
            }
        });
        tvshowAdapter.setListTVShow(list);
        rvCategory.setAdapter(tvshowAdapter);
    }
}
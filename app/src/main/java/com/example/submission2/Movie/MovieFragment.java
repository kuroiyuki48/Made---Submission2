package com.example.submission2.Movie;

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

public class MovieFragment extends Fragment {
    private RecyclerView rvCategory;
    private String[] dataImage;
    private String[] dataName;
    private String[] dataRelease;
    private String[] dataScore;
    private String[] dataGenre;
    private String[] dataOverview;
    private String[] dataPhoto;
    private String[] dataTrailer;
    private MovieAdapter adapter;
    private ArrayList<Movie> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_list, container, false);

        adapter = new MovieAdapter(getActivity());

        rvCategory = rootView.findViewById(R.id.recycler_view);
        rvCategory.setHasFixedSize(true);

        //Menyipakan data dari resource
        prepare();

        //Menambahkan data dari resource ke adapter
        addItem();
        showMovie();
        return rootView;
    }
    private void prepare() {
        dataImage = getResources().getStringArray(R.array.movie_image);
        dataName = getResources().getStringArray(R.array.movie_name);
        dataRelease = getResources().getStringArray(R.array.movie_release);
        dataScore = getResources().getStringArray(R.array.movie_score);
        dataGenre = getResources().getStringArray(R.array.movie_genre);
        dataOverview = getResources().getStringArray(R.array.movie_overview);
        dataPhoto = getResources().getStringArray(R.array.movie_photo);
        dataTrailer = getResources().getStringArray(R.array.movie_trailer);
    }
    private void addItem() {
        list = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            Movie movie = new Movie();
            movie.setImage(dataImage[i]);
            movie.setName(dataName[i]);
            movie.setRelease(dataRelease[i]);
            movie.setScore(dataScore[i]);
            movie.setGenre(dataGenre[i]);
            movie.setOverview(dataOverview[i]);
            movie.setPhoto(dataPhoto[i]);
            movie.setTrailer(dataTrailer[i]);
            list.add(movie);
        }

        adapter.setListMovie(list);
    }
    private void showMovie() {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(getActivity(), new MovieAdapter.OnClick(){
            @Override
            public void onClick(Object movie) {
                Movie parce = (Movie) movie;
                Intent intent = new Intent(getActivity(), MovieDetail.class);
                intent.putExtra("movie", parce);
                startActivity(intent);
            }
        });
        movieAdapter.setListMovie(list);
        rvCategory.setAdapter(movieAdapter);
    }
}
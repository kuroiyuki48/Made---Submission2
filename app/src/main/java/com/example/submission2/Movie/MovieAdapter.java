package com.example.submission2.Movie;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission2.CustomOnItemClickListener;
import com.example.submission2.R;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder>   {
    private Context context;
    private OnClick onClick;
    private ArrayList<Movie> listMovie;

    public MovieAdapter(FragmentActivity activity) {

    }

    private ArrayList<Movie> getListMovie() {
        return listMovie;
    }

    MovieAdapter(Context context, OnClick onClick) {
        this.context = context;
        this.onClick = onClick;
        listMovie = new ArrayList<>();
    }

    void setListMovie(ArrayList<Movie> listMovie) {
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder cardViewViewHolder, int i) {
        Movie movie = getListMovie().get(i);
        cardViewViewHolder.tvName.setText(movie.getName());
        cardViewViewHolder.tvRelease.setText(movie.getRelease());
        cardViewViewHolder.tvGenre.setText(movie.getGenre());

        Glide.with(context)
                .load(movie.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);

        cardViewViewHolder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int onClick) {
                Toast.makeText(context, "Movie : "+getListMovie().get(onClick).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        cardViewViewHolder.btnTrailer.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Watch : "+getListMovie().get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getListMovie().get(position).getTrailer()));
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);
            }
        }));

        cardViewViewHolder.tampil(movie, onClick);
    }

    @Override
    public int getItemCount() {
        return getListMovie().size();
    }

    class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName, tvRelease, tvGenre;
        Button btnDetail, btnTrailer;
        CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvRelease = itemView.findViewById(R.id.tv_item_release);
            tvGenre = itemView.findViewById(R.id.tv_item_genre);
            btnDetail = itemView.findViewById(R.id.btn_show_detail);
            btnTrailer = itemView.findViewById(R.id.btn_show_trailer);
        }

        void tampil(final Movie movie, final OnClick OnItemClick) {
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClick.onClick(movie);
                }
            });
        }

    }

    public interface OnClick {
        void onClick(Object movie);
    }
}

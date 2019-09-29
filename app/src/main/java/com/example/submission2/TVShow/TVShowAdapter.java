package com.example.submission2.TVShow;

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

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.CardViewViewHolder>   {
    private Context context;
    private TVShowAdapter.OnClick onClick;
    private ArrayList<TVShow> listTVShow;

    public TVShowAdapter(FragmentActivity activity) {

    }

    private ArrayList<TVShow> getListTVShow() {
        return listTVShow;
    }

    public TVShowAdapter(Context context, TVShowAdapter.OnClick onClick) {
        this.context = context;
        this.onClick = onClick;
        listTVShow = new ArrayList<>();
    }

    public void setListTVShow(ArrayList<TVShow> listTVShow) {
        this.listTVShow = listTVShow;
    }

    @NonNull
    @Override
    public TVShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup, false);
        return new TVShowAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowAdapter.CardViewViewHolder cardViewViewHolder, int i) {
        TVShow tvshow = getListTVShow().get(i);

        cardViewViewHolder.tvName.setText(tvshow.getName());
        cardViewViewHolder.tvRelease.setText(tvshow.getRelease());
        cardViewViewHolder.tvGenre.setText(tvshow.getGenre());

        Glide.with(context)
                .load(tvshow.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardViewViewHolder.imgPhoto);

        cardViewViewHolder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int onClick) {
                Toast.makeText(context, "TV Show : "+getListTVShow().get(onClick).getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        cardViewViewHolder.btnTrailer.setOnClickListener(new CustomOnItemClickListener(i, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Watch : "+getListTVShow().get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getListTVShow().get(position).getTrailer()));
                intent.setPackage("com.google.android.youtube");
                context.startActivity(intent);
            }
        }));

        cardViewViewHolder.tampil(tvshow, onClick);
    }

    @Override
    public int getItemCount() {
        return getListTVShow().size();
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

        public void tampil(final TVShow tvShow, final TVShowAdapter.OnClick OnItemClick) {
            btnDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    OnItemClick.onClick(tvShow);
                }
            });
        }

    }

    public interface OnClick {
        void onClick(Object tvshow);
    }
}
